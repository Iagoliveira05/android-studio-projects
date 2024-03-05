package com.example.loginmvvm

class PersonRepository {
    fun login(email: String, password: String): Boolean {
        return (email == "admin" && password == "pass")
    }
}