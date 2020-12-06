package com.example.examplefirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.widget.Toast
import com.example.examplefirebase.data.dataheroes
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save.setOnClickListener {
            saveItem()
        }




    }

    private fun saveItem(){
        val nameHero = edt_heroe.text.toString()
        val sexHero = edt_sex.text.toString()

        if (nameHero == "" && sexHero == ""){
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
            return
        }else{
            val database = Firebase.database.getReference("heroes")
            val heroId = database.push().key
            val item = dataheroes(heroId.toString(), nameHero, sexHero)

            database.child(heroId.toString()).setValue(item).addOnSuccessListener {

                Toast.makeText(this, "Item save sucessfull", Toast.LENGTH_SHORT).show()
                edt_heroe.setText("")
                edt_sex.setText("")

            }.addOnFailureListener {
                Toast.makeText(this, "Item not save sucessfull", Toast.LENGTH_SHORT).show()

            }

         }
        }


}