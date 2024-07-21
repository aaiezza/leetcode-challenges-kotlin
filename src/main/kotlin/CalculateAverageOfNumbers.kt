class CalculateAverageOfNumbers {
    fun findAverages(numbers: IntArray, numberOfOperands: UInt, targetAverage: Double): List<List<Int>> =
        (numbers choose numberOfOperands)
            .filter { it.average() == targetAverage }
            .toList()

    fun averages(numbers: IntArray, numberOfOperands: UInt, targetAverage: Double): Map<Pair<List<Int>, Double>, Int> =
        (numbers choose numberOfOperands)
            .groupingBy { it to it.average() }
            .eachCount()
}