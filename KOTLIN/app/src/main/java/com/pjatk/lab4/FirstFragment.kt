package com.pjatk.lab4

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    val args: FirstFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.calculate_bmi_button).setOnClickListener {
            //findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            val showCountTextView = view.findViewById<TextView>(R.id.textView_title_graph)
            val currentCount = showCountTextView.text.toString().toInt()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(currentCount)
            Log.i("TAG!", "Wartosc argumentu $currentCount")
            findNavController().navigate(action)
        }
        view.findViewById<Button>(R.id.toast_button).setOnClickListener {
            val myToast = Toast.makeText(context, getString(R.string.hello_toast_text), Toast.LENGTH_SHORT)
            myToast.show()
        }
        view.findViewById<Button>(R.id.count_button).setOnClickListener {
            countMe(view)
        }
    }

    private fun countMe(view: View){
        // Get the text view
        val showCountTextView = view.findViewById<TextView>(R.id.textView_title_graph)
        val countString = showCountTextView.text.toString()
        var count = countString.toInt()
        count++

        showCountTextView.text = count.toString()
    }

}
