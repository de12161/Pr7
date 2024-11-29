internal class SauceDecorator(wrappee: IPizzaria, prices: Map<String, Double>) : PizzariaDecorator(wrappee) {
    private val _sauces = prices.mapValues { Product(it.value) }

    private fun sellSauce(name: String) {
        _sauces[name]!!.sell()
    }

    private fun chooseSauce() {
        if (!prompt("Вы будете соус?")) return

        val sauce = prompt("Выберите соус: ", _sauces.keys)
        sellSauce(sauce)
    }

    override val menu: Set<String>
        get() = wrappee.menu

    override fun serveCustomer(): String {
        val pizza = wrappee.serveCustomer()
        chooseSauce()
        return pizza
    }

    override val totalIncome: Double
        get() = wrappee.totalIncome + _sauces.values.sumOf { it.income }

    override fun printProductStats() {
        wrappee.printProductStats()
        println()
        println("Продано соусов: ")
        _sauces.forEach {
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
