package com.pjatk.lab4

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, getString(R.string.snackbar_main_message), Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {

            R.id.menu_main -> {
                Log.i("tag", "menu clicked")
                return true
            }
            R.id.menu_bmi -> {
                Log.i("tag","bmi clicked")
                return true
            }
            R.id.menu_ppm -> {
                Log.i("tag","ppm clicked")
                return true
            }
            R.id.menu_graph -> {
                Log.i("tag","graph clicked")
                return true
            }
            R.id.action_settings -> {
                Log.i("tag","settings clicked")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }




    }
}
