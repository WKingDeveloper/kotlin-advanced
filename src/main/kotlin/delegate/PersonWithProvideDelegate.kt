package delegate

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * 특정 프로퍼티에만 위임을 허용하고 싶은 경우
 */
class PersonWithProvideDelegate {
    val name by DelegateProvider("WKingDeveloper")
    val country by DelegateProvider("한국") // 실수로 잘못 입력할 수도 있음.
}


class DelegateProvider(
    private val initValue: String
) : PropertyDelegateProvider<Any, DelegateProperty> {
    override fun provideDelegate(thisRef: Any, property: KProperty<*>): DelegateProperty {
        if (property.name != "name") {
            throw IllegalArgumentException("${property.name}은 안됩니다. name에만 연결 가능합니다.")
        }
        return DelegateProperty(initValue)
    }
}

class DelegateProperty(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        property.name == "name"
        return initValue
    }
}
