package function

/**
 * 고차 함수를 사용하게 되면 FunctionN 클래스가 만들어지고 인스턴스화 되어야해서 오버헤드가 발생할 수 있다.
 * 함수에서 변수를 포획할 경우(closer), 해당 변수를 Ref라는 객체로 감싸야 한다. 때문에 오버헤드가 발생할 수 있다.
 */
fun main() {

    /**
     * 람다식은 반환타입과 return을 적을 수 없다.
     */
    // 람다식
    compute(5, 3) { a, b -> a + b }

    // 익명 함수
    compute(5, 3, fun(a: Int, b: Int): Int { return a + b })
    compute(5, 3, fun(a: Int, b: Int) = a + b) // 리턴 축약
    compute(5, 3, fun(a, b) = a + b) // 파라미터 타입 축약

    iterate(listOf(1, 2, 3, 4, 5), fun(num) {
        if (num == 3) {
            return
        }
        println(num)
    })

    iterate(listOf(1, 2, 3, 4, 5)) { num ->
//            return // 에러 발생 ( non-local return (비지역적 반환) )
        if (num != 3) {
            println(num)
        }
    }

    var num = 5
    num += 1
    val plusOne = { num += 1 }
}

fun compute(num1: Int, num2: Int, op: (Int, Int) -> Int): Int {
    return op(num1, num2)
}

fun iterate(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}


fun calculate(num1: Int, num2: Int, oper: Operator) = oper.calcFun(num1, num2)
fun calculateOperator(num1: Int, num2: Int, oper: Operator): Int = oper(num1, num2)

enum class Operator(
    private val oper: Char,
    val calcFun: (Int, Int) -> Int
) {
    PLUS('+', { a, b -> a + b }),
    MINUS('-', { a, b -> a - b }),
    MULTIPLY('*', { a, b -> a * b }),
    DIVIDE('/', { a, b ->
        if (b == 0) {
            IllegalArgumentException("0으로 나눌 수 없습니다.")
        }
        a / b
    });

    operator fun invoke(num1: Int, num2: Int): Int {
        return calcFun(num1, num2)
    }
}
