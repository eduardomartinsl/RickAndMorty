package br.com.compose.rickandmortyapp.data.paginator

interface Paginator<key, item> {
    suspend fun loadNextPage()
    fun reset()
}