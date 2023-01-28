package com.example.criticaltechworkstaskapp.data.remote.dto
import com.example.criticaltechworkstaskapp.domian.model.News
import com.example.criticaltechworkstaskapp.domian.model.Source
import com.google.gson.annotations.SerializedName


data class NewsDto(
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    @SerializedName("source")
    val source: SourceDto?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val urlToImage: String?
)

data class SourceDto(
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?
)

data class NewsResponse(
    @SerializedName("articles")
    val articles: List<NewsDto>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)






fun NewsDto.toNews():News{
    return News(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        source = Source(source?.id, source?.name),
        title = title,
        urlToImage = urlToImage
    )
}
