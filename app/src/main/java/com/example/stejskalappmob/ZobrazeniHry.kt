package com.example.stejskalappmob

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_zobrazeni_hry.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.lang.Exception


class ZobrazeniHry : AppCompatActivity() {
    private var zobrazenititulek: TextView? = null
    private var zobrazenitext: TextView? = null
    private var imageView: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zobrazeni_hry)
        supportActionBar?.hide()
        zobrazenititulek = findViewById(R.id.nazev)
        zobrazenitext = findViewById(R.id.texthry)
        getCurrentData()
        val buttonzpet = findViewById<Button>(R.id.zpet)
        buttonzpet.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)

        }

    }

    internal fun getCurrentData() {

        var message = intent.getStringExtra(EXTRA_MESSAGE)
        val textView = findViewById<TextView>(R.id.nazev).apply {
            text = message
        }
        val retrofit = Retrofit.Builder()
            .baseUrl(MainActivity.BaseUrl)
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
                    try {
                        var obdrzenyseznam: List<com.example.stejskalappmob.Response>? =
                            response.body() as List<com.example.stejskalappmob.Response>
                        var obdrzeny: Array<String?> = arrayOfNulls<String>(obdrzenyseznam!!.size) // celkem 350 záznamů
                        var i: Int = 0
                        if (textView.text.toString() == "") {
                            i = (1..obdrzenyseznam!!.size-1).random() // RNG

                        } else {
                            obdrzeny[i] = obdrzenyseznam!![i]!!.title
                            var textposouzeni = obdrzeny[i]
                            while (textView.text != textposouzeni && i < 349) {
                                i++
                                obdrzeny[i] = obdrzenyseznam!![i]!!.title
                                textposouzeni = obdrzeny[i]
                            }
                        }
                        obdrzeny[i] = obdrzenyseznam!![i]!!.title
                        zobrazenititulek!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.short_description
                        zobrazenitext!!.text = obdrzeny[i]
                        if (obdrzenyseznam[i].thumbnail != null) {
                            imageView = findViewById<ImageView>(R.id.imageView)
                            var image = obdrzenyseznam!![i]!!.thumbnail
                            Picasso.with(this@ZobrazeniHry).load(image).into(imageView)
                        }
                        obdrzeny[i] = obdrzenyseznam!![i]!!.publisher
                        vydavatel!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.developer
                        tvurce!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.release_date
                        datum!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.freetogame_profile_url
                        odkaznahrufreetoplay!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.game_url
                        odkaznahru!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.genre
                        zanr!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.platform
                        platforma!!.text = obdrzeny[i]
                        obdrzeny[i] = obdrzenyseznam!![i]!!.id
                        id!!.text = obdrzeny[i]
                        val nazevsouboru = "data.txt" // implementovat
                        val radek = File(nazevsouboru)
                        radek.printWriter().use {
                            out->
                            out.println("ahoj")
                        }

                    }
                    catch (e:Exception)
                    {
                    }
                    finally {

                    }
                }
            }

            override fun onFailure(
                call: Call<List<com.example.stejskalappmob.Response>>,
                t: Throwable
            ) {
                zobrazenitext!!.text = t.message
            }
        })
    }
        companion object {
            var BaseUrl = "https://www.freetogame.com/"
        }
    }