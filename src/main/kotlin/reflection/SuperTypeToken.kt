package reflection

import kotlin.reflect.KType

/**
 * SuperTypeToken을 구현한 클래스가 인스턴스화 되자마자
 * T 정보를 내부 변수에 저장해버린다.
 */
abstract class SuperTypeToken<T> {
    val type: KType = this::class.supertypes[0].arguments[0].type!!

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as SuperTypeToken<*>
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}
