package org.example.lessons.lesson17.homework

//1. Игровой автомат
//Опиши схематически класс игрового автомата, правильно расставив объявление полей (val или var) и методов (аргументы и возвращаемые значения), включая модификаторы доступа (private для приватных)
//
//Поля:
//
//цвет
//модель
//включен
//ОС загружена
//список установленных игр
//наличие джойстика
//баланс вырученных средств
//владелец
//телефон поддержки.
//игровой сеанс оплачен
//стоимость одного игрового сеанса
//пин-код автомата
//Методы:
//
//включить
//выключить
//загрузить ОС
//завершить работу ОС
//показать список игр
//включить игру
//оплатить игровой сеанс
//забрать наличные с помощью пин-кода
//открыть сейф и выдать наличные
class SlotMachine(
    // Постоянные характеристики (не меняются после сборки)
    val color: String,
    val model: String,
    val hasJoystick: Boolean,

    // Изменяемые административные данные (доступны для чтения/записи)
    var owner: String,
    var supportPhone: String,
    var sessionCost: Double,

    // Приватные неизменяемые данные защиты
    private val pinCode: String
) {
    // Внутреннее состояние автомата (изменяется только методами класса)
    var isPoweredOn: Boolean = false
        private set // читать можно снаружи, изменять — только внутри класса

    var isOsLoaded: Boolean = false
        private set

    var isSessionPaid: Boolean = false
        private set

    var revenueBalance: Double = 0.0
        private set

    // Список игр (val, так как сам список не пересоздается, но его содержимое можно менять)
    val installedGames: MutableList<String> = mutableListOf()

    // --- МЕТОДЫ УПРАВЛЕНИЯ ПИТАНИЕМ И ОС ---

    fun powerOn() {
        if (!isPoweredOn) {
            isPoweredOn = true
            println("Автомат включен.")
            loadOs() // Автоматически загружаем ОС при включении
        }
    }

    fun powerOff() {
        if (isPoweredOn) {
            shutdownOs() // Безопасно завершаем работу ОС перед выключением
            isPoweredOn = false
            isSessionPaid = false
            println("Автомат выключен.")
        }
    }

    // Приватные методы, так как загрузка ОС — это внутренний процесс автомата
    private fun loadOs() {
        if (isPoweredOn && !isOsLoaded) {
            isOsLoaded = true
            println("Операционная система успешно загружена.")
        }
    }

    private fun shutdownOs() {
        if (isOsLoaded) {
            isOsLoaded = false
            println("Работа операционной системы завершена.")
        }
    }

    // --- ИГРОВОЙ ПРОЦЕСС ---

    // Возвращает список строк для вывода на экран
    fun getGamesList(): List<String> {
        return if (isOsLoaded) installedGames else emptyList()
    }

    // Возвращает Boolean (успешно ли запустилась игра)
    fun startGame(gameName: String): Boolean {
        if (!isOsLoaded) {
            println("Ошибка: ОС не загружена.")
            return false
        }
        if (!isSessionPaid) {
            println("Ошибка: Игровой сеанс не оплачен.")
            return false
        }
        if (!installedGames.contains(gameName)) {
            println("Ошибка: Игра '$gameName' не найдена.")
            return false
        }

        println("Запуск игры '$gameName'. Приятной игры!")
        isSessionPaid = false // Сеанс использован
        return true
    }

    // Принимает сумму от игрока. Возвращает сдачу (если внесено больше) или -1.0 при нехватке средств
    fun payForSession(amountInserted: Double): Double {
        if (!isOsLoaded) {
            println("Автомат временно не работает.")
            return amountInserted
        }
        if (amountInserted < sessionCost) {
            println("Недостаточно средств для оплаты сеанса.")
            return -1.0
        }

        isSessionPaid = true
        revenueBalance += sessionCost
        val change = amountInserted - sessionCost
        println("Сеанс успешно оплачен.")
        return change
    }

    // --- ИНКАССАЦИЯ (РАБОТА С ДЕНЬГАМИ) ---

    // Публичный метод для инкассации. Возвращает сумму забранных денег.
    fun collectCash(enteredPin: String): Double {
        if (enteredPin != pinCode) {
            println("Ошибка: Неверный пин-код! Доступ заблокирован.")
            return 0.0
        }

        // Если пин-код верный, вызываем внутренний механизм открытия сейфа
        return openSafeAndDispenseCash()
    }

    // Приватный метод, отвечающий за физическое / аппаратное открытие сейфа
    private fun openSafeAndDispenseCash(): Double {
        val cashToDispense = revenueBalance
        revenueBalance = 0.0
        println("Сейф открыт. Выдано наличных: $cashToDispense")
        return cashToDispense
    }
}

