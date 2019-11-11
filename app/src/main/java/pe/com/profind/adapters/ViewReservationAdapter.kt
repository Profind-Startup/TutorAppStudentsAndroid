package pe.com.profind.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_view_tutor.*
import kotlinx.android.synthetic.main.prototype_reservation.view.*
import pe.com.profind.R
import pe.com.profind.models.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ViewReservationAdapter(val postList: List<Reservation>, val context: Context) :
    RecyclerView.Adapter<ViewReservationAdapter.ViewHolder>() {

    var tutor = Tutor(0, 0, "", "", "", "")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.prototype_reservation,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text = postList.get(position).reservation_date
        holder.itemView.txtPostBody.text = postList.get(position).subject_id.toString()

        /*viewTutorInfo(postList.get(position).tutor_id)
        holder.itemView.txtTutorNames.text = tutor.academic_group_name
        holder.itemView.txtTutorLastNames.text = tutor.academic_group_address
*/
    }

   /* fun viewTutorInfo(idTutor: Int): Tutor {

        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(TutorInterface::class.java)
        postsApi.getTutorsById(idTutor).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {

                    tutor = it

                },
                { error ->
                    Log.e("ERROR", error.message)

                }
            )
    }*/


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
