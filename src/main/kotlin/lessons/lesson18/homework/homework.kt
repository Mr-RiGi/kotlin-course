package org.example.lessons.lesson18.homework

import java.io.InputStream
import java.io.ByteArrayInputStream

// ============================================================================
// ИСХОДНЫЕ ИНТЕРФЕЙСЫ
// ============================================================================

interface Powerable {
    fun powerOn()
    fun powerOff()
}

interface Openable {
    fun open()
    fun close()
}

interface WaterContainer {
    val capacity: Int
    fun fillWater(amount: Int)
    fun getWater(amount: Int)
}

interface TemperatureRegulatable {
    val maxTemperature: Int
    fun setTemperature(temp: Int)
}

interface WaterConnection {
    fun connectToWaterSupply()
    fun getWater(amount: Int)
}

interface AutomaticShutdown {
    val sensorType: String
    val maxSensoredValue: Int
    fun startMonitoring()
}

interface Drainable {
    fun connectToDrain()
    fun drain()
}

interface Timable {
    fun setTimer(time: Int)
}

interface BatteryOperated {
    fun getCapacity(): Double
    fun replaceBattery()
}

interface Mechanical {
    fun performMechanicalAction()
}

interface LightEmitting {
    fun emitLight()
    fun completeLiteEmission() // Сохранено оригинальное название из ТЗ
}

interface SoundEmitting {
    fun setVolume(volume: Int)
    fun mute()
    fun playSound(stream: InputStream)
}

interface Programmable {
    fun programAction(action: String)
    fun execute()
}

interface Movable {
    fun move(direction: String, distance: Int)
}

interface Cleanable {
    fun clean()
}

interface Rechargeable {
    fun getChargeLevel(): Double
    fun recharge()
}

// ============================================================================
// ЗАДАЧА 1. Абстрактные классы устройств (без реализации методов)
// ============================================================================

abstract class AbstractRefrigerator : Powerable, Openable, TemperatureRegulatable, AutomaticShutdown
abstract class AbstractWashingMachine : Powerable, Openable, WaterContainer, WaterConnection, Drainable, Timable, Programmable
abstract class AbstractSmartLamp : Powerable, LightEmitting, Programmable
abstract class AbstractElectronicClock : Powerable, Timable, LightEmitting, SoundEmitting
abstract class AbstractRobotVacuum : Powerable, Movable, Cleanable, Rechargeable, AutomaticShutdown, Programmable
abstract class AbstractMechanicalClock : Mechanical, Timable, SoundEmitting
abstract class AbstractFlashlight : Powerable, LightEmitting, BatteryOperated
abstract class AbstractCoffeeMachine : Powerable, WaterContainer, WaterConnection, TemperatureRegulatable, Timable, Programmable
abstract class AbstractSmartSpeaker : Powerable, SoundEmitting, Programmable

// ============================================================================
// ЗАДAЧА 2. Абстрактный класс для включаемого оборудования
// ============================================================================

abstract class PowerableDevice : Powerable {
    // Флаг состояния питания, доступный для чтения в подклассах
    var isPowerOn: Boolean = false
        protected set

    override fun powerOn() {
        if (!isPowerOn) {
            isPowerOn = true
            println("${this.javaClass.simpleName}: Питание включено.")
        } else {
            println("${this.javaClass.simpleName}: Устройство уже включено.")
        }
    }

    override fun powerOff() {
        if (isPowerOn) {
            isPowerOn = false
            println("${this.javaClass.simpleName}: Питание выключено.")
        } else {
            println("${this.javaClass.simpleName}: Устройство уже выключено.")
        }
    }
}

// ============================================================================
// ЗАДAЧА 3. Абстрактный класс для программируемого оборудования
// ============================================================================

abstract class ProgrammableDevice : PowerableDevice(), Programmable {
    protected var currentProgram: String? = null

    override fun programAction(action: String) {
        if (!isPowerOn) {
            println("${this.javaClass.simpleName}: Ошибка! Нельзя запрограммировать выключенное устройство.")
            return
        }
        currentProgram = action
        println("${this.javaClass.simpleName}: Задана программа -> \"$action\".")
    }

    override fun execute() {
        if (!isPowerOn) {
            println("${this.javaClass.simpleName}: Ошибка! Нельзя запустить программу на выключенном устройстве.")
            return
        }
        val program = currentProgram
        if (program != null) {
            println("${this.javaClass.simpleName}: Выполняется программа: \"$program\".")
        } else {
            println("${this.javaClass.simpleName}: Ошибка! Программа не задана.")
        }
    }
}

// ============================================================================
// ЗАДAЧА 4. Абстрактный класс с температурой и открыванием
// ============================================================================

abstract class ThermoOpenableDevice : ProgrammableDevice(), TemperatureRegulatable, Openable {
    var isOpened: Boolean = false
        protected set

