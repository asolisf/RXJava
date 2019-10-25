package com.alansolisflores.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), View.OnClickListener{

    private val TAG = "MainActivity"

    private var taskButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.initProperties()
    }

    private fun initProperties(){
        this.taskButton = findViewById(R.id.taskButton)
        this.taskButton?.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        startRStream()
    }

    private fun startRStream(){
        val myObservable = getObservable()

        val myObserver = getObserver()

        myObservable.subscribe(myObserver)
    }

    private fun getObserver(): Observer<String>{
        return object: Observer<String>{
            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
                Log.d(TAG,"onError: "+e.message)
            }

            override fun onComplete() {
                Log.d(TAG,"onComplete")
            }

            override fun onNext(t: String) {
                Log.d(TAG,"onNext $t")
            }
        }
    }

    private fun getObservable(): Observable<String> {
        return Observable.just("1","2","3","4","5")
    }
}
