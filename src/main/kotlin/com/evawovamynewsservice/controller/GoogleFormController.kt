package com.evawovamynewsservice.controller

import com.evawovamynewsservice.utils.logger
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/google/form")
class GoogleFormController {
    @PostMapping("/submit")
    fun submitForm(
        @RequestBody formData: GoogleFormData,
    ): String {
        logger().info(formData.toString())
        return "Form data received successfully"
    }
}

data class GoogleFormData(
    val formId: String,
    val formTitle: String,
    val email: String,
    val results: List<FormResult>,
)

data class FormResult(
    val id: Int,
    val type: String,
    val title: String,
    val response: Response,
)

sealed class Response {
    data class TextResponse(
        val response: String,
    ) : Response()

    data class CheckboxResponse(
        val response: List<String>,
    ) : Response()

    object EmptyResponse : Response()
}
