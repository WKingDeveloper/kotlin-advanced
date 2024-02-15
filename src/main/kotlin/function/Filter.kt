package function

fun interface Filter<T> {
    fun predicate(t: T): Boolean
}
