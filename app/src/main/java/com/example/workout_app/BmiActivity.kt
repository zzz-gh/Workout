package com.example.workout_app

import android.icu.lang.UCharacter
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_bmi.*
import kotlinx.android.synthetic.main.activity_finish.*
import kotlinx.android.synthetic.main.activity_finish.toolbar_exercise_activity
import java.math.BigDecimal
import java.math.RoundingMode

class BmiActivity : AppCompatActivity() {
    private val metric_unit_view = "Metric Unit View"
    private val us_unit_view = "US Unit View"

    private var currentBMIView = metric_unit_view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        setSupportActionBar(toolbar_exercise_activity)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar!!.title = "Calculate Me"
        }

        toolbar_exercise_activity.setNavigationOnClickListener() {
            onBackPressed()
        }

        makeMetricUnitViewVisible()
        rdg.setOnCheckedChangeListener { radioGroup, i ->
            if (i == R.id.rbMetricUnits) {
                makeMetricUnitViewVisible()

            } else if (i == R.id.rbUsUnits) {
                makeUSUnitViewVisible()
            }
        }

        btn_calculate.setOnClickListener {

            if(currentBMIView == metric_unit_view){
                if (isMetricValidate()) {
                    val weight: Float = etMetricUnitWeight.text.toString().toFloat()
                    val height: Float = etMetricUnitHeight.text.toString().toFloat() / 100

                    val bmi = weight / (height * height)
                    displayBMIResult(bmi)
                } else {
                    val toast: Toast = Toast.makeText(
                        this,
                        "You weight and height must not be empty",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }else{
                if(isUSValidatte()){
                    val weight  = etUSUnitWeight.text.toString().toFloat()
                    val heightFeet = etUSUnitHeightFeet.text.toString().toFloat()
                    val heightInch = etUSUnitHeightInch.text.toString().toFloat()

                    val realHeight = heightInch + (heightFeet*12)

                    val bmi = 703*(weight/(realHeight * realHeight))
                    displayBMIResult(bmi)
                }else{
                    val toast: Toast = Toast.makeText(
                        this,
                        "You weight and height must not be empty",
                        Toast.LENGTH_SHORT
                    )
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
            }






        }

    }

    private fun makeMetricUnitViewVisible() {
        currentBMIView = metric_unit_view
        bmi_input.visibility = View.VISIBLE
        bmi_us_input.visibility = View.GONE

        etMetricUnitWeight.text!!.clear()
        etMetricUnitHeight.text!!.clear()

        tilMetricUnitWeight.visibility = View.VISIBLE
        tilMetricUnitHeight.visibility = View.VISIBLE

//        llDisplayBMIResult.visibility = View.INVISIBLE
        tvYourBMI.visibility = View.GONE
        tvBMIValue.visibility = View.GONE
        tvBMIType.visibility = View.GONE
        tvBMIDescription.visibility = View.GONE

    }

    private fun makeUSUnitViewVisible() {
        currentBMIView = us_unit_view
        bmi_input.visibility = View.GONE
        bmi_us_input.visibility = View.VISIBLE

        etUSUnitWeight.text!!.clear()
        etUSUnitHeightFeet.text!!.clear()
        etUSUnitHeightInch.text!!.clear()

        tilUSUnitWeight.visibility = View.VISIBLE
        US_height_ft_in.visibility = View.VISIBLE

        tvYourBMI.visibility = View.GONE
        tvBMIValue.visibility = View.GONE
        tvBMIType.visibility = View.GONE
        tvBMIDescription.visibility = View.GONE


    }

    private fun isMetricValidate(): Boolean {
        var isValid = true
        if (etMetricUnitWeight.text.toString().isEmpty()) {
            isValid = false
        } else if (etMetricUnitHeight.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid

    }

    private fun isUSValidatte(): Boolean {
        var isValid = true
        when {
            etUSUnitWeight.text.toString().isEmpty() -> { isValid = false }
            etUSUnitHeightFeet.text.toString().isEmpty() -> { isValid = false }
            etUSUnitHeightInch.text.toString().isEmpty() -> { isValid = false }

        }
        return isValid
    }

    private fun displayBMIResult(bmi: Float) {
        var bmiLabel: String? = null
        var bmiDescription: String? = null

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! you really need to more take care of yourself! eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (java.lang.Float.compare(bmi, 25f) > 0 && java.lang.Float.compare(
                bmi,
                30f
            ) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0
        ) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE


        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_UP).toString()

        tvBMIValue.text = bmiValue
        tvBMIType.text = bmiLabel
        tvBMIDescription.text = bmiDescription

    }


}