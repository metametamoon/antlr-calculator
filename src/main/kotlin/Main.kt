import antlrGeneratedSource.CalculatorGrammarLexer
import antlrGeneratedSource.CalculatorGrammarParser
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Result
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

val syntaxError = Err("Syntax error.")

fun CalculatorVisitor.executeStatement(statement: String): Result<Int, String> {
    val inputStream: CharStream = CharStreams.fromString(statement)
    val lexer = CalculatorGrammarLexer(inputStream)
    val commonTokenStream = CommonTokenStream(lexer)
    val parser = CalculatorGrammarParser(commonTokenStream)
    val statementContext = parser.statement()
    if (parser.numberOfSyntaxErrors > 0) {
        return syntaxError
    }
    return this.visitStatement(statementContext)
}

fun main() {
    val commands = File("sample-input.txt").readLines()
    val visitor = CalculatorVisitor()
    for (line in commands) {
        val result = visitor.executeStatement(line)
        println("${line.padEnd(15, ' ')} -> $result")
    }
}