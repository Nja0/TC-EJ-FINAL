grammar MiLenguaje;

programa
    : (sentencia)* EOF
    ;

sentencia
    : sentenciaIf
    | sentenciaWhile
    | sentenciaFor
    | sentenciaBreak
    | sentenciaContinue
    | declaracionFuncion
    | declaracionVariable
    | asignacion
    | retorno
    ;

sentenciaIf
    : IF PA expresion PC bloque (ELSE bloque)?
    ;

sentenciaWhile
    : WHILE PA expresion PC bloque
    ;

sentenciaFor
    : FOR PA forInit? PYC expresion? PYC forUpdate? PC bloque
    ;

forInit
    : declaracionVariableSinPYC
    | asignacionSinPYC
    ;

forUpdate
    : asignacionSinPYC
    ;

sentenciaBreak
    : BREAK PYC
    ;

sentenciaContinue
    : CONTINUE PYC
    ;

bloque
    : LA (sentencia)* LC
    ;

declaracionFuncion
    : tipo ID PA parametros? PC bloque
    ;

llamadaFuncion
    : ID PA PC PYC
    ;    

parametros
    : parametro (COMA parametro)*
    ;

parametro
    : tipo ID
    ;

declaracionVariable
    : tipo ID PYC                                           // Variable simple
    | tipo ID IGUAL expresion PYC                           // Variable con inicialización
    | tipo ID CA expresion CC PYC                           // Array sin inicialización: int a[5];
    | tipo ID CA expresion CC IGUAL expresion PYC           // Array con inicialización: int a[5] = ...
    ;

declaracionVariableSinPYC
    : tipo ID
    | tipo ID IGUAL expresion
    | tipo ID CA expresion CC
    | tipo ID CA expresion CC IGUAL expresion
    ;


asignacion
    : ID IGUAL expresion PYC                       // x = 5;
    | ID CA expresion CC IGUAL expresion PYC       // x[0] = 5;
    ;


asignacionSinPYC
    : ID IGUAL expresion
    | ID CA expresion CC IGUAL expresion
    ;


retorno
    : RETURN expresion? PYC
    ;

tipo
    : INT
    | CHAR
    | DOUBLE
    | VOID
    | BOOL
    | STRING
    | FLOAT
    ;

expresion
    : expresion operadorBinario expresion     #expBinaria
    | NOT expresion                           #expNegacion
    | PA expresion PC                         #expParentizada
    | ID                                      #expVariable
    | ID CA expresion CC                      #expArrayAcceso
    | INTEGER                                 #expEntero
    | DECIMAL                                 #expDecimal
    | CHARACTER                               #expCaracter
    | ID PA argumentos? PC                    #expFuncion
    ;

operadorBinario
    : SUM | RES | MUL | DIV | MOD
    | MAYOR | MAYOR_IGUAL | MENOR | MENOR_IGUAL | EQL | DISTINTO
    | AND | OR
    ;
    
argumentos
    : expresion (COMA expresion)*
    ;

// TOKENS LÉXICOS
PA   : '(' ;
PC   : ')' ;
CA   : '[' ;
CC   : ']' ;
LA   : '{' ;
LC   : '}' ;

PYC  : ';' ;
COMA : ',' ;

IGUAL : '=' ;

MAYOR  : '>' ;
MAYOR_IGUAL: '>=' ;
MENOR  : '<' ;
MENOR_IGUAL: '<=' ;
EQL  : '==' ;
DISTINTO  : '!=' ;

SUM  : '+' ;
RES  : '-' ;
MUL  : '*' ;
DIV  : '/' ;
MOD  : '%' ;

OR   : '||' ;
AND  : '&&' ;
NOT  : '!'  ;

// PALABRAS CLAVE
FOR      : 'for' ;
WHILE    : 'while' ;
IF       : 'if' ;
ELSE     : 'else' ;
BREAK    : 'break' ;     // ← AGREGADO
CONTINUE : 'continue' ;  // ← AGREGADO

INT     : 'int' ;
CHAR    : 'char' ;
DOUBLE  : 'double' ;
VOID    : 'void' ;
BOOL    : 'bool' ;
STRING  : 'string' ;
FLOAT   : 'float' ;

RETURN : 'return' ;

// IDENTIFICADORES Y LITERALES
ID : (LETRA | '_') (LETRA | DIGITO | '_')* ;
INTEGER : DIGITO+ ;
DECIMAL : INTEGER '.' INTEGER ;
CHARACTER : '\'' (~['\r\n] | '\\' .) '\'' ;

// COMENTARIOS
COMENTARIO_LINEA : '//' ~[\r\n]* -> skip ;
COMENTARIO_BLOQUE : '/*' .*? '*/' -> skip ;

// ESPACIOS EN BLANCO
WS : [ \r\n\t] -> skip ;

// CATCH-ALL
OTRO : . ;

// FRAGMENTOS
fragment LETRA : [A-Za-z] ;
fragment DIGITO : [0-9] ;