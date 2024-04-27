package com.example.mini_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mini_project.Model.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        var pan_number = findViewById<EditText>(R.id.pan_number)

        var day = findViewById<TextInputEditText>(R.id.day)
        var month = findViewById<TextInputEditText>(R.id.month)
        var year = findViewById<TextInputEditText>(R.id.year)
        val next = findViewById<TextView>(R.id.next)

        var close = findViewById<TextView>(R.id.close)

        pan_number.addTextChangedListener {
            viewModel.validateDetails(pan_number.text.toString(), day.text.toString(), month.text.toString(), year.text.toString())
        }
        day.addTextChangedListener {
            viewModel.validateDetails(pan_number.text.toString(), day.text.toString(), month.text.toString(), year.text.toString())
        }

        month.addTextChangedListener {
            viewModel.validateDetails(pan_number.text.toString(), day.text.toString(), month.text.toString(), year.text.toString())
        }
        year.addTextChangedListener {
            viewModel.validateDetails(pan_number.text.toString(), day.text.toString(), month.text.toString(), year.text.toString())
        }

        viewModel.isValid.observe(this, Observer { isEnabled ->
            next.isEnabled = isEnabled
        })

        viewModel.isValid.observe(this, Observer { isEnabled ->
                    next.visibility = View.VISIBLE


        })



        next.setOnClickListener { Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show()
        finish()}

        close.setOnClickListener { finish() }

    }

}