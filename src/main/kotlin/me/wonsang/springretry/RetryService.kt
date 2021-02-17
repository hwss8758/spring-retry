package me.wonsang.springretry

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.retry.policy.SimpleRetryPolicy
import org.springframework.stereotype.Service
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.getForObject

@Service
class RetryService {

    @Autowired
    lateinit var restTemplateBuilder: RestTemplateBuilder

    @Retryable(
            value = [ResourceAccessException::class],
            maxAttempts = 2,
            backoff = Backoff(delay = 2000L)
    )
    fun useSpringRetry(): String {

        println(" ==> try restTemplate")

        val restTemplate = restTemplateBuilder.build()

        val forObject = restTemplate.getForObject<String>("http://localhost:8081/hello")

        return forObject
    }

}