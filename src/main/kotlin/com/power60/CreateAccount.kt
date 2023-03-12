package com.power60

import org.bson.Document
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import power60.services.app.ident.services.IdentMongoClient

@RestController
@RequestMapping("/ident")
class CreateAccount {

    @GetMapping("/create-account")
    fun index(): String {
        return testFun()
    }

    fun main(): Boolean {
        val account: Document = Document("firstName", "Taylor")
            .append("lastName", "Kaldahl")

        val result = IdentMongoClient.createAccount(account)
        return result.wasAcknowledged()
    }

    fun testFun(): String {
        return "Hello From Test Fun"
    }
}