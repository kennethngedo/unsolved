package com.example.unsolved.presentation.story_preview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.unsolved.R
import com.example.unsolved.common.utils.Constants.TAG_CIRCLE_PROGRESS_INDICATOR
import com.example.unsolved.common.utils.Constants.TAG_PLAY_BUTTON
import com.example.unsolved.common.utils.Constants.TAG_PREVIEW_BUTTON
import com.example.unsolved.common.utils.Constants.TAG_REFRESH_BUTTON
import com.example.unsolved.common.utils.Constants.TAG_SUMMARY_TEXT
import com.example.unsolved.common.utils.UIEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun StoryPreviewScreen(
    navigator: DestinationsNavigator? = null,
    viewModel: StoryPreviewViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val backgroundImageHeight = screenHeight / 2
    val screenHeightUnit = screenHeight / 10
    val isLoading = state.isLoading ?: false
    val hasStory = state.story != null
    val openDialog = remember { mutableStateOf(false) }
    val showSnackBar = remember { mutableStateOf(false) }


    LaunchedEffect(key1 = true) {
        viewModel.performGetStory()
        viewModel.event.collectLatest { event ->
            when (event) {
                is UIEvent.ShowSnackBar -> {
                    showSnackBar.value = true
                }
            }
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {

            AnimatedVisibility(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(backgroundImageHeight),
                visible = state.story != null
            ) {
                val imageUrl = state.story?.listImage?.get(0)?.resourceUri
                GlideImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageModel = imageUrl,
                    contentScale = ContentScale.FillBounds,
                )
            }

            val gradient = Brush.verticalGradient(
                0.2f to Color.Transparent,
                0.4f to MaterialTheme.colorScheme.background.copy(alpha = 0.9f),
                0.6f to MaterialTheme.colorScheme.background
            )
            Column(
                modifier = Modifier
                    .background(gradient)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(backgroundImageHeight - screenHeightUnit))

                val summary = state.story?.fullSummary ?: ""
                AnimatedVisibility(
                    visible = summary.isNotBlank(),
                ) {
                    Text(
                        text = summary, fontSize = 24.sp, modifier = Modifier.testTag(
                            TAG_SUMMARY_TEXT
                        )
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                AnimatedVisibility(
                    visible = summary.isNotBlank(),
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        OutlinedButton(onClick = {
                            openDialog.value = true
                        }) {
                            Text(
                                text = stringResource(id = R.string.watch_trailer),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.testTag(
                                    TAG_PREVIEW_BUTTON
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Button(onClick = {

                        }) {
                            Text(
                                text = stringResource(id = R.string.play),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.width(60.dp).testTag(
                                    TAG_PLAY_BUTTON
                                )
                            )
                        }

                    }
                }


            }

            AnimatedVisibility(visible = isLoading && hasStory) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            AnimatedVisibility(visible = isLoading && !hasStory) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.testTag(
                            TAG_CIRCLE_PROGRESS_INDICATOR
                        )
                    )
                }
            }

            val mediaItems = state.story?.previewMedia ?: emptyList()
            if (openDialog.value && mediaItems.isNotEmpty()) {

                Dialog(
                    onDismissRequest = { openDialog.value = false },
                    properties = DialogProperties(
                        dismissOnBackPress = true,
                        dismissOnClickOutside = true
                    )
                ) {
                    VideoPlayer(mediaItems = mediaItems)
                }

            }

            if (state.story == null && !isLoading) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = R.string.not_available))
                    Button(onClick = { viewModel.performGetStory() }, modifier = Modifier.testTag(TAG_REFRESH_BUTTON)) {
                        Icon(Icons.Filled.Refresh, contentDescription = "refresh")

                        Text(text = stringResource(id = R.string.reload))
                    }
                }
            }

        }
    }
}

