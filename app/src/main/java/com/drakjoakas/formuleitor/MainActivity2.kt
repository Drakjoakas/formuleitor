package com.drakjoakas.formuleitor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drakjoakas.formuleitor.databinding.ActivityMain2Binding
import kotlin.concurrent.thread

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        thread {
            Thread.sleep(3000)

            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}