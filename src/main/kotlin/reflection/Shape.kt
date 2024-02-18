package reflection

import kotlin.reflect.KClass

@Repeatable // 어노테이션을 2번 이상 작성할 수 있게 함
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.CLASS)
annotation class Shape(
    val texts: Array<String>,
    val clazz: KClass<*> // KClass란 class의 정보를 담고 있는 객체를 의미. object::class를 의미
)

@Target(AnnotationTarget.FIELD)
annotation class Circle
