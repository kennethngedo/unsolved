package com.example.unsolved.presentation.story_preview

import com.example.unsolved.domain.model.Story

data class StoryState(val isLoading: Boolean? = false, val story: Story? = null)