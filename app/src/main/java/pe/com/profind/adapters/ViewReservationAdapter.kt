package pe.com.profind.adapters

import android.content.Context
import android.content.Intent
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
import kotlinx.android.synthetic.main.prototype_reservation.view.txtPostBody
import kotlinx.android.synthetic.main.prototype_reservation.view.txtPostTitle
import kotlinx.android.synthetic.main.prototype_tutor.view.*
import pe.com.profind.R
import pe.com.profind.activities.DetailReservationActivity
import pe.com.profind.activities.ViewTutorActivity
import pe.com.profind.models.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class ViewReservationAdapter(val postList: List<Reservation>) :
    RecyclerView.Adapter<ViewReservationAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val itemReservation = itemView.itemReservation
        fun bindTo(reservation: Reservation) {

            itemReservation.setOnClickListener {
                this@ViewReservationAdapter.selectedIndex = adapterPosition
                val intent = Intent(it.context, DetailReservationActivity::class.java)
                intent.putExtra("reservation", reservation as Serializable)
                it.context.startActivity(intent)
            }
        }
    }

    var selectedIndex = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.prototype_reservation,
                parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostTitle.text = "Día de Reserva: " + postList.get(position).reservation_date
        holder.itemView.txtPostBody.text = "Tema Reservado" +  postList.get(position).subject_id.toString()
        holder.bindTo(postList.get(position))
    }




}
