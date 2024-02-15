package delegate

class PersonWithMap(map: Map<String, Any>) {
    val name: String by map
    val age: Int by map
}
