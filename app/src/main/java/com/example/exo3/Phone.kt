package com.example.exo3

class Phone {
    private var brand = ""
    private var name = ""
    private var slug = ""
    private var image = ""
    private var detail = ""

    constructor(brand: String, name: String, slug: String, image: String, detail: String) {
        this.brand = brand
        this.name = name
        this.slug = slug
        this.image = image
        this.detail = detail
    }

    public fun getBrand() : String {
        return this.brand
    }

    public fun getName() : String {
        return this.name
    }

    public fun getSlug() : String {
        return this.slug
    }

    public fun getImage() : String {
        return this.image
    }

    public fun getDetail() : String {
        return this.detail
    }
}