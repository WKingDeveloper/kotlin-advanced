package delegate

import kotlin.properties.Delegates.notNull
import kotlin.properties.Delegates.observable
import kotlin.properties.Delegates.vetoable

class PersonWithDelegate {
    /**
     * lateinit은 기본형 타입에 쓸 수 없으나 notNull()은 가능
     */
    var age: Int by notNull()

    /**
     * 초기값과 값이 변화할때 (set이 호출) 될때 호출되는 함수를 넣어줄 수 있다.
     */
    var weight: Int by observable(70) { property, oldValue, newValue ->
        if (oldValue != newValue) {
            println("프로퍼티 : $property / 옛날 값 : $oldValue / 새로운 값 : $newValue")
        }
    }

    /**
     * observable과 비슷하지만 boolean 타입을 반환값으로 받고 true인 경우에만 변경 값이 적용된다.
     */
    var shoeSize: Int by vetoable(260) { property, oldValue, newValue -> newValue < 300 }

    /**
     * num의 호환성은 유지하면서 다른 프로퍼티로 변경하고 싶은 경우 위임과 @Deprecated 활용
     */
    @Deprecated("age를 사용하세요!", ReplaceWith("eyeSight"))
    var num: Int = 0

    var eyeSight: Int by this::num

}
