package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET


interface SubjectInfertace {

    @GET("subjects")
    fun getAllSubjects(): Observable<List<Subject>>

    @GET("Tutors/1/Reservations/Subjects")
    fun getAllSubjectsByTutor(): Observable<List<Subject>>
}