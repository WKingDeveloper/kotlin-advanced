package delegate

class PersonWithBackingProperty {
    /**
     * 최초 초기화 한번에만 호출될 수 있다는 요구사항은 모두 만족하나 코드가 길어 매번 작성하기 불편
     */
    private var _name: String? = null
    val name: String
        get() {
            if (_name == null) {
                Thread.sleep(2000L)
                this._name = "김수한무"
            }
            return this._name!!
        }
}


