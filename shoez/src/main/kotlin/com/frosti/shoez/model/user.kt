package com.frosti.shoez.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class user(
    @Id
    val uid:ObjectId=ObjectId.get(),
    val name :String,
    val phoneNumber:String,
    val address : String
)
