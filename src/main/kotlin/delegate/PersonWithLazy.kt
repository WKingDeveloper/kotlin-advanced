package delegate

class PersonWithLazy {

    /**
     * backing property의 동작 방식가 동일하게 동작됨.
     * lazy 람다 블록안에 함수는 최초 호출될 때 실행되고, 기본적으로 Thread-safe 하다.
     */
    val name: String by lazy {
        Thread.sleep(2000L)
        "김수한무"
    }

}
