package function

fun main() {
    val filter: StringFilter = object : StringFilter {
        override fun predicate(str: String): Boolean {
            return true
        }
    }

    val filter2 = StringFilter { s -> s.startsWith("A") }

    consumeFilter { s -> s.startsWith("A") } // 명시적으로 적지 않는 경우에는 제너릭이 아닌 구체적인 인터페이스를 호출한다.
    consumeFilter(Filter<String> { s -> s.startsWith("A") })
}

fun interface StringFilter {
    fun predicate(str: String): Boolean
}

fun consumeFilter(filter: StringFilter) {}
fun <T> consumeFilter(filter: Filter<T>) {}
