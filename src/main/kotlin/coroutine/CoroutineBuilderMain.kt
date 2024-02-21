package coroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

fun example1() {
  runBlocking {
    printWithThread("START")
    launch {
      delay(2_000L) // yield()
//      yield()
      printWithThread("LAUNCH END")
    }
  }
  printWithThread("END")
}

fun example2(): Unit = runBlocking {
  val job = launch(start = CoroutineStart.LAZY) {
    printWithThread("Hello launch")
  }
  delay(1_000L)
  job.start()
}

fun example3(): Unit = runBlocking {
  val job = launch {
    (1..5).forEach {
      printWithThread(it)
      delay(500)
    }
  }
  delay(1_000L)
  job.cancel()
}

fun example4(): Unit = runBlocking {
  val job1 = launch {
    delay(1_000)
    printWithThread("Job 1")
  }

  job1.join()

  val job2 = launch {
    delay(2_000)
    printWithThread("Job 2")
  }

}

fun example5(): Unit = runBlocking {
  val job = async {
    3 + 5
  }
  val num = job.await()
  println("num = ${num}")
}

fun main(): Unit = runBlocking {
  val time = measureTimeMillis {
    val job1 = async { apiCall1() }
    val job2 = async { apiCall2(1) }
    val job3 = async(start = CoroutineStart.LAZY) { apiCall3() }
    // job3.start() // 하게될 경우 미리 대기하고 있어 딜레이가 추가로 1초가 안걸리게 된다.
    printWithThread(job1.await() + job2.await() + job3.await())
  }
  printWithThread("소요시간 = ${time}ms")
}

suspend fun apiCall1(): Int {
  delay((1_000L))
  return 1
}

suspend fun apiCall2(num: Int): Int {
  delay((1_000L))
  return num + 1
}

suspend fun apiCall3(): Int {
  delay((1_000L))
  return 3
}

