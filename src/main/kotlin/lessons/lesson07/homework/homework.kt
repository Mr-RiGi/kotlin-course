package org.example.lessons.lesson07.homework

fun main() {
    println("example 1")
    for (i in 1..5) {
        println(i)
    }
    println("example 2")
    for (i in 1..10) {
        if (i % 2 == 0) {
            println(i)
        }
    }
    println("example 3")
    for (i in 5 downTo 1) {
        println(i)
    }
    println("example 4")
    for (i in 10 downTo 1) {
        if (i % 2 == 0) {
            println(i)
        }
    }
    println("example 5")
    for (i in 1..9 step 2) {
        println(i)
    }
    println("example 6")
    for (i in 1..20 step 3) {
        println(i)
    }
    println("example 7")
    val size = 11
    for (i in 3 until size step 2) {
        println(i)
    }

    println("example 8")
    var i = 0
    while (i++ <5) {
        println(i * i)
    }

    println("example 9")
    var i2 = 10
    while(i2-- !=5){
        println(i2)
    }

    println("example 10")
    var i3 = 5
    do{
        println(i3)
    }while (i3-- > 1)

    println("example 11")
    var i4 = 5
    do{
        println(i4)
    }while (++i4 <10)

    println("example 12")
    for (i in 1 .. 10){
        if(i == 6) break
        println(i)
    }

    println("example 13")
    var i5 = 1
    while (true){
        if (i5 == 10) break
        println(i5)
        i5++
    }

    println("example 14")
    for (i in 1 .. 10){
        if (i % 2 == 0) continue
        println(i)
    }
    println("example 15")
    var i6 = 0
    while (++i6 <= 10){
        if (i6 % 3 == 0) continue
        println(i6)

    }
}