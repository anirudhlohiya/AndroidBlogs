package com.anirudh.androidblogs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.anirudh.androidblogs.domain.model.Blog
import com.anirudh.androidblogs.presentation.blog_list.BlogListScreen
import com.anirudh.androidblogs.presentation.blog_list.BlogListState
import com.anirudh.androidblogs.presentation.blog_list.BlogListViewModel
import com.anirudh.androidblogs.presentation.navigation.NavGraph
import com.anirudh.androidblogs.presentation.theme.AndroidBlogsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AndroidBlogsTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize() )
                { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController,
                    )
                }
            }
        }
    }
}