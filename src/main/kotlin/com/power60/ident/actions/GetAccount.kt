package com.power60.ident.actions

import com.power60.ident.models.Account
import power60.services.app.ident.services.IdentMongoService

class GetAccountRequest(val id: String)
class GetAccountResponse(val account: Account?)

class GetAccount constructor(private val mongoService: IdentMongoService) {
    fun main(req: GetAccountRequest): GetAccountResponse {
        val account = mongoService.getAccountById(req.id)
        return GetAccountResponse(account)
    }
}