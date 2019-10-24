package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface SubjectInfertace {

    @GET("subjects")
    fun getAllSubjects(): Observable<List<Subject>>

    @GET("Tutors/{id}/Reservations/Subjects")
    fun getAllSubjectsByTutor(@Path("id") groupId: Int): Observable<List<Subject>>
}