class ReverseString {
    fun reverseString(input: String): String {
        val sb = StringBuilder()
        (input.length - 1 downTo 0).forEach {n ->
            sb.append(input[n])
        }
        return sb.toString()
    }
}