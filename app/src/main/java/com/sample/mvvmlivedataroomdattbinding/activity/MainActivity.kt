package com.sample.mvvmlivedataroomdattbinding.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sample.mvvmlivedataroomdattbinding.R
import com.sample.mvvmlivedataroomdattbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*auto generated binding class ActivityMainBinding from activity_main.xml layout*/
        val binding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        /*specify MainActivity as the lifecyle owner*/
        binding.setLifecycleOwner(this)
    }
}
