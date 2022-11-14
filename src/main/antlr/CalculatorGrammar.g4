grammar CalculatorGrammar;
statement : (assignment  END) | (expr END);
assignment : ID '=' expr;
expr : expr PLUS expr
  | expr TIMES expr
  | '('  expr ')'
  | ID
  | INTEGER;

PLUS: '+';
TIMES: '*';
END: ';';
ID: ('a' .. 'z') (('a' .. 'z') | ('A' .. 'Z'))*;
INTEGER: ('0' .. '9')+;
WS: [ \r\n] -> skip;
//grammar Expr;
//prog:    expr EOF ;
//expr:    expr ('*'|'/') expr
//    |    expr ('+'|'-') expr
//    |    INT
//    |    '(' expr ')'
//    ;
//NEWLINE : [\r\n]+ -> skip;
//INT     : [0-9]+ ;