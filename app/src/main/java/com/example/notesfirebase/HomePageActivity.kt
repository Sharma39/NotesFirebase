package com.example.notesfirebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    private var inputFragment: InputFragment? = null
    private var infoFragment: InfoFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        inputFragment = supportFragmentManager.findFragmentById(R.id.input_fragment) as InputFragment?
        infoFragment = supportFragmentManager.findFragmentById(R.id.info_fragment) as InfoFragment?

    }
    }