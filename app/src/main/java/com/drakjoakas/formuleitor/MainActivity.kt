package com.drakjoakas.formuleitor

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.drakjoakas.formuleitor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerFormula.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
            {
                Log.d("SPINNER",parent?.getItemAtPosition(position).toString())
                Log.d("SPINNER",position.toString())
                binding.imageView.setImageResource(getImageFormula(position))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                print("Hola Mundo")
            }

            fun getImageFormula(index: Int): Int {
                return when (index) {
                    0 -> {
                        R.drawable.velocidad
                    }
                    1 -> {
                        R.drawable.aceleracion
                    }
                    2 -> {
                        R.drawable.fuerza
                    }
                    else -> {
                        0
                    }
                }


            }

            fun showFields(index: Int) {
                when (index) {
                    0 -> {
                        binding.distancia.visibility = View.VISIBLE
                        binding.tiempo.visibility    = View.VISIBLE
                        binding.velocidad.visibility = View.INVISIBLE
                    }
                    1 -> {
                        binding.distancia.visibility = View.INVISIBLE
                        binding.tiempo.visibility    = View.VISIBLE
                        binding.velocidad.visibility = View.VISIBLE
                    }
                    2 -> {
                        binding.distancia.visibility = View.INVISIBLE
                        binding.tiempo.visibility    = View.INVISIBLE
                        binding.velocidad.visibility = View.INVISIBLE
                    }
                    else -> {
                        return
                    }
                }
            }
        }
    }


}