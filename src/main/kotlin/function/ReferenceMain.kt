package function

fun main() {
    val add1 = { a: Int, b: Int -> a + b }
    val add2 = fun(a: Int, b: Int) = a + b
    val add3 = ::add
}

fun add(a: Int, b: Int) = a + b
