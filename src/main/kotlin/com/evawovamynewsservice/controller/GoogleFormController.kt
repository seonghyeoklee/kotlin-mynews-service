package com.evawovamynewsservice.controller

import com.evawovamynewsservice.service.GoogleFormService
import com.evawovamynewsservice.utils.logger
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/google/form")
class GoogleFormController(
    private val googleFormService: GoogleFormService,
) {
    @PostMapping("/submit")
    fun submitForm(
        @RequestBody formData: GoogleFormData,
    )  {
        logger().info(formData.toString())
        googleFormService.saveGoogleForm(formData)
    }
}

data class GoogleFormData(
    val formId: String,
    val formTitle: String,
    val email: String,
    val results: List<GoogleFormResult>,
)

data class GoogleFormResult(
    val id: Int,
    val type: String,
    val title: String,
    val response: Response,
)

@JsonDeserialize(using = ResponseDeserializer::class)
sealed class Response {
    data class TextResponse(
        val response: String,
    ) : Response()

    data class CheckboxResponse(
        val response: List<String>,
    ) : Response()

    object EmptyResponse : Response()
}

class ResponseDeserializer : JsonDeserializer<Response>() {
    override fun deserialize(
        parser: JsonParser,
        ctxt: DeserializationContext,
    ): Response {
        val node: JsonNode = parser.codec.readTree(parser)

        return when {
            node.isTextual -> Response.TextResponse(node.asText())
            node.isArray -> Response.CheckboxResponse(node.map { it.asText() })
            node.isNull -> Response.EmptyResponse
            else -> throw IllegalArgumentException("Unknown response type")
        }
    }
}
