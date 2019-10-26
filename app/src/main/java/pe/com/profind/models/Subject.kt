package pe.com.profind.models

import com.google.gson.annotations.SerializedName



data class Subject(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("area") val area: String,
    @SerializedName("id_tutor") val id_tutor: Int
)