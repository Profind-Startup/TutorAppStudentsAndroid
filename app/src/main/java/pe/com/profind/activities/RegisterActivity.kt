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
import kotlinx.android.synthetic.main.activity_register.*
import pe.com.profind.R
import pe.com.profind.activities.data.SharedPreference
import pe.com.profind.activities.ui.login.LoggedInUserView
import pe.com.profind.models.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btn_click_me = findViewById(R.id.btnRegistrarme) as Button
// set on-click listener
        btn_click_me.setOnClickListener {

            saveUser()


        }
    }

    private fun saveUser() {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(UserInterface::class.java)
        val sp = SharedPreference(this)
        var user = User(1,etNames.text.toString(),etLastnames.text.toString(),etDNI.text.toString(),etAddress.text.toString(),etUser.text.toString(), etPassword.text.toString())
        postsApi.createUser(user).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("POSTED USER", "" )
                    checkUserRegistered()},
                { error -> Log.e("ERROR", error.message ) }
            )
    }
    private fun saveStudent(user_id: Int) {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(StudentInterface::class.java)

        var student = Student(1,user_id)
        postsApi.createStudent(student).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("POSTED STUDENT", "" )
                checkStudentRegistered()
                },
                { error -> Log.e("ERROR", error.message ) }
            )
    }

    private fun checkUserRegistered()
    {

            val retrofit = Retrofit.Builder().addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://tutorapp.somee.com/api/").build()

            val postsApi = retrofit.create(UserInterface::class.java)
            var user = User(0,"","","","",etUser.text.toString(),etPassword.text.toString())
            postsApi.checkUser(user).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {

                        if(it == null)

                        else {
                            val sp = SharedPreference(this)
                            sp.save("user_id",it.id)

                            saveStudent(it.id)

                        }
                    },
                    { error -> Log.e("ERROR", error.message )

                    }
                )


        }


    private fun checkStudentRegistered()
    {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(StudentInterface::class.java)
        val sp = SharedPreference(this)
        postsApi.getStudentByUserId(sp.getValueInt("user_id")).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    if(it == null)

                    else {
                        val sp = SharedPreference(this)
                        sp.save("student_id",it.id)


                        val intent = Intent(this, MainActivity::class.java)
                        this.startActivity(intent)

                    }
                },
                { error -> Log.e("ERROR", error.message )

                }
            )


    }

}
