package pe.com.profind.adapters

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.prototype_tutor.view.*
import pe.com.profind.R
import pe.com.profind.activities.ViewTutorActivity
import pe.com.profind.models.Tutor
import java.io.ObjectInput
import java.io.Serializable

class TutorAdapter(var postList: List<Tutor>) :
    RecyclerView.Adapter<TutorAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

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
                parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.txtPostBody.text =  postList.get(position).academic_group_name
        holder.itemView.txtPostTitle.text = postList.get(position).academic_group_address
        holder.bindTo(postList.get(position))
    }


}