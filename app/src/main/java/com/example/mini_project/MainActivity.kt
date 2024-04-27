package com.example.mini_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mini_project.Model.MainViewModel
import com.google.android.material.textfield.TextInputEditText

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
      var next = findViewById<TextView>(R.id.next)

        var close = findViewById<TextView>(R.id.close)
        close.setOnClickListener { finish() }
        next.setOnClickListener {
            var Pan = pan_number.text.toString()
            var Year = year.text.toString()
            var Day =  day.text.toString()
            var Month = month.text.toString()

            if (Pan.isEmpty()){
                Toast.makeText(this, "Pleas enter PAN number", Toast.LENGTH_SHORT).show()

                next.visibility=View.GONE
            }else if (Day.isEmpty()){
                Toast.makeText(this, "Pleas enter Birth Date", Toast.LENGTH_SHORT).show()
                next.visibility=View.GONE
            }else if (Month.isEmpty()){
                Toast.makeText(this, "Pleas enter Birth Month", Toast.LENGTH_SHORT).show()
                next.visibility=View.GONE
            }else if (Year.isEmpty()){
                Toast.makeText(this, "Pleas enter per Year", Toast.LENGTH_SHORT).show()
                next.visibility=View.GONE

            }
            else{
                var dateofbirth = Day+"/"+Month+"/"+Year
                checkkerin(dateofbirth,Pan)

                Toast.makeText(this, "Details submitted successfully", Toast.LENGTH_SHORT).show()
                finish()
            }

        }

    }

    fun checkkerin(dateofbirth:String,panumb:String){

        viewModel.validateDetails(dateofbirth,panumb)
    }
}