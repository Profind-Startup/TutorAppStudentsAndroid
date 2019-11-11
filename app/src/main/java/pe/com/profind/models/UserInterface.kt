package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.*

interface UserInterface {

    @GET("users")
    fun getAllUsers(): Observable<List<User>>

    @POST("users/check")
    fun checkUser(@Body user: User): Observable<User>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("users")
    fun createUser(@Body user: User): Observable<User>

    @GET("users/{id}")
    fun getUserById(@Path("id") groupId: Int): Observable<User>
}