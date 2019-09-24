package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET

interface TutorInterface {

    @GET("tutors")
    fun getAllTutors(): Observable<List<Tutor>>
}