package com.power60.ident.models

import java.util.*

interface Account {
    val id: String?
    var firstName: String?
    var lastName: String?
    var email: String?
    var phoneNumber: Long?
}

class AccountFactory {
    fun createNewAccount(
        firstName: String?,
        lastName: String?,
        email: String?,
        phoneNumber: Long?
    ): Account {
        val id = UUID.randomUUID().toString()
        return mapOf(
            "id" to id,
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "phoneNumber" to phoneNumber
        ) as Account
    }
}