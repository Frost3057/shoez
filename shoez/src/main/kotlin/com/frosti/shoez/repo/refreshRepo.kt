package com.frosti.shoez.repo

import com.frosti.shoez.model.RefreshToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface refreshRepo:MongoRepository<RefreshToken,ObjectId> {
    fun findByUidandHashedToken(uid:ObjectId,hashedToken:String):RefreshToken?
    fun deleteRefreshTokenByUidandHashedToken(uid: ObjectId,hashedToken: String)
}