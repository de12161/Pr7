internal class ReceiptDecorator(wrappee: IPizzaria, private val discount: Double): PizzariaDecorator(wrappee) {
    private var _totalOffers = 0
    private var _photosShown = 0

    override val menu: Set<String>
        get() = wrappee.menu

    override fun serveCustomer(): String {
        val pizza = wrappee.serveCustomer()
        askForPhoto()
        return pizza
    }

    private fun askForPhoto() {
        _totalOffers++
        if (prompt("У вас есть фотография чека?")) {
            _photosShown++
            println("Вам предоставлена скидка $discount рублей")
        }
    }

    override val totalIncome: Double
        get() = wrappee.totalIncome - _photosShown * discount

    override fun printProductStats() {
        wrappee.printProductStats()
        println()
        if (_totalOffers > 0) println("Чек показывают ${100 * _photosShown / _totalOffers}% покупателей")
        println("Сумма скидок: ${_photosShown * discount}")
    }

    override fun printTotalIncome() {
        println("Общий доход: $totalIncome")
    }

    override fun printAllStats() {
        printProductStats()
        printTotalIncome()
    }
}
