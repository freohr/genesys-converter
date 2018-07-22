import converter.Die
import converter.DieType
import converter.Symbol

fun main(args: Array<String>) {
    println(message = "Hello World!")

    val table = mapOf(1 to arrayListOf(Symbol.Blank))
    val test = Die(conversionTable = table, type = DieType.Setback)

    test.side = 1

    println(test.result()[0])
}
