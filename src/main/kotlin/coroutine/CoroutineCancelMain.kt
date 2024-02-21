package coroutine

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
  launch {
    delay(600L)
    printWithThread("A")
  }

  launch {
    delay(500L)
    throw IllegalArgumentException("코루틴 실패!")
  }

}


fun example9(): Unit = runBlocking {
  val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
    printWithThread("예외")
    throw throwable
  }
  val job = CoroutineScope(Dispatchers.Default).launch(exceptionHandler) {
    throw IllegalArgumentException()
  }

  delay(1_000L)
  job.start()
}

fun example8(): Unit = runBlocking {
  val job = launch {
//    try {
//      delay(1_000L)
//    } catch (e: CancellationException) {
//      //아무것도 하지 않음.
//    }
    kotlin.runCatching {
      delay(1_000L)
    }
    printWithThread("delay에 의해 취소되지 않았다.")
  }

  delay(100L)
  printWithThread("취소 시작")
  job.cancel()

}


fun example7(): Unit = runBlocking {
  val job = launch(Dispatchers.Default) {
    var i = 1
    var nextPrintTime = System.currentTimeMillis()
    while (isActive && i <= 5) {
      if (nextPrintTime <= System.currentTimeMillis()) {
        printWithThread("${i++}번째 출력")
        nextPrintTime += 1_000L
      }
//      if (!isActive) {
//        throw CancellationException()
//      }
    }
  }
  printWithThread("@@@")
  delay(100L)
  job.cancel()
}

fun example6(): Unit = runBlocking {
  val job1 = launch {
    delay(1_000L)
    printWithThread("Job 1")
  }

  val job2 = launch {
    delay(1_000L)
    printWithThread("Job 2")
  }

  delay(100)

  job1.cancel()
}