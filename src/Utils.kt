internal fun prompt(message: String, options: Set<String>): String {
    while (true) {
        println(message)
        options.forEachIndexed { i, option -> println("${i + 1}. $option") }
        print("> ")

        val input = readln().toIntOrNull()

        if (input == null) {
            println("Введите число")
            continue
        }

        if (input <= 0 || input > options.size) {
            println("Неверный выбор")
            continue
        }

        return options.elementAt(input - 1)
    }
}

internal fun prompt(message: String): Boolean {
    return prompt(message, setOf("Да", "Нет")) == "Да"
}
