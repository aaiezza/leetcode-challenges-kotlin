class DigitFrequency {
    fun equalDigitFrequencyString(firstNumber: String, secondNumber: String): Boolean =
        firstNumber.groupingBy { it }.eachCount() == secondNumber.groupingBy { it }.eachCount()

    fun equalDigitFrequency(firstNumber: Int, secondNumber: Int): Boolean =
        equalDigitFrequencyString(firstNumber.toString(), secondNumber.toString())

}
