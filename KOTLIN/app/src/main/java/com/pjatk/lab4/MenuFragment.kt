package com.pjatk.lab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_menu.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    // My variables
    private val bmiViewArgs: MenuFragmentArgs? by navArgs()
    private var bmiTextViewScore: TextView? = null
    private var ppmTextViewTitle: TextView? = null
    private var genderTextViewScore: TextView? = null
    private var bmiResult: Double? = null
    private var ppmResult: Double? = null
    private var weight: Double? = null
    private var height: Double? = null
    private var age: Double? = null
    private var sex: Boolean? = null


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
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // init text views
        bmiTextViewScore = textView_bmi_score
        ppmTextViewTitle = textView_ppm_score
        genderTextViewScore = textView_sex_score

        // Body buttons nav
        view.findViewById<Button>(R.id.bmi_button).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_BMIFragment)
        }
        view.findViewById<Button>(R.id.ppm_button).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_PPMFragment)
        }
        view.findViewById<Button>(R.id.graph_button).setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_graphFragment)
        }

        catchIncomingActionParamsFromBMI()
        displayResults()
    }

    private fun catchIncomingActionParamsFromBMI(){
        // importowanie danych z argumentów
        bmiResult = if (bmiViewArgs?.bmi == "no_result") 0.0 else bmiViewArgs!!.bmi.toDouble()
        weight = if (bmiViewArgs?.weight == "no_result") 0.0 else bmiViewArgs!!.weight.toDouble()
        height = if (bmiViewArgs?.height == "no_result") 0.0 else bmiViewArgs!!.height.toDouble()

    }

    private fun displayResults(){
        if(bmiResult != 0.0) bmiTextViewScore?.text = bmiResult.toString()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
