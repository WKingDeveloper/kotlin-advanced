package extra


// kodc <Block Tag>
/**
 * 박스 나타내는 클래스.
 *
 * ** 강조 **
 * * A
 * * B
 * * C
 *
 * @param T 박스의 아이템 타입
 * @property name 박스의 이름
 * @sample extra.a.b.helloWorld
 */
class Box<T>(
    val name: String
) {
    /**
     * @param item 박스에 들어갈 아이템
     */
    fun add(item: T): Boolean {
        TODO()
    }
}
