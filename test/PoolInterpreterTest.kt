import com.sun.net.httpserver.Authenticator
import converter.Die
import converter.DieType
import converter.PoolInterpreter
import converter.Symbol
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PoolInterpreterTest {
    private val interpreter = PoolInterpreter()

    @Test
    fun getSimpleCheckSymbols() {
        val pool: ArrayList<Die> = interpreter.getDicePool(arrayListOf(DieType.Ability, DieType.Proficiency)) as ArrayList<Die>

        pool.forEach { die -> when(die.type) {
            DieType.Ability -> die.side = 2
            DieType.Proficiency -> die.side = 6
            else -> {
                // We ignore the other types of dice for this test
            }
        }}

        val symbolResult = interpreter.getPoolSymbols(pool)

        assertEquals(symbolResult[Symbol.Success], 1)
        assertEquals(symbolResult[Symbol.Advantage], 1)
    }

    @Test
    fun getDauntingCheckResult() {
        val pool: ArrayList<Die> = interpreter.getDicePool(arrayListOf(DieType.Ability, DieType.Proficiency, DieType.Proficiency, DieType.Proficiency, DieType.Boost,
                DieType.Challenge, DieType.Difficulty, DieType.Difficulty, DieType.Difficulty)) as ArrayList<Die>

        pool.forEach { die -> when(die.type) {
            DieType.Ability -> die.side = 4 // (Success, Success)
            DieType.Proficiency -> die.side = 12 // (Triumph)
            DieType.Boost -> die.side = 5 // (Adv, Adv)
            DieType.Difficulty -> die.side = 8 // (Threat, Failure)
            DieType.Challenge -> die.side = 12 // (Despair)
            else -> {
                // We ignore the other types of dice for this test
            }
        }}

        val poolResult = interpreter.getPoolResult(pool)

        val expectedResult = mapOf(
                Symbol.Success to 1,
                Symbol.Advantage to 0,
                Symbol.Triumph to 3,
                Symbol.Failure to 0,
                Symbol.Threat to 1,
                Symbol.Despair to 1
        )

        assertEquals(poolResult.getOrDefault(Symbol.Success, 0), expectedResult[Symbol.Success])
        assertEquals(poolResult.getOrDefault(Symbol.Advantage, 0), expectedResult[Symbol.Advantage])
        assertEquals(poolResult.getOrDefault(Symbol.Triumph, 0), expectedResult[Symbol.Triumph])
        assertEquals(poolResult.getOrDefault(Symbol.Failure, 0), expectedResult[Symbol.Failure])
        assertEquals(poolResult.getOrDefault(Symbol.Threat, 0), expectedResult[Symbol.Threat])
        assertEquals(poolResult.getOrDefault(Symbol.Despair, 0), expectedResult[Symbol.Despair])
    }
}