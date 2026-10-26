package org.example.lessons.lesson14.homework


fun main() {
    println("Example 1")
    val party = Party("Москва, ул. Тверская, 15", 25)
    party.details()

    println("Example 2")
    val joy = Emotion("радость", 8)
    val anger = Emotion("гнев", 10)
    val calm = Emotion("спокойствие", 2)

    joy.express()
    anger.express()
    calm.express()

    println("Example 3")
    Moon.showPhase()

    println("Example 4")
    val apple = Product("Яблоко", 0.5, 10)
    println(apple)

    println("Example 5")
    val concert = Concert("Кино", "Олимпийский", 35000, 1500)
    concert.printDetails()
    concert.buyTicket()
    concert.printDetails()

    println("Example 6")
    val fridge = Fridge(3)

    // Создаем полки разной вместимости (сумма длин названий продуктов)
    val topShelf = FridgeShelf(20)
    val middleShelf = FridgeShelf(25)
    val bottomShelf = FridgeShelf(15)

    // Добавляем полки
    fridge.addShelf(topShelf)
    fridge.addShelf(middleShelf)
    fridge.addShelf(bottomShelf)

    println("Добавление 4-й полки: ${fridge.addShelf(FridgeShelf(10))}")

    // Добавляем продукты
    println("--- Добавление продуктов ---")
    println(fridge.addProduct("Молоко"))       // на верхнюю полку
    println(fridge.addProduct("Сыр"))          // на верхнюю полку
    println(fridge.addProduct("Колбаса"))      // на среднюю полку
    println(fridge.addProduct("Яйца"))         // на среднюю полку
    println(fridge.addProduct("Хлеб"))         // на нижнюю полку

    // Выводим содержимое
    println("--- Содержимое холодильника ---")
    fridge.printContents()

    // Проверяем наличие
    println("Есть ли Сыр? ${fridge.containsProduct("Сыр")}")

    // Удаляем продукт
    println("Удаление Колбасы: ${fridge.removeProduct("Колбаса")}")

    // Удаляем среднюю полку (индекс 1) - продукты возвращаются
    println("--- Удаление средней полки ---")
    val removedProducts = fridge.removeShelf(1)
    println("Возвращенные продукты: $removedProducts")

    // Выводим содержимое после удаления
    println("--- Содержимое после удаления полки ---")
    fridge.printContents()

    // Сложное удаление полки с перераспределением продуктов
    println("--- Сложное удаление верхней полки (индекс 0) ---")
    val notReplaced = fridge.advancedRemoveShelf(0)
    println("Не удалось разместить: $notReplaced")

    println("--- Финальное содержимое ---")
    fridge.printContents()
}

    //    1. Событие: вечеринка. Создайте класс Party, который описывает вечеринку. У него должны быть свойства location (String) и attendees (Int).
//    Добавьте метод details(), который выводит информацию о месте проведения и количестве гостей.
    class Party(val location: String, val attendees: Int) {
    fun details() {
        println("Место проведения: $location")
        println("Количество гостей: $attendees")
    }
    }

//    println("Example 2")
//    2. Аспект реальности: эмоция. Создайте класс Emotion, который представляет эмоцию. У него должно быть свойство type (String) и intensity (Int).
//    Добавьте метод express(), который выводит описание эмоции в зависимости от её типа и интенсивности.

    class Emotion(val type: String, val intensity: Int) {
    fun express() {
        if (intensity !in 1..10) {
            println("Некорректная интенсивность: $intensity (должна быть от 1 до 10)")
            return
        }

        val description = when (type.lowercase()) {
            "радость"   -> "Я чувствую радость"
            "грусть"    -> "Мне грустно"
            "гнев"      -> "Меня переполняет гнев"
            "страх"     -> "Мне страшно"
            "удивление" -> "Я удивлён"
            "любовь"    -> "Я чувствую любовь"
            else        -> "Я испытываю эмоцию: $type"
        }

        val intensifier = when (intensity) {
            in 1..3  -> "слабо"
            in 4..6  -> "умеренно"
            in 7..8  -> "сильно"
            else     -> "очень сильно"
        }

        println("$description — $intensifier (интенсивность: $intensity/10)")
    }
    }

