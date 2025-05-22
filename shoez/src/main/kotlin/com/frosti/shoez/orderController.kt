package com.frosti.shoez

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Dynamic
import com.frosti.shoez.model.Order
import com.frosti.shoez.model.shoe
import com.frosti.shoez.repo.orderRepo
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Timestamp
import java.time.Instant

@RestController
@RequestMapping("/order")
class orderController
    (private val orderRepo: orderRepo)
{
    data class requestType(
        var order: List<Map<String,Any>>
    )
    data class responseType(
        val orderId: ObjectId,
            val order: List<Map<String, Any>>,
        val timestamp: Instant,
        val status :String
    )
    @PostMapping("/place_order")
    fun placeOrder(@RequestBody body:requestType):responseType{
        println("Body: $body")
        val order = orderRepo.save(
            Order(
                p_id = ObjectId.get(),
                timeStamp= Instant.now(),
                status = "Preparing",
                order = fromList(body.order)
            )
        )
        return order.toResponse(body.order)
    }
    private fun fromList(or : List<Map<String,Any>>):List<shoe>{
        val list = mutableListOf<shoe>()
        for(s : Map<String,Any> in or){
            list.add(shoe(
                s_name = s["s_name"].toString(),
                s_price = s["s_price"] as Double,
                s_image = s["s_image"].toString()
            ))
        }
        return list;
    }
    private fun Order.toResponse(or:List<Map<String,Any>>):responseType{
        return responseType(
            orderId = OId,
            order = or,
            status = status,
            timestamp = timeStamp
        )
    }
}

