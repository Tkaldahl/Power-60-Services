package com.power60.ident.actions

import power60.services.app.ident.services.IdentMongoService

class DeleteAccountRequest(val id: String)
class DeleteAccountResponse(val status: Boolean)

class DeleteAccount constructor(private val mongoService: IdentMongoService) {
    fun main(req: DeleteAccountRequest): DeleteAccountResponse {
        val mongoResponse = mongoService.deleteAccount(req.id).wasAcknowledged()
        return DeleteAccountResponse(mongoResponse)
    }
}