package com.frosti.shoez.controllers

import com.frosti.shoez.Services.AuthService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class authController(
    private val authService: AuthService
) {
    data class AuthRequest(
        val email:String,
        val password:String
    )
    data class RefreshRequest(
        val refreshToken:String
    )
    @PostMapping("/register")
    fun register( @RequestBody body:AuthRequest){
        authService.register(body.email,body.password)
    }
    @PostMapping("/login")
    fun login(@RequestBody body: AuthRequest):AuthService.TokenPair{
        return authService.login(body.email,body.password)
    }
    @PostMapping("/refresh")
    fun refresh(@RequestBody body: RefreshRequest):AuthService.TokenPair{
        return authService.refresh(body.refreshToken)
    }
}