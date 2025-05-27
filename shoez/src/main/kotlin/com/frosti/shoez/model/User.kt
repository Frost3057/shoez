package com.frosti.shoez.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
data class User(
    @Id
    val uid:ObjectId=ObjectId.get(),
    val name :String="",
    val phoneNumber:String = "",
    val email:String,
    val address : String = "",
    val password : String
)
