package com.frosti.shoez.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("Refresh Tokens")
data class RefreshToken(
    val userId:ObjectId,
    val hashedToken:String,
    @Indexed(expireAfter = "0s")
    val expiry: Instant,
    val issuedAt:Instant = Instant.now()
)