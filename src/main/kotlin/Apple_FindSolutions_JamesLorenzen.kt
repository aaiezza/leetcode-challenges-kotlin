fun isComplete(value: String): Boolean {
    val curr = ArrayDeque<String>()
    for (c in value) {
        when (c) {
            '0' -> {
                if (curr.lastOrNull() == "1") {
                    return false
                }
                curr.addLast(c.toString())
            }

            '1' -> {
                if (curr.lastOrNull() == "0") {
                    return false
                }
                curr.addLast(c.toString())
            }

            '*' -> {
                curr.addLast(c.toString())
            }
        }
    }

    return true
}

fun findSolutions(value: String): String {
    if (isComplete(value)) {
        return value
    }

    val curr = ArrayDeque<String>()
    for (c in value) {
        when (c) {
            '0' -> {
                if (curr.lastOrNull() == "1") {
                    curr.removeLastOrNull()
                    continue
                }
                curr.addLast(c.toString())
            }

            '1' -> {
                if (curr.lastOrNull() == "0") {
                    curr.removeLastOrNull()
                    continue
                }
                curr.addLast(c.toString())
            }

            '*' -> {
                curr.addLast(c.toString())
            }
        }
    }

    return findSolutions(curr.fold("") { r, c -> r + c })
}

fun findSolutionsWithWindows(value: String): String {
    if (isComplete(value)) {
        return value
    }

    val trimmed = filterOutBadPairsTwoByTwo(value)
        .let {
            if (value == it) {
                value[0] + filterOutBadPairsTwoByTwo(value.substring(1))
            } else it
        }

    return findSolutionsWithWindows(trimmed)
}

fun filterOutBadPairsTwoByTwo(value: String) =
    value.windowed(size = 2, step = 2, partialWindows = true)
        .filter { it != "10" && it != "01" }
        .fold("") { r, c -> r + c }

fun main(args: Array<String>) {
    println("findSolutions(\"01010\"): ${findSolutions("01010")}")
    println("findSolutionsWithWindows(\"01010\"): ${findSolutionsWithWindows("01010")}")
    println("findSolutions(\"111000\"): ${findSolutions("111000")}")
    println("findSolutionsWithWindows(\"111000\"): ${findSolutionsWithWindows("111000")}")
    println("findSolutions(\"111*000\"): ${findSolutions("111*000")}")
    println("findSolutionsWithWindows(\"111*000\"): ${findSolutionsWithWindows("111*000")}")
}
