class SumZero {
    fun sumZero(numbers: List<Int>): Pair<Int, Int>? =
        numbers.flatMapIndexed { i, n ->
                numbers.drop(i + 1)
                    .map { n to it } }
            .firstOrNull { (n1, n2) -> n1 + n2 == 0 }
}