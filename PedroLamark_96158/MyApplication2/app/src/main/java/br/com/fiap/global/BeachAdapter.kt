package br.com.fiap.global


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BeachAdapter(private val praias: MutableList<Beach>, private val onExcluirClick: (Beach) -> Unit) : RecyclerView.Adapter<BeachAdapter.BeachViewHolder>() {

    inner class BeachViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomePraia: TextView = itemView.findViewById(R.id.textViewNomePraia)
        val cidade: TextView = itemView.findViewById(R.id.textViewCidade)
        val estado: TextView = itemView.findViewById(R.id.textViewEstado)
        val buttonExcluir: Button = itemView.findViewById(R.id.buttonExcluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeachViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_beach, parent, false)
        return BeachViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BeachViewHolder, position: Int) {
        val praia = praias[position]
        holder.nomePraia.text = praia.nome
        holder.cidade.text = praia.cidade
        holder.estado.text = praia.estado

        holder.buttonExcluir.setOnClickListener {
            onExcluirClick(praia)
        }
    }

    override fun getItemCount(): Int = praias.size
}