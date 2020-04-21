package com.pjatk.lab4

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_graph.*

/*
 * Kotlin - graphs tut.
 * https://medium.com/@yilmazvolkan/kotlinlinecharts-c2a730226ff1
 */
// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GraphFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GraphFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        return inflater.inflate(R.layout.fragment_graph, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.back_button_graph).setOnClickListener {
            findNavController().navigate(R.id.action_graphFragment_to_menuFragment)
        }

        initGraph()

    }

    private fun initGraph(){
        //Part1
        val entries = ArrayList<Entry>()

        //Part2
        entries.add(Entry(12f, 27f))
        entries.add(Entry(12f, 24f))
        entries.add(Entry(13f, 13f))
        entries.add(Entry(14f, 18f))
        entries.add(Entry(15f, 23f))
        entries.add(Entry(16f, 28f))
        entries.add(Entry(17f, 18f))
        entries.add(Entry(18f, 15f))
        entries.add(Entry(19f, 13f))
        entries.add(Entry(20f, 20f))

        //Part3
        val vl = LineDataSet(entries, "Death toll in Poland - COVID - 10 consecutive days of April (X:20 => 20.04.2020)")

        //Part4
        vl.setDrawValues(false)
        vl.setDrawFilled(true)
        vl.lineWidth = 3f
        vl.fillColor = R.color.colorGray
        vl.fillAlpha = R.color.colorRed

        //Part5
        lineChart.xAxis.labelRotationAngle = 0f

        //Part6
        lineChart.data = LineData(vl)

        //Part7
        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.axisMaximum = 20+0.1f

        //Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        //Part9
        lineChart.description.text = "Days of April"
        lineChart.setNoDataText("No more deaths yet")

        //Part10
        lineChart.animateX(1800, Easing.EaseInExpo)

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GraphFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GraphFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
