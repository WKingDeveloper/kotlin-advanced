package reflection

import generic.Animal
import kotlin.reflect.KClass
import kotlin.reflect.cast

class TypeSafeCage {
    private val animals: MutableMap<KClass<*>, Animal> = mutableMapOf()

    fun <T : Animal> getOne(type: KClass<T>): T {
        if (animals[type] == null) throw IllegalArgumentException("존재하는 타입에 값이 없습니다.")
        return type.cast(animals[type])
    }

    fun <T : Animal> putOne(type: KClass<T>, animal: T) {
        animals[type] = type.cast(animal)
    }

    fun <T : Animal> put(animal: T) {
        animals[animal::class] = animal::class.cast(animal)
    }

    inline fun <reified T : Animal> getOne(): T {
        return this.getOne(T::class)
    }

    inline fun <reified T : Animal> putOne(animal: T) {
        return this.putOne(T::class, animal)
    }
}

