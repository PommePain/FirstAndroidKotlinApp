package com.example.exo3

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class BrandsPhoneActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.brand_phones_page)

        val brandName : String ? = intent.extras?.getString("brandName", "Pas trouvé")
        val brandNameText = findViewById<TextView>(R.id.tv_brand_page_name)
        brandNameText.text = brandName

        var detail : String ? = intent.extras?.getString("detail", "Inconnu")
        if (detail != null) {
            detail = detail.replace("http", "https")
        }
        val detailText = findViewById<TextView>(R.id.tv_brand_page_url_phones)
        detailText.text = detail.toString()

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, detail.toString(),
            { response ->
                val phones = mutableListOf<Phone>()
                val global = JSONObject(response)
                val data = global.getJSONObject("data")
                val phonesData = data.getJSONArray("phones")

                for (i in 0..<phonesData.length()) {
                    val currentJsonObj = phonesData.getJSONObject(i)
                    val m = Phone(
                        currentJsonObj.getString("brand"),
                        currentJsonObj.getString("phone_name"),
                        currentJsonObj.getString("slug"),
                        currentJsonObj.getString("image"),
                        currentJsonObj.getString("detail")
                    )
                    phones.add(m)
                }

                val lv = findViewById<ListView>(R.id.lv_brand_phones)
                val adap = PhoneAdapter(this, phones)
                lv.adapter = adap
            },
            { error ->
                Toast.makeText(applicationContext, error.message, Toast.LENGTH_LONG).show()
            }
        )
        queue.add(stringRequest);
    }
}