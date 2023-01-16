import java.util.stream.IntStream.range

class TwoSumSolution { // ktlint-disable filename
    fun twoSum(nums: IntArray, target: Int): IntArray {
        return nums.withIndex().mapNotNull { firstNumber ->
            nums.withIndex().filter { it.index != firstNumber.index }
                .find { secondNumber ->
                    firstNumber.value + secondNumber.value == target
                }?.let { listOf(firstNumber.index, it.index).toIntArray() }
        }.first()
    }

    fun convertToZigZag(s: String, numRows: Int): String {
        if (numRows <= 1) return s
        val out: Array<String> = Array(numRows) { "" }

        var goingDown = false
        var row = -1

        s.map {
            if ((goingDown && row >= (numRows - 1)) || (!goingDown && row <= 0)) {
                goingDown = !goingDown
            }

            if (goingDown) {
                row++
            } else {
                row--
            }
            out[row] += it.toString()
        }

        return out.joinToString("")
    }
}
