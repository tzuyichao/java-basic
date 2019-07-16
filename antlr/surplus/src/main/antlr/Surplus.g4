grammar Surplus;

prog
    : expression EOF                            # program
    ;

expression
    : NOT expression                            # notExpression
    | expression AND expression                 # andExpression
    | expression OR expression                  # orExpression
    | '(' expression ')'                        # expressionExpression
    | phraseExpr                                # phraseExpression
    | termExpr                                  # termExpression
    ;

phraseExpr
    : PHRASE
    ;

termExpr
    : TERM
    ;

AND
    : '&&'
    ;

OR
    : '||'
    ;

NOT
    : '!'
    ;

WS
   : [ \t\r\n\u000C] -> skip
   ;

PHRASE
    : '"' ~ ["\r\n]* '"'
    ;

TERM
    :  ~ ["\r\n!|&()]*
    ;