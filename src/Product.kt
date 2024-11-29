internal class Product(private val price: Double) {
    private var _amountSold = 0
    private var _income = 0.0

    val amountSold: Int
        get() = _amountSold

    val income: Double
        get() = _income

    fun sell() {
        _amountSold++
        _income += price
    }
}
