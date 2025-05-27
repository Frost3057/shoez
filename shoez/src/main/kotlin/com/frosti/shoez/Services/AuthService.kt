package com.frosti.shoez.Services

import com.frosti.shoez.model.RefreshToken
import com.frosti.shoez.model.user
import com.frosti.shoez.repo.refreshRepo
import com.frosti.shoez.repo.userRepo
import org.bson.types.ObjectId
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.server.ResponseStatusException
import java.security.MessageDigest
import java.time.Instant
import java.util.*

@Service
class AuthService(
    private val jwtService: jwtService,
    private val hashEncoder: hashEncoder,
    private val userRepo: userRepo,
    private val refreshRepo: refreshRepo
) {
    data class TokenPair(
        val accessToken:String,
        val refreshToken:String
    )
    fun register(email:String,password:String){
        val user = userRepo.findUserByEmail(email)?:throw ResponseStatusException(
            HttpStatusCode.valueOf(401),
            "User already exists"
        )
        userRepo.save(
            user(
                email = email,
                password = hashEncoder.encodePass(password)
            )
        )
    }
    fun login(email:String,password: String):TokenPair{
        val use = userRepo.findUserByEmail(email)?:throw ResponseStatusException(
            HttpStatusCode.valueOf(401),
            "User already exists"
        )
        if(!hashEncoder.matchesPass(password,use.password)){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(401),
                "Bad Credentials"
            )
        }
        val accessToken = jwtService.generateAccessToken(use.uid.toHexString())
        val refreshToken = jwtService.generateRefreshToken(use.uid.toHexString())
        val expriy = jwtService.refreshTime;
        val tim = Instant.now().plusMillis(expriy)
        refreshRepo.save(RefreshToken(
            userId = use.uid,
            hashedToken = HashToken(refreshToken),
            expiry = tim
        ))
        return TokenPair(
            accessToken = accessToken,
            refreshToken = refreshToken
        )
    }
    @Transactional
    fun refresh(token:String):TokenPair{
        if(!jwtService.validateRefreshToken(token)){
            throw ResponseStatusException(
                HttpStatusCode.valueOf(401),
                "bad info"
            )
        }
        val uid = jwtService.findUserIdFromToken(token)
        val user = userRepo.findById(ObjectId(uid)).orElseThrow {
            throw ResponseStatusException(
                HttpStatusCode.valueOf(401),
                "bad info"
            )
        }
        val refreshToken = refreshRepo.findByUserIdAndHashedToken(ObjectId(uid),HashToken(token))?:throw ResponseStatusException(
            HttpStatusCode.valueOf(401),
            "token might have expired"
        )
        refreshRepo.deleteRefreshTokenByUserIdAndHashedToken(user.uid,refreshToken.hashedToken)
        val accessToken = jwtService.generateAccessToken(uid)
        val refreshToke = jwtService.generateRefreshToken(uid)
        val expiry = jwtService.refreshTime
        val tim = Instant.now().plusMillis(expiry)
        refreshRepo.save(RefreshToken(
            userId = user.uid,
            hashedToken = HashToken(refreshToke),
            expiry = tim
        ))
        return TokenPair(
            accessToken = accessToken,
            refreshToken = refreshToke
        )
    }

    private fun HashToken(token:String):String{
        val digest = MessageDigest.getInstance("SHA-256")
        val encodedToken = digest.digest(token.encodeToByteArray())
        return Base64.getEncoder().encodeToString(encodedToken)
    }
}