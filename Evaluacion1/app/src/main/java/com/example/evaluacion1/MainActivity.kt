package com.example.evaluacion1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.evaluacion1.turestaurante.ItemMenu
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
//Creacion de variables cantidad de items, precios y switch de propina.
    private lateinit var edtCantidadPastel: EditText
    private lateinit var edtCantidadCazuela: EditText
    private lateinit var txtPrecioPastel: TextView
    private lateinit var txtPrecioCazuela: TextView
    private lateinit var txtSubtotalComida: TextView
    private lateinit var txtValorPropina: TextView
    private lateinit var txtTotalFinal: TextView
    private lateinit var switchPropina: Switch
//Creacion de los objetos ItemMenu asignandole nombres y correspondiente precio.
    private val pastelDeChoclo = ItemMenu("Pastel de Choclo", 12000)
    private val cazuela = ItemMenu("Cazuela", 10000)

    // NumberFormat configurado para Chile
    private val formatoPesos: NumberFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtCantidadPastel = findViewById(R.id.edtCantidadPastel)
        edtCantidadCazuela = findViewById(R.id.edtCantidadCazuela)
        txtPrecioPastel = findViewById(R.id.txtPrecioPastel)
        txtPrecioCazuela = findViewById(R.id.txtPrecioCazuela)
        txtSubtotalComida = findViewById(R.id.txtSubtotalComida)
        txtValorPropina = findViewById(R.id.txtValorPropina)
        txtTotalFinal = findViewById(R.id.txtTotalFinal)
        switchPropina = findViewById(R.id.switchPropina)

        val watcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                actualizarTotales()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        edtCantidadPastel.addTextChangedListener(watcher)
        edtCantidadCazuela.addTextChangedListener(watcher)
        switchPropina.setOnCheckedChangeListener { _, _ -> actualizarTotales() }
    }

    private fun actualizarTotales() {
        val cantidadPastel = edtCantidadPastel.text.toString().toIntOrNull() ?: 0
        val cantidadCazuela = edtCantidadCazuela.text.toString().toIntOrNull() ?: 0

        val subtotalPastel = pastelDeChoclo.precio * cantidadPastel
        val subtotalCazuela = cazuela.precio * cantidadCazuela
        val subtotalComida = subtotalPastel + subtotalCazuela

        val propina = if (switchPropina.isChecked) (subtotalComida * 0.1).toInt() else 0
        val totalFinal = subtotalComida + propina

        // Mostrar valores con formato de pesos chilenos
        txtPrecioPastel.text = formatoPesos.format(subtotalPastel)
        txtPrecioCazuela.text = formatoPesos.format(subtotalCazuela)
        txtSubtotalComida.text = formatoPesos.format(subtotalComida)
        txtValorPropina.text = formatoPesos.format(propina)
        txtTotalFinal.text = formatoPesos.format(totalFinal)
    }
}


