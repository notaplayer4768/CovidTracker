package com.example.covidtracker

import android.os.Parcelable
import android.text.BoringLayout
import java.util.*
import kotlinx.android.parcel.Parcelize

//make sire the variable name matches the API's
@Parcelize
data class CountyData(
    val state: String?,
    val county: String?,
    val cdcTransmissionLevel: Int,
    val lastUpdatedDate: String,
    val metrics: Metrics,
    val actuals: Actuals
) : Parcelable { @Parcelize
    data class Actuals (
        val cases: Int?,
        val newCases: Int?
    ) : Parcelable

    @Parcelize
    data class Metrics (
        val testPositivityRatio: Double?,
        val weeklyNewCasesPer100k: Double?
    ) : Parcelable
}



