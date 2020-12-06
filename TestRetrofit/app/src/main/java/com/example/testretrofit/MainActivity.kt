package com.example.testretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.testretrofit.Retrofit.InterfaceApiRetrofit
import com.example.testretrofit.Retrofit.RetrofitConfig
import com.example.testretrofit.Retrofit.StarWarsApiItem
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.io.IOException

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            btn_peticion.setOnClickListener {
                okHttp3Api()
            }


    }

    private fun RestApiPeticion() {
        val interfas: InterfaceApiRetrofit = RetrofitConfig.ConfRetrofit().create(InterfaceApiRetrofit::class.java)
        val result: Call<List<StarWarsApiItem>> = interfas.GetAllResponse()
        result.enqueue(object : Callback<List<StarWarsApiItem>>{
            override fun onResponse(
                call: Call<List<StarWarsApiItem>>,
                response: Response<List<StarWarsApiItem>>
            ) {
                Log.d("ok", response.body().toString())
            }

            override fun onFailure(call: Call<List<StarWarsApiItem>>, t: Throwable) {
                Log.d("Fallo",t.message.toString())
            }

        })
    }

    private fun okHttp3Api(){
        val url = "https://swapi.dev/api/people/10/"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d("fallo",e.message.toString())
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                response.use {
                    if (response.isSuccessful){
                        response.body()
                        it.body()
                    }else{
                        Log.d("fallo",response.message())
                    }
                }
            }

        })
    }

    //funciona
    private fun VolleyApi(){
        val queue = Volley.newRequestQueue(this@MainActivity)
        val url = "https://swapi.dev/api/people/10/"
        val StringRequest = StringRequest(com.android.volley.Request.Method.GET, url, {

            Log.d("respuesta", it)
            Log.d("respuesta", it.toString())

        }, {

            Log.d("fallo",it.message.toString())
        })
        queue.add(StringRequest)
    }


}