package com.evawovamynewsservice.controller

import com.evawovamynewsservice.controller.dto.toResponse
import com.evawovamynewsservice.service.NewsService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController(
    private val newsService: NewsService,
) {
    @GetMapping("/news")
    fun getPageNews(pageable: Pageable): ResponseEntity<*> {
        val pageNews = newsService.getPageNews(pageable)

        return ResponseEntity.ok(pageNews.map { it.toResponse() })
    }
}
