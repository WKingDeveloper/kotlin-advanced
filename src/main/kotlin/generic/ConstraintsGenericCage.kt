package generic

//class ConstraintsGenericCage<T : Animal> {
class ConstraintsGenericCage<T>(
    private val animals: MutableList<T> = mutableListOf()
) where  T : Animal, T : Comparable<T> {

    fun printAfterSorting() {
        animals.sorted().map { it.name }.let { println(it) }
    }

    fun getFirst(): T {
        return animals.first()
    }

    fun put(animal: T) {
        this.animals.add(animal)
    }

    fun moveForm(otherCage: ConstraintsGenericCage<out T>) {
        if (this == otherCage) {
            throw RuntimeException("같은 케이지입니다")
        }
//        otherCage.getFirst() // 가능
//        otherCage.put(this.getFirst()) // out을 붙여 불가능
        this.animals.addAll(otherCage.animals)
    }

    fun moveTo(otherCage: ConstraintsGenericCage<in T>) {
        if (this == otherCage) {
            throw RuntimeException("같은 케이지입니다")
        }
        otherCage.animals.addAll(this.animals)
    }
}
