package com.example.footballleague.model

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class CompetitionsListResponse(

    @SerializedName("count")
    val totalResults: Int? = 0,

    @SerializedName("competitions")
    var competitions: ArrayList<Competition>? = ArrayList(),


)