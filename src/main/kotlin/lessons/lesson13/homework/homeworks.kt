package org.example.lessons.lesson13.homework


fun main() {


// Для решения каждой задачи постарайтесь использовать наиболее подходящий метод, не повторяясь с решением других задач.

// 1. Дан словарь с именем и временем выполнения каждого автоматизированного теста в секундах. Определите среднее время выполнения теста.
    val testTimes = mapOf("login" to 12, "time" to 8, "paymentTest" to 20)
    println(testTimes.values.average())

// 2. Имеется словарь с метаданными автоматизированных тестов, где ключи — это имена тестовых методов а значения - строка с метаданными.
// Выведите список всех тестовых методов.
    val testMetadata = mapOf(
        "loginTest" to "Anna; priority = high",
        "searchTest" to "Ivan; priority = low"
    )
    println(testMetadata.keys)

// 3. В изменяемый словарь с данными о прохождении тестов добавьте новый тест и его результат.
    val testResults = mutableMapOf("loginTest" to "passed", "searchTest" to "failed")
    testResults.put("paymentTest", "passed")
    println(testResults)


// 4. Посчитайте количество успешных тестов в словаре с результатами (ключ - название, значение - результат из passed, failed, skipped).
    val results = mapOf("t1" to "passed", "t2" to "failed", "t3" to "passed", "t4" to "skipped")
    println(results.count { it.value == "passed" })

// 5. Удалите из изменяемого словаря с баг-трекингом запись о баге, который был исправлен (ключ - название, значение - статус исправления).
    val bugs = mutableMapOf("BUG-1" to "fixed", "BUG-2" to "open", "BUG-3" to "open")
    bugs.remove("BUG-1")
    println(bugs)

// 6. Для словаря с результатами тестирования веб-страниц (ключ — URL страницы, значение — статус ответа), выведите сообщение о странице и статусе её проверки.
    val pages = mapOf("https://example.com" to 150, "https://example.com/admin" to 306)
    pages.forEach { (url, status) ->
        println("Страница $url проверена, статус ответа: $status")
    }

// 7. Найдите в словаре с названием и временем ответа сервисов только те, время ответа которых превышает заданный порог.
    val responseTimes = mapOf("auth" to 120, "catalog" to 450, "payment" to 800)
    val threshold = 300
    println(responseTimes.filterValues { it > threshold })

// 8. В словаре хранятся результаты тестирования API (ключ — endpoint, значение — статус ответа в виде строки).
// Для указанного endpoint найдите статус ответа, если endpoint отсутствует, предположите, что он не был протестирован.
    val apiResults = mapOf("/login" to "200 OK", "/users" to "500 Error")
    val endpoint = "/orders"
    println(apiResults.getOrElse(endpoint) { "не тестировался" })

// 9. Из словаря, содержащего конфигурации тестового окружения (ключ — название параметра конфигурации, значение - сама конфигурация),
// получите значение для "browserType". Ответ не может быть null.
    val envConfig = mapOf("browserType" to "chrome", "timeout" to "30")
    val browser: String = envConfig.getValue("browserType")
    println(browser)

// 10. Создайте копию неизменяемого словаря с данными о версиях тестируемого ПО, добавив новую версию.
    val versions = mapOf("1.0" to "stable", "1.1" to "stable")
    val newVersions = versions.toMutableMap()
    newVersions.put("2.0", "beta")
    println(newVersions)

// 11. Используя словарь с настройками тестирования для различных мобильных устройств (ключ — модель устройства, значение - строка с настройками),
// получите настройки для конкретной модели или верните настройки по умолчанию.
    val deviceSettings = mapOf("Pixel 8" to "1080x2400, dark", "Galaxy S23" to "1080x2340, light")
    println(deviceSettings.getOrDefault("iPhone 15", "настройки по умолчанию"))

// 12. Проверьте, содержит ли словарь с ошибками тестирования (ключ - код ошибки, значение - описание ошибки) определенный код ошибки.
    val errors = mapOf(404 to "Not Found", 500 to "Internal Server Error")
    println(errors.containsKey(404))

// 13. Дан неизменяемый словарь, где ключи — это идентификаторы тестовых сценариев в формате "TestID_Version",
// а значения — статусы выполнения этих тестов ("Passed", "Failed", "Skipped"). Отфильтруйте словарь, оставив только те сценарии,
// идентификаторы которых соответствуют определённой версии тестов, то-есть в ключе содержится требуемая версия.
    val scenarios = mapOf(
        "Login_v1" to "Passed",
        "Login_v2" to "Failed",
        "Search_v2" to "Skipped"
    )
    val version = "v2"
    println(scenarios.filterKeys { it.substringAfterLast("_") == version })

// 14. У вас есть словарь, где ключи — это названия функциональных модулей приложения, а значения — результаты их тестирования.
// Проверьте, есть ли модули с неудачным тестированием.
    val modules = mapOf("auth" to "passed", "cart" to "failed", "profile" to "passed")
    println(modules.any { it.value == "failed" })

// 15. Добавьте в изменяемый словарь с настройками тестовой среды настройки из другого словаря.
    val settings = mutableMapOf("browser" to "chrome", "timeout" to "30")
    val extraSettings = mapOf("retries" to "3", "headless" to "true")
    settings.putAll(extraSettings)
    println(settings)

// 16. Объедините два неизменяемых словаря с данными о багах.
    val bugsA = mapOf("BUG-1" to "open", "BUG-2" to "fixed")
    val bugsB = mapOf("BUG-3" to "open", "BUG-4" to "open")
    val allBugs = bugsA + bugsB
    println(allBugs)

// 17. Очистите изменяемый словарь с временными данными о последнем прогоне автоматизированных тестов.
    val lastRun = mutableMapOf("t1" to "passed", "t2" to "failed")
    lastRun.clear()
    println(lastRun)

// 18. Исключите из отчета по автоматизированному тестированию те случаи, где тесты были пропущены (имеют статус “skipped”).
// Ключи - название теста, значения - статус.
    val report = mapOf("t1" to "passed", "t2" to "skipped", "t3" to "failed")
    println(report.filterNot { it.value == "skipped" })

// 19. Создайте копию словаря с конфигурациями тестирования удалив из него несколько конфигураций.
    val configs = mapOf("config1" to "a", "config2" to "b", "config3" to "c", "config4" to "d")
    val trimmedConfigs = configs - listOf("config2", "config3")
    println(trimmedConfigs)

// 20. Создайте отчет о тестировании, преобразовав словарь с результатами тестирования (ключ — идентификатор теста, значение — результат)
// в список строк формата "Test ID: результат".
    val testRun = mapOf("101" to "passed", "102" to "failed")
    val lines: List<String> = testRun.entries.map { "Test ${it.key}: ${it.value}" }
    println(lines)

// 21. Преобразуйте изменяемый словарь с результатами последнего тестирования в неизменяемый для архивации.
    val mutableResults = mutableMapOf("t1" to "passed", "t2" to "failed")
    val archive: Map<String, String> = mutableResults.toMap()
    println(archive)

// 22. Преобразуйте словарь, содержащий числовой ID теста и данные о времени выполнения тестов, заменив идентификаторы тестов
// на их строковый аналог (например через toString()).
    val timesById = mapOf(1 to 12.5, 2 to 8.3, 3 to 20.1)
    println(timesById.mapKeys { it.key.toString() })

// 23. Для словаря с оценками производительности различных версий приложения (ключи - строковая версия, значения - дробное число времени ответа сервера)
// увеличьте каждую оценку на 10%, чтобы учесть новые условия тестирования.
    val performance = mapOf("1.0" to 100.0, "1.1" to 120.5, "2.0" to 90.0)
    println(performance.mapValues { it.value * 1.1 })

// 24. Проверьте, пуст ли словарь с ошибками компиляции тестов.
    val compileErrors = mapOf<String, String>()
    println(compileErrors.isEmpty())

// 25. Убедитесь, что словарь с результатами нагрузочного тестирования не пуст.
    val loadResults = mapOf("test1" to "ok")
    println(loadResults.isNotEmpty())

// 26. Проверьте, прошли ли успешно все автоматизированные тесты в словаре с результатами.
    val autoTests = mapOf("t1" to "passed", "t2" to "passed", "t3" to "failed")
    println(autoTests.all { it.value == "passed" })

// 27. Определите, содержит ли словарь с результатами тестирования хотя бы один тест с ошибкой.
    println(autoTests.containsValue("failed"))

// 28. Отфильтруйте словарь с результатами тестирования сервисов, оставив только те тесты, которые не прошли успешно и содержат в названии “optional”.
    val serviceTests = mapOf(
        "auth_optional" to "failed",
        "cart_optional" to "passed",
        "payment" to "failed",
        "search_optional" to "skipped"
    )
    println(serviceTests.filter { it.value != "passed" && it.key.contains("optional") })
}