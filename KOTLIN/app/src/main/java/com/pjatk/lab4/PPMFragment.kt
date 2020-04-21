package com.pjatk.lab4

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_ppm.*
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PPMFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PPMFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var genderInput: Switch? = null
    private var weightInput: EditText? = null
    private var heightInput: EditText? = null
    private var ageInput: EditText? = null
    private var calcBtn: Button? = null
    private var recipeBtn: Button? = null
    private var calcResult: TextView? = null
    private var result: TextView? = null
    private var maleTextView: TextView? = null
    private var femaleTextView: TextView? = null


    private var gender: Boolean = false
    private var weight: Double? = null
    private var height: Double? = null
    private var age: Double? = null
    private var ppm: Double? = 0.0

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
        return inflater.inflate(R.layout.fragment_ppm, container, false)
    }

    // Best to use this why? : https://stackoverflow.com/questions/25119090/difference-between-oncreateview-and-onviewcreated-in-fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weight = 0.0
        height = 0.0
        age = 0.0
        textView_man_ppm.setBackgroundColor(Color.GREEN)

        recipeBtn = recipe_button_ppm
        maleTextView = textView_man_ppm
        femaleTextView = textView_woman_ppm
        genderInput = gender_switch_ppm
        weightInput = input_weight_ppm
        heightInput = input_height_ppm
        ageInput = input_age_ppm
        calcBtn = calculate_button_ppm
        calcResult = textView_result_ppm
        result = textView_result_ppm


        view.findViewById<Switch>(R.id.gender_switch_ppm).setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Enabled
                gender = true;
                textView_man_ppm.setBackgroundColor(Color.TRANSPARENT)
                textView_woman_ppm.setBackgroundColor(Color.GREEN)
            } else {
                // Disabled
                gender = false;
                textView_man_ppm.setBackgroundColor(Color.GREEN)
                textView_woman_ppm.setBackgroundColor(Color.TRANSPARENT)
            }
        }

        view.findViewById<Button>(R.id.calculate_button_ppm).setOnClickListener {
            calculatePPM()
        }

        view.findViewById<Button>(R.id.recipe_button_ppm).setOnClickListener {
            //findNavController().navigate(R.id.action_PPMFragment_to_recipeFragment)
            val action = PPMFragmentDirections.actionPPMFragmentToRecipeFragment(ppm?.round(2).toString())
            findNavController().navigate(action)
        }

        view.findViewById<Button>(R.id.back_button_ppm).setOnClickListener {
            findNavController().navigate(R.id.action_PPMFragment_to_menuFragment)
        }

    }

    private fun calculatePPM(){
        weight = weightInput?.text.toString().toDouble()
        height = heightInput?.text.toString().toDouble()
        age = ageInput?.text.toString().toDouble()

        try {
            if(weight != 0.0 && height != 0.0 && age != 0.0){
                ppm = if(gender){
                        655.1 + 9.563 * weight!! + 1.85 * height!! - 4.676 * age!!
                    } else{
                        66.5 + 13.75 * weight!! + 5.003 * height!! - 6.775 * age!!
                    }
                result?.text = "PPM result = ${ppm?.round(2)} [Cal]"
                recipeBtn?.visibility = View.VISIBLE
            }
        }
        catch(ex : Exception){
            Log.e("PPM EX", ex.toString())
        }
    }

    // rounding double to appropriate number of places after dot
    private fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PPMFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PPMFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