//    println("Example 3")
//    3. Природное явление: луна. Создайте объект Moon, который будет представлять Луну. Добавьте свойства isVisible (Boolean), чтобы отображать,
//    видна ли Луна в данный момент, и phase (String), чтобы отображать текущую фазу Луны (например, "Full Moon", "New Moon"). Добавьте метод showPhase(),
//    который выводит текущую фазу Луны.
// Создаём объект Moon, представляющий Луну
    object Moon {
    var isVisible: Boolean = true
    var phase: String = "Full Moon"

    fun showPhase() {
        if (isVisible) {
            println("The moon is visible, and it's currently a $phase.")
        } else {
            println("The moon is not visible right now. Current phase: $phase.")
        }
    }
    }
//    4. Покупка: продукт. Создайте дата класс для продукта, который будет представлять продукт в магазине.
//    У него должны быть свойства “название”, “цена”, “количество”.

    data class Product(val name: String, val price: Double, val quantity: Int) {
    fun info() {
        println("Продукт: $name")
        println("Цена: $price руб.")
        println("Количество: $quantity шт.")
        println("Общая стоимость: ${price * quantity} руб.")
    }
    }

//    5. Мероприятие: концерт. Создайте класс, который будет представлять концерт. У него должны быть свойства “группа”, “место проведения”,
//    “стоимость”, “вместимость зала”. Также приватное поле “проданные билеты”. Добавьте метод,
//    который выводит информацию о концерте и метод “купить билет”, который увеличивает количество проданных билетов на один.
class Concert(val group: String, val place: String, val cost: Int, val capacity: Int) {

    private var ticketsSold = 0

    fun buyTicket() {
        if (ticketsSold < capacity) {
            ticketsSold++
        }
    }

    fun printDetails() {
        println("Концерт группы $group в $place. Стоимость билета $cost. Осталось ${capacity - ticketsSold} мест")
    }
}

//    Задание 6. Стеллаж и полки.
//
//    Цель задания: Создать систему управления складским пространством с использованием классов "Стеллаж" и "Полка стеллажа".
//
//    Класс Shelf (Полка Стеллажа)
//
//    Характеристики:
//
//    Вместимость полки (capacity): максимальная сумма букв всех названий предметов, которые могут быть размещены на полке.
//    Список предметов (items): хранит названия предметов на полке.
//    Методы:
//
//Добавление Предмета (addItem):
//Принимает название предмета.
//Проверяет, поместится ли предмет на полку, учитывая длину его названия и текущее заполнение полки.
//Возвращает true и добавляет предмет, если есть место. В противном случае возвращает false.
//Удаление Предмета (removeItem):
//Принимает название предмета.
//Удаляет предмет, если он найден на полке.
//Возвращает true, если предмет был удален, и false, если такой предмет не найден.
//Проверка Вместимости (canAccommodate):
//Принимает название предмета.
//Определяет, вместится ли предмет на полку.
//Возвращает true, если предмет вместится, и false, если места недостаточно.
//Проверка наличия предмета (containsItem):
//Принимает название предмета
//Возвращает true если такой предмет есть
//Получение списка предметов (getItems):
//Возвращает неизменяемый список предметов
//Класс Rack (Стеллаж)
//
//Характеристики:
//
//Список полок (shelves): хранит полки стеллажа.
//Максимальное количество полок.
//Методы:
//
//Добавление Полки (addShelf):
//Добавляет новую полку в стеллаж.
//Возвращает true, если полка была успешно добавлена или false если стеллаж уже заполнен или была попытка добавить полку которая уже установлена.
//Удаление Полки (removeShelf):
//Принимает индекс полки для удаления.
//Удаляет полку по указанному индексу.
//Возвращает список предметов полки, если полка была успешно удалена или пустой список если полка не существует.
//Добавление Предмета (addItem):
//Добавляет предмет на первую свободную полку.
//Возвращает true, если предмет успешно добавлен, и false, если на всех полках недостаточно места.
//Удаление Предмета (removeItem):
//Находит и удаляет только один предмет с любой полки.
//Возвращает true, если предмет был удален, и false, если такой предмет не найден.
//Проверка наличия предмета на стеллаже (containsItem):
//Возвращает true если предмет есть на одной из полок
//Получение списка полок (getShelves):
//Возвращает неизменяемый список полок
//Печать Содержимого (printContents):
//Выводит в консоль информацию о каждой полке: индекс, вместимость, оставшуюся вместимость, список предметов. Информацию выводить в наглядном читаемом виде
//Сложное удаление полки (advancedRemoveShelf):
//Принимает индекс полки для удаления
//Перераспределяет предметы по имеющимся полкам, начиная с самых длинных. Если очередной предмет никуда не вмещается, его нужно пропустить и попробовать разместить следующий.
//Удаляет полку с оставшимися предметами
//Возвращает неизменяемый список предметов, которые не удалось удалить или пустой список если полки с таким индексом нет.
class FridgeShelf(val capacity: Int) {

