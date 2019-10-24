package pe.com.profind.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class User (
    @SerializedName("id") val id: Int,
    @SerializedName("names") val names: Int,
    @SerializedName("last_names") val last_names: String,
    @SerializedName("dni") val dni: String,
    @SerializedName("address") val address: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String
): Serializable