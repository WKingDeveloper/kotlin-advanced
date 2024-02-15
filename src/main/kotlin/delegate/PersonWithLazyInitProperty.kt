package delegate

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PersonWithLazyInitProperty {
    /**
     * 위임 패턴을 이용하여 name에 값을 직접 응답하는게 아니라 delegateProperty에 위임함
     */
    private val delegateProperty = LazyInitProperty {
        Thread.sleep(2000L)
        "김수한무"
    }

    val name: String
        get() = delegateProperty.value

    val job: String by LazyInitProperty {
        Thread.sleep(2000L)
        "개발자"
    }

    val hobby: String by lazy {
        Thread.sleep(2000L)
        "독서"
    }
}

/**
 * Backing Property를 일반화시킴
 * ReadWriteProperty, ReadOnlyProperty를 통해 필요한 메서드를 쉽게 오버라이드 가능
 */
class LazyInitProperty<T>(val init: () -> T) : ReadWriteProperty<Any, T> {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this._value = value
    }
}
