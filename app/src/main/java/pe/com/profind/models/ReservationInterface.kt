package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.*


interface ReservationInterface {

    @GET("reservations")
    fun getAllReservations(): Observable<List<Reservation>>

    @GET("Tutors/{id}/Reservations")
    fun getAllReservationsByTutor(@Path("id") groupId: Int): Observable<List<Reservation>>


    @GET("Students/{id}/Reservations")
    fun getAllReservationsByStudents(@Path("id") groupId: Int): Observable<List<Reservation>>

    @GET("Reservations/{id}/details")
    fun getReservationDetails(@Path("id") groupId: Int): Observable<ReservationDetails>


    @Headers("Content-Type: application/json;charset=utf-8")
    @POST("reservations")
    fun postReservation(@Body reservation: Reservation): Observable<Reservation>


}