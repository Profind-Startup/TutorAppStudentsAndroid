package pe.com.profind.models


import com.google.gson.annotations.SerializedName



data class Tutor(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("academic_group_name") val academic_group_name: String,
    @SerializedName("academic_group_foundation_date") val academic_group_foundation_date: String,
    @SerializedName("academic_group_address") val academic_group_address: String,
    @SerializedName("birth_date") val birth_date: String
)