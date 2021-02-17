package me.wonsang.springretry

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RetryController {

    @Autowired
    lateinit var retryService: RetryService

    @GetMapping("/1")
    fun retryRestTemplate(): String {
        return retryService.useSpringRetry()
    }

}