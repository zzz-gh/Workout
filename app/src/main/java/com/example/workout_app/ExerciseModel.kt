package com.example.workout_app

import android.media.Image

class ExerciseModel(
    private var id : Int,
    private var name : String,
    private var image : Int,
    private var isCompleted : Boolean,
    private var isSelected : Boolean

) {
    fun getId():Int{
        return id
    }
    fun setId(id: Int){
        this.id = id
    }

    fun getName():String{
        return name
    }
    fun setName(name: String){
        this.name = name
    }

    fun getImage():Int{
        return image
    }
    fun setImage(image: Int){
        this.image = image
    }

    fun getisCompleted():Boolean{
        return isCompleted
    }
    fun isCompleted(isCompleted: Boolean){
        this.isCompleted = isCompleted
    }
    fun getisSelected():Boolean{
        return isSelected
    }
    fun setisSelected(isSelected: Boolean){
        this.isSelected = isSelected
    }


}