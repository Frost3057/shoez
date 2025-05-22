package com.frosti.shoez.repo

import com.frosti.shoez.model.shoe
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository


interface shoeRepo:MongoRepository<shoe, ObjectId> {

}