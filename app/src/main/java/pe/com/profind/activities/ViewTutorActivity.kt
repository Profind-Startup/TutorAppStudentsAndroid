package pe.com.profind.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_view_tutor.*
import pe.com.profind.R
import pe.com.profind.activities.data.SharedPreference
import pe.com.profind.models.Tutor
import pe.com.profind.models.User
import pe.com.profind.models.UserInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ViewTutorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tutor)
       var tutor = Tutor(0,0,"","","","")
        intent?.extras?.apply {
             tutor = getSerializable("tutor") as Tutor
            txtSpecialities.text = tutor.academic_group_name
            txtAddress.text =  tutor.academic_group_address
            viewTutorInfo(tutor.user_id)
        }
        val btn_click_me = findViewById(R.id.btnReserv) as Button
// set on-click listener
        btn_click_me.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra("tutorId", tutor.id)
            it.context.startActivity(intent)

        }


    }

     fun viewTutorInfo(idUser : Int)
    {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(UserInterface::class.java)
        postsApi.getUserById(idUser).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    txtTutorNames.text = it.name
                  txtTutorLastnames.text = it.last_names


                },
                { error -> Log.e("ERROR", error.message )

                }
            )
    }
}
