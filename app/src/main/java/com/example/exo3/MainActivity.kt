package com.example.exo3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val launchButton = findViewById<Button>(R.id.btn_launch)
        val statusText = findViewById<TextView>(R.id.status_text)
        launchButton.setOnClickListener() {
            val queue = Volley.newRequestQueue(this)
            val url = "https://phone-specs-api.vercel.app/brands"

            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    statusText.text = "Response is success"
                    val brands = mutableListOf<Brand>()
                    val global = JSONObject(response)
                    val brandsData = global.getJSONArray("data")

                    for (i in 0..<brandsData.length()) {
                        val currentJsonObj = brandsData.getJSONObject(i)
                        val m = Brand(
                            currentJsonObj.getInt("brand_id"),
                            currentJsonObj.getString("brand_name"),
                            currentJsonObj.getString("brand_slug"),
                            currentJsonObj.getInt("device_count"),
                            currentJsonObj.getString(("detail"))
                        )
                        brands.add(m)
                    }

                    val lv = findViewById<ListView>(R.id.lv_brand)
                    lv.setOnItemClickListener() {parent, view, position, id ->
                        val selectedBrand = brands.get(position)
                        val i = Intent(this, BrandsPhoneActivity::class.java)
                        i.putExtra("detail", selectedBrand.getDetail())
                        i.putExtra("brandName", selectedBrand.getBrandName())
                        startActivity(i)
                    }
                    val adap = BrandAdapter(this, brands)
                    lv.adapter = adap
                },
                { error ->
                    statusText.text = "That didn't work!"
                    Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show()
                }
            )

            queue.add(stringRequest);
        }
    }
}