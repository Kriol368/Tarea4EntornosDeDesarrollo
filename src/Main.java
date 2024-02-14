import java.util.Scanner;

/**
 * Comentario para nuestra clase principal main.
 *
 * @author Andreu
 * @version 1.0
 * @since 14/2/2024
 */
public class Main {
    /**
     * Metodo main de la clase <code>Main</code> <br>
     * Este metodo recibe un numero por teclado y calcula la criba de Erasstótenes en base a este, es decir, muestra por pantalla todos los numeros primos entre 2 y la entrada
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Introduce el número para la criba de Erastótenes:");
        int dato = teclado.nextInt();
        int vector[] = new int[dato];
        imprimirVectorInicial(dato, vector);
        vector = generarPrimos(dato);
        imprimirVectorPrimos(dato, vector);
    }

    /**
     * Metodo imprimirVectorPrimos de la clase <code>Main</code> <br>
     * Este metodo no devuelve nada y imprime todos los numeros primos del 2 a dato
     *
     * @param dato   parametro de entrada que representa el numero maximo de la criba de Erastótenes
     * @param vector parametro de entrada que es un array de todos los numeros primos entre 2 y dato
     */
    public static void imprimirVectorPrimos(int dato, int[] vector) {
        System.out.println("\nVector de primos hasta:" + dato);
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(vector[i] + "\t");
        }
    }

    /**
     * Metodo imprimirVectorInicial de la clase <code>Main</code> <br>
     * Este metodo no devuelve nada y imprime todos los numeros del 1 a dato
     *
     * @param dato   parametro de entrada que representa el numero maximo de la criba de Erastótenes
     * @param vector parametro de entrada que es un array de todos los numeros entre 1 y dato
     */
    public static void imprimirVectorInicial(int dato, int[] vector) {
        System.out.println("\nVector inicial hasta :" + dato);
        for (int i = 0; i < vector.length; i++) {
            if (i % 10 == 0) System.out.println();
            System.out.print(i + 1 + "\t");
        }
    }

    /**
     * Metodo generarPrimos de la clase <code>Main</code>
     *
     * @param max parametro de entrada que indica el numero maximo del vector de numeros
     * @return <ul>
     * <li>Devolvera un array llamado primos con todos los primos entre el 2 y max si el numero es igual o mayor a 2</li>
     * <li>Devolvera un array vacio si el numero es menor a 2</li>
     * </ul
     */
    // Generar números primos de 1 a max
    public static int[] generarPrimos(int max) {
        int i, j;
        if (max >= 2) {
// Declaraciones
            int dim = max + 1; // Tamaño del array
            boolean[] esPrimo = new boolean[dim];
// Inicializar el array
            for (i = 0; i < dim; i++)
                esPrimo[i] = true;
// Eliminar el 0 y el 1, que no son primos
            eliminar0Y1(esPrimo);
// Criba
            criba(dim, esPrimo);
// ¿Cuántos primos hay?
            int cuenta = cuantosPrimos(dim, esPrimo);
// Rellenar el vector de números primos
            int[] primos = rellenarVector(cuenta, dim, esPrimo);
            return primos;
        } else { // max < 2
            return new int[0];
// Vector vacío
        }
    }

    /**
     * Metodo eliminar0y1 de la clase <code>Main</code> <br>
     * Este metodo no devuelve nada y declara que las posiciones 0 y 1 del array esPrimo son falsas, por tanto 0 y 1 no son primos*
     *
     * @param esPrimo parametro de entrada que es un array de booleanos del tamaño de max que indica si el numero en la posicion i de este es primo o no
     */
    public static void eliminar0Y1(boolean[] esPrimo) {
        esPrimo[0] = esPrimo[1] = false;
    }

    /**
     * Metodo criba de la clase <code>Main</code> <br>
     * Este metodo no devuelve nada y actualiza el array de booleanos esPrimo para que solo queden en true los numeros primos
     *
     * @param esPrimo parametro de entrada que es un array de booleanos del tamaño de max que indica si el numero en la posicion i de este es primo o no
     * @param dim     parametro de entrada que indica las dimensiones del array. Es igual a max + 1
     */
    public static void criba(int dim, boolean[] esPrimo) {
        int i;
        for (i = 2; i < Math.sqrt(dim) + 1; i++) {
            if (esPrimo[i]) {
// Eliminar los múltiplos de i
                eliminarMultiplosDei(i, dim, esPrimo);
            }
        }
    }

    /**
     * Metodo eliminarMultiplosDei de la clase <code>Main</code> <br>
     * Este metodo no devuelve nada y actualiza el array de booleanos esPrimo para que los multiplos de i queden en false y asi ir descartando numeros no primos
     *
     * @param esPrimo parametro de entrada que es un array de booleanos del tamaño de max que indica si el numero en la posicion i de este es primo o no
     * @param dim     parametro de entrada que indica las dimensiones del array. Es igual a max + 1
     * @param i       parametro de entrada que se corresponde al valor actual de i en el bucle for del metodo criba
     */
    public static void eliminarMultiplosDei(int i, int dim, boolean[] esPrimo) {
        int j;
        for (j = 2 * i; j < dim; j += i)
            esPrimo[j] = false;
    }

    /**
     * Metodo rellenarVector de la clase <code>Main</code>
     *
     * @param cuenta  parametro de entrada que indica el numero de primos que hay en el array esPrimo
     * @param dim     parametro de entrada que indica las dimensiones del array. Es igual a max + 1
     * @param esPrimo parametro de entrada que es un array de booleanos del tamaño de max que indica si el numero en la posicion i de este es primo o no
     * @return Devolvera un vector de enteros con los numeros que hayan sido declarados como primos (true) en esPrimo
     */
    public static int[] rellenarVector(int cuenta, int dim, boolean[] esPrimo) {
        int i;
        int j;
        int[] primos = new int[cuenta];
        for (i = 0, j = 0; i < dim; i++) {
            if (esPrimo[i]) primos[j++] = i;
        }
        return primos;
    }

    /**
     * Metodo cuantosPrimos de la clase <code>Main</code>
     *
     * @param dim     parametro de entrada que indica las dimensiones del array. Es igual a max + 1
     * @param esPrimo parametro de entrada que es un array de booleanos del tamaño de max que indica si el numero en la posicion i de este es primo o no
     * @return Devolvera el numero de primos que hay entre 1 y dim
     */
    public static int cuantosPrimos(int dim, boolean[] esPrimo) {
        int i;
        int cuenta = 0;
        for (i = 0; i < dim; i++) {
            if (esPrimo[i]) cuenta++;
        }
        return cuenta;
    }
}