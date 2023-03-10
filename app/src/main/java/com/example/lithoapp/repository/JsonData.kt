package com.example.lithoapp.repository

import com.google.gson.annotations.SerializedName

data class ApiResponse (

    @SerializedName("personalizationType"     ) var personalizationType     : String?                            = null,
    @SerializedName("personalizationSequence" ) var personalizationSequence : ArrayList<PersonalizationSequence> = arrayListOf()

)

data class PersonalizationSequence (

    @SerializedName("widget_id"   ) var widgetId   : String? = null,
    @SerializedName("widget_name" ) var widgetName : String? = null,
    @SerializedName("jsonURL"     ) var jsonURL    : String? = null,
    @SerializedName("priority"    ) var priority   : String?    = null

)