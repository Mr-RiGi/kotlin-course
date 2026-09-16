package org.example.lessons.lesson05.homework

fun main() {
    //Задача 1
    val baseIntensity: Double = 5.0
    val coefficient: Double? = 0.123
    val baseCoefficient = 0.5
    val resultIntensity = baseIntensity * (coefficient ?: baseCoefficient)
    println(resultIntensity)

    //Задача 2
    val defaultPrice: Double = 50.0
    val price: Double? = 30.0
    val cargoPrice = 10
    val insuranceCoefficient = 0.5
    val insurancePrice = (price ?: defaultPrice) * insuranceCoefficient / 100
    val totalPrice: Double = cargoPrice + insurancePrice
    println(totalPrice)

    //Задача 3
    val atmPressure: Double? = 10.7
    val message = "Ошибка! Отсутствует показатель атмосферного давления"
    val dateMessage = atmPressure ?: message
    println(dateMessage)
}