
# 🎓 CodigoVisitor y GeneradorCodigo
## Generación de Código Intermedio en Compiladores



---

## 📚 ¿Qué es el Código Intermedio?

### Es el "puente" entre tu código fuente y el código de máquina

```
Código Fuente       Código Intermedio       Código de Máquina
    ↓                       ↓                        ↓
if (x > 5) {        →   t0 = x > 5          →    CMP x, 5
    y = 10;             if !t0 goto L1           JLE L1
}                       y = 10                   MOV y, 10
                        L1:                      L1:
```

### ✅ **Ventajas del código intermedio:**
- **Independiente de la máquina** - Un solo código para múltiples procesadores
- **Fácil de optimizar** - Instrucciones simples y uniformes
- **Fácil de analizar** - Estructura clara de tres direcciones

---

## 🎯 ¿Por qué "Código de 3 Direcciones"?

### **Formato básico:**
```
resultado = operando1 operador operando2
    ↑          ↑                ↑
Dirección 1  Dirección 2   Dirección 3
```

### **Ejemplos concretos:**
```
t0 = a + b     ← 3 direcciones: t0, a, b
x = y * 2      ← 3 direcciones: x, y, 2  
z = temp1      ← 2 direcciones: z, temp1 (asignación simple)
goto L1        ← 1 dirección: L1 (salto)
```

### **Una instrucción NUNCA tiene más de 3 referencias a memoria:**

#### ✅ **Ejemplos válidos:**
```
t0 = a + b        (3 direcciones: t0, a, b)
x = y             (2 direcciones: x, y)  
goto L1           (1 dirección: L1)
if x goto L2      (2 direcciones: x, L2)
```

#### ❌ **Lo que NO es código de 3 direcciones:**
```
w = a + b + c     (4 direcciones: w, a, b, c)
x = y + z * w     (4 direcciones: x, y, z, w)
```

### **Cómo convertimos expresiones complejas:**

**Expresión original:** `resultado = a + b * c + d;`

**En código de 3 direcciones:**
```
t0 = b * c
t1 = a + t0  
t2 = t1 + d
resultado = t2
```

---

## 🏗️ División de Responsabilidades

### **Analogía: Construyendo una Casa**

#### **CodigoVisitor = El Arquitecto** 🏛️
- **Lee los planos** (el AST del código fuente)
- **Toma decisiones estratégicas**: "Aquí va una pared", "Aquí una puerta"
- **Da órdenes específicas** al constructor
- **Coordina todo el proyecto**

#### **GeneradorCodigo = El Constructor** 🔨
- **Ejecuta las órdenes** del arquitecto
- **Conoce los detalles técnicos**: qué herramientas usar, cómo hacer cada cosa
- **Tiene las herramientas**: martillo, sierra, etc.
- **Lleva registro** de todo lo construido

### **Arquitectura del Sistema:**
```
┌─────────────────────┬─────────────────────┐
│   CodigoVisitor     │   GeneradorCodigo   │
│     (Arquitecto)    │    (Constructor)    │
├─────────────────────┼─────────────────────┤
│ ¿QUÉ construir?     │ ¿CÓMO construir?    │
│ ¿CUÁNDO hacerlo?    │ ¿CON QUÉ hacerlo?   │
│ ¿EN QUÉ ORDEN?      │ ¿DÓNDE guardarlo?   │
│                     │                     │
│ • Recorre el AST    │ • Crea instrucciones│
│ • Toma decisiones   │ • Maneja temporales │
│ • Maneja contexto   │ • Maneja etiquetas  │
│ • Coordina fases    │ • Almacena código   │
└─────────────────────┴─────────────────────┘
```

---

## 🤝 Cómo Trabajan Juntos

### **CodigoVisitor** - "¿QUÉ hacer?"
- **Encuentra** un nodo IF en el AST
- **Decide** que necesita evaluar una condición
- **Determina** qué etiquetas crear
- **Planifica** el orden de las operaciones

