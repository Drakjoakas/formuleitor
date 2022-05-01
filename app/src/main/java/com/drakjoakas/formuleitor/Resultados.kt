package com.drakjoakas.formuleitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.drakjoakas.formuleitor.databinding.ActivityMainBinding
import com.drakjoakas.formuleitor.databinding.ActivityResultadosBinding

class Resultados : AppCompatActivity() {

    private lateinit var binding: ActivityResultadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle =  intent.extras
        val res    = bundle?.getFloat("resultado",42.0f)
        bundle?.getInt("index")?.let { loadTextOnScreen(it,res) }
    }

    fun loadTextOnScreen(index: Int,result: Float?) {
        binding.mensajeResultado.text = when(index) {
            0 -> {
                getString(R.string.result_velocity_msg)
            }
            1 -> {
                getString(R.string.result_acceleration_msg)
            }
            2 -> {
                getString(R.string.result_force_msg)
            }
            else -> {
                getString(R.string.hint_result_msg)
            }
        }

        binding.unidades.text = when(index) {
            0 -> {
                getString(R.string.result_velocity_units)
            }
            1 -> {
                getString(R.string.result_acceleration_units)
            }
            2 -> {
                getString(R.string.result_force_units)
            }
            else -> {
                getString(R.string.hint_result_units)
            }
        }

        binding.resultado.text = result?.toString() ?: getString(R.string.hint_result_res)
    }
}