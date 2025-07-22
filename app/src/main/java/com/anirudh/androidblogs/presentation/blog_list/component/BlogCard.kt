package com.anirudh.androidblogs.presentation.blog_list.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.request.ImageRequest
import coil3.compose.AsyncImage
import coil3.request.crossfade
import com.anirudh.androidblogs.R
import com.anirudh.androidblogs.domain.model.Blog

@Composable
fun BlogCard(
    modifier: Modifier = Modifier,
    blog: Blog
){
    Card(
        modifier = modifier
    ) {
        BlogCardImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 2f),
            imageUrl = blog.thumbnailUrl
        )
        Text(
            modifier = Modifier.padding(10.dp),
            text = blog.title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BlogCardImage(
    modifier: Modifier = Modifier,
    imageUrl: String
) {
    val context = LocalContext.current

    val imageRequest = ImageRequest
        .Builder(context)
        .data(imageUrl)
        .crossfade(enable = true)
        .build()

    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.error_image),
            error = painterResource(id = R.drawable.error_image)
        )
    }
}

@Preview
@Composable
private fun BlogCardPreview() {
    val dummyBlog = Blog(
        id = 1,
        title = "Dummy Blog",
        thumbnailUrl = "https://www.mitrais.com/wp-content/uploads/2022/09/Blog_Banner_2022-Jetpack-Compose-02.jpg",
        contentUrl = "",
        content = null
    )
    BlogCard(
        blog = dummyBlog
    )
}