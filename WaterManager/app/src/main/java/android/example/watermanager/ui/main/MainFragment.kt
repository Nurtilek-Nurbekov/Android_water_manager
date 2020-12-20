package android.example.watermanager.ui.main

import android.example.watermanager.R
import android.example.watermanager.data.AppDatabase
import android.example.watermanager.data.Model
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import kotlinx.android.synthetic.main.fragment_main.*
import java.text.DateFormat.getDateTimeInstance
import java.util.*

class MainFragment : Fragment() {
    private lateinit var db: AppDatabase

    var total = 0
    val time = getDateTimeInstance()
    val currentDate = time.format(Date())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initButtons()
        initDatabase()
        setBalance()
    }

    private fun setBalance() {
        try {
            if (db.modelDao().getBalance().bottleTotal.toString() != "") {
                if (db.modelDao().getBalance().bottleTotal.toString().contains("(-1)")){
                    balance.text = cutString(db.modelDao().getBalance().bottleTotal.toString())
                } else {
                    balance.text = db.modelDao().getBalance().bottleTotal.toString()
                }
            } else balance.text = "0"
        } catch (e: Exception){
            Log.e("setBalance", e.message)
        }
    }


    private fun initButtons() {
        number_picker.minValue = 0
        number_picker.maxValue = 50
        number_picker.wrapSelectorWheel = false

        number_picker.setOnValueChangedListener { picker, oldVal, newVal ->
            done_button.setOnClickListener {
                balance.text = "$newVal"
                val model = Model(
                    0,
                    newVal.toString(),
                    currentDate
                )
                db.modelDao().insertHistory(model)
            }

        }


        minusOne.setOnClickListener {
            total = balance.text.toString().toInt()
            if (total != 0) {
                total--
            } else if (total <= 0) {
                Toast.makeText(context,
                    R.string.toast_msg, Toast.LENGTH_SHORT).show()
            }
            balance.text = total.toString()
            val model = Model(0, "$total (-1)", currentDate)
            db.modelDao().insertHistory(model)
        }
    }

    fun cutString(str: String): String? {
        return str.substring(0, str.length - 4)
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

}