//2. Почемучка
//Нужно скопировать код к себе и постараться ответить на все “почему” в комментариях и заданным образом доработать класс ChildrenClass (BaseClass доработке не подлежит)
//
abstract class BaseClass(
    // 1. объясни, почему это поле доступно в main() для чтения из класса ChildrenClass
    // Ответ: Само поле BaseClass.privateVal в main() НЕ доступно. Однако в ChildrenClass объявлено
    // ОДНОИМЕННОЕ свойство «val privateVal: String», которое перекрывает (shadows) родительский private.
    // Оно публичное по умолчанию, поэтому `children.privateVal` успешно читается в main().
    private val privateVal: String,

    // 2. объясни, почему это поле недоступно в main()
    // Ответ: Модификатор `protected` ограничивает видимость свойства: оно доступно только внутри
    // самого класса и в его классах-наследниках. Внешний код (включая функцию main()) доступа к нему не имеет.
    protected val protectedVal: String,
    val publicVal: String
) {
    var publicField = "3. измени меня из функции main() на Антонио Бандераса и проверь через функцию getAll()" +
            "4. Доработай ChildrenClass таким образом, чтобы это получилось"
        set(value) {
            if (verifyPublicField(value)) {
                field = value
            }
        }
    protected var protectedField = "5. измени меня из функции main() через сеттер в наследнике"
    private var privateField = "6. добавь сеттер чтобы изменить меня из main()"

    // Дополнительный геттер/сеттер для демонстрации работы с privateField (для пункта 6)
    fun getPrivateFieldCustom() = privateField
    fun setPrivateFieldCustom(value: String) {
        privateField = value
    }

    fun getAll(): String {
        return mapOf(
            "privateVal" to privateVal,
            "protectedVal" to protectedVal,
            "publicVal" to publicVal,
            "publicField" to publicField,
            "protectedField" to protectedField,
            "privateField" to privateField,
            "generate" to generate(),
        ).map { "${it.key}: ${it.value}" }
            .joinToString("\n")
    }

    fun printText() {
        privatePrint()
    }

    // 7. объясни, почему эта функция не может быть публичной
    // Ответ: Потому что она возвращает тип `ProtectedClass`, который имеет видимость `protected`.
    // Публичная функция не может возвращать тип с меньшей видимостью (protected или private),
    // так как внешний код не сможет работать с типом возвращаемого значения. В Kotlin это запрещено компилятором.
    protected open fun getProtectedClass() = ProtectedClass()

    protected open fun verifyPublicField(value: String): Boolean {
        return value.length < 3
    }

    // 8. Распечатай getAll() и объясни, почему в поле "generate" другой текст
    // Ответ: Происходит полиморфизм. Метод `generate()` объявлен как `open` в родителе и переопределен (`override`)
    // в дочернем классе. Когда `getAll()` вызывается у объекта ChildrenClass, Kotlin вызывает переопределенную версию
    // метода из дочернего класса, поэтому выводится «Это генерация из дочернего класса».
    open fun generate(): String {
        return "Это генерация из родительского класса"
    }

    private fun privatePrint() {
        println("Печать из класса BaseClass")
    }

    // 9. объясни, почему эта функция не может быть публичной или protected
    // Ответ: Аналогично пункту 7. Она возвращает `PrivateClass`, который является приватным (private).
    // Экспонировать наружу приватный тип через public или protected функции строго запрещено правилами видимости Kotlin.
    private fun getPrivateClass() = PrivateClass()

    protected class ProtectedClass() {}
    private class PrivateClass() {}
}

