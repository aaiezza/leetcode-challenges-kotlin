class CombinationGenerator<T>(private val items: List<T>, choose: UInt = 1u) : Iterator<List<T>>, Iterable<List<T>> {
    private val combinationIndicies = Array(choose.toInt()) { it }
    private var first = true

    init {
        if (items.isEmpty() || choose.toInt() > items.size || choose < 1u)
            error("list must have more than 'choose' items and 'choose' min is 1")
    }

    override fun hasNext(): Boolean = combinationIndicies.filterIndexed { index, it ->
        first || when (index) {
            combinationIndicies.lastIndex -> items.lastIndex > it
            else -> combinationIndicies[index + 1] - 1 > it
        }
    }.any()

    override fun next(): List<T> {
        if (!hasNext()) error("Out of elements")
        if (!first) {
            incrementAndCarry()
        } else
            first = false
        return List(combinationIndicies.size) { items[combinationIndicies[it]] }
    }

    private fun incrementAndCarry() {
        var carry = false
        var place = combinationIndicies.lastIndex
        do {
            carry = if (
                (place == combinationIndicies.lastIndex // we are at the end of our combo list
                        && combinationIndicies[place] < items.lastIndex) // AND not at the end of the list of items
                || (place != combinationIndicies.lastIndex // OR we are NOT at the end of our combo list
                        && combinationIndicies[place] < combinationIndicies[place + 1] - 1) // AND
            // this index in the combo list is LESS THAN the items index value - 1,
            // of the following element in the combo list
            ) {
                combinationIndicies[place]++
                (place + 1..combinationIndicies.lastIndex).forEachIndexed { index, i ->
                    combinationIndicies[i] = combinationIndicies[place] + index + 1
                }
                false
            } else
                true
            place--
        } while (carry && place > -1)
    }

    override fun iterator(): Iterator<List<T>> = this
}

fun main() {
    val combGen = CombinationGenerator(listOf("A", "B", "C", "D"), 4u)
    combGen.map { println(it.joinToString()) }
}

infix fun <T> List<T>.choose(n: UInt) = CombinationGenerator(this, n)
