package com.anirudh.androidblogs.data.remote

import com.anirudh.androidblogs.data.util.Constant.GITHUB_URL
import com.anirudh.androidblogs.data.remote.dto.BlogDto
import com.anirudh.androidblogs.domain.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import java.net.UnknownHostException

class KtorRemoteBlogDataSource(
    private val httpClient: HttpClient
) : RemoteBlogDataSource {
    override suspend fun getAllBlogs(): Result<List<BlogDto>> {
        return try {
            val response = httpClient.get(GITHUB_URL)
            val blogs = response.body<List<BlogDto>>()
            Result.Success(blogs)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            Result.Error(message = "Network error. Please verify your internet connection.")
        }
        catch (e: Exception) {
            e.printStackTrace()
            Result.Error(message = "Something went wrong. ${e.message}")
        }
    }

    override suspend fun fetchBlogContent(url: String): Result<String> {
        return try {
            val response = httpClient.get(url)
            val blogContent = response.bodyAsText()
            Result.Success(blogContent)
        } catch (e: UnknownHostException) {
            e.printStackTrace()
            Result.Error(message = "Network error. Please verify your internet connection.")
        }
        catch (e: Exception) {
            e.printStackTrace()
            Result.Error(message = "Something went wrong. ${e.message}")
        }
    }
}