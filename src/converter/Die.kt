package converter

@Suppress("ConvertSecondaryConstructorToPrimary")
class Die {

    val type: DieType
    private val conversionTable: Map<Int, ArrayList<Symbol>>

    constructor(type: DieType, conversionTable: Map<Int, ArrayList<Symbol>>) {
        this.type = type
        this.conversionTable = conversionTable
    }

    var side: Int = 0
        get() {
            if (field <= 0) {
                throw Throwable(message = "This die of type $type has not been assigned too")
            } else {
                return field
            }
        }
        set(value) {
            if (value <= 0)
                throw IllegalArgumentException("Die side cannot be negative")

            field = when (type) {
                DieType.Setback, DieType.Boost -> if (value > 6) {
                    throw IllegalArgumentException("Die side must 6 or lower")
                } else {
                    value
                }
                DieType.Ability,DieType.Difficulty -> if (value > 8) {
                    throw IllegalArgumentException("Die side must 8 or lower")
                } else {
                    value
                }
                DieType.Proficiency ,DieType.Challenge -> if (value > 12) {
                    throw IllegalArgumentException("Die side must 12 or lower")
                } else {
                    value
                }
            }
        }


    fun result(): ArrayList<Symbol>{
        return conversionTable.getOrDefault(side, arrayListOf(Symbol.Blank))
    }
}