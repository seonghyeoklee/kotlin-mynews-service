package com.evawovamynewsservice.controller

import com.evawovamynewsservice.controller.dto.ResponseNews
import com.evawovamynewsservice.controller.dto.toResponse
import com.evawovamynewsservice.service.NewsService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController(
    private val newsService: NewsService,
) {
    @Operation(
        summary = "나만의 뉴스 목록 조회",
        description = "페이징을 이용하여 나만의 뉴스 목록을 조회합니다.",
        parameters = [
            Parameter(name = "page", description = "페이징", required = true),
            Parameter(name = "size", description = "사이즈", required = true),
            Parameter(name = "sort", description = "정렬"),
        ],
        responses = [
            ApiResponse(
                responseCode = "200",
                description = "Ok",
                content = [Content(schema = Schema(implementation = ResponseNews::class)) ],
            ),
        ],
    )
    @GetMapping("/news")
    fun getPageNews(
        @Parameter(hidden = true) pageable: Pageable,
    ): ResponseEntity<*> {
        val pageNews = newsService.getPageNews(pageable)

        return ResponseEntity.ok(pageNews.map { it.toResponse() })
    }
}
