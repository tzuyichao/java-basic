grammar Surplus;


expression
    :  (NOT)? (phraseExpr | termExpr) ((AND | OR | NOT) (phraseExpr | termExpr))*
    ;

phraseExpr
    : PHRASE
    ;

termExpr
    : TERM
    ;

PHRASE
    : '"' ~ ["\r\n]* '"'
    ;

TERM
    :  ~["\r\n]*
    ;

AND
    : 'AND'
    ;

OR
    : 'OR'
    ;

NOT
    : 'NOT'
    ;

WS
   : [ \t] -> skip
   ;