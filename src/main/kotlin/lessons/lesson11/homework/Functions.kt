package org.example.lessons.lesson11.homework

//Задачи на сигнатуру метода
//Напишите сигнатуру метода в которую входит модификатор доступа, название функции, список аргументов с типами и возвращаемое значение.
//В теле метода можешь сделать возврат объекта нужного типа если это требуется для устранения ошибок.
//
//1. Не принимает аргументов и не возвращает значения.
fun Hello () {
    println("Hello")
}
//2. Принимает два целых числа и возвращает их сумму.
fun num (a: Int, b: Int): Int {
    return a + b
}
//3. Принимает строку и ничего не возвращает.
fun st (str: String) {
    println("String number one")
}
//4. Принимает список целых чисел и возвращает среднее значение типа Double.
fun qw (list: List<Int>): Double {
    return list.average()
}
//5. Принимает nullable строку и возвращает её длину в виде nullable целого числа и доступна только в текущем файле.
private fun er (ert: String?): Int? = ert?.length
//6. Не принимает аргументов и возвращает nullable вещественное число.
fun returnDouble(): Double? = null
//7. Принимает nullable список целых чисел, не возвращает значения и доступна только в текущем файле.
private fun nullInt (rt: List<Int>?) {
    println(rt)
}
//8. Принимает целое число и возвращает nullable строку.
fun numInt (num: Int): String? = null
//9. Не принимает аргументов и возвращает список nullable строк.
fun returnString (): List<String>? = null
//10. Принимает nullable строку и nullable целое число и возвращает nullable булево значение.
fun nullStInt (st: String?, num: Int?): Boolean? {
    return null
}
//
//Задачи на написание кода
//Напишите валидную сигнатуру метода а так же рабочий код для задач.
//
//11. Напишите функцию multiplyByTwo, которая принимает целое число и возвращает его, умноженное на 2.
fun multiplyByTwo (num: Int): Int = num * 2
//12. Создайте функцию isEven, которая принимает целое число и возвращает true, если число чётное, и false в противном случае.
fun isEven (int: Int): Boolean {
    return int % 2 == 0
}
//13. Напишите функцию printNumbersUntil, которая принимает целое число n и выводит на экран числа от 1 до n.
// Если число n меньше 1, функция должна прекратить выполнение с помощью return без вывода сообщений.
fun printNumbersUntil (n: Int) {
    if (n < 1) return
    for (i in 1..n) {
        println(i)
    }
}
//14. Создайте функцию findFirstNegative, которая принимает список целых чисел и возвращает первое отрицательное число в списке.
// Если отрицательных чисел нет, функция должна вернуть null.
fun findFirstNegative (l: List<Int>): Int?{
    for (i in l) {
        if (i < 0) return i
    }
    return null
}
//15. Напишите функцию processList, которая принимает список строк. Функция должна проходить по списку и выводить каждую строку.
// Если встречается null значение, функция должна прекратить выполнение с помощью return без возврата значения.
fun processList (p: List<String?>) {
    for (i in p){
        if (i == null) return
        println(i)
    }
}