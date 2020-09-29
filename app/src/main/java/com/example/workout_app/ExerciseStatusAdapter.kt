package com.example.workout_app

import android.content.Context
import android.graphics.Color
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_exercise_status.view.*



class ExerciseStatusAdapter(val context: Context,val items:ArrayList<ExerciseModel>) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val tvItem = view.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_exercise_status,parent,false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = items[position]
        holder.tvItem.text = model.getId().toString()

        if(model.getisSelected()){
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.item_circular_accent_border)
            holder.tvItem.setTextColor(Color.parseColor("#B2212121"))
        }else if(model.getisCompleted()){
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.item_circular_color_accent_background)
            holder.tvItem.setTextColor(Color.parseColor("#FFFFFF"))
        }else{
            holder.tvItem.background = ContextCompat.getDrawable(context,R.drawable.item_circuclar_gray_background)
            holder.tvItem.setTextColor(Color.parseColor("#B2212121"))
        }
    }

}

