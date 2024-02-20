@file:Suppress("DIVISION_BY_ZERO")

package extra

import kotlin.system.measureTimeMillis

fun main() {
//    TODO("메인 함수 구현")


    repeat(3) {
        println("Hello World")
    }

    val time = measureTimeMillis {
        repeat(100000) {
            val a = 1
            val b = 2
            a + b
        }
    }
    println("time = ${time}")

//    acceptOnlyTwo(3)

    val person = Person()
//    person.active()

    val result: Result<Int> = runCatching { 1 / 0 }
    result.getOrNull()
    println(result.getOrNull())

}

fun acceptOnlyTwo(num: Int) {
    require(num == 2) { "2만 허용" }
//    if (num != 2) {
//        throw IllegalArgumentException("2만 허용!")
//    }
}

class Person {
    val status: PersonStatus = PersonStatus.ACTIVE

    fun active() {
        check(this.status != PersonStatus.ACTIVE) { "상태가 이미 ACTIVE!" }

        if (this.status == PersonStatus.ACTIVE) {
            throw IllegalStateException("상태가 이미 ACTIVE!!!")
        }
    }

    enum class PersonStatus {
        ACTIVE, INACTIVE
    }
}
