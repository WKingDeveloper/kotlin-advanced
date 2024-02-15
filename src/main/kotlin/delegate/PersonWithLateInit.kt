package delegate

class PersonWithLateInit(
) {
    /**
     * lateinit은 컴파일 시 nullable하게 바뀌고 초기값이 null이 들어가 호출 시 null인지 아닌지 체크한다.
     * 따라서 null이 될 수 없는 기본형 타입에서는 lateinit을 사용할 수 없다.
     */
    lateinit var name: String

    val isKim: Boolean
        get() = this.name.startsWith("김")

    val maskingName: String
        get() = name[0] + (1..<name.length).joinToString("") { "*" }
}
