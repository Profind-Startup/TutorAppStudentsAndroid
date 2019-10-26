package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface UserInterface {

    @GET("users/{id}")
    fun getUserById(@Path("id") groupId: Int):  Observable<List<User>>


}