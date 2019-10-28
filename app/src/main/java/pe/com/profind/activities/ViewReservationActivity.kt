package pe.com.profind.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_view_reservation.*
import pe.com.profind.R
import pe.com.profind.activities.data.SharedPreference
import pe.com.profind.adapters.ViewReservationAdapter
import pe.com.profind.models.ReservationInterface
import pe.com.profind.models.Student

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ViewReservationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R
            .layout.activity_view_reservation)

        rvReservations.layoutManager =  LinearLayoutManager(this)

        getReservations()
    }

    private fun getReservations() {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(ReservationInterface::class.java)

        val sp = SharedPreference(this)

        var response = postsApi.getAllReservationsByStudents(sp.getValueInt("student_id"))

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
            {   rvReservations.adapter = ViewReservationAdapter(it, this)
                // no op
            },
            { error -> Log.e("ERROR", error.message )
            })

    }
}
