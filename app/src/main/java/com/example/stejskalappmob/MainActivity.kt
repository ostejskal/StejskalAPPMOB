package com.example.stejskalappmob

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val EXTRA_MESSAGE = "pametzjistitvice"

class MainActivity : AppCompatActivity() {
    private var titulek: TextView? = null
    /*public var pametnavstev: Array<Int?> = arrayOfNulls<Int>(999)
    public var pocitadlo: Int = 0*/ // testování paměti
    public var zkouska: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        titulek = findViewById(R.id.textview2)
        findViewById<View>(R.id.button).setOnClickListener { getCurrentData()
        if(zkouska==true) {
            zjistitvice.visibility = View.VISIBLE
        }}
        val buttonrng = findViewById<Button>(R.id.button2)
        buttonrng.setOnClickListener{
            val editText2 = findViewById<EditText>(R.id.vstupuser)
            val message2 = editText2.text.toString()
            val intent2 = Intent(this, ZobrazeniHry::class.java).apply {
                putExtra(EXTRA_MESSAGE, message2)
            }
            startActivity(intent2)
        }
        val buttonzjistitvice = findViewById<Button>(R.id.zjistitvice)
        buttonzjistitvice.setOnClickListener{
            val editText = findViewById<EditText>(R.id.vstupuser)
            val message = editText.text.toString()
            val intent = Intent(this, ZobrazeniHry::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        }
    }
    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(Service::class.java)
        val call = service.getCurrentData()
        call.enqueue(object : Callback<List<com.example.stejskalappmob.Response>> {
            override fun onResponse(
                call: Call<List<com.example.stejskalappmob.Response>>,
                response: Response<List<com.example.stejskalappmob.Response>>
            ) {
                if (response.code() == 200) {
                    var obdrzenyseznam: List<com.example.stejskalappmob.Response>? =
                        response.body() as List<com.example.stejskalappmob.Response>
                    var obdrzeny: Array<String?> = arrayOfNulls<String>(obdrzenyseznam!!.size)
                    for (i: Int in obdrzenyseznam!!.indices) {
                        obdrzeny[i] = obdrzenyseznam!![i]!!.title
                        var oduzivatele = vstupuser.text.toString()
                        if (obdrzeny[i] == oduzivatele && obdrzenyseznam[i] != null) {
                            var imageView: ImageView? = null
                            titulek!!.text = obdrzeny[i]
                            zkouska = true
                            imageView2.alpha = 1.0.toFloat()
                            imageView = findViewById<ImageView>(R.id.imageView2)
                            var image = obdrzenyseznam!![i]!!.thumbnail
                            Picasso.with(this@MainActivity).load(image).into(imageView)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<com.example.stejskalappmob.Response>>, t: Throwable) {
                titulek!!.text = t.message
            }
        })
    }
    companion object {
        var BaseUrl = "https://www.freetogame.com/"
    }
}