package com.anirudh.androidblogs.presentation.blog_list

import com.anirudh.androidblogs.domain.model.Blog

data class BlogListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val blogs: List<Blog> = emptyList()
)
