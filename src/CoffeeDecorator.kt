internal class CoffeeDecorator(wrappee: IPizzaria, private val coffeePrice: Double) : PizzariaDecorator(wrappee) {
    private var _totalOffers = 0
    private val _offers = wrappee.menu.associateWith { _ -> Product(coffeePrice) }

    private fun sellCoffee(name: String) {
        _offers[name]!!.sell()
    }

    private fun offerCoffee(pizza: String) {
        _totalOffers++
        if (prompt("Вы будете кофе?")) {
            println("С вас $coffeePrice рублей")
            sellCoffee(pizza)
        }
    }

    override val menu: Set<String>
        get() = wrappee.menu

    override fun serveCustomer(): String {
        val pizza = wrappee.serveCustomer()
        offerCoffee(pizza)
        return pizza
    }

    override val totalIncome: Double
        get() = wrappee.totalIncome + _offers.values.sumOf { it.income }

    override fun printProductStats() {
        wrappee.printProductStats()
        if (_totalOffers > 0) {
            println()
            println("Продано кофе: ")
            _offers.forEach {
                println("К пицце ${it.key} - ${it.value.amountSold} (${100 * it.value.amountSold / _totalOffers}%), доход: ${it.value.income}")
            }
            println("Кофе берут ${100 * _offers.values.sumOf { it.amountSold } / _totalOffers}% покупателей")
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
