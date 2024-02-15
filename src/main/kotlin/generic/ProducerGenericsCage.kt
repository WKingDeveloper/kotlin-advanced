package generic

class ProducerGenericsCage<out T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun getAll(): List<T> {
        return animals
    }
}
