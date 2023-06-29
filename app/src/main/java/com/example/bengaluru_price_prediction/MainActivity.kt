package com.example.bengaluru_price_prediction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.bengaluru_price_prediction.databinding.ActivityMainBinding
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.predict.setOnClickListener {

            val queue = Volley.newRequestQueue(this@MainActivity)

            val request: StringRequest =
                object : StringRequest(Method.POST, url, object : Response.Listener<String?> {
                    override fun onResponse(response: String?) {
                        try {
                            val jsonObject = JSONObject(response!!)
                            binding.price.text = jsonObject.getString("price")
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }, object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError?) {
                        // displaying toast message on response failure.
                        Log.e("tag", "error is " + error!!.message)
                        Toast.makeText(this@MainActivity, "Fail to update data..", Toast.LENGTH_SHORT)
                            .show()
                    }
                }) {
                    override fun getParams(): Map<String, String> {

                        val params: MutableMap<String, String> = HashMap()

                        params["bhk"] = binding.bhk.text.toString()
                        params["totalsqft"] = binding.totalSqft.text.toString()
                        params["bath"] = binding.bath.text.toString()

                        return params
                    }
                }
            queue.add(request)
        }
    }
}