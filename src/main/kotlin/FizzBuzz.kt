class FizzBuzz {
    fun fizzBuzz(input: Int): List<Any> =
        (1..input).map {
            when {
                it % 3 == 0 -> "Fizz"
                it % 5 == 0 -> "Buzz"
                else -> it
            }
        }.toList()
}