package android.example.watermanager.ui.history

import android.example.watermanager.R
import android.example.watermanager.data.AppDatabase
import android.example.watermanager.data.Bottle
import android.example.watermanager.ui.main.MainFragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.fragment_history.*
import kotlin.collections.ArrayList

class HistoryFragment : Fragment() {

    private lateinit var db: AppDatabase
    private val historyAdapter by lazy { HistoryAdapter() }
    private val list = ArrayList<Bottle>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(R.layout.fragment_history, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDatabase()
        getHistory()
    }

    private fun getHistory() {
        db.modelDao().getHistory().forEach{
            if (it.bottleTotal != null){
                val bottle = Bottle(
                    it.date,
                    it.bottleTotal.toString(),
                    R.drawable.bottle
                )
                list.add(bottle)
                list_recycler_view.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = historyAdapter
                }
            }
        }
        historyAdapter.setHistoryData(list)
    }

    companion object {
        fun newInstance(): MainFragment =
            MainFragment()
    }

    private fun initDatabase() {
        db = Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "database-name"
        ).allowMainThreadQueries().build()
    }
}