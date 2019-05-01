package com.example.valyutaazunibank

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    //http://data.fixer.io/api/latest?access_key=248bddd3b227e749acc8833b772cd0e6&symbols=USD,AZN,RUB,GBP,CHF&format=1

    val KEY = "9a2bda9c4f7b8d71c72d2c334fdd34f5"
    val SYM = "EUR,AZN,RUB,GBP,CHF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ApiInterface = ApiClient.client?.create(ApiInterface::class.java)
        var ApiCall = ApiInterface?.getApi(KEY,SYM)

        ApiCall?.enqueue(object : Callback<Data>{

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Log.e("Hamid",call.toString())
                Toast.makeText(this@MainActivity,"Api ve ya Internetde problem var.",Toast.LENGTH_LONG).show()

            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                var data = response?.body()

                var valyutalar = data?.quotes

                var user = editText.text.toString().toDouble()

                var cnvrteur = Convert(valyutalar!!.usdeur,user).toString()
                var cnvrtazn = Convert(valyutalar!!.usdazn,user).toString()
                var cnvrtrub = Convert(valyutalar!!.usdrub,user).toString()
                var cnvrtgbp = Convert(valyutalar!!.usdgbp,user).toString()
                var cnvrtchf = Convert(valyutalar!!.usdchf,user).toString()


                textViewConvertEUR.text = cnvrteur
                textViewConvertAZN.text = cnvrtazn
                textViewConvertRUB.text = cnvrtrub
                textViewConvertGBP.text = cnvrtgbp
                textViewConvertCHF.text = cnvrtchf

            }

        })


    }

    fun Convert(vlyt : Double , userVLYT : Double) : Double{
        return vlyt*userVLYT
    }
}
