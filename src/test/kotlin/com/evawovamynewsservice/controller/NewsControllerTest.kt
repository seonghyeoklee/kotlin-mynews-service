package com.evawovamynewsservice.controller

import com.evawovamynewsservice.domain.News
import com.evawovamynewsservice.repository.NewsRepository
import com.evawovamynewsservice.service.NewsService
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest

@SpringBootTest
class NewsControllerTest(
    private val newsService: NewsService,
    private val newsRepository: NewsRepository,
) : BehaviorSpec({
        beforeSpec {
            newsRepository.saveAll(
                listOf(
                    News(title = "title1", link = "link1", searchKeyword = "searchKeyword1"),
                    News(title = "title2", link = "link2", searchKeyword = "searchKeyword2"),
                    News(title = "title3", link = "link3", searchKeyword = "searchKeyword3"),
                ),
            )
        }
        given("뉴스 목록 조회 요청시") {
            When("정상 조회시") {
                val pageNews = newsService.getPageNews(PageRequest.of(0, 2))
                then("뉴스 목록이 2개 조회되어야 한다") {
                    pageNews.size shouldBe 2L
                }
                then("뉴스 목록의 첫번째 뉴스는 title3이어야 한다") {
                    pageNews.number shouldBe 0
                    pageNews.size shouldBe 2
                    pageNews.content.size shouldBe 2
                    pageNews.content[0].title shouldBe "title3"
                    pageNews.content[0].link shouldBe "link3"
                    pageNews.content[0].searchKeyword shouldBe "searchKeyword3"
                }
            }
        }
    })
