package hi.sejun.room_coroutines_example1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user:User)

    @Query("select * from user_table")
    suspend fun getAllUser() : List<User>
}