package com.example.workout_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sevenminuteworkout.SqliteOpenHelper
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.activity_history.*

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        setSupportActionBar(toolbar_history_activity)
        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar!!.title = "HISTORY"
        }

        toolbar_history_activity.setNavigationOnClickListener(){
            onBackPressed()
        }

        getAllCompletedDates()
    }

    private fun getAllCompletedDates() {
        val dbHandler = SqliteOpenHelper(this, null)
        val allCompletedDatesList =
            dbHandler.getAllCompletedDatesList() // List of history table data

        if(allCompletedDatesList.size>0){
            tv_history.visibility = View.VISIBLE
            rvHistory.visibility = View.VISIBLE
            no_data_history.visibility = View.GONE

            rvHistory.layoutManager = LinearLayoutManager(this)
            val rvAdapter = HistoryAdapter(this,allCompletedDatesList)
            rvHistory.adapter = rvAdapter
        }else{
            tv_history.visibility = View.GONE
            rvHistory.visibility = View.GONE
            no_data_history.visibility = View.VISIBLE
        }

    }
}