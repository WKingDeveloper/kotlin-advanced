package delegate

interface Fruit {
    val name: String
    val color: String
    fun bite()
}

open class Apple : Fruit {
    override val name: String
        get() = "사과"
    override val color: String
        get() = "빨간색"

    override fun bite() {
        println("사과 톡톡~ 아삭~")
    }
}

class GreenAppleFromFruit : Fruit {
    override val name: String
        get() = "사과"
    override val color: String
        get() = "초록색"

    override fun bite() {
        println("사과 톡톡~ 아삭~")
    }
}

class GreenAppleFromApple : Apple() {
    override val color: String
        get() = "초록색"
}

class GreenAppleFromSynthesis(
    private val apple: Apple
) : Fruit {
    override val name: String
        get() = apple.name
    override val color: String
        get() = "초록색"

    override fun bite() {
        apple.bite()
    }
}

class GreenAppleFromLazy(
    private val apple: Apple
) : Fruit by apple {
    override val color: String
        get() = "초록색"
}
