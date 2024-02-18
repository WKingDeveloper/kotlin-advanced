package dsl

import java.time.LocalDate

fun main() {
    var point = Point(20, -10)
    println(point.zeroPintSymmetry())
    println(-point)
    println(++point)

    LocalDate.of(2023, 1, 1).plusDays(3)
    LocalDate.of(2023, 1, 1) + Days(3)
    LocalDate.of(2023, 1, 1) + 3.d

    val mutableList = mutableListOf("A", "B", "C")
    mutableList += "D"

    var list = listOf("A", "B", "C")
    list += "D" // list = list+D로 동작하게됨. val list이면 에러가 발생.
}

data class Point(
    val x: Int,
    val y: Int,
) {
    fun zeroPintSymmetry(): Point = Point(-x, -y)

    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }

    operator fun unaryPlus(): Int {
        return x
    }

    operator fun inc(): Point {
        return Point(x + 1, y + 1)
    }

    operator fun dec(): Point {
//        return y // 에러 발생. inc,dec는 같은 타입만 응답으로 가능
        return Point(x - 1, y - 1)
    }
}

data class Days(val day: Long)

val Int.d: Days
    get() = Days(this.toLong())

operator fun LocalDate.plus(days: Days): LocalDate {
    return this.plusDays(days.day)
}
