package pe.com.profind.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.profind.R
import pe.com.profind.adapters.TutorAdapter
import pe.com.profind.models.TutorInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvTutors.layoutManager =  LinearLayoutManager(this)
        
        getTutors()

        val btn_click_me = findViewById(R.id.btnViewTutor) as Button
// set on-click listener
        btn_click_me.setOnClickListener {
            val intent = Intent(this, ViewTutorActivity::class.java)
            this.startActivity(intent)
        }

    }

    private fun getTutors() {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(TutorInterface::class.java)

        var response = postsApi.getAllTutors()

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
            {   rvTutors.adapter = TutorAdapter(it)
                // no op
            },
            { throwable ->
                // throw new RuntimeException("Error observing strings", throwable);
                // instead of throwing, just propagate
                Exceptions.propagate(throwable)
            })



    }
}
