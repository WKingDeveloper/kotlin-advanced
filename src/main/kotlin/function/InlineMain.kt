package function


/**
 * inline 함수는 본인만 인라이닝 되는게 아니라 알 수 있는 함수 파라미터도 인라이닝 시키고
 * non-local return 역시 쓸 수 있게 해준다.
 * noinline - 함수 파라미터의 인라이닝을 막는다
 * crossinline - 인라인 함수의 파라미터가 non-local return을 쓸 수 없게한다.
 */
fun main() {
    iterateWithInline(listOf(1, 2, 3, 4, 5)) { num ->
        if (num == 3) {
            return // -> main 함수를 return하게 된다.
        }
        println("num : $num")
    }
}

inline fun iterateWithInline(numbers: List<Int>, exec: (Int) -> Unit) {
    for (number in numbers) {
        exec(number)
    }
}
