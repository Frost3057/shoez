package com.frosti.shoez.repo

import com.frosti.shoez.model.RefreshToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface refreshRepo:MongoRepository<RefreshToken,ObjectId> {
    fun findByUserIdAndHashedToken(userId:ObjectId, hashedToken:String):RefreshToken?
    fun deleteRefreshTokenByUserIdAndHashedToken(userId: ObjectId, hashedToken: String)
}