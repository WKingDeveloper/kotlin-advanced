package generic

class ChildCage<T : Animal> : ParentCage<T>() {
    override fun addAnimal(animal: T) {

    }
}

class GoldFishCage : ParentCage<GoldFish>() {

    override fun addAnimal(animal: GoldFish) {
        
    }
}
