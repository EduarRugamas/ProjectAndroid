package com.example.testapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokeAdapter constructor(
    private val ListaPokemones: ArrayList<DataPokeApi>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokemon, parent, false)
        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PokemonViewHolder).bind(ListaPokemones[position])
    }

    override fun getItemCount(): Int = ListaPokemones.size

    inner class PokemonViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){
            fun bind (Lpokemones: DataPokeApi){
                itemView.name_pokemon.text = Lpokemones.name
                itemView.url_pokemon.text = Lpokemones.url
            }
    }
}