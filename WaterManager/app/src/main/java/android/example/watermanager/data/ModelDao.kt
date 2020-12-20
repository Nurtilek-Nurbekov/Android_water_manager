package android.example.watermanager.data

import android.example.watermanager.data.Model
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ModelDao {
    @Query("SELECT * FROM history")
    fun getHistory(): List<Model>

    @Query("SELECT * FROM history ORDER BY uid DESC LIMIT 1")
    fun getBalance(): Model

    @Insert
    fun insertHistory(model: Model)

    @Delete
    fun deleteHistory(model: Model)
}