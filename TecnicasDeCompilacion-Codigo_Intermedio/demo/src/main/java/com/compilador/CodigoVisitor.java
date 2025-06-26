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
        String variable = ctx.ID().getText();
        System.out.println("🎯 VISITOR: Encontré asignación -> " + variable + " = ...");
        
        // Procesar la expresión del lado derecho
        System.out.println("🎯 VISITOR: Evaluando expresión del lado derecho...");
        String resultado = visit(ctx.expresion());
        
        // Generar la asignación
        System.out.println("🎯 VISITOR: Generando asignación final...");
        generador.genAsignacion(variable, resultado);
        
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



@Override
public String visitSentenciaFor(MiLenguajeParser.SentenciaForContext ctx) {
    System.out.println("🎯 VISITOR: Encontré un FOR");

    String labelCondicion = generador.newLabel();
    String labelInicio = generador.newLabel();
    String labelFin = generador.newLabel();

    // Inicialización (declaración o asignación)
    if (ctx.getChild(2) instanceof MiLenguajeParser.DeclaracionVariableContext) {
        visit(ctx.declaracionVariable());
    } else if (ctx.getChild(2) instanceof MiLenguajeParser.AsignacionContext) {
        visit(ctx.asignacion());
    }

    pilaInicioBucle.push(labelInicio);
    pilaFinBucle.push(labelFin);

    generador.genLabel(labelCondicion);

    // Condición (opcional)
    if (ctx.expresion(0) != null) {
        String condicion = visit(ctx.expresion(0));
        generador.genIfFalse(condicion, labelFin);
    }

    generador.genLabel(labelInicio);
    visit(ctx.bloque());

    // Actualización (segunda expresión, también opcional)
    if (ctx.expresion(1) != null) {
        visit(ctx.expresion(1));
    }

    generador.genGoto(labelCondicion);
    generador.genLabel(labelFin);

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