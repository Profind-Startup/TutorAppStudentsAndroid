package pe.com.profind.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.com.profind.R
import pe.com.profind.models.Tutor

class TutorAdapter(val postList: List<Tutor>, val context: Context) :
    RecyclerView.Adapter<TutorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.prototype_tutor,
                parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //holder.itemView.txtPostTitle.text = postList.get(position).reservation_date
     //   holder.itemView.txtPostBody.text = postList.get(position).subject_id

    }
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}