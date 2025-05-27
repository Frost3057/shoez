package com.frosti.shoez.Services

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
 class jwtfilterService(
     private val jwtService: jwtService
 ):OncePerRequestFilter(){
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if(authHeader.isNotEmpty()&&authHeader.startsWith("Bearer ")){
            if(jwtService.validateAccessToken(authHeader)){
                val userId = jwtService.findUserIdFromToken(authHeader)
                val auth = UsernamePasswordAuthenticationToken(userId,null, emptyList())
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        return filterChain.doFilter(request,response)
    }

}
