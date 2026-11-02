package org.example.lessons.lesson16.homework

import kotlin.math.PI
import kotlin.math.sin
//Переопределение методов

//1. Животные и Их Звуки
//    1. Базовый класс: Animal с методом makeSound().
    open class Animal {
    //    3. Базовый метод makeSound() в классе Animal выводит "This animal makes no sound."
        open fun makeSound() {
            println("This animal makes no sound.")
        }
    }
//    2. Классы наследники: Dog, Cat, Bird.
//    4. Переопределите метод makeSound() в каждом классе-наследнике, чтобы Dog выводил "Bark", Cat - "Meow", Bird - "Tweet". Обогати вывод звуков цветом.
class Dog : Animal() {
    override fun makeSound() {
        println("\u001B[33mBark\u001B[0m") // жёлтый
    }
}

class Cat : Animal() {
    override fun makeSound() {
        println("\u001B[35mMeow\u001B[0m") // фиолетовый
    }
}

class Bird : Animal() {
    override fun makeSound() {
        println("\u001B[36mTweet\u001B[0m") // голубой
    }
}

//    5. Создайте список животных List<Animal> и вызовите в цикле метод makeSound()
    val animals: List<Animal> = listOf(Dog(), Cat(), Bird(), Animal())


//2. Геометрические Фигуры и Их Площадь
//    1. Абстрактный (базовый) класс: Shape с open методом area().
    open class Shape {
        //    3. Метод area() возвращает 0.0.
        open fun area(): Double = 0.0
}
//    2. Классы наследники: Circle, Square, Triangle.
    class Circle(private val radius: Double) : Shape() {
    //    4. Переопределите метод area() в каждом классе-наследнике для расчета площади соответствующей фигуры.
        override fun area(): Double = PI * radius * radius
}

// Квадрат: сторона
    class Square(private val side: Double) : Shape() {
    //    4. Переопределите метод area() в каждом классе-наследнике для расчета площади соответствующей фигуры.
        override fun area(): Double = side * side
}

// Треугольник: две стороны и угол между ними (в градусах)
    class Triangle(
        private val a: Double,
        private val b: Double,
        private val angleDegrees: Double
    ) : Shape() {
    //    4. Переопределите метод area() в каждом классе-наследнике для расчета площади соответствующей фигуры.
    override fun area(): Double =
        0.5 * a * b * sin(Math.toRadians(angleDegrees))
    }
//    5. Подсказка: каждый класс должен иметь аргументы конструктора, позволяющие вычислить площадь (для круга это радиус, для квадрата это сторона,
//    для треугольника это две стороны и угол между ними)
//    6. Создай набор геометрических фигур и выведи в цикле их площадь
    //IN MANE
//3. Принтер
//    1. Создай абстрактный принтер, который имеет абстрактный метод печати, принимающий в качестве аргумента строку.
    abstract class Printer {
        abstract fun print(text: String)
}
//    2. Создай классы наследники: лазерный и струйный
class LaserPrinter : Printer() {
    override fun print(text: String) {
//    3. Лазерный принтер должен разбивать фразу на слова по пробелу и выводить каждое слово отдельно чёрными буквами на белом фоне (хорошо работает на тёмной теме)
        text.split(" ").forEach { word ->
            println("\u001B[30;47m $word \u001B[0m")
        }
    }
}
class InkjetPrinter : Printer() {
    private val colors: List<Pair<Int, Int>> = listOf(
        31 to 47,  // красный на белом
        32 to 40,  // зелёный на чёрном
        33 to 44,  // жёлтый на синем
        34 to 43,  // синий на жёлтом
        35 to 46,  // фиолетовый на голубом
        36 to 41   // голубой на красном
    )

    override fun print(text: String) {
        val words = text.split(" ")
        words.forEachIndexed { index, word ->
//    4. Струйный принтер должен разбивать фразу на слова по пробелу и выводить каждое слово отдельно цветными буквами на цветном фоне.
            //    Слова должны быть разноцветны. Можно сделать список пар с цветом текста и фона и проходить циклически по этому списку при выводе текста.
            //    Проверить работу на длинном тексте
            val (fg, bg) = colors[index % colors.size]
            println("\u001B[$fg;${bg}m $word \u001B[0m")
        }
    }
}


