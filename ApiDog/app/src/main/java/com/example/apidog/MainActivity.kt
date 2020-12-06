package com.example.apidog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.apidog.Data.ApiDog
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        btn_peticion.setOnClickListener {
            val dogItem = spinner.selectedItem.toString()
            Log.d("spinner", dogItem)
            if (dogItem == "Selecciona un perro"){
                Toast.makeText(this, "Seleciona una opcion distinta", Toast.LENGTH_SHORT).show()
            }else{
                peticionVolley(dogItem)
            }

        }
    }

    private fun peticionVolley(Item: String){

        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://dog.ceo/api/breed/${Item}/images/random/3/alt"
        val solicitud = StringRequest(Request.Method.GET,url, { result ->

            Log.d("respuesta",result)


            val gson = Gson()
            val dog = gson.fromJson(result, ApiDog::class.java)
            Log.d("gson", dog.message!!.get(2).url)
            Log.d("gson", dog.message!!.get(3).altText)
            

        }, {  error ->
            Log.d("error",error.message.toString())
        })
        queue.add(solicitud)
    }
}