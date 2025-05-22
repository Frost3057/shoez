package com.frosti.shoez.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("orders")
data class Order(
    @Id
    val OId:ObjectId = ObjectId.get(),
    val p_id:ObjectId,
    val timeStamp: Instant,
    val status : String,
    val order: List<shoe>
    )
