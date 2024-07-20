class DuplicateParams {
    fun getDuplicateParams(vararg input: String): List<String> =
        input.groupingBy { it }.eachCount()
            .filter { it.value > 1 }
            .map { it.key }
}
