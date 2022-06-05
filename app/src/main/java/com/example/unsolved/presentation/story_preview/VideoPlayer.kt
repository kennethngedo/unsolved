package com.example.unsolved.presentation.story_preview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.unsolved.domain.model.Media
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun VideoPlayer(mediaItems: List<Media>) {
    val context = LocalContext.current
    var playWhenReady = remember { mutableStateOf(true) }
    val exoPlayer = remember(context) {
        ExoPlayer.Builder(context).build().apply {
            for (media in mediaItems ) {
                addMediaItem(MediaItem.fromUri(media.resourceUri))
            }
            playWhenReady = playWhenReady
            prepare()
            play()
        }
    }

    DisposableEffect(
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    useController = true
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}