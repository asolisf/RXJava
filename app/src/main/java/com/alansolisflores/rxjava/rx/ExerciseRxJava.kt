package com.alansolisflores.rxjava.rx

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>){
    Examples.filerAndMap()
    Examples.flatMapExample()
    Examples.Create()
    Examples.Flowable()
    Examples.GroupByExample()
}

object Examples {

    fun filerAndMap(){
        val numbers = intArrayOf(5,8,65,8,2,1,7,9,6,4)

        numbers.toObservable()
            .filter{ it > 4 }
            .map { it.toString() }
            .subscribe{println("$it > 4")}

        //Par number
        numbers.toObservable()
            .filter{ (it % 2) == 0 }
            .map { it.toString() }
            .subscribe{println("Par number $it")}

        println("Distinct")
        numbers.toObservable()
            .distinct()
            .map { it.toString() }
            .subscribe{print("[$it]")}
    }

    fun flatMapExample(){
        println("\nFlatMap")
        val array2d: Array<IntArray> = arrayOf(intArrayOf(2,4,6,8,10), intArrayOf(1,3,5,7,9))

        array2d.toObservable()
               .flatMap { it.toObservable() }
            .map { it.toString() }
            .subscribe{print("[$it]")}
    }

    fun GroupByExample(){
        val wordsObservable =
            Observable.just("hello","come","create","single","done")

        wordsObservable.groupBy { it.length == 6 }
            .subscribe{group->
                group.subscribe{println("Group ${group.key} [$it]")}
            }
    }

    fun Create(){
        getObservable().map { it.toString() }
                        .subscribe(::println)
    }

    /**
     * Collection more than 10000 elements
     */
    fun Flowable(){
        val flowableObservable = Flowable.just(1,2,3,4)
        flowableObservable.reduce(50,{t1,t2->t1+t2}).subscribe(getSingleObserver())
    }

    private fun getObservable(): Observable<Int>{
        return Observable.create{subscriber ->
            for (i in 1..10){
                subscriber.onNext(i)
            }

            subscriber.onComplete()
        }
    }

    private fun getSingleObserver(): SingleObserver<Int>{
        return object: SingleObserver<Int>{
            override fun onSubscribe(d: Disposable) {
                println("OnSubscribe")
            }

            override fun onSuccess(t: Int) {
                println("OnSuccess: $t")
            }

            override fun onError(e: Throwable) {
                println("OnError ${e.message}")
            }
        }
    }
}