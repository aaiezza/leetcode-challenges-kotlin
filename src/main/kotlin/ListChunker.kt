class ListChunker {
    fun <T> chunkList(input: List<T>, sizeOfChunks: UInt): List<List<T>> =
        input.chunked(sizeOfChunks.toInt())

    fun <T> chunkListUsingIndicies(input: List<T>, sizeOfChunks: UInt): List<List<T>> =
        (input.indices step sizeOfChunks.toInt())
            .fold(mutableListOf<List<T>>()) { chunks, i ->
                val rightIndex = minOf(i + sizeOfChunks.toInt(), input.size)
                chunks.add(input.subList(i, rightIndex))
                chunks.toMutableList()
            }.toList()
}