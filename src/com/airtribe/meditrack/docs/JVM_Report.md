# JVM Report

## 1. Class Loader
The **Class Loader Subsystem** is responsible for loading Java class files (.class) into the JVM at runtime.  
- **Responsibilities:**
  - Loads classes into memory.
  - Verifies bytecode for security.
  - Prepares memory for class variables.
  - Resolves references between classes.
- **Types of Class Loaders:**
  - **Bootstrap Class Loader** – Loads core Java classes (e.g., `java.lang`, `java.util`).
  - **Extension Class Loader** – Loads classes from the `ext` directory.
  - **Application Class Loader** – Loads user‑defined classes from the classpath.

---

## 2. Runtime Data Areas
The JVM defines several memory regions to manage program execution:

| Runtime Area      | Description |
|-------------------|-------------|
| **Heap**          | Stores objects and their instance variables. Managed by Garbage Collector. |
| **Stack**         | Each thread has its own stack. Stores method frames, local variables, and partial results. |
| **Method Area**   | Stores class structures (runtime constant pool, field/method data, code for methods). |
| **PC Register**   | Each thread has a Program Counter register that points to the current executing instruction. |

---

## 3. Execution Engine
The **Execution Engine** runs the bytecode loaded by the Class Loader.  
- **Interpreter** – Reads and executes bytecode instructions one by one.  
- **JIT Compiler (Just‑In‑Time)** – Converts frequently used (“hot”) bytecode into native machine code for faster execution.  
- **Garbage Collector** – Reclaims memory from unused objects.

---

## 4. JIT Compiler vs Interpreter
| Feature            | Interpreter | JIT Compiler |
|--------------------|-------------|--------------|
| **Execution**      | Executes bytecode line by line. | Compiles bytecode into native machine code. |
| **Speed**          | Slower (due to repeated interpretation). | Faster (compiled code runs directly on CPU). |
| **Use Case**       | Good for quick startup. | Good for long‑running applications. |
| **Optimization**   | Minimal. | Performs optimizations like inlining, caching, and loop unrolling. |

---

## 5. "Write Once, Run Anywhere"
Java achieves **platform independence** through the JVM:  
- Java source code is compiled into **bytecode** (`.class` files).  
- The JVM interprets or compiles this bytecode into native instructions depending on the host machine.  
- Since every platform has its own JVM implementation, the same bytecode can run anywhere without modification.  
This is the essence of Java’s famous **“Write Once, Run Anywhere” (WORA)** principle.

---

## 📌 Summary
The JVM is a layered architecture consisting of:
- **Class Loader** (loads classes),
- **Runtime Data Areas** (manages memory),
- **Execution Engine** (runs code via Interpreter/JIT),
- **Garbage Collector** (manages memory cleanup).

Together, these components enable Java’s portability, performance, and reliability.