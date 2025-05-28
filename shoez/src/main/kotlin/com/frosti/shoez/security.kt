package com.frosti.shoez

import com.frosti.shoez.Services.jwtfilterService
import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class security(
    private val jwtAuthFilter:jwtfilterService
) {
    @Bean
    fun filterChain(httpSecurity: HttpSecurity):SecurityFilterChain{
        return httpSecurity.csrf{csrf->csrf.disable()}.sessionManagement{it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)}.authorizeHttpRequests {
            auth->
            auth.requestMatchers("/auth/**").permitAll().dispatcherTypeMatchers(
                DispatcherType.ERROR,
                DispatcherType.FORWARD
            ).permitAll().anyRequest().authenticated()
        }.exceptionHandling {
            config->
            config.authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        }.addFilterBefore(jwtAuthFilter,UsernamePasswordAuthenticationFilter::class.java).build()
    }
}