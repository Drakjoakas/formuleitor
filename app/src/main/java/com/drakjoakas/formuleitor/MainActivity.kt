package com.drakjoakas.formuleitor

import android.content.Intent
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
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
                showFields(position)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                print("Hola Mundo")
            }


        }
    }

    override fun onRestart() {
        super.onRestart()

        binding.distancia.text.clear()
        binding.tiempo.text.clear()
        binding.velocidad.text.clear()

        binding.masa.text.clear()
        binding.aceleracion.text.clear()
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

        binding.distancia.visibility   = if(index == 0)  View.VISIBLE else View.INVISIBLE
        binding.tiempo.visibility      = if (index == 0 || index == 1) View.VISIBLE else View.INVISIBLE
        binding.velocidad.visibility   = if(index == 1)  View.VISIBLE else View.INVISIBLE
        binding.aceleracion.visibility = if(index == 2)  View.VISIBLE else View.INVISIBLE
        binding.masa.visibility        = if(index == 2)  View.VISIBLE else View.INVISIBLE

    }

    fun calculate(view: View) {
        val selItem = binding.spinnerFormula.selectedItemPosition
        if (dataIsValid(selItem)) {
            val intent = Intent(this,Resultados::class.java)
            val bundle = loadData(selItem)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        //intent.putExtra
    }

    fun dataIsValid(index: Int): Boolean {
        when (index) {
            0 -> {  //Validaciones Velocidad


                if (binding.distancia.text.isEmpty() || binding.tiempo.text.isEmpty()) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_empty),Toast.LENGTH_LONG).show()

                    binding.distancia.text.isEmpty().apply {
                        binding.distancia.requestFocus()
                    }
                    binding.tiempo.text.isEmpty().apply {
                        binding.tiempo.requestFocus()
                    }
                    return false
                }

                val distancia: Int = binding.distancia.text.toString().toInt()
                val tiempo: Int    = binding.tiempo.text.toString().toInt()

                if (tiempo == 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_numerator_cero),Toast.LENGTH_LONG).show()
                    return false
                }

                if (tiempo < 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_negative_time),Toast.LENGTH_LONG).show()
                    return false
                }

                if (distancia < 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_negative_distance),Toast.LENGTH_LONG).show()
                    return false
                }

                return true

            }
            1 -> { //Validaciones Aceleración


                if (binding.velocidad.text.isEmpty() || binding.tiempo.text.isEmpty()) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_empty),Toast.LENGTH_LONG).show()

                    binding.velocidad.text.isEmpty().apply {
                        binding.velocidad.requestFocus()
                    }
                    binding.tiempo.text.isEmpty().apply {
                        binding.tiempo.requestFocus()
                    }
                    return false
                }

                val velocidad: Int = binding.velocidad.text.toString().toInt()
                val tiempo: Int    = binding.tiempo.text.toString().toInt()

                if (tiempo == 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_numerator_cero),Toast.LENGTH_LONG).show()
                    return false
                }

                if (tiempo < 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_negative_time),Toast.LENGTH_LONG).show()
                    return false
                }

                if (velocidad < 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_negative_velocity),Toast.LENGTH_LONG).show()
                    return false
                }
                return true
            }
            2 -> { // validaciones fuerza


                if (binding.masa.text.isEmpty() || binding.aceleracion.text.isEmpty()) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_empty),Toast.LENGTH_LONG).show()

                    binding.masa.text.isEmpty().apply {
                        binding.masa.requestFocus()
                    }
                    binding.aceleracion.text.isEmpty().apply {
                        binding.aceleracion.requestFocus()
                    }
                    return false
                }

                val masa: Int           = binding.masa.text.toString().toInt()
                val aceleracion: Int    = binding.aceleracion.text.toString().toInt()

                if (masa < 0) {
                    Toast.makeText(this@MainActivity,getString(R.string.error_negative_mass),Toast.LENGTH_LONG).show()
                    return false
                }

                return true
            }
            else -> {
                return false
            }
        }
    }

    fun loadData(index: Int): Bundle {
        val bundle = Bundle()
        bundle.putInt("index",index)
        when (index) {
            0 -> {
                bundle.putFloat("distancia",binding.distancia.text.toString().toFloat())
                bundle.putFloat("tiempo",binding.tiempo.text.toString().toFloat())
            }
            1 -> {
                bundle.putFloat("velocidad",binding.velocidad.text.toString().toFloat())
                bundle.putFloat("tiempo",binding.tiempo.text.toString().toFloat())
            }
            2 -> {
                bundle.putFloat("masa",binding.masa.text.toString().toFloat())
                bundle.putFloat("aceleracion",binding.aceleracion.text.toString().toFloat())
            }
        }
        bundle.putFloat("resultado",getResult(index))
        return bundle
    }

    fun getResult(index:Int): Float {
        val res: Float
         when(index) {
            0 -> {
                val distancia = binding.distancia.text.toString().toFloat()
                val tiempo    = binding.tiempo.text.toString().toFloat()
                res = distancia/tiempo
            }

            1 -> {
                val velocidad = binding.velocidad.text.toString().toFloat()
                val tiempo    = binding.tiempo.text.toString().toFloat()
                res = velocidad/tiempo
            }

            2 -> {
                val masa        = binding.masa.text.toString().toFloat()
                val aceleracion = binding.aceleracion.text.toString().toFloat()
                res = masa*aceleracion
            }

            else -> {
                 res = (-1.0).toFloat()
            }
        }
        return res
    }
}