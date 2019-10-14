package pe.com.profind.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import pe.com.profind.R

class ViewTutorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_tutor)

        val btn_click_me = findViewById(R.id.btnReserv) as Button
// set on-click listener
        btn_click_me.setOnClickListener {
            val intent = Intent(this, ReservationActivity::class.java)
            this.startActivity(intent)

        }
    }
}
