package com.frosti.shoez.repo

import com.frosti.shoez.model.user
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface userRepo:MongoRepository<user,ObjectId> {

}