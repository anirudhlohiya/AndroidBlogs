package com.anirudh.androidblogs.data.remote

import com.anirudh.androidblogs.data.remote.dto.BlogDto
import com.anirudh.androidblogs.domain.util.Result

interface RemoteBlogDataSource {
    suspend fun getAllBlogs(): Result<List<BlogDto>>
    suspend fun fetchBlogContent(url: String): Result<String>
}