    var currentTemperature: Int = 20 // Начальная комнатная температура
        protected set

    override fun open() {
        isOpened = true
        println("${this.javaClass.simpleName}: Дверца/крышка открыта.")
    }

    override fun close() {
        isOpened = false
        println("${this.javaClass.simpleName}: Дверца/крышка закрыта.")
    }

    override fun setTemperature(temp: Int) {
        if (!isPowerOn) {
            println("${this.javaClass.simpleName}: Ошибка! Нельзя установить температуру на выключенном устройстве.")
            return
        }
        if (temp > maxTemperature) {
            println("${this.javaClass.simpleName}: Ошибка! Указанная температура $temp°C превышает максимум ($maxTemperature°C).")
            return
        }
        currentTemperature = temp
        println("${this.javaClass.simpleName}: Установлена температура: $temp°C.")
    }
}

// ============================================================================
// ЗАДAЧА 5. Реальные классы устройств с проверками и интерфейсами
// ============================================================================

// ХОЛОДИЛЬНИК
class Refrigerator : ThermoOpenableDevice() {
    override val maxTemperature: Int = 8 // Максимальная температура охлаждения
}

// СТИРАЛЬНАЯ МАШИНА
class WashingMachine : ThermoOpenableDevice(), WaterContainer, Drainable {
    override val maxTemperature: Int = 90
    override val capacity: Int = 50
    private var waterLevel: Int = 0

    override fun fillWater(amount: Int) {
        waterLevel = (waterLevel + amount).coerceAtMost(capacity)
        println("Стиральная машина: Набрано воды: $waterLevel л. из $capacity л.")
    }

    override fun getWater(amount: Int) {
        waterLevel = (waterLevel - amount).coerceAtLeast(0)
        println("Стиральная машина: Использовано $amount л. Осталось: $waterLevel л.")
    }

    override fun connectToDrain() {
        println("Стиральная машина: Подключена к сливу.")
    }

    override fun drain() {
        waterLevel = 0
        println("Стиральная машина: Вода полностью слита.")
    }
}

// ЧАЙНИК
class Kettle : ThermoOpenableDevice(), WaterContainer {
    override val maxTemperature: Int = 100
    override val capacity: Int = 2
    private var waterLevel: Int = 0

    override fun fillWater(amount: Int) {
        waterLevel = (waterLevel + amount).coerceAtMost(capacity)
        println("Чайник: Налито воды: $waterLevel л. из $capacity л.")
    }

    override fun getWater(amount: Int) {
        waterLevel = (waterLevel - amount).coerceAtLeast(0)
        println("Чайник: Вылито $amount л. Осталось: $waterLevel л.")
    }
}

// ДУХОВКА
class Oven : ThermoOpenableDevice(), Timable {
    override val maxTemperature: Int = 250
    private var timerMinutes: Int = 0

    override fun setTimer(time: Int) {
        if (!isPowerOn) {
            println("Духовка: Ошибка! Нельзя поставить таймер на выключенном устройстве.")
            return
        }
        timerMinutes = time
        println("Духовка: Таймер установлен на $timerMinutes минут.")
    }
}

// ============================================================================
// ТОЧКА ВХОДА И ДЕМОНСТРАЦИЯ РАБОТЫ
// ============================================================================

fun main() {
    println("--- ТЕСТИРОВАНИЕ ХОЛОДИЛЬНИКА ---")
    val fridge = Refrigerator()
    fridge.open()
    fridge.setTemperature(4) // Ошибка: выключен
    fridge.powerOn()
    fridge.setTemperature(4)  // Успешно
    fridge.close()

    println("\n--- ТЕСТИРОВАНИЕ СТИРАЛЬНОЙ МАШИНЫ ---")
    val washer = WashingMachine()
    washer.powerOn()
    washer.connectToDrain()
    washer.fillWater(30)
    washer.programAction("Хлопок 60°С") // Считай - задание режима работы
    washer.setTemperature(60)
    washer.execute()
    washer.drain()

    println("\n--- ТЕСТИРОВАНИЕ ЧАЙНИКА (Проверка блокировок) ---")
    val kettle = Kettle()
    kettle.fillWater(2) // Механическое действие (налить воду) доступно всегда
    kettle.programAction("Кипячение") // Ошибка: выключен
    kettle.setTemperature(100)        // Ошибка: выключен

    kettle.powerOn()
    kettle.programAction("Поддержание 80°C")
    kettle.setTemperature(80)
    kettle.execute()

    println("\n--- ТЕСТИРОВАНИЕ ДУХОВКИ ---")
    val oven = Oven()
    oven.powerOn()
    oven.setTemperature(180)
    oven.setTimer(45)
    oven.programAction("Запекание курицы")
    oven.execute()
    oven.powerOff()
}
