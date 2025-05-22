package com.frosti.shoez.repo

import com.frosti.shoez.model.Order
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface orderRepo:MongoRepository<Order,ObjectId> {
    fun findOrderByOId(id: ObjectId):Order?

}