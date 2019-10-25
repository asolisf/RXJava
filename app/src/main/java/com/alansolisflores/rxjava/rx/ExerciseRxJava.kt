package com.alansolisflores.rxjava.rx

import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>){
    val exercise = Examples
    exercise.filerAndMap()
    exercise.flatMapExample()
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
}