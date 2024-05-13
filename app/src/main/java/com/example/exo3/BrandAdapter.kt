package com.example.exo3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class BrandAdapter: BaseAdapter {
    private var context:Context
    private var lbrands:MutableList<Brand>

    constructor(context: Context, lbrands: MutableList<Brand>) : super() {
        this.context = context
        this.lbrands = lbrands
    }


    override fun getCount(): Int {
        return this.lbrands.size
    }

    override fun getItem(position: Int): Any {
        return this.lbrands.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v:View
        if (convertView == null) {
            val inflater = LayoutInflater.from(this.context)
            v = inflater.inflate(R.layout.row_brand, null)
        } else {
            v = convertView
        }

        val currentBrand = getItem(position) as Brand
        val tvBrandName = v.findViewById<TextView>(R.id.tv_brand_name)
        val tvBrandDeviceCount = v.findViewById<TextView>(R.id.tv_brand_device_count)

        tvBrandName.text = currentBrand.getBrandName()
        tvBrandDeviceCount.text = currentBrand.getBrandDeviceCount().toString()

        return v
    }
}