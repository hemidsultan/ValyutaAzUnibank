package com.example.valyutaazunibank

import com.google.gson.annotations.SerializedName

class Data {


    @SerializedName("success")
    var success: Boolean = false
    @SerializedName("terms")
    var terms: String? = null
    @SerializedName("privacy")
    var privacy: String? = null
    @SerializedName("timestamp")
    var timestamp: Int = 0
    @SerializedName("source")
    var source: String? = null
    @SerializedName("quotes")
    var quotes: Quotes? = null

    class Quotes {
        @SerializedName("USDEUR")
        var usdeur: Double = 0.toDouble()
        @SerializedName("USDAZN")
        var usdazn: Double = 0.toDouble()
        @SerializedName("USDRUB")
        var usdrub: Double = 0.toDouble()
        @SerializedName("USDGBP")
        var usdgbp: Double = 0.toDouble()
        @SerializedName("USDCHF")
        var usdchf: Double = 0.toDouble()
    }
}
