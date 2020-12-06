package com.example.apidogretrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_list_dog.view.*
import java.security.interfaces.DSAKey

class DogAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var lista: List<Dog> = emptyList()
    fun setDogList(list: List<Dog>){
            this.lista = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_dog,parent, false)

        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val Dlista = lista[position]

        holder.itemView.nombre.text = Dlista.message.get(0).altText
        Glide.with(holder.itemView.context).load(Dlista.message.get(1).url).into(holder.itemView.images)
    }

    override fun getItemCount(): Int = lista.size
    inner class DogViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}