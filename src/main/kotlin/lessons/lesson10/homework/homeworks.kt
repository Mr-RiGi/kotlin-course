package org.example.lessons.lesson10.homework

fun main() {

//Задачи на работу со словарём
    println("Example 1.1")
//1. Создайте пустой неизменяемый словарь, где ключи и значения - целые числа.
    val emptyMap: Map<Int, Int> = emptyMap()
    println(emptyMap)

    println("Example 1.2")
//2. Создайте словарь, инициализированный несколькими парами "ключ-значение", где ключи - float, а значения - double
    val map1: Map<Float, Double> = mapOf(1.4F to 1.7, 2.7F to 2.22)
    println(map1)

    println("Example 1.3")
//3. Создайте изменяемый словарь, где ключи - целые числа, а значения - строки.
    val map2: MutableMap<Int, String> = mutableMapOf()
    println(map2)

    println("Example 1.4")
//4. Имея изменяемый словарь, добавьте в него новые пары "ключ-значение".
    map2[1] = "One"
    map2.put(2, "Two")
    println(map2)

    println("Example 1.5")
//5. Используя словарь из предыдущего задания, извлеките значение, используя ключ. Попробуй получить значение с ключом, которого в словаре нет.
    val map2_1 = map2[3]
    println(map2_1)

    println("Example 1.6")
//6. Удалите определенный элемент из изменяемого словаря по его ключу.
    map2.remove(1)
    println(map2)

    println("Example 1.7")
//7. Создайте словарь (ключи Double, значения Int) и выведи в цикле результат деления ключа на значение.
// Не забудь обработать деление на 0 (в этом случае выведи слово “бесконечность”)
    val map3: Map<Double, Int> = mapOf(10.2 to 4, 115.7 to 3, 256.4 to 0)
    for ((key, value) in map3) {
        if (value == 0) {
            println("$key / $value = бесконечность")
        }
        else {
            println("$key / $value = ${key / value}")
        }
    }

    println("Example 1.8")
//8. Измените значение для существующего ключа в изменяемом словаре.
    map2[2] = "Three"
    println(map2)

    println("Example 1.9")
//9. Создайте два словаря и объедините их в третьем изменяемом словаре через циклы.
    val map4: Map<String, Int> = mapOf("Qwe" to 1, "Rty" to 2)
    val map4_2 = mapOf("Asd" to 3, "Fgh" to 4)
    val map4_3: MutableMap<String, Int> = mutableMapOf()
    for ((key, value) in map4) {
        map4_3[key] = value
    }
    for ((key, value) in map4_2){
        map4_3[key] = value
    }
    println(map4_3)

    println("Example 1.10")
//10. Создайте словарь, где ключами являются строки, а значениями - списки целых чисел. Добавьте несколько элементов в этот словарь.
    val map5: MutableMap<String, List<Int>> = mutableMapOf()
    map5["qwe"] = listOf(1, 2, 3, 4)
    map5["rty"] = listOf(5, 6, 7, 8)
    println(map5)

    println("Example 1.11")
//11. Создай словарь, в котором ключи - это целые числа, а значения - изменяемые множества строк. Добавь данные в словарь.
    // Получи значение по ключу (это должно быть множество строк) и добавь в это множество ещё строку. Распечатай полученное множество.
    val map6: MutableMap<Int, MutableSet<String>> = mutableMapOf()
    map6[1] = mutableSetOf("one", "two")
    map6[2] = mutableSetOf("three", "four")
    println(map6)
    val set = map6[1]
    set?.add("Five")
    println(set)

    println("Example 1.12")
//12. Создай словарь, где ключами будут пары чисел. Через перебор найди значение у которого пара будет содержать цифру 5 в качестве первого или второго значения.
    val map7: MutableMap<List<Int>, String> = mutableMapOf(listOf(4,4) to "zxc", listOf(4,5) to "vbn")
    for ((key, value) in map7){
        for(element in key)
            if(element == 5)
                println(value)
    }


//Задачи на подбор оптимального типа для словаря
    println("Example 2.1")
//1. Словарь библиотека: Ключи - автор книги, значения - список книг
    val map8: MutableMap<String, MutableSet<String>> = mutableMapOf("Venni Pok" to mutableSetOf("Edition 1", "Edition 2"))
    println(map8)

    println("Example 2.2")
//2. Справочник растений: Ключи - типы растений (например, "Цветы", "Деревья"), значения - списки названий растений
    val map9: MutableMap<String, MutableList<String>> = mutableMapOf("Деревья" to mutableListOf("Дуб", "Клен"), "Цветы" to mutableListOf("Роза", "Лилия"))
    println(map9)

    println("Example 2.3")
//3. Четвертьфинала: Ключи - названия спортивных команд, значения - списки игроков каждой команды
    val map10: MutableMap<String, MutableList<String>> = mutableMapOf("ASD" to mutableListOf("Foch Anderson", "Benedict Bremen"), "CXZ" to mutableListOf("Nol NulevbIy", "Ygol Yglovoy"))
    println(map10)

    println("Example 2.4")
//4. Курс лечения: Ключи - даты, значения - список препаратов принимаемых в дату
    val map11: MutableMap<String, MutableList<String>> = mutableMapOf("10.10.2026" to mutableListOf("Aspirin"), "11.10.2026" to mutableListOf("Paracetamol"))
    println(map11)

    println("Example 2.5")
//5. Словарь путешественника: Ключи - страны, значения - словари из городов со списком интересных мест.
    val map12: MutableMap<String, MutableMap<String, MutableList<String>>> = mutableMapOf(
        "Беларусь" to mutableMapOf("Минск" to mutableListOf("Площадь Освободителей", "Парк Челюскинцев")),
        "Испания" to mutableMapOf("Барселона" to mutableListOf("Саграда Фамилия", "Парк Гуэль"))
    )
    println(map12)
}