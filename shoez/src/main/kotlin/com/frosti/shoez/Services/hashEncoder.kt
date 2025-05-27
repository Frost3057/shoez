package com.frosti.shoez.Services

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class hashEncoder {
    val encoder = BCryptPasswordEncoder()
    fun encodePass(password:String):String = encoder.encode(password)
    fun matchesPass(password: String,hashedPass:String):Boolean = encoder.matches(password,hashedPass)
}