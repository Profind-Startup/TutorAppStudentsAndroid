package pe.com.profind.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: Int,
    @SerializedName("names") val name: String,
    @SerializedName("last_names") val area: String,
    @SerializedName("dni") val dni: String,
    @SerializedName("address") val address: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
)