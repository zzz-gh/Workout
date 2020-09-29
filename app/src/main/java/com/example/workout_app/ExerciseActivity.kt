package com.example.workout_app

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.dialog_custom_back_comfirmation.*
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList



class ExerciseActivity : AppCompatActivity() ,TextToSpeech.OnInitListener{
    private var player:MediaPlayer? = null

    private var tts:TextToSpeech? = null
    private var textForSpeech : String? = null

    private var restTimeAmount : Long = 10
    private var exerciseTimeAmount : Long = 30

    private var restProgressTimer:CountDownTimer? =null
    private var restProgress= 0

    private var exerciseProgressTimer:CountDownTimer? =null
    private var exerciseProgress= 0

    private var exerciseList  = ArrayList<ExerciseModel>()
    private var currentPosotion = -1

    private var exerciseAdapter : ExerciseStatusAdapter? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        toolbar_exercise_activity.setNavigationOnClickListener(){

            backIconDialog()
        }
        exerciseList = Constants.defaultExerciseList()
        tts = TextToSpeech(this,this)
        setupRestView()

        setupExerciseStatusRecyclerView()


    }

    override fun onDestroy() {

        if(restProgressTimer!=null){
            restProgressTimer!!.cancel()
            restProgress = 0
        }

        if(exerciseProgressTimer!=null){
            exerciseProgressTimer!!.cancel()
            exerciseProgress = 0
        }
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player != null){
            player!!.stop()
        }
        super.onDestroy()

    }

    private fun setRestProgressBar(){
        progressBar.progress = restProgress
        restProgressTimer = object :CountDownTimer(restTimeAmount*1000,1000){
            override fun onTick(restTime: Long) {
                restProgress++
                progressBar.progress = restTimeAmount.toInt()-restProgress
                tvTimer.text= (restTimeAmount.toInt()-restProgress).toString()
//                speakOut(tvTimer.text.toString())
            }

            override fun onFinish() {
                currentPosotion++
                exerciseList!![currentPosotion].setisSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()



            }
        }.start()

    }

    private fun setupRestView(){

//        val soundURI =
//            Uri.parse("android.resource://com.example.workout_app/" + R.raw.press_start)
        player = MediaPlayer.create(applicationContext,R.raw.press_start)
        player!!.isLooping = false
        player!!.start()

        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE
        if(restProgressTimer!=null){
            restProgressTimer!!.cancel()
            restProgress = 0
        }
        next_exe_name.text = exerciseList!![currentPosotion+1].getName()

        setRestProgressBar()


    }



    private fun setExerciseProgressBar(){
        progressExerciseBar.progress = exerciseProgress
        exerciseProgressTimer = object :CountDownTimer(exerciseTimeAmount*1000,1000){
            override fun onTick(exerciseTime: Long) {
                exerciseProgress++
                progressExerciseBar.progress = exerciseTimeAmount.toInt()-exerciseProgress
                tvExerciseTimer.text= (exerciseTimeAmount.toInt()-exerciseProgress).toString()
//                speakOut(tvExerciseTimer.text.toString())
            }

            override fun onFinish() {
                if(currentPosotion < (exerciseList.size)-1) {
                    exerciseList!![currentPosotion].isCompleted(true)
                    exerciseList!![currentPosotion].setisSelected(false)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else{
                    finish()
                    val intent = Intent(this@ExerciseActivity,FinishActivity::class.java)
                    startActivity(intent)
                }
            }
        }.start()

    }

    private fun setupExerciseView(){
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE
        if(exerciseProgressTimer!=null){
            exerciseProgressTimer!!.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()
        speakOut(exerciseList[currentPosotion].getName())
        tv_image.setImageResource(exerciseList[currentPosotion].getImage())
        tv_text_name.text = exerciseList[currentPosotion].getName()


    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            var result = tts!!.setLanguage(Locale.US)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"Your device is not supported text to speech",Toast.LENGTH_LONG).show()
            }else{
                speakOut(textForSpeech.toString())
            }
        }else{
            Toast.makeText(this,"Initialization failed",Toast.LENGTH_SHORT).show()
        }
    }

    private fun speakOut(text :String){
        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }

    private fun setupExerciseStatusRecyclerView(){
        exerciseAdapter = ExerciseStatusAdapter(this,exerciseList!!)
        rvExerciseStatus.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun backIconDialog(){
        val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_back_comfirmation)
        customDialog.tvYes.setOnClickListener{
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener{
            customDialog.dismiss()
        }

        customDialog.show()
    }


}