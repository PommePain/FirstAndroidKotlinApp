package com.example.exo3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class PhoneAdapter: BaseAdapter {
    private var context: Context
    private var lphones:MutableList<Phone>

    constructor(context: Context, lphones: MutableList<Phone>) : super() {
        this.context = context
        this.lphones = lphones
    }

    override fun getCount(): Int {
        return this.lphones.size
    }

    override fun getItem(position: Int): Any {
        return this.lphones.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v:View
        if (convertView == null) {
            val inflater = LayoutInflater.from(this.context)
            v = inflater.inflate(R.layout.row_phone, null)
        } else {
            v = convertView;
        }

        val currentPhone = getItem(position) as Phone
        val tvBrandName = v.findViewById<TextView>(R.id.tv_brand_phone_name)
        val tvPhoneName = v.findViewById<TextView>(R.id.tv_phone_name)

        tvBrandName.setText(currentPhone.getBrand())
        tvPhoneName.setText(currentPhone.getName())

        return v
    }
}