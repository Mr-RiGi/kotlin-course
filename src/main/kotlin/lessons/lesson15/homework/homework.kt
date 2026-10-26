package org.example.lessons.lesson15.homework


fun main() {
    println("Example 1.1")
    val circle = Circle(5.0)
    circle.info()

    println("Example 1.2")

    val triangle = Triangle(3.0, 4.0, 5.0)
    triangle.info()

    println("Example 1.3")

    val quad = Quadrilateral(2.0, 3.0, 4.0, 5.0)
    quad.info()
    println()

    println("Example 2.2")
    val school = School("Школа №15", "ул. Ленина, 10", 11)
    school.info()

    println()

    val university = University("МГУ", "Ленинские горы, 1", 15)
    university.info()

    println("Example 2.3")

    val faculty = Faculty("МГУ", "Ленинские горы, 1", 15, "Факультет ВМК", 1200)
    faculty.info()

    println()

    println("Example 3.1")
    val cabinet = Cabinet("Шкаф-купе", "ДСП", 2)
    cabinet.info()

    println("Example 3.2")

    val bookshelf = Bookshelf("Библиотека", "дуб", 2, 5)
    bookshelf.info()

    println("Example 3.3")

    val table = Table("Обеденный стол", "стекло", 6)
    table.info()

    println()

    println("=== Example 4 ===")
    val bottom = BottomInsertContainer()
    bottom.addMaterial("Дерево")
    bottom.insertAtBottom("Металл")
    bottom.insertAtBottom("Пластик")
    bottom.printContainer()
    // [0]: Пластик, [1]: Металл, [2]: Дерево

    println("\n=== Example 5 ===")
    val interleaved = InterleavedContainer()
    interleaved.addMaterial("A")
    interleaved.addMaterial("B")
    interleaved.addMaterials(listOf("X", "Y", "Z"))
    interleaved.printContainer()
    // [0]: X, [1]: A, [2]: Y, [3]: B, [4]: Z

    println("\n=== Example 6===")
    val sorted = SortedContainer()
    sorted.addSorted("Металл")
    sorted.addSorted("Дерево")
    sorted.addSorted("Пластик")
    sorted.printContainer()
    // [0]: Дерево, [1]: Металл, [2]: Пластик

    println("\n===Example 7 ===")
    val dict = DictContainer()
    dict.addMaterial("Существующий1")
    dict.addMaterial("Существующий2")
    dict.addDictionary(mapOf("Ключ1" to "Значение1", "Ключ2" to "Значение2"))
    dict.printContainer()
}


//Для каждого задания опиши в коде базовый класс с базовыми свойствами и если применимо - методами.
//
//Для производных классов также добавь специфичные для класса свойства. Детально описывать не нужно, упражнение на то, чтобы усвоить иерархию наследования и развитие функциональности у наследников.
//
//    1. Геометрические Фигуры
//        1. Базовый класс: Геометрическая Фигура
    open class Shape(val name: String) {
        open fun info() {
            println("Фигура: $name")
        }
    }
//            2. Производные классы: Многоугольник, Круг
class Circle(val radius: Double) : Shape("Круг") {
    fun area(): Double = Math.PI * radius * radius

    override fun info() {
        super.info()
        println("Радиус: $radius")
        println("Площадь: ${"%.2f".format(area())}")
    }
}
//                3. Дополнительное разветвление для Многоугольник: Треугольник, Четырехугольник
open class Polygon(val sides: Int) : Shape("Многоугольник") {
    override fun info() {
        super.info()
        println("Количество сторон: $sides")
    }
}

class Triangle(val a: Double, val b: Double, val c: Double) : Polygon(3) {
    override fun info() {
        super.info()
        println("Тип: Треугольник")
        println("Стороны: $a, $b, $c")
    }
}

class Quadrilateral(val a: Double, val b: Double, val c: Double, val d: Double) : Polygon(4) {
    override fun info() {
        super.info()
        println("Тип: Четырехугольник")
        println("Стороны: $a, $b, $c, $d")
    }
}
//    2. Учебные Заведения
//        1. Базовый класс: Учебное Заведение
open class EducationalInstitution(val name: String, val address: String) {
    open fun info() {
        println("Учебное заведение: $name")
        println("Адрес: $address")
    }
}
//            2. Производные классы: Школа, …
class School(name: String, address: String, val grades: Int) : EducationalInstitution(name, address) {
    override fun info() {
        super.info()
        println("Тип: Школа")
        println("Классы: с 1 по $grades")
    }
}

