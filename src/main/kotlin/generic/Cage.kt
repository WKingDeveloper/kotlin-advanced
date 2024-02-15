package generic

import generic.Animal

class Cage {
    private val animals: MutableList<Animal> = mutableListOf()

    fun getFirst(): Animal {
        return animals.first()
    }

    fun put(animal: Animal) {
        this.animals.add(animal)
    }

    fun moveForm(cage: Cage) {
        if (this == cage) {
            throw RuntimeException("같은 케이지입니다")
        }
        this.animals.addAll(cage.animals)
    }
}


