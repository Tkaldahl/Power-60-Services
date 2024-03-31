package com.power60.ident.services

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ident")
class IdentRestController {

    @GetMapping("/")
    fun index(): String {
        return testFun()
    }

    fun testFun(): String {
        return "Hello World From Ident!"
    }
}