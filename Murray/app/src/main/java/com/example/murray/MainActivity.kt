package com.example.murray

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mainButton: Button

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Log.d("Afisare", "OK")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeViews()
        setListeners()
    }

    private fun initializeViews() {
        mainButton = findViewById(R.id.buttonMain)
    }

    private fun setListeners() {
        mainButton.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent = Intent ( this, AccountActivity::class.java )
        intent.putExtra("user_name", "Dragos")
        intent.putExtra("user_surname", "Neag")
        intent.putExtra("user_password", "dragos")
        resultLauncher.launch(intent)
    }
}