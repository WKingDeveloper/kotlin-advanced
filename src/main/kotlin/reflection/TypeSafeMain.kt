package reflection

import generic.Cage
import generic.Carp
import generic.GoldFish

fun main() {
    val cage = Cage()
    cage.put(Carp("잉어"))
    val first = cage.getFirst()
    cage.getFirst() as Carp // 위험


    /**
     * 리플렉션을 이용하여 제너릭 타입의 데이터를 안전하게 가져옴.
     */
    val typeSafeCage = TypeSafeCage()
    typeSafeCage.putOne(Carp::class, Carp("잉어"))
    typeSafeCage.putOne(Carp("잉어2"))
    typeSafeCage.put(Carp("잉어3"))

    val carp: Carp = typeSafeCage.getOne()
    val carp2 = typeSafeCage.getOne<Carp>()
    val carp3 = typeSafeCage.getOne(Carp::class)
    println("carp = ${carp.name}")
//    val goldFish = typeSafeCage.getOne(GoldFish::class) // 에러 발생

    /**
     * 제너릭 타입이 런타임시에 *로 변경되어 List<GoldFish>를 저장하고 List<Carp>를 호출해도
     * List<GoldFish> 데이터가 호출됨
     */
    val typeSafeCage2 = TypeSafeCage2()
    typeSafeCage2.putOne(listOf(GoldFish("금붕어1"), GoldFish("금붕어2")))
    val type2 = typeSafeCage2.getOne<List<Carp>>()
    println("type2 = ${type2}")

    /**
     * 제너릭 타입이 *로 변경되는 것을 방지하여 SuperTypeToken을 이용하여 런타임시에 해당 타입에 정보를 저장하고
     * 그 타입을 기준으로 데이터가 호출되도록 함.
     */
    val superTypeToken1 = object : SuperTypeToken<List<GoldFish>>() {}
    val superTypeToken2 = object : SuperTypeToken<List<GoldFish>>() {}
    val superTypeToken3 = object : SuperTypeToken<List<Carp>>() {}
    println(superTypeToken2.equals(superTypeToken1))
    println(superTypeToken3.equals(superTypeToken1))

    val superTypeSafeCage = SuperTypeSafeCage()
    superTypeSafeCage.putOne(superTypeToken2, listOf(GoldFish("금붕어1"), GoldFish("금붕어2")))
//    superTypeSafeCage.putOne(superTypeToken3, listOf(Carp("잉어1"), Carp("잉어2")))
    println(superTypeSafeCage.getOne(superTypeToken2))
//    println(superTypeSafeCage.getOne(superTypeToken3)) // 에러 발생

}
