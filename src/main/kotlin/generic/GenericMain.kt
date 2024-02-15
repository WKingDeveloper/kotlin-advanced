package generic

fun main() {

    /**
     * cage.getFirst()의 타입은 Animal인데 getFirst()로 나오는 값이 어떤 타입일지 알 수 없음
     */
    val cage = Cage()
    cage.put(Carp("잉어"))
//    val first: Carp = cage.getFirst() // Error: Type Mismatch

    /**
     * 제너릭 클래스를 이용하여 위 문제를 해결
     */
    val genericCage = GenericCage<Carp>()
    genericCage.put(Carp("잉어"))
    val first: Carp = genericCage.getFirst()

    val goldFishCage = GenericCage<GoldFish>()
    goldFishCage.put(GoldFish("금붕어"))

    /**
     * GoldFish는 fish의 하위 타입이지만 put은 가능하고 moveForm은 불가능함.
     * moveForm 파라미터 타입을 GenericCage<out T>과 같이 out을 붙여 공변으로 지정하면 타입에러가 안남.
     */
    val fishCage = GenericCage<Fish>()
    fishCage.moveForm(goldFishCage)
    fishCage.put(GoldFish("금붕어"))
    val fish: Fish = fishCage.getFirst()

    val fishCage2 = GenericCage<Fish>()
    val goldFishCage2 = GenericCage<GoldFish>()
    goldFishCage2.put(GoldFish("금붕어"))
    goldFishCage.moveTo(fishCage2) // moveTo에는 반공변 in을 붙여 type miss match를 해결


    /**
     * 생산만 하는 ProducerGenericsCage는 클래스의 out(변성)을 줘서 공변을 만들어 해당 작업이 가능
     */
    val fishCage3 = ProducerGenericsCage<Fish>()
    val animalCage: ProducerGenericsCage<Animal> = fishCage3

    /**
     * Cage 타입에 제약을 건 경우
     */
//    val constraintsGenericCage = ConstraintsGenericCage<Int>() // 에러 발생

    /**
     * Bird 클래스의 참새와 독수리가 사이즈 크기로 정렬
     */
    val birdCage = ConstraintsGenericCage(mutableListOf(Eagle(), Sparrow()))
    birdCage.printAfterSorting()

    /**
     * 코틀린 raw type 객체를 만들 수 없음. 자바는 가능
     */
//    val list: List = listOf(1, 2, 3) // 컴파일 에러 발생

    val num = 3
    num.toSuperString() // "Int: 3"

    val str = "ABC"
    str.toSuperString() // "String: 3"

    /**
     * inline, reified를 사용하는 함수 예제
     */
    val numbers = listOf(1, 2f, 3.0)
    numbers.filterIsInstance<Float>() // [2f]만 담김

    /**
     * 타입 파라미터 섀도잉
     */
    val cageShadow = CageShadow<GoldFish>()
    cageShadow.addAnimal(GoldFish("금붕어"))
    cageShadow.addAnimal(Carp("잉어")) // 함수에서 T 제너릭을 또 받고 있어 클래스의 GoldFish(T) 타입과 상관없이 가능해짐.
}

/**
 * 코틀린 타입 소거 확인할 수 있는 대표적인 코드
 */
fun checkList(data: Any) {
//    if (data is List<String>) { // 에러 발생. 런타임 시 List의 타입이 소거되기 때문에 String인지 알 수 없음.
    if (data is List<*>) { // star projection을 활용해 최소한 List인지 확인 가능

    }
}

/**
 * 제너릭 함수에서도 타입 정보는 사라진다.
 */
fun <T> T.toSuperString() {
//    println("${T::class.java.name}: $this") // 에러 발생. T의 타입이 사라져 클래스 정보를 알 수 없음.
}

inline fun <reified T> List<*>.hasAnyInstanceOf(): Boolean {
    return this.any { it is T }
}
