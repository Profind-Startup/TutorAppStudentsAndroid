package pe.com.profind.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.com.profind.R
import java.util.*
import android.app.DatePickerDialog
import android.app.Dialog
import android.view.View
import android.app.TimePickerDialog
import android.widget.*
import com.google.gson.GsonBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.exceptions.Exceptions
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.activity_main.*
import pe.com.profind.adapters.TutorAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.annotation.SuppressLint
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.util.Log
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_reservation.*
import kotlinx.android.synthetic.main.activity_view_tutor.*
import pe.com.profind.models.*


class ReservationActivity : AppCompatActivity(), View.OnClickListener {


    private var textView: TextView? = null
    private var dialogBtn: ImageButton? = null
    private var btnReserva: Button? =null

    var myImageNameList = mutableListOf("NA")
    var myIds = mutableListOf(0)


    var tutorid = 0

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

    //var Reservation = Reservation(1,1,tutorid, etFecha!!.text.toString() ,etHora!!.text.toString(), etHora!!.text.toString(),myIds.elementAt(obtenerId()).toString(),"1")


    fun obtenerId(): Int
    {
        var index = 0
        for(String in myImageNameList)
        {

            if(String == et_selecccionar_subject!!.text.toString())
            return index
            else
                index++
        }
        return index
    }
    @SuppressLint("CheckResult")
    private fun getSubjects() {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(SubjectInfertace::class.java)


        var response = postsApi.getAllSubjectsByTutor(tutorid)


         myImageNameList.clear()
         myIds.clear()

        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe(
            {

                for (Subject in it) {

                    myImageNameList.add(Subject.name)

                    myIds.add(Subject.id)


                }
                // no op

            },
            { error -> Log.e("ERROR", error.message )
            })



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)


        intent?.extras?.apply {
            tutorid = getInt("tutorId")

        }
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


        textView = findViewById(R.id.et_selecccionar_subject)

        dialogBtn = findViewById(R.id.seleccionar_subject)

        dialogBtn!!.setOnClickListener(this)

        btnReserva = findViewById(R.id.btnEnvReserv)

        btnReserva!!.setOnClickListener{

            saveReservation()
        }
        getSubjects()
    }

    private fun saveReservation() {
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl("http://tutorapp.somee.com/api/").build()

        val postsApi = retrofit.create(ReservationInterface::class.java)
        var reservation = Reservation(1,1,tutorid, etFecha!!.text.toString() ,etHora!!.text.toString(), etHora!!.text.toString(),myIds.elementAt(obtenerId()),1)
        postsApi.postReservation(reservation).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> Log.v("POSTED RESERVATION", "" ) },
                { error -> Log.e("ERROR", error.message ) }
            )
    }


    override fun onClick(v: View) {
        when (v.id) {
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

        showDialog(this@ReservationActivity)
    }

    fun showDialog(activity: Activity) {

        val dialog = Dialog(activity)
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_listview)

        val btndialog = dialog.findViewById(R.id.btndialog) as Button
        btndialog.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {

                dialog.dismiss()
            }
        })

        val listView = dialog.findViewById(R.id.listview) as ListView
        val arrayAdapter = ArrayAdapter(this, R.layout.list_item,R.id.tv, myImageNameList)
        listView.setAdapter(arrayAdapter)

        listView.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                textView!!.text = myImageNameList[position]
                dialog.dismiss()
            }
        })

        dialog.show()

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
                etFecha!!.setText(year.toString() + BARRA + mesFormateado + BARRA + diaFormateado)
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
               // etHora!!.setText("$horaFormateada$DOS_PUNTOS$minutoFormateado $AM_PM")
                etHora!!.setText(hourOfDay.toString() +":" +minute.toString())
            },
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
            hora, minuto, false
        )

        recogerHora.show()
    }

}


