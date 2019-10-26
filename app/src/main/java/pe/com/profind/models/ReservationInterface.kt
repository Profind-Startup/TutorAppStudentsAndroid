package pe.com.profind.models

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap




interface ReservationInterface {

    @GET("reservations")
    fun getAllReservations(): Observable<List<Reservation>>

    @GET("Tutors/{id}/Reservations")
    fun getAllReservationsByTutor(@Path("id") groupId: Int): Observable<List<Reservation>>



}