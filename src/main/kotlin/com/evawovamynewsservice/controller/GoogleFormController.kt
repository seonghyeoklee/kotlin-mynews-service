package com.evawovamynewsservice.controller

import com.evawovamynewsservice.utils.logger
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
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

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type",
)
@JsonSubTypes(
    JsonSubTypes.Type(value = Response.TextResponse::class, name = "TEXT"),
    JsonSubTypes.Type(value = Response.CheckboxResponse::class, name = "CHECKBOX"),
    JsonSubTypes.Type(value = Response.ParagraphTextResponse::class, name = "PARAGRAPH_TEXT"),
    JsonSubTypes.Type(value = Response.EmptyResponse::class, name = "EMPTY"),
)
sealed class Response {
    data class TextResponse(
        val response: String,
    ) : Response()

    data class CheckboxResponse(
        val response: List<String>,
    ) : Response()

    data class ParagraphTextResponse(
        val response: String,
    ) : Response()

    object EmptyResponse : Response()
}
