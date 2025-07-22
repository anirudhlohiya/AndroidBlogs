package com.anirudh.androidblogs.presentation.blog_content

import com.anirudh.androidblogs.domain.model.Blog

data class BlogContentState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val blog: Blog? = null
)
