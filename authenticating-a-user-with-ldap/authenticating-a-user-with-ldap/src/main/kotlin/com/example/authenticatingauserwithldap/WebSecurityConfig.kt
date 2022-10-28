package com.example.authenticatingauserwithldap

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.Exception

@Configuration
class WebSecurityConfig() : WebSecurityConfigurerAdapter() {

    @Override
    override fun configure(http: HttpSecurity?): Unit = run {
        http
                ?.authorizeRequests()
                ?.anyRequest()
                ?.fullyAuthenticated()
                ?.and()
                ?.formLogin() ?: throw Exception()
    }

    override fun configure(auth: AuthenticationManagerBuilder?): Unit = run {
        auth
                ?.ldapAuthentication()
                ?.userDnPatterns("uid={0},ou=people")
                ?.groupSearchBase("ou=groups")
                ?.contextSource()
                ?.url("ldap://localhost:8389/dc=springframework,dc=org")
                ?.and()
                ?.passwordCompare()
                ?.passwordEncoder(BCryptPasswordEncoder())
                ?.passwordAttribute("userPassword") ?: throw Exception()
    }

}