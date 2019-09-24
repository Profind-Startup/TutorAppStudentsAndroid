package pe.com.profind.models


import com.google.gson.annotations.SerializedName



data class Tutor(
    @SerializedName("id") val id: Int,
    @SerializedName("student_id") val student_id: Int,
    @SerializedName("tutor_id") val tutor_id: Int,
    @SerializedName("reservation_date") val reservation_date: String,
    @SerializedName("reservation_time_start") val reservation_time_start: String,
    @SerializedName("reservation_time_end") val reservation_time_end: String,
    @SerializedName("subject_id") val subject_id: String,
    @SerializedName("payment_id") val payment_id: String
)