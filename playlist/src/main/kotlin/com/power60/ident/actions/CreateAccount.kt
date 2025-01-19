package com.power60.ident.actions

import com.power60.ident.models.Account
import power60.services.app.ident.services.IdentMongoService

class CreateAccountRequest(val account: Account)
class CreateAccountResponse(val id: String?)

class CreateAccount constructor(private val mongoService: IdentMongoService) {
    fun main(req: CreateAccountRequest): CreateAccountResponse {
        val accountId = mongoService.save(req.account)
        return CreateAccountResponse(accountId)
    }
}
