package converter

class PoolInterpreter {
    companion object {
        fun generateDie(dieType: DieType): Die {
            return when(dieType) {
                DieType.Ability -> Die(DieType.Ability, generateAbilityDie())
                DieType.Boost -> Die(DieType.Boost, generateBoostDie())
                DieType.Proficiency -> Die(DieType.Proficiency, generateProficiencyDie())
                DieType.Difficulty -> Die(DieType.Difficulty, generateDifficultyDie())
                DieType.Setback -> Die(DieType.Setback, generateSetbackDie())
                DieType.Challenge -> Die(DieType.Challenge, generateChallengeDie())
            }
        }

        private fun generateAbilityDie(): Map<Int, ArrayList<Symbol>> {
            return mapOf(
                    1 to arrayListOf(Symbol.Blank),
                    2 to arrayListOf(Symbol.Success),
                    3 to arrayListOf(Symbol.Success),
                    4 to arrayListOf(Symbol.Success, Symbol.Success),
                    5 to arrayListOf(Symbol.Advantage),
                    6 to arrayListOf(Symbol.Advantage),
                    7 to arrayListOf(Symbol.Advantage, Symbol.Success),
                    8 to arrayListOf(Symbol.Advantage, Symbol.Advantage))
        }

        private fun generateBoostDie(): Map<Int, ArrayList<Symbol>> {
            return mapOf(
                    1 to arrayListOf(Symbol.Blank),
                    2 to arrayListOf(Symbol.Blank),
                    3 to arrayListOf(Symbol.Success),
                    4 to arrayListOf(Symbol.Advantage, Symbol.Success),
                    5 to arrayListOf(Symbol.Advantage, Symbol.Advantage),
                    6 to arrayListOf(Symbol.Advantage))
        }

        private fun generateProficiencyDie(): Map<Int, ArrayList<Symbol>> {
            return mapOf(
                    1 to arrayListOf(Symbol.Blank),
                    2 to arrayListOf(Symbol.Success),
                    3 to arrayListOf(Symbol.Success),
                    4 to arrayListOf(Symbol.Success, Symbol.Success),
                    5 to arrayListOf(Symbol.Success, Symbol.Success),
                    6 to arrayListOf(Symbol.Advantage),
                    7 to arrayListOf(Symbol.Advantage, Symbol.Success),
                    8 to arrayListOf(Symbol.Advantage, Symbol.Success),
                    9 to arrayListOf(Symbol.Advantage, Symbol.Success),
                    10 to arrayListOf(Symbol.Advantage, Symbol.Advantage),
                    11 to arrayListOf(Symbol.Advantage, Symbol.Advantage),
                    12 to arrayListOf(Symbol.Triumph))
        }

        private fun generateDifficultyDie(): Map<Int, ArrayList<Symbol>> {
            return mapOf(
                    1 to arrayListOf(Symbol.Blank),
                    2 to arrayListOf(Symbol.Failure),
                    3 to arrayListOf(Symbol.Failure, Symbol.Failure),
                    4 to arrayListOf(Symbol.Threat),
                    5 to arrayListOf(Symbol.Threat),
                    6 to arrayListOf(Symbol.Threat),
                    7 to arrayListOf(Symbol.Threat, Symbol.Threat),
                    8 to arrayListOf(Symbol.Failure, Symbol.Threat))
        }

        private fun generateSetbackDie(): Map<Int, ArrayList<Symbol>> {
            return mapOf(
                    1 to arrayListOf(Symbol.Blank),
                    2 to arrayListOf(Symbol.Blank),
                    3 to arrayListOf(Symbol.Failure),
                    4 to arrayListOf(Symbol.Failure),
                    5 to arrayListOf(Symbol.Threat),
                    6 to arrayListOf(Symbol.Threat))
        }

        private fun generateChallengeDie(): Map<Int, ArrayList<Symbol>> {
            return mapOf(
                    1 to arrayListOf(Symbol.Blank),
                    2 to arrayListOf(Symbol.Failure),
                    3 to arrayListOf(Symbol.Failure),
                    4 to arrayListOf(Symbol.Failure, Symbol.Failure),
                    5 to arrayListOf(Symbol.Failure, Symbol.Failure),
                    6 to arrayListOf(Symbol.Threat),
                    7 to arrayListOf(Symbol.Threat),
                    8 to arrayListOf(Symbol.Failure, Symbol.Threat),
                    9 to arrayListOf(Symbol.Failure, Symbol.Threat),
                    10 to arrayListOf(Symbol.Threat, Symbol.Threat),
                    11 to arrayListOf(Symbol.Threat, Symbol.Threat),
                    12 to arrayListOf(Symbol.Despair))
        }
    }

    fun getDicePool(typeList: ArrayList<DieType>): Iterable<Die> {
        return typeList.map { it -> generateDie(it) }
    }

    fun getPoolSymbols(dieList: ArrayList<Die>): Map<Symbol, Int> {
        val symbols: List<Symbol> = dieList.flatMap { it -> it.result().toList() }

        return symbols.associate { symbol -> Pair(symbol, symbols.count { it -> it == symbol }) }
    }

    fun getPoolResult(dieList: ArrayList<Die>):  Map<Symbol, Int> {
        val symbols = getPoolSymbols(dieList)

        return symbols
    }
}