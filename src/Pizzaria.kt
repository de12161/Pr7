internal class Pizzaria(
    prices: Map<String, Double>,
) : IPizzaria {
    private val _products = prices.mapValues { Product(it.value) }

    private fun sellPizza(productName: String) {
        _products[productName]!!.sell()
    }

    override val menu: Set<String>
        get() = _products.keys

    override val totalIncome: Double
        get() = _products.values.sumOf { it.income }

    override fun serveCustomer(): String {
        val pizza = prompt("Выберите пиццу: ", menu)
        sellPizza(pizza)
        println("Спасибо за покупку!")
        return pizza
    }

    override fun printProductStats() {
        println("Продано пиццы:")
        _products.forEach {
            product -> println("${product.key} - ${product.value.amountSold}, доход: ${product.value.income}")
        }
    }

    override fun printTotalIncome() {
        println("Общий доход: $totalIncome")
    }

    override fun printAllStats() {
        printProductStats()
        printTotalIncome()
    }
}
