package pe.com.profind.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_detail_reservation.*
import kotlinx.android.synthetic.main.activity_view_reservation.*
import kotlinx.android.synthetic.main.activity_view_tutor.*
import pe.com.profind.R
import pe.com.profind.activities.data.SharedPreference
import pe.com.profind.adapters.ViewReservationAdapter
import pe.com.profind.models.Reservation
import pe.com.profind.models.ReservationDetails
import pe.com.profind.models.ReservationInterface
import pe.com.profind.models.Tutor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DetailReservationActivity : AppCompatActivity() {

    var reservation = Reservation(0,0,0,"","","",0,0)
    var reservationid = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_reservation)


        intent?.extras?.apply {
            reservation = getSerializable("reservation") as Reservation



            reservationid = reservation.id
        }
        getReservationsDetail()
    }


    private fun getReservationsDetail() {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(ReservationInterface::class.java)


        var response = postsApi.getReservationDetails(reservationid)

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
            {
                // no op
                txtTutorName.text = "Nombre del tutor: " + it.tutorNames + " "+ it.tutorLastnames
                txtStudentName.text = "Nombre del estudiante: " + it.studentNames
                txtReservationDate.text = "Día de reserva: "+ it.reservation_date
                txtReservationStart.text = "Hora de reserva: " + it.reservation_time_start
                txtSubjectName.text = "Tema: " + it.subjectname
                txtPayment.text = "Estado: Pendiente"
            },
            { error -> Log.e("ERROR", error.message )
            })

    }



}
