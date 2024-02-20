package extra

fun main() {
    val key = Key("비밀 번호")
    println(key)

    val userId = 1L
    val bookId = 2L
    handle(bookId, bookId)

    val inlineUserId = Id<User>(1L)
    val inLineBookId = Id<Book>(2L)
    handleV2(inlineUserId, inLineBookId)

    try {
        TODO()
    } catch (e: Exception) {
        when (e) {
            is AException -> TODO()
            is BException -> TODO()
            is CException -> TODO()
        }
        throw e
    }
}

/**
 * 꼬리 재귀함수에 tailrec를 붙이면 컴파일 시 최적화가 된다.
 */
tailrec fun factorialV2(n: Int, curr: Int = 1): Int {
    return if (n <= 1) {
        curr
    } else {
        factorialV2(n - 1, n * curr)
    }
}

/**
 * @JvmInline, + value class => 인라인 클래스
 * 컴파일 시 프로퍼티가 클래스를 사용한 곳에 인라이닝 되어 들어간다.
 */
@JvmInline
value class Key(val key: String)

class User(
    val id: Id<User>,
    val name: String
)

class Book(
    val id: Id<Book>,
    val author: String
)

fun handle(userId: Long, bookId: Long) {

}

fun handleV2(userId: Id<User>, bookId: Id<Book>) {

}

@JvmInline
value class Id<T>(val id: Long)

@JvmInline
value class Number(val num: Long) {
    init {
        require((num in 1..10))
    }
}

fun logic(n: Int) {
    when {
        n > 0 -> throw AException()
        n == 0 -> throw BException()
    }
    throw CException()
}

class AException : RuntimeException()
class BException : RuntimeException()
class CException : RuntimeException()
