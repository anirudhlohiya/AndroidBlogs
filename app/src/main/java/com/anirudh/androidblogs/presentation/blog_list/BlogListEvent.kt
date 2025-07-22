package com.anirudh.androidblogs.presentation.blog_list

sealed interface BlogListEvent {
    data class Error(val error: String): BlogListEvent

}