class CalculateAverage {
    fun hasAverage(targetAverage: Double, vararg values: Int) : Boolean =
        values.average()
            .also { println(it) }
            .let { it == targetAverage }
}