    private val products = mutableListOf<String>()

    fun addProduct(name: String): Boolean {
        if (!canAccommodate(name)) {
            return false
        }
        products.add(name)
        return true
    }

    fun removeProduct(name: String): Boolean {
        if (!products.contains(name)) {
            return false
        }
        products.remove(name)
        return true
    }

    fun canAccommodate(name: String): Boolean {
        val currentAccommodation = products.map {
            it.length
        }.sum()
        return currentAccommodation + name.length <= capacity
    }

    fun containsProduct(name: String) = products.contains(name)

    fun getProducts() = products.toList()
}

class Fridge(private val maxShelves: Int) {

    private val shelves = mutableListOf<FridgeShelf>()

    fun addShelf(shelf: FridgeShelf): Boolean {
        if (shelves.contains(shelf) || shelves.size == maxShelves) {
            return false
        }
        shelves.add(shelf)
        return true
    }

    fun removeShelf(index: Int): List<String> {
        if (index !in shelves.indices) {
            return emptyList()
        }
        val products = shelves[index].getProducts()
        shelves.removeAt(index)
        return products
    }

    fun addProduct(name: String): Boolean {
        if (shelves.isEmpty()) {
            return false
        }
        for (shelf in shelves) {
            if (shelf.canAccommodate(name)) {
                return shelf.addProduct(name)
            }
        }
        return false
    }

    fun removeProduct(name: String): Boolean {
        for (shelf in shelves) {
            if (shelf.containsProduct(name)) {
                return shelf.removeProduct(name)
            }
        }
        return false
    }

    fun containsProduct(name: String): Boolean {
        return shelves.any { it.containsProduct(name) }
    }

    fun getShelves() = shelves.toList()

    fun printContents() {
        for (i in shelves.indices) {
            val capacity = shelves[i].capacity
            val accessiblePlace = capacity - shelves[i].getProducts()
                .map { it.length }
                .sum()
            val meta = "$i вместимость: $capacity доступно: $accessiblePlace"
            val products = shelves[i].getProducts().joinToString()
            println(meta)
            println("Продукты: $products")
            println()
        }
    }

    fun advancedRemoveShelf(index: Int): List<String> {
        if (index !in shelves.indices) {
            return emptyList()
        }
        val notReplacedProducts = mutableListOf<String>()
        val otherShelves = shelves - shelves[index]
        for (product in shelves[index].getProducts()) {
            if (otherShelves.none { it.addProduct(product) }) {
                notReplacedProducts.add(product)
            }
        }
        shelves.removeAt(index)
        return notReplacedProducts.toList()
    }
}
