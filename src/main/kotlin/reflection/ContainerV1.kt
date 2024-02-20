package reflection

import kotlin.reflect.KClass

object ContainerV1 {

    //등록할 클래스 보관 = KClass를 보관
    private val registeredClasses = mutableSetOf<KClass<*>>()

    fun register(clazz: KClass<*>) {
        registeredClasses.add(clazz)
    }

    fun <T : Any> getInstance(type: KClass<T>): T {
        registeredClasses.firstOrNull { it == type }
            ?.let { clazz -> return clazz.constructors.first().call() as T }
            ?: throw IllegalArgumentException("해당 인스턴스 타입을 찾을 수 없습니다")
    }
}
