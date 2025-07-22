package com.anirudh.androidblogs.domain.repository

import com.anirudh.androidblogs.domain.model.Blog
import com.anirudh.androidblogs.domain.util.Result

interface BlogRepository {
    suspend fun getAllBlogs(): Result<List<Blog>>
    suspend fun getBlogById(blogId: Int): Result<Blog>
}