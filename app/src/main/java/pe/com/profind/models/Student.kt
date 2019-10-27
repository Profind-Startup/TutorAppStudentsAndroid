package pe.com.profind.models

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val user_id: Int
)