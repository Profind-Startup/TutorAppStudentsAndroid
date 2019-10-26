package pe.com.profind.adapters

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.prototype_tutor.view.*
import pe.com.profind.R
import pe.com.profind.activities.ViewTutorActivity
import pe.com.profind.models.Tutor
import pe.com.profind.models.TutorInterface
import pe.com.profind.models.User
import pe.com.profind.models.UserInterface
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.ObjectInput
import java.io.Serializable

class TutorAdapter(var postList: List<Tutor>) :
    RecyclerView.Adapter<TutorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTutor = itemView.itemTutor
        fun bindTo(tutor: Tutor) {

            itemTutor.setOnClickListener {
                this@TutorAdapter.selectedIndex = adapterPosition
                val intent = Intent(it.context, ViewTutorActivity::class.java)
                intent.putExtra("tutor", tutor as Serializable)
                it.context.startActivity(intent)
            }
        }
    }

    var selectedIndex = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorAdapter.ViewHolder {


        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.prototype_tutor,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
     //  var us = getUser(postList.get(position).id)

        holder.itemView.txtPostBody.text = postList.get(position).academic_group_name
        holder.itemView.txtPostTitle.text = postList.get(position).academic_group_address

   //     holder.itemView.txtPostBody.text = us.names.toString() + us.last_names.toString()
   //     holder.itemView.txtPostTitle.text = postList.get(position).academic_group_address



        holder.bindTo(postList.get(position))



    }

    private fun getUser(TutorId: Int): User {

        var us = User(0, 0, "0", "0", "0", "0", "0")
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(UserInterface::class.java)

        var response = postsApi.getUserById(TutorId)

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
            {
                for (item in it) {
                    us = item;
                }
            },
            { throwable ->
                // throw new RuntimeException("Error observing strings", throwable);
                // instead of throwing, just propagate
                Exceptions.propagate(throwable)
            })

    return us
    }



}