### **GeneradorCodigo** - "¿CÓMO hacerlo?"
- **Crea** variables temporales únicas (`t0`, `t1`, `t2`...)
- **Genera** etiquetas únicas (`L0`, `L1`, `L2`...)
- **Construye** instrucciones específicas
- **Almacena** todo el código generado

---

## 🔄 Flujo de Colaboración

### **Ejemplo paso a paso: `if (a > b) { x = 1; }`**

1. **Visitor encuentra IF**: "Necesito procesar una condición"
2. **Visitor evalúa condición**: "Necesito comparar a > b"
3. **Generador crea la comparación**: Genera `t0 = a > b`
4. **Visitor decide el salto**: "Si es falso, saltar"
5. **Generador ejecuta el salto**: Genera `if !t0 goto L0`

### **Código intermedio resultante:**
```
0: t0 = a > b
1: if !t0 goto L0
2: x = 1
3: L0:
```

---

## ⚠️ Advertencias Importantes

### 🚨 **PROBLEMA 1: Orden de Operandos en RESTA**

#### ❌ **Error Común:**
```
// Código fuente: x = a - b
// INCORRECTO:
t0 = b - a
x = t0
```

#### ✅ **Correcto:**
```
// Código fuente: x = a - b  
t0 = a - b
x = t0
```

---

### 🚨 **PROBLEMA 2: División por Cero**

#### ❌ **Código Peligroso:**
```
// Código fuente: result = x / 0
t0 = x / 0
result = t0
```

#### ✅ **Solución:**
- Detectar división por cero en tiempo de compilación
- Generar error o verificación en tiempo de ejecución

---

### 🚨 **PROBLEMA 3: Precedencia en Restas Encadenadas**

#### ❌ **Error de Precedencia:**
```
// Código fuente: x = a - b - c
t0 = b - c
t1 = a - t0
x = t1
```

#### ✅ **Correcto (Asociatividad izquierda):**
```
// Código fuente: x = a - b - c
t0 = a - b
t1 = t0 - c
x = t1
```

---

### 🚨 **PROBLEMA 4: Operadores Sensibles al Orden**

#### **Operadores NO conmutativos (orden importa):**
```
- (resta)
 / (división)
 % (módulo)
 < (menor que)
 > (mayor que)
 <= (menor igual)
 >= (mayor igual)
```

#### **Operadores conmutativos (orden no importa):**
```
+ (suma)
* (multiplicación)
== (igualdad)
!= (diferencia)
&& (AND lógico)
|| (OR lógico)
```

---

## ✅ Lista de Verificación para Operaciones

### **Antes de generar código:**
- [ ] ¿El orden de operandos es correcto?
- [ ] ¿Hay división por cero literal?
- [ ] ¿Los tipos son compatibles?
- [ ] ¿La precedencia es correcta?
- [ ] ¿La asociatividad es izquierda para -, /, %?

### **Durante la generación:**
- [ ] ¿Los operadores no conmutativos mantienen el orden?
- [ ] ¿Se generan advertencias apropiadas?
- [ ] ¿Se manejan las conversiones de tipo?

### **Después de generar:**
- [ ] ¿El código intermedio refleja la semántica original?
- [ ] ¿Las optimizaciones posteriores respetan el orden?

---

## 🎯 Beneficios de esta Arquitectura

### ✅ **Separación de responsabilidades**
- **Visitor**: Se enfoca en la lógica del lenguaje
- **Generador**: Se enfoca en la mecánica de construcción

### ✅ **Reutilización**
- El mismo generador puede usarse con diferentes visitors
- Fácil crear visitors especializados (optimización, depuración)

### ✅ **Mantenibilidad**
- Cambios en la generación no afectan la lógica de recorrido
- Fácil agregar nuevas optimizaciones

### ✅ **Extensibilidad**
- Agregar nuevas instrucciones es sencillo
- Soporte para múltiples arquitecturas

