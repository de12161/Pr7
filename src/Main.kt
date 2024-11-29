fun main() {
    val moscowPizzaria = ReceiptDecorator(
        Pizzaria(
            mapOf(
                "Неаполитанская" to 215.0,
                "Римская" to 250.5,
                "Сицилийская" to 180.5,
                "Тирольская" to 240.0
            )
        ),
        discount = 50.0
    )

    val spbPizzaria = CoffeeDecorator(
        Pizzaria(
            mapOf(
                "Неаполитанская" to 175.0,
                "Римская" to 241.5,
                "Сицилийская" to 167.5,
                "Тирольская" to 215.0
            )
        ),
        coffeePrice = 200.0
    )

    val kirovPizzaria = ReceiptDecorator(
        SauceDecorator(
            CoffeeDecorator(
                Pizzaria(
                    mapOf(
                        "Неаполитанская" to 200.0,
                        "Римская" to 245.0,
                        "Сицилийская" to 170.5,
                        "Тирольская" to 230.5
                    )
                ),
                coffeePrice = 225.5
            ),
            mapOf(
                "Чесночный" to 80.5,
                "Грибной" to 95.0
            )
        ),
        discount = 100.0
    )

    val pizzariaMap = mapOf(
        "Москва" to moscowPizzaria,
        "Санкт-Петербург" to spbPizzaria,
        "Киров" to kirovPizzaria
    )

    while (true) {
        if (!prompt("Вы хотите пойти в пиццерию?")) {
            break
        }

        val currentPizzaria = pizzariaMap[prompt("Выберите город: ", pizzariaMap.keys)]!!

        when (prompt("Выберите действие: ", setOf("Купить пиццу", "Посмотреть статистику"))) {
            "Купить пиццу" -> currentPizzaria.serveCustomer()
            "Посмотреть статистику" -> currentPizzaria.printAllStats()
        }
    }
}