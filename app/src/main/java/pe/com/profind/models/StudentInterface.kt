package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.*

interface StudentInterface {

    @GET("Users/{id}/Student")
    fun getStudentByUserId(@Path("id") groupId: Int): Observable<Student>

    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("Students")
    fun createStudent(@Body student: Student): Observable<Student>
}