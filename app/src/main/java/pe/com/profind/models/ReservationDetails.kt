package pe.com.profind.models

import com.google.gson.annotations.SerializedName

data class ReservationDetails(
    @SerializedName("id") val id: Int,
    @SerializedName("tutorNames") val tutorNames: String,
    @SerializedName("tutorLastnames") val tutorLastnames: String,
    @SerializedName("studentNames") val studentNames: String,
    @SerializedName("reservation_date") val reservation_date: String,
    @SerializedName("reservation_time_start") val reservation_time_start: String,
    @SerializedName("subjectname") val subjectname: String,
    @SerializedName("totalpayment") val totalpayment: String
)
