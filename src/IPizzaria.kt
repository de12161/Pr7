internal interface IPizzaria {
    val menu: Set<String>
    val totalIncome: Double

    fun serveCustomer(): String
    fun printAllStats()
    fun printProductStats()
    fun printTotalIncome()
}
