package hi.sejun.room_coroutines_example1

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    val name:String,
    @PrimaryKey
    val phone:String,
    val address:String
)