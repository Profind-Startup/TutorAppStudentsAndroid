package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET


interface ReservationInterface {

    @GET("reservations")
    fun getAllReservations(): Observable<List<Reservation>>

    @GET("Tutors/1/Reservations")
    fun getAllReservationsByTutor(): Observable<List<Reservation>>

}