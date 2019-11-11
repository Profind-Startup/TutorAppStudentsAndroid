package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TutorInterface {

    @GET("tutors")
    fun getAllTutors(): Observable<List<Tutor>>

    @GET("tutors/{id}")
    fun getTutorsById(@Path("id") groupId: Int): Observable<Tutor>

}