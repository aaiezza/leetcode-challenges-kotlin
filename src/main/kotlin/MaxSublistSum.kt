class MaxSublistSum {
    fun maxSublistSum(nums: IntArray, sublistSize: Int): Int =
        nums.toList().windowed(sublistSize)
            .maxOfOrNull { it.sum() } ?: 0

    fun maxComboSum(nums: IntArray, numerOfOperands: Int): Int =
        (nums choose numerOfOperands.toUInt()).maxOfOrNull { it.sum() } ?: 0
}
