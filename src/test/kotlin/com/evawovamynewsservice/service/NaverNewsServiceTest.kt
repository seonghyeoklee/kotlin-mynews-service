package com.evawovamynewsservice.service

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class NaverNewsServiceTest {
    @Autowired
    lateinit var newsService: NewsService

    @Test
    fun callApi() {
        newsService.getSearchNews(PageRequest.of(1, 3), "엔비디아")
    }
}
