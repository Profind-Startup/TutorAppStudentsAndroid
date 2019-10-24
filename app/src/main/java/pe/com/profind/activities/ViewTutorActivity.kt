package pe.com.profind.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_view_tutor.*
import pe.com.profind.R
import pe.com.profind.models.Tutor
import java.io.Serializable

class ViewTutorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tutor)
       var tutor = Tutor(0,0,"","","","")
        intent?.extras?.apply {
             tutor = getSerializable("tutor") as Tutor
            txtTutorNames.text = tutor.academic_group_address
        }
        val btn_click_me = findViewById(R.id.btnReserv) as Button
// set on-click listener
        btn_click_me.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            intent.putExtra("tutorId", tutor.id)
            it.context.startActivity(intent)

        }
    }
}
