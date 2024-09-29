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
        @RequestBody formData: GoogleForm,
    ): String {
        logger().info(formData.toString())
        return "Form data received successfully"
    }
}

data class GoogleForm(
    val formId: String,
    val formTitle: String,
    val email: String,
    val results: GoogleFormData,
)

data class GoogleFormData(
    val id: String,
    val type: String,
    val title: String,
    val response: String,
)
