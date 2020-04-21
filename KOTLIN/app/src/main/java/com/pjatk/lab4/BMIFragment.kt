package com.pjatk.lab4

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlin.math.pow
import kotlin.math.round
import androidx.navigation.fragment.navArgs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BMIFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BMIFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var weightInput: EditText? = null
    private var heightInput: EditText? = null
    private var resultTextViewDescription: TextView? = null
    private var resultTextViewScore: TextView? = null
    private var weight: Double? = null
    private var height: Double? = null
    private var bmi: Double? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bmi, container, false)
    }

    // Best to use this why? : https://stackoverflow.com/questions/25119090/difference-between-oncreateview-and-onviewcreated-in-fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weight = 0.0
        height = 0.0
        bmi = 0.0

        weightInput = view.findViewById(R.id.textinput_weight_bmi)
        heightInput = view.findViewById(R.id.textinput_height_bmi)
        resultTextViewDescription = view.findViewById(R.id.textview_result_bmi)
        resultTextViewScore = view.findViewById(R.id.textview_result_score_bmi)

        view.findViewById<Button>(R.id.back_button_bmi).setOnClickListener {
            // just navigate back
            //findNavController().navigate(R.id.action_BMIFragment_to_menuFragment)
            // navigate with params
            val action = BMIFragmentDirections.actionBMIFragmentToMenuFragment(bmi.toString(),weight.toString(),height.toString())
            // trigger action
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.calculate_bmi_button).setOnClickListener {
            calculateBmi()
        }

    }

    private fun calculateBmi(){
        var weightInputTxt: String = weightInput?.text.toString()
        var heightInputTxt: String = heightInput?.text.toString()

        // assigning input decimal
        weight = if (weightInputTxt.isEmpty()) 0.0
        else weightInputTxt.toDouble()
        height = if (heightInputTxt.isEmpty()) 0.0
        else heightInputTxt.toDouble()

        // bmi calculation
        bmi =  if(height!! > 0.0) weight!! / (height!! / 100).pow(2.0)
        else 0.0

        // rounding bmi result
        bmi = bmi!!.round(2)

        // display
        displayBmiToResult()
    }

    // rounding double to appropriate number of places after dot
    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }

    private fun displayBmiToResult(){
        var answer: String = if(bmi!! < 16)"starvation"
        else if(bmi!! >= 16 && bmi!! < 17 ) "extra skinny"
        else if(bmi!! >= 17 && bmi!! < 18.5 ) "slim-shady"
        else if(bmi!! >= 18.5 && bmi!! < 24.5) "perfect weight"
        else if(bmi!! >= 24.5 && bmi!! < 30) "obesity beginner"
        else if(bmi!! >= 30 && bmi!! < 35) "obesity I level"
        else if(bmi!! >= 35 && bmi!! < 40) "obesity II level (be aware)"
        else if(bmi!! >= 40) "obesity III level (master)"
        else "BMI \\n ${bmi.toString()}: Error! You can't be so fat"
        resultTextViewScore?.textSize = 32F
        resultTextViewScore?.text = bmi.toString()
        resultTextViewDescription?.text = answer
        setColorBasedOnBodyForm(resultTextViewScore)
        setColorBasedOnBodyForm(resultTextViewDescription)
    }


    private fun setColorBasedOnBodyForm( textView: TextView?){
        if (bmi!! < 24.5) textView?.setTextColor(Color.GREEN)
        else if(bmi!! >=24.5 && bmi!! < 35) textView?.setTextColor(Color.YELLOW)
        else textView?.setTextColor(Color.RED)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BMIFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BMIFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
