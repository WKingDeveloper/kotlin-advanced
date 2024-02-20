package reflection

import kotlin.reflect.KClass
import kotlin.reflect.cast

class TypeSafeCage2 {
    private val animals: MutableMap<KClass<*>, Any> = mutableMapOf()

    fun <T : Any> getOne(type: KClass<T>): T {
        if (animals[type] == null) throw IllegalArgumentException("존재하는 타입에 값이 없습니다.")
        return type.cast(animals[type])
    }

    fun <T : Any> putOne(type: KClass<T>, animal: T) {
        animals[type] = type.cast(animal)
    }

    fun <T : Any> put(Any: T) {
        animals[Any::class] = Any::class.cast(Any)
    }

    inline fun <reified T : Any> getOne(): T {
        return this.getOne(T::class)
    }

    inline fun <reified T : Any> putOne(animal: T) {
        return this.putOne(T::class, animal)
    }
}

