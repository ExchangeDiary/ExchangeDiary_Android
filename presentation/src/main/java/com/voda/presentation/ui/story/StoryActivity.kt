package com.voda.presentation.ui.story

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.voda.presentation.R

class StoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_story_list)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.storyListContainer, StoryFragment.newInstance())
                .commitNow()
        }
    }
}