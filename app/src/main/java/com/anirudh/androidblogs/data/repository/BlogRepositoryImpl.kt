package com.anirudh.androidblogs.data.repository

import com.anirudh.androidblogs.data.local.BlogDao
import com.anirudh.androidblogs.data.local.entity.BlogContentEntity
import com.anirudh.androidblogs.data.mapper.toBlog
import com.anirudh.androidblogs.data.mapper.toBlogEntityList
import com.anirudh.androidblogs.data.mapper.toBlogList
import com.anirudh.androidblogs.data.remote.RemoteBlogDataSource
import com.anirudh.androidblogs.domain.model.Blog
import com.anirudh.androidblogs.domain.repository.BlogRepository
import com.anirudh.androidblogs.domain.util.Result

class BlogRepositoryImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource,
    private val localBlogDataSource: BlogDao
) : BlogRepository {

    override suspend fun getAllBlogs(): Result<List<Blog>> {
        val remoteBlogsResult = remoteBlogDataSource.getAllBlogs()
        return when (remoteBlogsResult) {
            is Result.Success -> {
                remoteBlogsResult.data?.let { blogs ->
                    localBlogDataSource.deleteAllBlogs()
                    localBlogDataSource.insertAllBlogs(blogs.toBlogEntityList())
                    Result.Success(data = blogs.toBlogList())
                } ?: Result.Error(message = "No data available")
            }
            is Result.Error -> {
                val localBlogs = localBlogDataSource.getAllBlogs()
                if (localBlogs.isNotEmpty()) {
                    Result.Error(
                        data = localBlogs.toBlogList(),
                        message = remoteBlogsResult.message?: "Failed to fetch blogs"
                    )
                } else {
                    Result.Error(
                        message = remoteBlogsResult.message?: "Failed to fetch blogs, and no local data available"
                    )
                }
            }
        }
    }

    override suspend fun getBlogById(blogId: Int): Result<Blog> {
        val blogEntity = localBlogDataSource.getBlogById(blogId)
            ?:return Result.Error(message = "Blog not found in local database")
        val contentResult = remoteBlogDataSource.fetchBlogContent(blogEntity.contentUrl)
        return when(contentResult){
            is Result.Success -> {
                val blogContentEntity = BlogContentEntity(
                    blogId = blogId,
                    content = contentResult.data ?: ""
                )
                localBlogDataSource.insertBlogContent(blogContentEntity)
                Result.Success(data = blogEntity.toBlog(contentResult.data))
            }
            is Result.Error -> {
                val contentEntity = localBlogDataSource.getBlogContent(blogId)
                if (contentEntity != null){
                    Result.Success(data = blogEntity.toBlog(contentEntity.content))
                } else {
                    Result.Error(message = "Failed to fetch blog content. ${contentResult.message}")
                }
            }
        }
    }
}