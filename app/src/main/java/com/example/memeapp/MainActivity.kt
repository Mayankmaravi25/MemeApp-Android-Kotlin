package com.example.memeapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.memeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
          binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadmeme()


    }
    private fun loadmeme(){
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"

// Request a string response from the provided URL.
        val JsonObjectRequest =JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                val url=response.getString("url")
                Glide.with(this).load(url).into(binding.imageview)

            },
            {
                Toast.makeText(this,"something went wrong",Toast.LENGTH_LONG).show()

            })

// Add the request to the RequestQueue.
        queue.add(JsonObjectRequest)

    }

    fun share(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
         intent.type="image/jpeg"
        intent.putExtra(Intent.EXTRA_TEXT,"check this funny meme")
        val chooser=Intent.createChooser(intent,"share this meme" )
        startActivity(chooser)



    }
    fun next(view: View) {
        loadmeme()
    }
}