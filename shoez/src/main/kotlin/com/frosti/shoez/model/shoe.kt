package com.frosti.shoez.model
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("shoes")
data class shoe(
    @Id
    val s_id : ObjectId = ObjectId.get(),
    val s_name :String,
    val s_price : Double,
    val s_image : String
)
