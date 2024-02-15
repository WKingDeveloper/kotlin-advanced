package delegate

fun main() {
    val person = PersonWithLateInit()

    /**
     * 런타임에서
     * Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property name has not been initialized
     * 에러 발생
     */
//    person.isKim

    val personWithDelegate = PersonWithDelegate()
    personWithDelegate.weight = 80 // 로깅 출력
    personWithDelegate.weight = 80 // 로깅 출력 X
    personWithDelegate.shoeSize = 250
    println("personWithDelegate.shoeSize : ${personWithDelegate.shoeSize}") // 250
    personWithDelegate.shoeSize = 270
    println("personWithDelegate.shoeSize : ${personWithDelegate.shoeSize}") // 270
    personWithDelegate.shoeSize = 310
    println("personWithDelegate.shoeSize : ${personWithDelegate.shoeSize}") // 310


    val personWithMap = PersonWithMap(mapOf("name" to "age"))
    println(personWithMap.name)
//    println(personWithMap.age) // 예외 발생

//    val personWithProvideDelegate = PersonWithProvideDelegate() // 예외 발생
}
