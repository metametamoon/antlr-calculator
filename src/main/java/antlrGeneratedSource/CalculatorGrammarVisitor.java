// Generated from java-escape by ANTLR 4.11.1
package antlrGeneratedSource;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CalculatorGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CalculatorGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CalculatorGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CalculatorGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(CalculatorGrammarParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link CalculatorGrammarParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(CalculatorGrammarParser.ExprContext ctx);
}