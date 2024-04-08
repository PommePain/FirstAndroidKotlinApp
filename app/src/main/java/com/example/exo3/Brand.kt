package com.example.exo3

class Brand {
    private var brand_id = 0;
    private var brand_name = "";
    private var brand_slug = "";
    private var device_count = 0;
    private var detail = "";

    constructor(brand_id: Int, brand_name: String, brand_slug: String, device_count: Int, detail: String) {
        this.brand_id = brand_id
        this.brand_name = brand_name
        this.brand_slug = brand_slug
        this.device_count = device_count
        this.detail = detail
    }

    public fun getBrandName(): String {
        return this.brand_name;
    }

    public fun getBrandDeviceCount(): Int {
        return this.device_count;
    }

    public fun getDetail(): String {
        return this.detail
    }
}