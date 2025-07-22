package com.anirudh.androidblogs.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.anirudh.androidblogs.presentation.blog_content.BlogContentScreen
import com.anirudh.androidblogs.presentation.blog_content.BlogContentViewModel
import com.anirudh.androidblogs.presentation.blog_list.BlogListScreen
import com.anirudh.androidblogs.presentation.blog_list.BlogListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Route.BlogListScreen
    ) {
        composable<Route.BlogListScreen> {
            val viewModel = koinViewModel<BlogListViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            BlogListScreen(
                state = state,
                event = viewModel.events,
                onBlogCardClick = { id ->
                    navController.navigate(Route.BlogContentScreen(id))
                }
            )
        }
        composable<Route.BlogContentScreen> {
            val viewModel = koinViewModel<BlogContentViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()
            BlogContentScreen(
                state = state,
                onBackClick = { navController.navigateUp() },
                onAction = viewModel::onAction
            )
        }
    }
}