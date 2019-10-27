package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StudentInterface {

    @GET("Users/{id}/Student")
    fun getStudentByUserId(@Path("id") groupId: Int): Observable<Student>
}