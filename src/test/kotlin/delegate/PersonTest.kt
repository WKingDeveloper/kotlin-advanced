package delegate

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PersonTest {

    private val person = PersonWithLateInit()

    @Test
    fun isKimTest() {
        val person = person.apply { name = "김수한무" }
        assertEquals(true, person.isKim)
    }

    @Test
    fun maskingNameTest() {
        val person = person.apply { name = "최태현" }
        assertEquals("최**", person.maskingName)
    }
}
