package pe.com.profind.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.com.profind.R
import android.widget.ImageButton
import android.widget.EditText
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.util.*
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.view.View
import android.widget.TimePicker
import android.app.TimePickerDialog

class ReservationActivity : AppCompatActivity(), View.OnClickListener {

    private val CERO = "0"
    private val BARRA = "/"
    private val DOS_PUNTOS = ":"

    val c = Calendar.getInstance()

    //Variables para obtener la fecha
    val mes = c.get(Calendar.MONTH)
    val dia = c.get(Calendar.DAY_OF_MONTH)
    val anio = c.get(Calendar.YEAR)

    val hora = c.get(Calendar.HOUR_OF_DAY)
    val minuto = c.get(Calendar.MINUTE)

    var etFecha: EditText? = null
    var ibObtenerFecha: ImageButton? = null

    var etHora: EditText? = null
    var ibObtenerHora: ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        etFecha = findViewById(R.id.et_mostrar_fecha_picker) as EditText
        //Widget ImageButton del cual usaremos el evento clic para obtener la fecha
        ibObtenerFecha = findViewById(R.id.ib_obtener_fecha) as ImageButton
        //Evento setOnClickListener - clic
        ibObtenerFecha!!.setOnClickListener(this)

        etHora = findViewById(R.id.et_mostrar_hora_picker) as EditText
        //Widget ImageButton del cual usaremos el evento clic para obtener la hora
        ibObtenerHora = findViewById(R.id.ib_obtener_hora) as ImageButton
        //Evento setOnClickListener - clic
        ibObtenerHora!!.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        when (v.getId()) {
            R.id.ib_obtener_fecha -> obtenerFecha()

        }
        when (v.id) {
            R.id.ib_obtener_hora -> obtenerHora()
        }
        when (v.id) {
            R.id.seleccionar_subject -> actualizarSubjects()
        }
    }

    private fun actualizarSubjects() {

    }
    private fun obtenerFecha() {
        val recogerFecha = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                val mesActual = month + 1
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                val diaFormateado =
                    if (dayOfMonth < 10) CERO + dayOfMonth.toString() else dayOfMonth.toString()
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                val mesFormateado =
                    if (mesActual < 10) CERO + mesActual.toString() else mesActual.toString()
                //Muestro la fecha con el formato deseado
                etFecha!!.setText(diaFormateado + BARRA + mesFormateado + BARRA + year)
            },
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             * También puede cargar los valores que usted desee
             */
            anio, mes, dia
        )
        //Muestro el widget
        recogerFecha.show()

    }

    private fun obtenerHora() {
        val recogerHora = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                val horaFormateada = if (hourOfDay < 10) CERO + hourOfDay else hourOfDay.toString()
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                val minutoFormateado = if (minute < 10) CERO + minute else minute.toString()
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                val AM_PM: String
                if (hourOfDay < 12) {
                    AM_PM = "a.m."
                } else {
                    AM_PM = "p.m."
                }
                //Muestro la hora con el formato deseado
                etHora!!.setText("$horaFormateada$DOS_PUNTOS$minutoFormateado $AM_PM")
            },
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
            hora, minuto, false
        )

        recogerHora.show()
    }

}


