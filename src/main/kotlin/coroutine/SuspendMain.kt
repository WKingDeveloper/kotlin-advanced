package coroutine

import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture

fun main(): Unit = runBlocking {
  printWithThread("START")
  printWithThread(calculateResult())
  printWithThread(calculateResult2())
  printWithThread("END")

  launch {
    a()
    b()
  }

  launch {
    c()
  }

  val result = call1()
  val result2 = call2(result)
  printWithThread("result2 = ${result2}")
}

suspend fun calculateResult(): Int = coroutineScope {
  val num1 = async {
    printWithThread("calculateResult num1")
    delay(1_000L)
    10
  }
  val num2 = async {
    printWithThread("calculateResult num2")
    delay(1_000L)
    20
  }
  num1.await() + num2.await()
}

suspend fun calculateResult2(): Int = withContext(Dispatchers.Default) {
  val num1 = async {
    printWithThread("calculateResult num1")
    delay(1_000L)
    10
  }
  val num2 = async {
    printWithThread("calculateResult num2")
    delay(1_000L)
    20
  }
  num1.await() + num2.await()
}

suspend fun call1(): Int {
  return CoroutineScope(Dispatchers.Default).async {
    printWithThread("call1")
    delay(1_000L)
    100
  }.await()
}

suspend fun call2(num: Int): Int {
  return CompletableFuture.supplyAsync {
    printWithThread("call2")
    Thread.sleep(1_000L)
    num * 2
  }.await()
}

suspend fun a() {
  printWithThread("A")
}

suspend fun b() {
  printWithThread("B")
}

suspend fun c() {
  printWithThread("C")
}

interface AsyncCaller {
  suspend fun call()
}

class AsyncCallerImpl : AsyncCaller {
  override suspend fun call() {
    TODO("Not yet implemented")
  }

}