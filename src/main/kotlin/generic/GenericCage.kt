package generic

class GenericCage<T> {
    private val animals: MutableList<T> = mutableListOf()

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveForm(otherCage: GenericCage<out T>) {
        if (this == otherCage) {
            throw RuntimeException("같은 케이지입니다")
        }
//        otherCage.getFirst() // 가능
//        otherCage.put(this.getFirst()) // out을 붙여 불가능
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: GenericCage<in T>) {
        if (this == otherCage) {
            throw RuntimeException("같은 케이지입니다")
        }
        otherCage.animals.addAll(this.animals)
    }
}
