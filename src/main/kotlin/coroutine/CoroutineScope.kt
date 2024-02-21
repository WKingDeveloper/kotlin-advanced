package coroutine

import kotlinx.coroutines.*
import java.util.concurrent.Executors

suspend fun main() {
  val job = CoroutineScope(Dispatchers.Default).launch {
    delay(1_000L)
    printWithThread("Job 1")
  }

//  job.join()
  generateCoroutineContext()
  Thread.sleep(2_000L)

  CoroutineName("나만의 코루틴") + SupervisorJob() + Dispatchers.Default

  val threadPool = Executors.newSingleThreadExecutor()
  CoroutineScope(threadPool.asCoroutineDispatcher()).launch {
    printWithThread("새로운 코루틴")
  }
  printWithThread("main() END")
}

suspend fun generateCoroutineContext() {
  val job2 = CoroutineScope(Dispatchers.Default).launch {
    delay(1_000L)
    printWithThread("Job 2")
    coroutineContext + CoroutineName("이름")
    coroutineContext.minusKey(CoroutineName.Key)
  }
  job2.start()
}

class AsyncLogic {
  private val scope = CoroutineScope(Dispatchers.Default)

  fun doSomething() {
    scope.launch {
      // 무언가 코루틴이 시작되어 작업!
    }
  }

  fun destroy() {
    scope.cancel()
  }
}