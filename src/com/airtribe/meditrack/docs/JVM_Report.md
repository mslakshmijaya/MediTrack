# JVM Report

## 1. Class Loader
The **Class Loader Subsystem** is responsible for loading Java class files (.class) into the JVM at runtime.  
- **Responsibilities:**
- **Loading**: Brings class bytecode into memory.
- **Verification**: Ensures bytecode follows JVM rules and is safe to execute.
- **Preparation**: Allocates memory for class variables and sets default values.
- **Resolution**: Replaces symbolic references with direct references to classes, methods, and fields.

- **Types of Class Loaders:**
  - **Bootstrap Class Loader** –   - Part of the JVM core, written in native code.
   - Loads core Java classes from `rt.jar` (e.g., `java.lang`, `java.util`).
   - Example:
   ```java
   public class BootstrapDemo {
       public static void main(String[] args) {
           // String class is loaded by Bootstrap Class Loader
           String text = "Hello JVM";
           System.out.println(text);
       }
   }
   ```

  - **Extension Class Loader** – Loads classes from the `ext` directory.
  - Loads classes from the jre/lib/ext directory or any other extension directories.
   - Example :
   ```java
    import javax.crypto.Cipher; // Comes from extension libraries
    public class ExtensionDemo {
    public static void main(String[] args) throws Exception {
        Cipher cipher = Cipher.getInstance("AES"); 
        System.out.println("Extension class loaded: " + cipher.getAlgorithm());
    }
}```
  - **Application Class Loader** – Loads user‑defined classes from the classpath.
  - Example : - Loads user-defined classes from the classpath (CLASSPATH environment variable).
  
  ```java  
  public class ApplicationDemo {
    public static void main(String[] args) {
        ApplicationDemo demo = new ApplicationDemo();
        System.out.println("Loaded by: " + demo.getClass().getClassLoader());
    }
}
```
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

### **Heap**
**Description:**  
- Shared across all threads.  
- Stores all objects and arrays created during program execution.  
- Divided into **Young Generation** (new objects, short‑lived), **Old Generation** (long‑lived objects), and sometimes **Permanent Generation/Metaspace** (class metadata).  
- Managed automatically by the **Garbage Collector**, which reclaims memory from unused objects.  
- Heap size can be tuned using JVM options like `-Xms` (initial size) and `-Xmx` (maximum size).

- Example:
```java
class Demo {
    String name = new String("Anup"); // Stored in Heap
}
```

---

### **Stack**
**Description:**  
- Each thread has its own private stack.  
- Contains **frames** for each method call, storing:  
  - Local variables  
  - Operand stack (intermediate calculations)  
  - Return values and references  
- When a method is invoked, a new frame is pushed; when it completes, the frame is popped.  
- Stack overflow occurs if recursion or deep calls exceed the allocated stack size.  
- Stack size can be tuned using `-Xss`.

- Example:
```java
public class StackDemo {
    public static void main(String[] args) {
        int x = 10;   // Stored in Stack
        int y = 20;   // Stored in Stack
        int sum = x + y;
        System.out.println(sum);
    }
}
```

---

### **Method Area**
**Description:**  
- Shared across all threads.  
- Stores class‑level data:  
  - Class structures (fields, methods)  
  - Static variables  
  - Runtime constant pool  
  - Compiled bytecode for methods  
- In modern JVMs, this is implemented as **Metaspace** (uses native memory).  
- Can be tuned with options like `-XX:MetaspaceSize` and `-XX:MaxMetaspaceSize`.

- Example:
```java
class MethodAreaDemo {
    static int counter = 0; // Class variable stored in Method Area
    static void increment() {
        counter++;
    }
}
```

---

### **PC Register**
**Description:**  
- Each thread has its own Program Counter (PC) register.  
- Points to the current bytecode instruction being executed.  
- Helps the JVM resume execution correctly after method calls or thread context switches.  
- Very lightweight and managed internally by the JVM — no tuning required.

- Example:
```java
public class PCRegisterDemo {
    public static void main(String[] args) {
        System.out.println("Instruction 1"); // PC points here
        System.out.println("Instruction 2"); // PC moves here
    }
}
```
---

---

## 3. Execution Engine

The **Execution Engine** is the heart of the JVM. It takes the bytecode loaded by the Class Loader and executes it. The engine ensures efficient execution through interpretation, compilation, and memory management.

### Components

1. **Interpreter**
   - Reads and executes bytecode instructions one by one.
   - Simple but slower, since each instruction is interpreted repeatedly.
   - Example:
   ```java
   public class InterpreterDemo {
       public static void main(String[] args) {
           int a = 5;
           int b = 10;
           int sum = a + b; // Executed line by line by the interpreter
           System.out.println("Sum: " + sum);
       }
   }```
   
2. **JIT Compiler (Just‑In‑Time)** 
   – Converts frequently used (“hot”) bytecode into native machine code for faster execution.
   – Compiles them into native machine code for faster execution.
   – Improves performance significantly compared to pure interpretation.


  ```java
   public class JITDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += compute(i); // JIT optimizes this method after repeated calls
        }
        long end = System.currentTimeMillis();
        System.out.println("Sum: " + sum + " Time: " + (end - start) + "ms");
    }

    static int compute(int x) {
        return x * x;
    }
}
```
 -The JVM will initially interpret compute(), but after many calls, JIT compiles it into native code.

3. **Garbage Collector**
 – Reclaims memory from unused objects.
 - Automatically reclaims memory from objects no longer in use.
 - Prevents memory leaks and optimizes heap usage.

```java
   public class GarbageCollectorDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            String temp = new String("Object " + i);
            // temp becomes unreachable after each loop iteration
        }
        System.gc(); // Suggests JVM to run Garbage Collector
        System.out.println("Garbage Collection requested.");
    }
}
```
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