package com.power60.ident.services

import com.power60.ident.actions.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import power60.services.app.ident.services.IdentMongoService

@RestController
@RequestMapping("/ident")
class IdentRestController {
    // TODO: Bind AccountMongoService as an eager singleton so we don't keep rebuilding it.
    private val mongoService = IdentMongoService()

    @GetMapping("/")
    fun index(): String {
        return testFun()
    }

    @PostMapping("/CreateAccount")
    fun handleCreateAccount(request: CreateAccountRequest): CreateAccountResponse {
        return CreateAccount(mongoService).main(request)
    }

    @PostMapping("/GetAccount")
    fun handleGetAccount(request: GetAccountRequest): GetAccountResponse {
        return GetAccount(mongoService).main(request)
    }

    @PostMapping("/UpdateAccount")
    fun handleUpdateAccount(request: UpdateAccountRequest): UpdateAccountResponse {
        return UpdateAccount(mongoService).main(request)
    }

    @PostMapping("/DeleteAccount")
    fun handleDeleteAccount(request: DeleteAccountRequest): DeleteAccountResponse {
        return DeleteAccount(mongoService).main(request)
    }

    fun testFun(): String {
        return "Hello World From Ident!"
    }
}