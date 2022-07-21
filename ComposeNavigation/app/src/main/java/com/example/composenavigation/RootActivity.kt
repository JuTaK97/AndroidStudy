package com.example.composenavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.composenavigation.databinding.ActivityRootBinding

class RootActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.composeView.setContent {
            SetupBottomNavigation()
        }
    }
}
