package com.example.testapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.JsonRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private val listaPokemones: ArrayList<DataPokeApi> = ArrayList()
    private val AdapterPokemones = PokeAdapter(listaPokemones)
    //private var requestQueue = Volley.newRequestQueue(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        peticionVolley.setOnClickListener {

            PeticionVolley()
            RCV_pokemon.layoutManager = LinearLayoutManager(this)
            RCV_pokemon.adapter = AdapterPokemones
        }
    }

    private fun PeticionVolley() {
        val requestQueue= Volley.newRequestQueue(this)
        val url = "https://pokeapi.co/api/v2/pokemon"
        val request = JsonObjectRequest(Request.Method.GET, url, null, {
                 result ->
            try {
                val jsonArray = result.getJSONArray("results")
                listaPokemones.clear()
                for (i in 0 until jsonArray.length()){
                    val lista = jsonArray.getJSONObject(i)
                    val name = lista.getString("name")
                    val urlpoke = lista.getString("url")
                    Log.d("objetos", lista.toString())
                    Log.d("name", name)
                    Log.d("url", urlpoke)
                    listaPokemones.add(DataPokeApi(name,urlpoke))
                }
                AdapterPokemones.notifyDataSetChanged()
            }catch (e:JSONException){
                Log.d("error", e.message.toString())
            }

        }, {
            Log.d("error", it.message.toString())
        })
        requestQueue?.add(request)
    }

}