package com.example.catonoff

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import java.io.DataOutputStream
import java.net.Socket


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    val catOnButton: ImageButton = findViewById(R.id.cat_on)
    catOnButton.setOnClickListener{ catOn() }

    val catOffButton: ImageButton = findViewById(R.id.cat_off)
    catOffButton.setOnClickListener{ catOff() }
    }


    private fun catOn() {
        val thread1 = CatOn()
        thread1.start()
    }

    private fun catOff(){
        val thread2 = CatOff()
        thread2.start()
    }

    class CatOn : Thread() {
        override fun run(){
            val soc = Socket("192.168.5.100", 59595)
            val dout = DataOutputStream(soc.getOutputStream())
            dout.write("1".toByteArray())

            dout.flush()
            dout.close()
            soc.close()
        }
    }

    class CatOff : Thread() {
        override fun run(){
            val soc = Socket("192.168.5.100", 59595)
            val dout = DataOutputStream(soc.getOutputStream())
            dout.write("0".toByteArray())

            dout.flush()
            dout.close()
            soc.close()
        }
    }
}