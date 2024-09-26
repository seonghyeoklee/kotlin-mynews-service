package com.evawovamynewsservice.service

import com.evawovamynewsservice.service.dto.ItemDto
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class NaverNewsService(
    @Value("\${naver.api.client-id}") val clientId: String,
    @Value("\${naver.api.client-secret}") val clientSecret: String,
) {
    fun getSearchNews(
        query: String,
        size: Int,
        page: Int,
    ): List<ItemDto> {
        val url = "https://openapi.naver.com/v1/search/news.json?query=$query&display=$size&start=$page&sort=sim"
        val headers =
            HttpHeaders().apply {
                set("X-Naver-Client-Id", clientId)
                set("X-Naver-Client-Secret", clientSecret)
            }
        val entity = HttpEntity<String>(headers)
        val response: ResponseEntity<String> = RestTemplate().exchange(url, HttpMethod.GET, entity, String::class.java)

        return extractItems(response.body!!)
    }

    fun extractItems(json: String): List<ItemDto> {
        val mapper = jacksonObjectMapper()
        val rootNode = mapper.readTree(json)
        val itemsNode = rootNode["items"]

        return mapper.readValue<List<ItemDto>>(itemsNode.toString())
    }
}
