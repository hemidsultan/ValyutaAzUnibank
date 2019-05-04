package com.example.valyutaazunibank

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    //http://data.fixer.io/api/latest?access_key=248bddd3b227e749acc8833b772cd0e6&symbols=USD,AZN,RUB,GBP,CHF&format=1

    //http://apilayer.net/api/live?access_key=9a2bda9c4f7b8d71c72d2c334fdd34f5&currencies=EUR,AZN,RUB,GBP,CHF&format=1

    val KEY = "9a2bda9c4f7b8d71c72d2c334fdd34f5"
    val SYM = "EUR,AZN,RUB,GBP,CHF"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textViewTarix.text = getDate()

        var ApiInterface = ApiClient.client?.create(ApiInterface::class.java)
        var ApiCall = ApiInterface?.getApi(KEY,SYM)

        setData(ApiCall!!,"1")

        editText.addTextChangedListener(object : TextWatcher{

            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if(s.toString().length==0){

                    setData(ApiCall,"0")
                }
                else{
                    setData(ApiCall,s!!.toString())
                }

            }


        })
    }

    fun setData(ApiCall : Call<Data>,userInput : String){

        ApiCall.clone().enqueue(object : Callback<Data>{

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Api ve ya Internetde problem var.",Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<Data>, response: Response<Data>) {

                var data = response.body()

                var valyutalar = data?.quotes

                var user = userInput.toDouble()

                var cnvrteur = Convert(valyutalar!!.usdeur,user).toString()
                var cnvrtazn = Convert(valyutalar!!.usdazn,user).toString()
                var cnvrtrub = Convert(valyutalar!!.usdrub,user).toString()
                var cnvrtgbp = Convert(valyutalar!!.usdgbp,user).toString()
                var cnvrtchf = Convert(valyutalar!!.usdchf,user).toString()


                textViewConvertEUR.text = cnvrteur.toDouble().manipulator()
                textViewConvertAZN.text = cnvrtazn.toDouble().manipulator()
                textViewConvertRUB.text = cnvrtrub.toDouble().manipulator()
                textViewConvertGBP.text = cnvrtgbp.toDouble().manipulator()
                textViewConvertCHF.text = cnvrtchf.toDouble().manipulator()

            }

        })

    }


    fun Convert(vlyt : Double , userVLYT : Double) : Double{
        return vlyt*userVLYT
    }

    fun Double.manipulator() = java.lang.String.format("%.4f",this)

    fun getDate() : String{

        var data = Calendar.getInstance().time
        var set_format = SimpleDateFormat("dd.MM.yyyy",Locale("az"))
        var day =  set_format.format(data)

        return day
    }

}
