package com.pjatk.lab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_recipe.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecipeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecipeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val ppmViewArgs: RecipeFragmentArgs? by navArgs()
    private var ppm: Double? = null
    private var imageView: ImageView? = null
    private var recipeDesc: TextView? = null
    private var recipeIngr: TextView? = null
    private var showRecipeBtn: Button? = null
    private var isRecipeDisplayed: Boolean = false

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
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    // Best to use this why? : https://stackoverflow.com/questions/25119090/difference-between-oncreateview-and-onviewcreated-in-fragment
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRecipeBtn = button_show_recipe
        imageView = imageView_photo
        recipeDesc = textView_description
        recipeIngr = textView_ingredients

        button_show_recipe.setOnClickListener {
            displayRecipeConditional()
            isRecipeDisplayed = !isRecipeDisplayed
        }
        button_back_recipe.setOnClickListener {
            findNavController().navigate(R.id.action_recipeFragment_to_PPMFragment)
        }

        catchIncomingActionParamsFromPPM()
        displayPpmValue()

    }

    private fun displayRecipeConditional(){
        if(ppm != null && ppm != 0.0) {
            if( !isRecipeDisplayed ){
                imageView?.visibility = View.VISIBLE
                recipeDesc?.visibility = View.VISIBLE
                recipeIngr?.visibility = View.VISIBLE
                isRecipeDisplayed = false
                showRecipeBtn?.text = "Hide Recipe"

            }
            else {
                isRecipeDisplayed = true
                showRecipeBtn?.text = "Show Recipe"
                imageView?.visibility = View.INVISIBLE
                recipeDesc?.visibility = View.INVISIBLE
                recipeIngr?.visibility = View.INVISIBLE
            }
        }

    }
    private fun catchIncomingActionParamsFromPPM(){
        // importowanie danych z argumentów
        ppm = if (ppmViewArgs?.ppm == "no_value") 0.0 else ppmViewArgs!!.ppm.toDouble()
    }

    private fun displayPpmValue(){
        textView_ppm_result.text = ppm.toString()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RecipeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RecipeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
