package com.example.workout_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        

        ll_Start.setOnClickListener {
//            Toast.makeText(this,"You clicked start button ",Toast.LENGTH_LONG).show()
            val intent = Intent(this,ExerciseActivity::class.java)
            startActivity(intent)
        }

        ll_BMI.setOnClickListener{
            val intent = Intent(this,BmiActivity::class.java)
            startActivity(intent)
        }

        ll_History.setOnClickListener{
            val intent = Intent(this,HistoryActivity::class.java)
            startActivity(intent)
        }


    }
}