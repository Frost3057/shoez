package com.frosti.shoez.Services
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.time.Instant
import java.util.*
@Service
class jwtService(
    @Value("\${jwt.secret}") private val secret : String
) {
    private val refreshTime = 30L*24*60*60*1000L
    private val accessTime = 15L*60*1000L
    private val secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret))

    fun generateToken(
         userId:String,
         type:String,
         expir:Long
    ):String{
        val time = Date()
        val expiry = Date(time.time+expir)
        return Jwts.builder().subject(userId).signWith(secretKey,Jwts.SIG.HS256).expiration(expiry).claim("type",type).issuedAt(time).compact()
    }
    fun generateAccessToken(userId:String):String{
        return generateToken(userId = userId, type = "access", expir = accessTime)
    }
    fun generateRefreshToken(userId:String):String{
        return generateToken(userId = userId, type = "refresh", expir = refreshTime)
    }
    fun validateAccessToken(token:String):Boolean{
        val tok = parseAllclaims(token) ?: throw ResponseStatusException(
            HttpStatusCode.valueOf(401),
            "Invalid Token"
        )
        val type = tok["type"] as String? ?: return false
        return type == "access"
    }
    fun validateRefreshToken(token:String):Boolean{
        val tok = parseAllclaims(token) ?: throw ResponseStatusException(
            HttpStatusCode.valueOf(401),
            "Invalid Token"
        )
        val type = tok["type"] as String? ?: return false
        return type == "refresh"
    }

    fun findUserIdFromToken(token: String): String{
        val claim = parseAllclaims(token) ?: throw ResponseStatusException(
            HttpStatusCode.valueOf(401),
            "Invalid Token"
        )
        return claim.subject
    }
    fun parseAllclaims( token:String): Claims?{
        val rawToken = if(token.startsWith("Bearer")){
             token.removePrefix("Bearer ")
        }else token
        return try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(rawToken).payload
        }catch (e:Exception){
            print("Error in parsing claim")
            null
        }
    }
}