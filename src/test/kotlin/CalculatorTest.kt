import com.github.michaelbull.result.Err
import com.github.michaelbull.result.unwrap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

class CalculatorTest {
    @Test
    fun `test parsing errors`() {
        assertEquals(syntaxError, CalculatorVisitor().executeStatement("2 ++ 3;"))
        assertEquals(syntaxError, CalculatorVisitor().executeStatement("2 ()+ 3;"))
        assertEquals(syntaxError, CalculatorVisitor().executeStatement("2 (+ 3);"))
        assertEquals(syntaxError, CalculatorVisitor().executeStatement("2 + 3"))
        assertNotEquals(syntaxError, CalculatorVisitor().executeStatement("(2 + 3);"))
    }

    @Test
    fun `simple calculations`() {
        val visitor = CalculatorVisitor()
        assertEquals(3, visitor.executeStatement("1 + 2;").unwrap())
        assertEquals(9, visitor.executeStatement("(1 + 2) * 3;").unwrap())
        assertEquals(7, visitor.executeStatement("1 + (2 * 3);").unwrap())
        assertEquals(21, visitor.executeStatement("(1 + 2) * (3 + 4);").unwrap())
    }

    @Test
    fun `variables definition and redefinition`() {
        val visitor = CalculatorVisitor()
        Assertions.assertTrue(visitor.executeStatement("a + 2;") is Err<String>)
        assertEquals(3, visitor.executeStatement("a = 1 + 2;").unwrap())
        assertEquals(9, visitor.executeStatement("a * 3;").unwrap())
        assertEquals(4, visitor.executeStatement("a = a + 1;").unwrap())
        assertEquals(4, visitor.executeStatement("a;").unwrap())
    }
}