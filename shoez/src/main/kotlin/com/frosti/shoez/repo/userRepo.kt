package com.frosti.shoez.repo

import com.frosti.shoez.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface userRepo:MongoRepository<User,ObjectId> {
    fun findUserbyUID(uid:ObjectId):User?
    fun findUserbyEmail(email:String):User?
}