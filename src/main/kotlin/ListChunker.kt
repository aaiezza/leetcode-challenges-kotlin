class ListChunker {
    fun <T> chunkList(input: List<T>, sizeOfChunks: UInt): List<List<T>> =
        input.chunked(sizeOfChunks.toInt())
}