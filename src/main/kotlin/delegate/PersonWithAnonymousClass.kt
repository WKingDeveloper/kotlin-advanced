package delegate

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class PersonWithAnonymousClass {
    val status: String by object : ReadOnlyProperty<PersonWithAnonymousClass, String> {
        private var isGreen: Boolean = false
        override fun getValue(thisRef: PersonWithAnonymousClass, property: KProperty<*>): String {
            return if (isGreen) {
                isGreen = false
                "Happy"
            } else {
                isGreen = true
                "Sad"
            }
        }
    }
}
