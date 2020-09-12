package hi.sejun.room_coroutines_example1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    companion object{
        const val TAG = "MainActivity"
    }

    lateinit var appDatabase :AppDatabase
    lateinit var userDao :UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appDatabase =  AppDatabase.getInstance(this)
        userDao = appDatabase.userDao()
        insert_btn.setOnClickListener {
            if (name_text.text.isEmpty() || phone_text.text.isEmpty() || address_text.text.isEmpty()) {
                Toast.makeText(this, "제대로 입력해주세요", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            insertUser()
        }

        getAllUser()

    }

    private fun insertUser() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userDao.insertUser(
                    User(
                        name = name_text.text.toString(), phone = phone_text.text.toString(),
                        address = address_text.text.toString()
                    )
                )
                Log.d(TAG, "insertUser()...")
                getAllUser()
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    private fun getAllUser(){
        CoroutineScope(Dispatchers.IO).launch {
            val tmp = userDao.getAllUser()
            withContext(Dispatchers.Main){
                info_text.text = tmp.toString()
            }
        }
    }
}