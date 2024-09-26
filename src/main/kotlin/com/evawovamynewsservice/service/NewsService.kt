package com.evawovamynewsservice.service

import com.evawovamynewsservice.domain.News
import com.evawovamynewsservice.repository.NewsRepository
import com.evawovamynewsservice.service.dto.NewsDto
import com.evawovamynewsservice.service.dto.toDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class NewsService(
    private val naverNewsService: NaverNewsService,
    private val emailService: EmailService,
    private val newsRepository: NewsRepository,
) {
    fun getPageNews(pageable: Pageable): Page<NewsDto> = newsRepository.findPageBy(pageable).map { it.toDto() }

    @Transactional
    fun getSearchNews(
        pageable: Pageable,
        search: String,
    ): List<News> {
        val searchNews = naverNewsService.getSearchNews(search, pageable.pageSize, pageable.pageNumber)
        val newsList = searchNews.map { it.toEntity() }

        newsRepository.saveAll(newsList)

        return newsList
    }

    @Transactional
    fun sendNews(
        email: String,
        subject: String,
        search: String,
    ) {
        val newsList = this.getSearchNews(PageRequest.of(1, 5), search)

        emailService.sendEmail(email, subject, newsList)
    }
}
