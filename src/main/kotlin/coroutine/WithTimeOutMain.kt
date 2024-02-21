package coroutine

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun main(): Unit = runBlocking {


  val result1: Int? = withTimeoutOrNull(timeMillis = 1_000L) {
    delay(1_500L)
    10 + 20
  }

  printWithThread("result1 : $result1")

  val result2: Int = withTimeout(timeMillis = 1_000L) {
    delay(1_500L)
    10 + 20
  }
}