class ChildrenClass(
    val privateVal: String,
    protectedVal: String,
    // 10. объясни, почему этот аргумент доступен в main() несмотря на то, что это не поле
    // Ответ: В конструкторе ChildrenClass перед `publicVal` нет ключевого слова val/var, то есть
    // здесь это действительно просто параметр конструктора. Но в main() доступно свойство `publicVal`,
    // которое ChildrenClass *наследует* от `BaseClass` (ведь в BaseClass оно объявлено как `val publicVal`).
    publicVal: String
) : BaseClass(privateVal, protectedVal, publicVal) {

    // 11. объясни, почему в main() доступна функция getAll() хотя её здесь нет
    // Ответ: ChildrenClass является наследником BaseClass (`: BaseClass(...)`). По принципам
    // наследования в ООП, все открытые (public) методы родительского класса автоматически становятся
    // доступны у объектов дочернего класса.

    // 12. проверь, что выводится на печать при вызове функции printText()
    // и объясни, почему не происходит переопределение метода privatePrint()
    // Ответ: Выведется "Печать из класса BaseClass". Переопределения не происходит, потому что метод `privatePrint()`
    // в родительском классе помечен как `private`. Приватные методы не видны наследникам, не являются `open`
    // и не участвуют в полиморфизме. Версия `privatePrint()` в ChildrenClass — это просто новая, независимая функция.
    private fun privatePrint() {
        println("Печать из класса ChildrenClass")
    }

    override fun generate(): String {
        return "Это генерация из дочернего класса"
    }

    // --- ДОРАБОТКА КЛАССА CHILDRENCLASS ---

    // Для пункта 4: В BaseClass сеттер publicField проверяет значение через verifyPublicField.
    // В родителе длина строки должна быть < 3 (а "Антонио Бандерас" намного длиннее).
    // Переопределяем валидацию, чтобы она пропускала это имя.
    override fun verifyPublicField(value: String): Boolean {
        return value == "Антонио Бандерас" || super.verifyPublicField(value)
    }

    // Для пункта 5: Создаем публичный сеттер для protectedField, к которому у наследника есть доступ
    fun setProtectedFieldFromMain(value: String) {
        this.protectedField = value
    }

    // Для пункта 6: privateField родителя скрыт полностью. Изменить его напрямую из наследника нельзя.
    // Единственный рабочий способ доработки (раз BaseClass менять нельзя) — это использовать публичные
    // методы родителя, если бы они были, либо (как сделано выше в BaseClass) вызвать метод-мост,
    // либо воспользоваться Reflection (рефлексией).
    // Ниже представлен классический способ изменения private-поля через Kotlin/Java Reflection:
    fun setPrivateFieldViaReflection(value: String) {
        val field = BaseClass::class.java.getDeclaredField("privateField")
        field.isAccessible = true
        field.set(this, value)
    }
}

fun main() {
    val children = ChildrenClass(
        privateVal = "Приватное значение",
        protectedVal = "Защищенное значение",
        publicVal = "Публичное значение"
    )

    println("=== Проверка пункта 1 ===")
    println("children.privateVal: ${children.privateVal}") // Доступно, так как перекрыто в ChildrenClass

    println("\n=== Проверка пункта 12 (вызов printText) ===")
    children.printText() // Выведет текст родителя

    println("\n=== Модификация полей (пункты 3, 4, 5, 6) ===")

    // Пункт 3 и 4: Изменение publicField
    children.publicField = "Антонио Бандерас"

    // Пункт 5: Изменение protectedField через созданный сеттер в наследнике
    children.setProtectedFieldFromMain("Новое значение для protectedField")

    // Пункт 6: Изменение privateField через рефлексию в дочернем классе
    children.setPrivateFieldViaReflection("Новое значение для privateField")

    println("\n=== Итоговый getAll() ===")
    println(children.getAll())
}