//Перегрузка методов
//4. Класс “Корзина товаров”: содержит поле словаря из ID товаров и их количества.
    class ShoppingCart {
        private val items: MutableMap<String, Int> = mutableMapOf()
//    1. Базовый метод addToCart(itemId) добавляет одну единицу товара к уже существующим в корзине.
    fun addToCart(itemId: String) {
        items[itemId] = (items[itemId] ?: 0) + 1
    }
//    2. Выполнить перегрузку addToCart который:
//        1. Принимает два аргумента (itemId и количество amount)
    fun addToCart(itemId: String, amount: Int) {
        require(amount > 0) { "Количество должно быть положительным: $amount" }
        items[itemId] = (items[itemId] ?: 0) + amount
}
//        2. Принимает словарь из id и количества и добавляет всё в корзину
    fun addToCart(batch: Map<String, Int>) {
        batch.forEach { (id, amount) -> addToCart(id, amount) }
    }
//        3. Принимает список из id (добавляет по одной единице).
    fun addToCart(ids: List<String>) {
        ids.forEach { id -> addToCart(id) }
    }
//    3. Учесть, что если товар уже есть в корзине, нужно увеличить его количество, если нет - добавить.
//    4. Попробуй создать корзину и положить в неё товар разными способами, потом распечатать корзину в консоль.
    // IN MANE
//    5. Переопредели у корзины метод toString для красивого форматирования содержимого таблицы, включая итоговое количество артикулов и общее количество всего товара в корзине.
override fun toString(): String {
    if (items.isEmpty()) return "Корзина пуста"

    val idWidth = maxOf("ID товара".length, items.keys.maxOf { it.length })
    val qtyWidth = maxOf("Кол-во".length, items.values.maxOf { it.toString().length })

    val line = "+-${"-".repeat(idWidth)}-+-${"-".repeat(qtyWidth)}-+"
    val sb = StringBuilder()

    sb.appendLine("Корзина товаров:")
    sb.appendLine(line)
    sb.appendLine("| ${"ID товара".padEnd(idWidth)} | ${"Кол-во".padEnd(qtyWidth)} |")
    sb.appendLine(line)
    for ((id, qty) in items) {
        sb.appendLine("| ${id.padEnd(idWidth)} | ${qty.toString().padEnd(qtyWidth)} |")
    }
    sb.appendLine(line)

    val totalArticles = items.size          // уникальные артикулы
    val totalItems = items.values.sum()     // всего единиц товара
    sb.appendLine("Артикулов: $totalArticles, всего товара: $totalItems")

    return sb.toString()
}
}
//5. Класс “Логгер”: представляет из себя инструмент для вывода информации в консоль с разным префиксами уровня логирования (INFO, WARNING, ERROR, DEBUG)
    enum class LogLevel {
        INFO, WARNING, ERROR, DEBUG
    }
    class Logger {
//    1. Базовый метод log(message) выводит в консоль сообщение message с префиксом INFO
    fun log(message: String) {
        log(LogLevel.INFO, message)
    }
//    2. Выполнить перегрузку метода log, который:
//        1. Принимает уровень логирования и сообщение. Выводит сообщения типа WARNING жёлтым цветом, а ERROR белым цветом на красном фоне.
    fun log(level: LogLevel, message: String) {
        val prefix = "[$level]"
        when (level) {
            LogLevel.INFO    -> println("\u001B[36m$prefix\u001B[0m $message")           // голубой
            LogLevel.DEBUG   -> println("\u001B[90m$prefix\u001B[0m $message")           // серый
            LogLevel.WARNING -> println("\u001B[33m$prefix $message\u001B[0m")           // жёлтый
            LogLevel.ERROR   -> println("\u001B[37;41m$prefix $message\u001B[0m")        // белый на красном
        }
    }
//        2. Принимает список сообщений и все их выводит с уровнем INFO
    fun log(messages: List<String>) {
        messages.forEach { log(LogLevel.INFO, it) }
    }
//        3. Принимает объект типа Exception и выводит его поле message с префиксом уровня ERROR
    fun log(exception: Exception) {
        log(LogLevel.ERROR, exception.message ?: "Unknown error")
    }
        }
//    3. Попробуй создать логгер и добавить в него сообщения разными способами. Проверь вывод в консоли.


@Suppress("DIVISION_BY_ZERO")
fun main() {
    for (animal in animals) {
        animal.makeSound()
    }
//    3.6. Создай набор геометрических фигур и выведи в цикле их площадь
    val shapes: List<Shape> = listOf(
        Circle(5.0),
        Square(4.0),
        Triangle(6.0, 8.0, 30.0),
        Shape() // проверим базовое поведение — 0.0
    )

    for (shape in shapes) {
        println("${shape::class.simpleName}: area = ${"%.2f".format(shape.area())}")
    }
    val laser: Printer = LaserPrinter()
    val inkjet: Printer = InkjetPrinter()

    val shortText = "Hello Kotlin World"
    val longText = """
        Kotlin is a modern statically typed programming language
        that runs on the Java Virtual Machine and can also be
        compiled to JavaScript or native code. It is concise safe
        and fully interoperable with Java. Many Android applications
        are written in Kotlin because it reduces boilerplate code
        and helps developers avoid common programming errors
    """.trimIndent().replace("\n", " ").replace(Regex("\\s+"), " ")

    println("=== Лазерный принтер (короткий текст) ===")
    laser.print(shortText)

    println("\n=== Струйный принтер (короткий текст) ===")
    inkjet.print(shortText)

    println("\n=== Лазерный принтер (длинный текст) ===")
    laser.print(longText)

    println("\n=== Струйный принтер (длинный текст) ===")
    inkjet.print(longText)

//#4
    val cart = ShoppingCart()
    cart.addToCart("apple")                                  // 1 шт
    cart.addToCart("banana", 5)                    // 5 шт
    cart.addToCart(mapOf("orange" to 3, "apple" to 2))       // orange=3, apple += 2 → 3
    cart.addToCart(listOf("banana", "kiwi", "kiwi", "banana")) // banana += 2 → 7, kiwi=2

//    4.4 Попробуй создать корзину и положить в неё товар разными способами, потом распечатать корзину в консоль.
    println(cart)

//    #5
    val logger = Logger()

    // Разные способы логирования
    logger.log("Сервис запущен")
    logger.log(LogLevel.DEBUG, "Подключение к БД: localhost:5432")
    logger.log(LogLevel.WARNING, "Мало свободной памяти: 128 MB")
    logger.log(LogLevel.ERROR, "Не удалось записать файл config.json")

    logger.log(listOf(
        "Загрузка конфигурации",
        "Инициализация кэша",
        "Запуск фоновых задач"
    ))

    try {
        val x = 10 / 0
        println(x)
    } catch (e: ArithmeticException) {
        logger.log(e)
    }

    logger.log(IllegalStateException("Некорректное состояние сессии"))
}