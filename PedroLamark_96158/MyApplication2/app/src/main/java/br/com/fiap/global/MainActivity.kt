package br.com.fiap.global

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNomePraia: EditText
    private lateinit var editTextCidade: EditText
    private lateinit var editTextEstado: EditText
    private lateinit var buttonIncluir: Button
    private lateinit var recyclerViewPraias: RecyclerView
    private lateinit var praiaAdapter: BeachAdapter
    private val praias = mutableListOf<Beach>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNomePraia = findViewById(R.id.etNomePraia)
        editTextCidade = findViewById(R.id.etCidade)
        editTextEstado = findViewById(R.id.etEstado)
        buttonIncluir = findViewById(R.id.btnIncluir)
        recyclerViewPraias = findViewById(R.id.recyclerViewPraias)

        praiaAdapter = BeachAdapter(praias) { praia ->
            excluirPraia(praia)
        }

        recyclerViewPraias.adapter = praiaAdapter
        recyclerViewPraias.layoutManager = LinearLayoutManager(this)

        buttonIncluir.setOnClickListener {
            incluirPraia()
        }

        val buttonVerIntegrantes: Button = findViewById(R.id.buttonVerIntegrantes)
        buttonVerIntegrantes.setOnClickListener {
            val intent = Intent(this, GrupoActivity::class.java)
            startActivity(intent)
        }

    }



    private fun incluirPraia() {
        val nomeBeach = editTextNomePraia.text.toString().trim()
        val cidade = editTextCidade.text.toString().trim()
        val estado = editTextEstado.text.toString().trim()

        if (nomeBeach.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
            Toast.makeText(this, "Preecha os campos para o cadastro.", Toast.LENGTH_SHORT).show()
            return
        }

        val novaPraia = Beach(nomeBeach, cidade, estado)
        praias.add(novaPraia)
        praiaAdapter.notifyItemInserted(praias.size - 1)

        editTextNomePraia.text.clear()
        editTextCidade.text.clear()
        editTextEstado.text.clear()
    }

    private fun excluirPraia(praia: Beach) {
        val position = praias.indexOf(praia)
        if (position != -1) {
            praias.removeAt(position)
            praiaAdapter.notifyItemRemoved(position)
        }
    }
}