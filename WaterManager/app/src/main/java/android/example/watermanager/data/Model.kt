package android.example.watermanager.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class Model(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "bottle_total") val bottleTotal: String,
    @ColumnInfo(name = "data") val date: String
)