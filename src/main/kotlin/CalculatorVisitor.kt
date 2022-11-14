import antlrGeneratedSource.CalculatorGrammarBaseVisitor
import antlrGeneratedSource.CalculatorGrammarParser
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.toResultOr

class CalculatorVisitor : CalculatorGrammarBaseVisitor<Result<Int, String>>() {
    private val calculatedValues = mutableMapOf<String, Int>()

    private fun functionFromText(text: String): (Int, Int) -> Int =
        if (text == "+") { x, y -> x + y } else { x, y -> x * y }

    override fun visitExpr(ctx: CalculatorGrammarParser.ExprContext): Result<Int, String> {
        when {
            ctx.children.first().text == "(" -> {
                return ctx.children[1].accept(this)
            }

            ctx.childCount == 3 -> {
                val left = ctx.children.first().accept(this)
                val right = ctx.children.last().accept(this)
                val opText = ctx.children[1].text
                return if (left !is Ok<Int>) {
                    left
                } else if (right !is Ok<Int>) {
                    right
                } else {
                    Ok(functionFromText(opText)(left.value, right.value))
                }
            }

            else -> {
                return if (ctx.text.matches("[a-z][a-zA-Z]*".toRegex())) {
                    calculatedValues[ctx.text].toResultOr { "Failed lookup of ${ctx.text}" }
                } else {
                    Ok(ctx.text.toInt())
                }
            }
        }
    }

    override fun visitStatement(ctx: CalculatorGrammarParser.StatementContext): Result<Int, String> =
        ctx.children.first().accept(this)

    override fun visitAssignment(ctx: CalculatorGrammarParser.AssignmentContext): Result<Int, String> {
        val rightHandSide = ctx.children[2].accept(this)
        return if (rightHandSide !is Ok<Int>)
            rightHandSide
        else {
            calculatedValues[ctx.children.first().text] = rightHandSide.value
            rightHandSide
        }
    }
}