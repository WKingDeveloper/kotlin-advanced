package coroutine

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
//  val job1 = CoroutineScope(Dispatchers.Default).launch {
//    throw IllegalStateException()
//  }
//
//
//  val job2 = CoroutineScope(Dispatchers.Default).async {
//    throw IllegalStateException()
//  }

//  val job3 = async {
//    throw IllegalStateException()
//  }

  // 부모에게 예외를 전파하지 않음
  val job4 = async(SupervisorJob()) {
    throw IllegalStateException()
  }

  val job = launch {
    try {
      throw IllegalStateException()
    } catch (e: IllegalStateException) {
      printWithThread("정상 종료")
    }
  }
  delay(1_000L)
}