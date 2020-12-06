package com.example.apidogretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apidogretrofit.Retrofit.DogInterfaces
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        dogs_recycler.layoutManager = GridLayoutManager(this, 2)
        dogs_recycler.adapter = DogAdapter()

        Peticcion.setOnClickListener {
            val dogItem = spinner_item.selectedItem.toString()
            if (dogItem == "Seleccionar un Item") {
                Toast.makeText(this, "Selecciona otra opcion", Toast.LENGTH_SHORT).show()
            }else {
                SolicitudRetrofit(dogItem)
            }
        }
    }

    private fun SolicitudRetrofit(Item: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DogInterfaces::class.java)
        val soli = service.ListDogs()
        soli.enqueue(object : Callback<List<Dog>>{
            override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {
                response.body()?.let { result ->
                    (dogs_recycler.adapter as DogAdapter).setDogList(result)
                    Log.d("result", result.toString())

                }
            }

            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                call.cancel()
            }

        })

    }
}