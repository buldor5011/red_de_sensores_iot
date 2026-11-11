# Criterios de Decisión: Estructuras de Datos en la Red de Sensores

Este documento es la parte más importante de tu entrega. No basta con que el código funcione; debes justificar **por qué** elegiste una colección de Java sobre otra.

Responde a las siguientes preguntas basándote en la teoría de nodos y el rendimiento del JCF.

### 1. El dilema del Historial
Para el `historialEventos`, podrías usar un `ArrayList` o un `LinkedList`. 
- Si la plataforma registra miles de eventos por segundo y necesitas eliminar los más antiguos frecuentemente para ahorrar memoria, ¿cuál de las dos colecciones es más eficiente y por qué? (Pista: piensa en el costo de eliminar el primer elemento).

Respuesta: Es mas eficiente LinkedList. Quitar el primer elemento cuesta O(1) (se suelta el primer nodo), mientras que en un ArrayList cuesta O(n) porque hay que desplazar todos los demás.

### 2. Navegación Bidireccional
En el `navegadorAlertas`, utilizamos un `Deque` (Double Ended Queue). 
- ¿Qué ventaja ofrece un `Deque` sobre una `Queue` simple para implementar la funcionalidad de "Atrás" y "Adelante"? 
- ¿Cómo se relaciona esto con el concepto de un `NodoDoble`?

Respuesta: Una Queue solo se recorre en un sentido, mientras que un Deque permite insertar y sacar por ambos extremos en O(1), lo que habilita "adelante" y "atrás". Es la misma idea del NodoDoble, que tiene un enlace al siguiente y otro al anterior.

### 3. El Ciclo de Monitoreo
Para la `colaMonitoreo`, implementaste una rotación (sacar del frente y meter al final).
- ¿Por qué una `Queue` es la estructura natural para este problema? 
- ¿Qué pasaría con la equidad del sistema si usaras una `Stack` (Pila) en su lugar?

Respuesta: Una Queue es el primer sensor programado es el primero en consultarse y al rotarlo al final todos reciben turno.Con una Stack siempre se sacaria y se volveria a meter el mismo sensor del tope y los demas nunca serían consultados

### 4. Topología de Red (El Grafo)
Representaste la red de sensores como un `Map<String, List<String>>`.
- ¿Por qué usamos un `Map` y no un arreglo bidimensional (matriz de adyacencia) considerando que la mayoría de los sensores solo están conectados a 2 o 3 vecinos?
- ¿Cómo se traduce esta estructura de `Map` a la idea de un `NodoGrafo` con una lista de adyacencias?

Respuesta:  Con 2 o 3 vecinos por sensor, una matriz n × n sería casi toda ceros y gastaría memoria de más, mientras que el Map guarda solo las conexiones que existen

### 5. Buffer de Ingesta y Rendimiento
El `bufferIngesta` procesa datos en orden FIFO.
- Si el volumen de datos aumenta drásticamente y necesitas procesar las lecturas en paralelo usando múltiples hilos, ¿seguiría siendo adecuado un `ArrayDeque` o deberías cambiar a una `ConcurrentLinkedQueue`? Justifica tu respuesta basándote en la seguridad de hilos (Thread-safety).

Respuesta: Habría que cambiar a ConcurrentLinkedQueue, porque ArrayDeque no es thread-safe: con varios hilos se pierden lecturas sin lanzar excepcion. Con un solo hilo, ArrayDeque sigue siendo mejor.

USO IA
Durante este proyecto utilizamos Claude como herramienta de apoyo. Nos ayudo a plantear e implementar los retos de GestorDeRed, a documentar el código con comentarios y a resolver inconvenientes de configuración en IntelliJ y Git.