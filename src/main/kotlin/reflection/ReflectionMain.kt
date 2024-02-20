package reflection

import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KType
import kotlin.reflect.cast
import kotlin.reflect.full.createType
import kotlin.reflect.full.hasAnnotation

fun main() {
    /**
     * KClass - 코틀린의 리플렉션 클래스
     * Class - 자바의 리플렉션 클래스
     * 둘은 언어 차이에 따라 공통된 기능도 있고 다른 기능도 존재한다.
     */
    val kClass: KClass<Reflection> = Reflection::class
    val ref = Reflection()
    val kClass2: KClass<out Reflection> = ref::class
    val kClass3: KClass<out Any> = Class.forName("reflect.Reflection").kotlin

    executeAll(Reflection())

    // 타입을 얻을 수 있음
    val kType: KType = Cap::class.createType()
    println("kClass = $kClass")

    val cap = Cap("빨강")
    cap::class.members.first { it.name == "print" }.call(cap)
}

fun executeAll(obj: Any) {
    val kClass = obj::class
    if (!kClass.hasAnnotation<Executable>()) {
        return
    }

    val callableFunctions = kClass.members.filterIsInstance<KFunction<*>>()
        .filter { it.returnType == Unit::class.createType() }
        .filter { it.parameters.size == 1 && it.parameters[0].type == kClass.createType() }

    callableFunctions.forEach {
        it.call(obj)
    }
}

class Cap(
    val color: String
) {
    fun print() {
        println("모자의 색깔은 ${color}입니다.")
    }
}

fun castToCap(obj: Any): Cap {
//    return obj as Cap
    return Cap::class.cast(obj)
}
