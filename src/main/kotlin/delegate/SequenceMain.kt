package delegate

fun main() {
    val fruits = listOf(
        MyFruit("사과", 1000L),
        MyFruit("사과", 1100L),
        MyFruit("사과", 1200L),
        MyFruit("바나나", 3000L),
    )

    /**
     * Sequence는 한 원소에 대해 모든 연산을 수행하고 다음 원소로 넘어간다.
     * 최종 연산이 나오기 전가지 계산 자체를 미리 하지 않는다. (지연 연산)
     */
    val avg = fruits.asSequence()
        .filter { it.name == "사과" }
        .map { it.price }
        .take(2)
        .average()
}

data class MyFruit(
    val name: String,
    val price: Long
)
