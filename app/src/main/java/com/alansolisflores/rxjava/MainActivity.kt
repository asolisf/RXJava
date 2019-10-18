package com.alansolisflores.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.reactivex.rxjava3.core.Observable

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private lateinit var taskButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initProperties()
    }

    private fun initProperties(){
        this.taskButton = findViewById(R.id.taskButton)
    }

    override fun onClick(p0: View?) {
        //Intentionally blank
    }
}
