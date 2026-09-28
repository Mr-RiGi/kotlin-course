package org.example.lessons.lesson08.homework


//val originalString = "Kotlin is fun"
//val subString = originalString.substring(7)  // "is fun"
//val subString2 = originalString.substring(3, 6) // "lin"
//val replacedString = originalString.replace("fun", "awesome")  // "Kotlin is awesome"
//val words = originalString.split(" ")  // ["Kotlin", "is", "fun"]
//val length = "Hello".length  // 5
//val upper = "hello".uppercase()  // "HELLO"
//val lower = "HELLO".lowercase()  // "hello"
//val trimmed = "  hello  ".trim()  // "hello"
//val starts = "Kotlin".startsWith("Kot")  // true
//val ends = "Kotlin".endsWith("lin")  // true
//val contains = "Hello".contains("ell")  // true
//val empty = "".isNullOrEmpty()  // true
//val blank = "  ".isNullOrBlank()  // true
//val repeat = "ab".repeat(3)  // "ababab"
//val letter = originalString[5] // 'n'
//val indexOfChar = "Kotlin".indexOf('t')
//val indexOfWord = "Kotlin is the best language".indexOf("best")
//val backReverse = "niltoK".reversed()


//1. Преобразование строк
//Создайте функцию, которая будет анализировать входящие фразы и применять к ним различные преобразования,
// делая текст более ироничным или забавным. Функция должна уметь распознавать ключевые слова или условия и
// соответственно изменять фразу.
//
//Правила проверки и преобразования:
//
//Если фраза содержит слово "невозможно":
//Преобразование: Замените "невозможно" на "совершенно точно возможно, просто требует времени".
//Если фраза начинается с "Я не уверен":
//Преобразование: Добавьте в конец фразы ", но моя интуиция говорит об обратном".
//Если фраза содержит слово "катастрофа":
//Преобразование: Замените "катастрофа" на "интересное событие".
//Если фраза заканчивается на "без проблем":
//Преобразование: Замените "без проблем" на "с парой интересных вызовов на пути".
//Если фраза содержит только одно слово:
//Преобразование: Добавьте перед словом "Иногда," и после слова ", но не всегда".
//Примеры Тестовых Фраз:
//
//"Это невозможно выполнить за один день"
//"Я не уверен в успехе этого проекта"
//"Произошла катастрофа на сервере"
//"Этот код работает без проблем"
//"Удача"

fun example1(phrase: String) {
    val result = when {
        phrase.contains("невозможно", ignoreCase = true) -> phrase.replace("невозможно", "совершенно точно возможно, просто требует времени", ignoreCase = true)
        phrase.startsWith("Я не уверен", ignoreCase = true) -> phrase + ", но моя интуиция говорит об обратном."
        phrase.contains("катастрофа", ignoreCase = true) -> phrase.replace("катастрофа", "интересное событие", ignoreCase = true)
        phrase.endsWith("без проблем", ignoreCase = true) -> phrase.replace("без проблем", "с парой интересных вызовов на пути", ignoreCase = true)
        !phrase.contains(" ") -> "Иногда, ${phrase}, но не всегда"
        else -> phrase
    }

    println(result)
}

fun main () {
    println("Example 1")
    example1("Это невозможно выполнить за один день")
    example1("Я не уверен в успехе этого проекта")
    example1("Произошла катастрофа на сервере")
    example1("Этот код работает без проблем")
    example1("Удача")

    //2. Извлечение даты из строки лога
//У вас есть строка лога, например "Пользователь вошел в систему -> 2021-12-01 09:48:23"
//(данные могут быть любыми, но формат всегда такой).
//Извлеките отдельно дату и время из этой строки и сразу распечатай их по очереди.
//Используй indexOf или split для получения правой части сообщения.
    val log = "Пользователь вошел в систему -> 2021-12-01 09:48:23"
    val (date, time) = log.substringAfterLast("-> ").split(" ")
    println("Example 2")
    println(date)
    println(time)


//    3. Маскирование личных данных
//    Дана строка с номером кредитной карты, например "4539 1488 0343 6467".
//    Замаскируйте все цифры, кроме последних четырех, символами "*".
    println("Example 3")
    val numCard = "4539 1488 0343 6467"
    val lastDigits = numCard.substring(numCard.length - 4)
    var masked = ""
    for (i in 0 until numCard.length - 4) {
        val ch = numCard[i]
        masked = if (ch == ' ') masked + " " else masked + "*"
    }
    masked = masked + lastDigits
    println(masked)

//    4. Форматирование адреса электронной почты.
//    У вас есть электронный адрес, например "username@example.com".
//    Преобразуйте его в строку "username [at] example [dot] com", используя функцию replace()
    println("Example 4")
    val eMail = "username@example.com"
    val form = eMail.replace("@", " [at] ").replace(".", " [dot] ")
    println(form)

//    5. Извлечение имени файла из пути.
//    Дан путь к файлу, например "C:/Пользователи/Документы/report.txt" или
//    "D:/good.themes/dracula.theme" (может быть любым).Извлеките название файла с расширением.
    println("Example 5")
    val path = "D:/good.themes/dracula.theme"
    val lastIndex = path.lastIndexOf('/')
    val fileName = path.substring(lastIndex + 1)
    println(fileName)

//    6. Создание аббревиатуры из фразы.
//    У вас есть фраза, например "Котлин лучший язык программирования" (может быть любой с разделителями слов - пробел).
//    Создайте аббревиатуру из начальных букв слов (например, "ООП").
//    Используйте split. Используйте for для перебора слов. Используйте var переменную для накопления первых букв.
    println("Example 6")
    val phrase = "Котлин лучший язык программирования"
    var abb = ""
    for (word in phrase.split(" ")){
        abb = abb + word[0]
    }
    println(abb.uppercase())

}