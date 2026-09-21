package org.example.lessons.lesson06.homework

fun main() {
    println()
    println("Задача 1")
    example1(13)
    example1(5)
    example1(7)
    example1(9)
    example1(1)
    println()
    println("Задача 2")
    example2(0.9)
    example2(4.5)
    println()
    println("Задача 3")
    example3(800)
    example3(4567)
    example3(5001)
    println()
    println("Задача 4")
    example4(600)
    example4(1200)
    println()
    println("Задача 5")
    example5("txt")
    example5("jpeg")
    example5("csv")
    example5("txttt")
    println()
    println("Задача 6")
    example6(10.0, "C")
    example6(100.6, "F")
    example6(123.0, "H")
    println()
    println("Задача 7")
    example7(-30)
    example7(19)
    example7(10)
    example7(18)
    println()
    println("Задача 8")
    example8(2)
    example8(18)
    example8(19)
}

fun example1 (monthNumber: Int) {
    if (monthNumber !in 1..12) {
        println("Некорректный номер месяца. Ожидается число от 1 до 12.")
        return
    }
    if (monthNumber in 3..5) {
        println("Весна")
    } else if (monthNumber in 6..8) {
        println("Лето")
    } else if (monthNumber in 9..11) {
        println("Осень")
    } else {
        println("Зима")
    }
}

fun example2 (dogAge: Double) {
    if (dogAge <= 0) {
        println ("Не корректный ввод")
    }else if (dogAge <= 2) {
        val result = dogAge * 10.5
        println(result)
    }else {
        val result = 21 + (dogAge - 2) * 4
        println(result)
    }
}

fun example3 (distance: Int){ // Дистанция в метрах, можно было бы применить Double
    when {
        distance <= 1000 -> println("Пешком")
        distance <= 5000 -> println("Велосипед")
        else -> println("Автомобиль")
    }
}

fun example4 (bonusBalls: Int) {
    val result = if (bonusBalls <= 1000) {
        (bonusBalls / 100) * 2
    }else {
        (bonusBalls / 100) * 3
    }
    println(result)
}

fun example5 (documentsType: String){
    val documentType = when (documentsType){
        "txt", "doc", "pdf" -> ("Текстовый документ")
        "jpeg", "jpg", "png" -> ("Изображение")
        "xlsx", "ods", "csv" -> ("Таблица")
        else -> ("Неизвестный тип")
    }
    println(documentType)
}

fun example6 (temp: Double, unit: String){
    if (unit == "C"){
        val fahrenheit = (temp * 9 / 5) + 32
        print(fahrenheit)
        println("F")
    }else if (unit == "F"){
        val celsius = (temp - 32) * 5 / 9
        print(celsius)
        println("C")
    }else{
        println("Не верный ввод")
    }
}

fun example7 (temp: Int) {
    if (temp <= -30 || temp >= 35){
        println("Остаться дома")
    }else if (temp <= 10){
        println("Куртка и шапка")
    }else if (temp <= 18){
        println("Ветровка")
    }else{
        println("Футболка и шорты")
    }
}

fun example8 (age: Int){
    when {
        age in 0..9 -> println("Детские")
        age in 10..18 -> println("Подростковые")
        else -> println("18+")
    }
}

