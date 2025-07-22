package com.anirudh.androidblogs.di

import com.anirudh.androidblogs.data.local.BlogDatabase
import com.anirudh.androidblogs.data.local.DatabaseFactory
import com.anirudh.androidblogs.data.remote.HttpClientFactory
import com.anirudh.androidblogs.data.remote.KtorRemoteBlogDataSource
import com.anirudh.androidblogs.data.remote.RemoteBlogDataSource
import com.anirudh.androidblogs.data.repository.BlogRepositoryImpl
import com.anirudh.androidblogs.domain.repository.BlogRepository
import com.anirudh.androidblogs.presentation.blog_content.BlogContentViewModel
import com.anirudh.androidblogs.presentation.blog_list.BlogListViewModel
import org.koin.dsl.module
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind

val KoinMainModule = module {

    single { DatabaseFactory.create(get())}
    single { get<BlogDatabase>().blogDao() }

    single { HttpClientFactory.create(OkHttp.create( )) }

    singleOf(::KtorRemoteBlogDataSource).bind<RemoteBlogDataSource>()
    singleOf(::BlogRepositoryImpl).bind<BlogRepository>()

    viewModelOf(::BlogListViewModel)
    viewModelOf(::BlogContentViewModel)

}