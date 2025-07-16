package com.compilador;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class CodigoVisitor extends MiLenguajeBaseVisitor<String> {
    
    private GeneradorCodigo generador;
    private TablaSimbolos tabla;
    private Deque<String> pilaInicioBucle = new ArrayDeque<>();
    private Deque<String> pilaFinBucle = new ArrayDeque<>();
    
    public CodigoVisitor(TablaSimbolos tabla) {
        this.generador = new GeneradorCodigo();
        this.tabla = tabla;
        System.out.println("🎯 VISITOR: Iniciado con tabla de símbolos");
    }
    
    /**
     * Obtiene el generador de código
     */
    public GeneradorCodigo getGenerador() {
        return generador;
    }
    
    @Override
    public String visitPrograma(MiLenguajeParser.ProgramaContext ctx) {
        System.out.println("🎯 VISITOR: Iniciando recorrido del programa");
        
        for (MiLenguajeParser.SentenciaContext sentencia : ctx.sentencia()) {
            System.out.println("🎯 VISITOR: Procesando una sentencia...");
            visit(sentencia);
        }
        
        System.out.println("🎯 VISITOR: Programa completado");
        return null;
    }
    
    @Override
    public String visitDeclaracionFuncion(MiLenguajeParser.DeclaracionFuncionContext ctx) {
        String nombreFuncion = ctx.ID().getText();
        System.out.println("🎯 VISITOR: Encontré función -> " + nombreFuncion);
        
        // Generar etiqueta para la función
        generador.genLabel("func_" + nombreFuncion);
        
        // Cambiar ámbito (simulado)
        System.out.println("🎯 VISITOR: Cambiando al ámbito de " + nombreFuncion);
        
        // Procesar el bloque de código
        System.out.println("🎯 VISITOR: Procesando cuerpo de la función...");
        visit(ctx.bloque());
        
        System.out.println("🎯 VISITOR: Función " + nombreFuncion + " completada");
        return null;
    }
    
    @Override
    public String visitBloque(MiLenguajeParser.BloqueContext ctx) {
        System.out.println("🎯 VISITOR: Procesando bloque con " + ctx.sentencia().size() + " sentencias");
        
        for (MiLenguajeParser.SentenciaContext sentencia : ctx.sentencia()) {
            visit(sentencia);
        }
        
        return null;
    }
    
    @Override
public String visitAsignacion(MiLenguajeParser.AsignacionContext ctx) {
    // Casos:
    // ID IGUAL expresion PYC
    // ID CA expresion CC IGUAL expresion PYC

    if (ctx.CA() != null) {
        // Asignación a un array: x[2] = 10;
        String nombreArray = ctx.ID().getText();
        String indice = visit(ctx.expresion(0));  // índice dentro de los []
        String valor = visit(ctx.expresion(1));   // valor a asignar
        generador.getCodigo().add(nombreArray + "[" + indice + "] = " + valor + ";");
        System.out.println("🎯 VISITOR: Asignación a array -> " + nombreArray + "[" + indice + "] = " + valor);
    } else {
        // Asignación normal: x = 10;
        String variable = ctx.ID().getText();
        String resultado = visit(ctx.expresion(0)); // Usamos expresion(0) para evitar error de tipo
        generador.genAsignacion(variable, resultado);
        System.out.println("🎯 VISITOR: Asignación simple -> " + variable + " = " + resultado);
    }

    return null;
}

    
    @Override
    public String visitSentenciaIf(MiLenguajeParser.SentenciaIfContext ctx) {
        System.out.println("🎯 VISITOR: Encontré sentencia IF");
        
        // Evaluar la condición
        System.out.println("🎯 VISITOR: Evaluando condición del IF...");
        String condicion = visit(ctx.expresion());
        
        // Crear etiquetas
        String labelElse = generador.newLabel();
        String labelFin = generador.newLabel();
        
        // Generar salto condicional
        System.out.println("🎯 VISITOR: Si condición es falsa, saltar a " + labelElse);
        generador.genIfFalse(condicion, labelElse);
        
        // Procesar bloque IF
        System.out.println("🎯 VISITOR: Procesando bloque IF...");
        visit(ctx.bloque(0));
        
        // Si hay ELSE
        if (ctx.ELSE() != null) {
            System.out.println("🎯 VISITOR: Hay ELSE, saltando al final...");
            generador.genGoto(labelFin);
            generador.genLabel(labelElse);
            
            System.out.println("🎯 VISITOR: Procesando bloque ELSE...");
            visit(ctx.bloque(1));
            
            generador.genLabel(labelFin);
        } else {
            generador.genLabel(labelElse);
        }
        
        System.out.println("🎯 VISITOR: IF completado");
        return null;
    }

   @Override
public String visitSentenciaWhile(MiLenguajeParser.SentenciaWhileContext ctx) {
    System.out.println("🎯 VISITOR: Encontré un WHILE");

    String labelInicio = generador.newLabel();
    String labelFin = generador.newLabel();

    pilaInicioBucle.push(labelInicio);
    pilaFinBucle.push(labelFin);

    generador.genLabel(labelInicio);

    String condicion = visit(ctx.expresion());
    generador.genIfFalse(condicion, labelFin);

    visit(ctx.bloque());

    generador.genGoto(labelInicio);
    generador.genLabel(labelFin);

    pilaInicioBucle.pop();
    pilaFinBucle.pop();

    return null;
}

// Métodos que necesitas AGREGAR o MODIFICAR en tu CodigoVisitor.java

@Override
public String visitForInit(MiLenguajeParser.ForInitContext ctx) {
    System.out.println("🎯 VISITOR: Procesando inicialización del FOR");
    
    if (ctx.declaracionVariableSinPYC() != null) {
        return visit(ctx.declaracionVariableSinPYC());
    } else if (ctx.asignacionSinPYC() != null) {
        return visit(ctx.asignacionSinPYC());
    }
    return null;
}

@Override
public String visitForUpdate(MiLenguajeParser.ForUpdateContext ctx) {
    System.out.println("🎯 VISITOR: Procesando actualización del FOR");
    
    if (ctx.asignacionSinPYC() != null) {
        return visit(ctx.asignacionSinPYC());
    }
    return null;
}

// NUEVOS métodos que necesitas AGREGAR para las versiones sin punto y coma
@Override
public String visitDeclaracionVariableSinPYC(MiLenguajeParser.DeclaracionVariableSinPYCContext ctx) {
    String tipo = ctx.tipo().getText();
    String variable = ctx.ID().getText();

    if (ctx.CA() != null && ctx.expresion(0) != null) {
        // Caso: tipo ID [expresion]
        String tamanio = visit(ctx.expresion(0));
        System.out.println("🎯 VISITOR: Declaración de array sin ; -> " + tipo + " " + variable + "[" + tamanio + "]");
        generador.getCodigo().add(tipo + " " + variable + "[" + tamanio + "]");
        
        // Si tiene inicialización también
        if (ctx.IGUAL() != null && ctx.expresion(1) != null) {
            String valorInicial = visit(ctx.expresion(1));
            generador.getCodigo().add(variable + "[0] = " + valorInicial); // ejemplo simple
            System.out.println("🎯 VISITOR: Inicialización de " + variable + "[0] = " + valorInicial);
        }
    } else {
        // Declaración simple o con inicialización
        System.out.println("🎯 VISITOR: Declaración variable sin ; -> " + tipo + " " + variable);
        if (ctx.IGUAL() != null && ctx.expresion(0) != null) {
            String valor = visit(ctx.expresion(0));
            generador.genAsignacion(variable, valor);
        }
    }

    return null;
}

@Override
public String visitExpArrayAcceso(MiLenguajeParser.ExpArrayAccesoContext ctx) {
    String arreglo = ctx.ID().getText();
    String indice = visit(ctx.expresion());
    
    System.out.println("🎯 VISITOR: Acceso a array -> " + arreglo + "[" + indice + "]");
    
    // Retornar la representación textual (o podés generar código intermedio si tenés algo como loadArray)
    return arreglo + "[" + indice + "]";
}


@Override
public String visitAsignacionSinPYC(MiLenguajeParser.AsignacionSinPYCContext ctx) {
    if (ctx.CA() != null) {
        // Asignación a un array sin punto y coma
        String nombreArray = ctx.ID().getText();
        String indice = visit(ctx.expresion(0));
        String valor = visit(ctx.expresion(1));
        generador.getCodigo().add(nombreArray + "[" + indice + "] = " + valor);
        System.out.println("🎯 VISITOR: Asignación a array (sin ;) -> " + nombreArray + "[" + indice + "] = " + valor);
    } else {
        // Asignación simple sin punto y coma
        String variable = ctx.ID().getText();
        String resultado = visit(ctx.expresion(0));
        generador.genAsignacion(variable, resultado);
        System.out.println("🎯 VISITOR: Asignación simple (sin ;) -> " + variable + " = " + resultado);
    }

    return null;
}


@Override
public String visitSentenciaFor(MiLenguajeParser.SentenciaForContext ctx) {
    System.out.println("🎯 VISITOR: Encontré un FOR");

    String labelCondicion = generador.newLabel();
    String labelInicio = generador.newLabel();
    String labelFin = generador.newLabel();

    // 🔸 Inicialización (forInit)
    if (ctx.forInit() != null) {
        visit(ctx.forInit());
    }

    // 🔹 Guardar etiquetas para break/continue
    pilaInicioBucle.push(labelInicio);
    pilaFinBucle.push(labelFin);

    // 🔸 Etiqueta para condición
    generador.genLabel(labelCondicion);

    // 🔸 Evaluar condición (opcional)
    if (ctx.expresion() != null) {
        String condicion = visit(ctx.expresion());
        generador.genIfFalse(condicion, labelFin);
    }

    // 🔸 Etiqueta de inicio del cuerpo
    generador.genLabel(labelInicio);

    // 🔸 Cuerpo del for
    visit(ctx.bloque());

    // 🔸 Actualización (forUpdate)
    if (ctx.forUpdate() != null) {
        visit(ctx.forUpdate());
    }

    // 🔸 Volver a evaluar la condición
    generador.genGoto(labelCondicion);

    // 🔸 Fin del bucle
    generador.genLabel(labelFin);

    // 🔸 Limpiar pilas
    pilaInicioBucle.pop();
    pilaFinBucle.pop();

    return null;
}



@Override
public String visitSentenciaBreak(MiLenguajeParser.SentenciaBreakContext ctx) {
    System.out.println("🎯 VISITOR: Encontré BREAK");

    if (pilaFinBucle.isEmpty()) {
        System.err.println("❌ Error: BREAK fuera de un bucle");
        return null;
    }

    generador.genGoto(pilaFinBucle.peek());
    return null;
}

@Override
public String visitSentenciaContinue(MiLenguajeParser.SentenciaContinueContext ctx) {
    System.out.println("🎯 VISITOR: Encontré CONTINUE");

    if (pilaInicioBucle.isEmpty()) {
        System.err.println("❌ Error: CONTINUE fuera de un bucle");
        return null;
    }

    generador.genGoto(pilaInicioBucle.peek());
    return null;
}




    
    @Override
    public String visitExpBinaria(MiLenguajeParser.ExpBinariaContext ctx) {
        String operador = ctx.operadorBinario().getText();
        System.out.println("🎯 VISITOR: Encontré expresión binaria con operador " + operador);
        
        // Evaluar operando izquierdo
        System.out.println("🎯 VISITOR: Evaluando operando izquierdo...");
        String left = visit(ctx.expresion(0));
        
        // Evaluar operando derecho
        System.out.println("🎯 VISITOR: Evaluando operando derecho...");
        String right = visit(ctx.expresion(1));
        
        // Generar la operación
        System.out.println("🎯 VISITOR: Generando operación binaria...");
        return generador.genOperacionBinaria(operador, left, right);
    }
    
    @Override
    public String visitExpVariable(MiLenguajeParser.ExpVariableContext ctx) {
        String variable = ctx.ID().getText();
        System.out.println("🎯 VISITOR: Encontré variable -> " + variable);
        return variable;
    }
    
    @Override
    public String visitExpEntero(MiLenguajeParser.ExpEnteroContext ctx) {
        String numero = ctx.INTEGER().getText();
        System.out.println("🎯 VISITOR: Encontré número -> " + numero);
        return numero;
    }
    
    @Override
    public String visitExpParentizada(MiLenguajeParser.ExpParentizadaContext ctx) {
        System.out.println("🎯 VISITOR: Encontré expresión entre paréntesis");
        return visit(ctx.expresion());
    }
    
    // Métodos adicionales simplificados
    @Override
    public String visitDeclaracionVariable(MiLenguajeParser.DeclaracionVariableContext ctx) {
        String variable = ctx.ID().getText();
        String tipo = ctx.tipo().getText();
        System.out.println("🎯 VISITOR: Declaración de variable " + tipo + " " + variable);
        // En versión básica, no generamos código para declaraciones
        return null;
    }
    
    @Override
    public String visitRetorno(MiLenguajeParser.RetornoContext ctx) {
        System.out.println("🎯 VISITOR: Encontré return");
        if (ctx.expresion() != null) {
            String valor = visit(ctx.expresion());
            generador.getCodigo().add("return " + valor);
        } else {
            generador.getCodigo().add("return");
        }
        return null;
    }
}