open class University(name: String, address: String, val facultiesCount: Int) : EducationalInstitution(name, address) {
    override fun info() {
        super.info()
        println("Тип: Университет")
        println("Количество факультетов: $facultiesCount")
    }
}
//                3. Дополнительное разветвление для …: …
class Faculty(
    name: String,
    address: String,
    facultiesCount: Int,
    val facultyName: String,
    val studentsCount: Int
) : University(name, address, facultiesCount) {
    override fun info() {
        super.info()
        println("Факультет: $facultyName")
        println("Студентов на факультете: $studentsCount")
    }
}
//    3. Иерархия Мебели
//        1. Базовый класс: Мебель
abstract class Furniture(val name: String, val material: String) {
    abstract fun purpose(): String

    open fun info() {
        println("Мебель: $name")
        println("Материал: $material")
        println("Назначение: ${purpose()}")
    }
}
//            2. Производные классы: …
open class Cabinet(name: String, material: String, val doorsCount: Int) : Furniture(name, material) {
    override fun purpose(): String = "Хранение вещей"

    override fun info() {
        super.info()
        println("Тип: Шкаф")
        println("Количество дверей: $doorsCount")
    }
}
//                3. Дополнительное разветвление для …: …
class Bookshelf(name: String, material: String, doorsCount: Int, val shelvesCount: Int) :
    Cabinet(name, material, doorsCount) {

    override fun purpose(): String = "Хранение книг"

    override fun info() {
        super.info()
        println("Тип: Книжный шкаф")
        println("Количество полок: $shelvesCount")
    }
}
class Table(name: String, material: String, val seatsCount: Int) : Furniture(name, material) {
    override fun purpose(): String = "Рабочая поверхность"

    override fun info() {
        super.info()
        println("Тип: Стол")
        println("Количество мест: $seatsCount")
    }
}


//Это класс контейнера. Он абстрактный, это означает, что нельзя создавать экземлпяр этого класса, он нужен только для того, чтобы быть основой для подклассов.
//
//Создай подклассы этого контейнера, которые будут добавлять строку material (или коллекции или словари строк) в какое-то определённое место (места).
//

abstract class Materials {
    private val materials = mutableListOf<String>()
    fun addMaterial(material: String) {
        materials.add(material)
    }
    fun extractMaterials(): List<String> {
        val extracted = materials.toList()
        materials.clear()
        return extracted
    }
    fun printContainer() {
        materials.forEachIndexed { index, layer ->
            println("[$index]: $layer")
        }
    }
}
//    4. Первый класс должен вставлять строку на дно контейнера (должно появиться под индексом 0)
class BottomInsertContainer : Materials() {
    fun insertAtBottom(material: String) {
        val existing = extractMaterials()          // забираем всё содержимое
        addMaterial(material)                      // кладём новый на дно
        existing.forEach { addMaterial(it) }       // возвращаем остальные сверху
    }
}
//    5. Второй класс должен получать список строк и вставлять их в начало контейнера но поочерёдно с теми данными, которые уже есть. То-есть, наш список должен появиться в контейнере по индексам 0, 2, 4 и так далее.
class InterleavedContainer : Materials() {
    fun addMaterials(newMaterials: List<String>) {
        val existing = extractMaterials()
        val maxSize = maxOf(existing.size, newMaterials.size)
        for (i in 0 until maxSize) {
            if (i < newMaterials.size) addMaterial(newMaterials[i])
            if (i < existing.size) addMaterial(existing[i])
        }
    }
}
//    6. Третий класс при добавлении строки должен отсортировать весь список метиралов в алфавитном порядке включая добавляемый материал.
class SortedContainer : Materials() {
    fun addSorted(material: String) {
        val existing = extractMaterials()
        val all = existing + material
        all.sorted().forEach { addMaterial(it) }
    }
}
//    7. Четвёртый класс должен принимать словарь (ключи и значения строки). Каждая пара ключа и значения должна попадать в контейнер следующим образом - ключ в начало, значение в конец. Таким образом в начале должны быть ключи в обратном порядке, потом существующие материалы, потом все значения.
class DictContainer : Materials() {
    fun addDictionary(dictionary: Map<String, String>) {
        val existing = extractMaterials()
        val keys = dictionary.keys.toList().reversed()
        val values = dictionary.values.toList()

        keys.forEach { addMaterial(it) }
        existing.forEach { addMaterial(it) }
        values.forEach { addMaterial(it) }
    }
}
