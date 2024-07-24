class SublistChecker {
    fun isSublist(input: String, targetSubstring: String): Boolean =
        input.windowed(targetSubstring.length)
            .any { it == targetSubstring }


    fun isSublistUsingContains(input: String, targetSubstring: String): Boolean = input.contains(targetSubstring)
}
