package com.frosti.shoez.Services

import com.frosti.shoez.repo.userRepo
import io.jsonwebtoken.security.Message
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.*

@Service
class AuthService(
    private val jwtService: jwtService,
    private val hashEncoder: hashEncoder,
    private val userRepo: userRepo
) {

    private fun HashToken(token:String):String{
        val digest = MessageDigest.getInstance("SHA-256")
        val encodedToken = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(encodedToken)
    }
}