class SumAll {
    fun sumAll(numbers: List<Int>, numberOfOperands: UInt, targetSum: Int): List<List<Int>> =
        (numbers choose numberOfOperands)
            .filter { it.toIntArray().sum() == targetSum }
}
