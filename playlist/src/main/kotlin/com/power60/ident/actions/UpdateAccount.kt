package com.power60.ident.actions

import com.power60.ident.models.Account
import power60.services.app.ident.services.IdentMongoService

class UpdateAccountRequest(val account: Account)
class UpdateAccountResponse(val id: String?)

class UpdateAccount constructor(private val mongoService: IdentMongoService) {
    fun main(req: UpdateAccountRequest): UpdateAccountResponse {
        val updatedAccountId = mongoService.save(req.account)
        return UpdateAccountResponse(updatedAccountId)
    }
}