package com.drakjoakas.formuleitor

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
                id: Long) {
                Log.d("SPINNER",parent?.getItemAtPosition(position).toString())
                Log.d("SPINNER",position.toString())
                binding.imageView.setImageResource(getImageFormula(position))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                print("Hola Mundo")
            }

            fun getImageFormula(index: Int): Int {
                if (index == 0) {
                    return R.drawable.velocidad
                } else if (index == 1) {
                    return R.drawable.aceleracion
                }else if (index == 2) {
                    return R.drawable.fuerza
                } else {
                    return 0
                }


            }
        }
    }


}