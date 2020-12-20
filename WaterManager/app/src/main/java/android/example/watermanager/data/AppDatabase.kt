package android.example.watermanager.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Model::class)], version = 2)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun modelDao() : ModelDao
}