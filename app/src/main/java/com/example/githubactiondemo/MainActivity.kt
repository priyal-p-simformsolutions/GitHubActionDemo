package com.example.githubactiondemo

import androidx.activity.viewModels
import com.example.githubactiondemo.databinding.ActivityMainBinding

class MainActivity : BaseAppCompatActivity<ActivityMainBinding, MainViewModel>() {
    override val viewModel: MainViewModel by viewModels()

    override fun initialize() {
       binding.data = "hello"
    }

    override fun getLayoutResId() = R.layout.activity_main
}