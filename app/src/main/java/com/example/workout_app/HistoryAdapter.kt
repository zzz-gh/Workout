package com.example.workout_app

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_history_date_row.view.*

class HistoryAdapter(val context: Context,val items :ArrayList<String>) :
    RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        var llHistoryItem = view.ll_history_item
        val tvItem = view.tv_item
        val tvPosition = view.tv_position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_history_date_row,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = items.get(position)
        holder.tvPosition.text = (position+1).toString()
        holder.tvItem.text = date

        if(position % 2 == 0){
            holder.llHistoryItem.setBackgroundColor(
                Color.parseColor("#91d18b")
            )
        }else{
            holder.llHistoryItem.setBackgroundColor(
                Color.parseColor("#EBEBEB")
            )
        }

    }
}