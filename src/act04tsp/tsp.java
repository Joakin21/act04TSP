
package act04tsp;

import java.util.Scanner;

public class tsp {
    
    int N;//cantidad de ciudades
    
    int[][] conexiones;//los valores de esta matriz representan la distancia entre ciuades que son representadas mediante los indices fila y columna de esta matriz

    int ciudadesRecorridas = 0;//cuantas ciudades hemos recorrido
    int indice_ciudad = 0;//ciudad en la que estamos actualmente
    int[] solucion;//guardaremos las conexiones de las ciudades que determine el algoritmo, siendo el indice la ciudad de origen y el valor la ciudad de destino
    int[] ciudades;//guardaremos la ciudades y si han sido o no visitadas (si tiene un -1 significa que fue visitada)
    int masCerca;
    int distanciaTotal = 0;
    
    int ejer_a_revisar=0;
    Scanner entrada = new Scanner(System.in);
    
    public tsp(){
        while(ejer_a_revisar!= 2 && ejer_a_revisar!= 3){
            System.out.println("pulsar 2 para ver el problema 2 o 3 para ver el problema 3");
            ejer_a_revisar = entrada.nextInt();
        }
        //Segun el problema que quiera revisar inicializamos N (cantidad de ciudades) y la matriz a utilizar
        if(ejer_a_revisar == 2){
            N = 5;
            conexiones = new int[N][N];
            inicializarConexionesTSP5();
            System.out.println("Algoritmo voraz que resuelve el problema tsp de cinco ciudades con distancias aleatorias");
        }
        if(ejer_a_revisar == 3){
            N = 17;
            inicializarConexionesTSP17();
            System.out.println("Algoritmo voraz que resuelve el problema tsp de 17 ciudades con distancias predefinidas");
        }
        solucion = new int[N];
        ciudades = new int[N];
        
        System.out.println("Conexiones entre ciudades: ");
        mostrarConexiones();
        inicializar_Ciudades_y_Solucion();  
        
        //Comienza el algoritmo greedy
        while(ciudadesRecorridas<N){//Mientras no hayamos recorrido todas las ciudades
           masCerca = primeraDisponible();//intenta tomar la primera que puede (que no sea la mismoa y que no se haya recorrido antes)
           if(masCerca!=-1){//Significa que tomo una ciudad
               for(int i=0; i<N; i++){
                   if(conexiones[i][indice_ciudad] < conexiones[masCerca][indice_ciudad] && ciudades[i]!=-1){//si hay una ciudad con menor distancia y aun no ha sido recorrida
                       masCerca = i; // ahora esa sera la mas cercana  
                   }        
               }
               distanciaTotal = distanciaTotal+ conexiones[masCerca][indice_ciudad];//sumamos la distancia de esa ciudad a la distancia total
               solucion[indice_ciudad] = masCerca;//la agregamos a la solucion
               indice_ciudad = masCerca;//nos movemos a esa ciudad para repetir el proceso pero con esa ciudad
               ciudades[masCerca] = -1;//la marcamos como no disponible, para que no se vuelva a tomar como opcion
               
           }      
           ciudadesRecorridas++;//decimos que hemos recorrido una nueva ciudad  
        }
        distanciaTotal = distanciaTotal + conexiones[0][indice_ciudad]; //le sumo a distancia la distancia de la ultima ciudad a la de origen
        //fin del algoritmo
        
        //Mostramos datos de interes
        System.out.println("distancia: "+distanciaTotal);
        System.out.println("Ruta recorrida: ");
        mostrarRutaRecorrida();
        System.out.println();
        
        
    }
    public void inicializar_Ciudades_y_Solucion(){
        for(int i=0; i<N; i++){
            solucion[i] = 0;
            ciudades[i] = i;
        }
        solucion[0] = -1;//desabilitamos la ciudad de origen
    }
    public void inicializarConexionesTSP5(){//Se inicializa la matriz para el problema 2: matriz de 5x5 con distancias aleatorias 
        int distancia_aleatoria;
        int p = 0;
        while(p < N){
            
            for(int i=p+1; i<N; i++){
                distancia_aleatoria = (int) (Math.random() * 9) + 1;
                conexiones[p][i] =  distancia_aleatoria;
                conexiones[i][p] =  distancia_aleatoria;
            }
            conexiones[p][p] =0;
            p++;
        }

    }
    public void inicializarConexionesTSP17(){
        conexiones = new int[][]{
            {0,633, 257, 91, 412, 150, 80, 134, 259, 505, 353, 324, 70, 211, 268, 246, 121},
            {633, 0, 390, 661, 227, 488, 572, 530, 555, 289, 282, 638, 567, 466, 420, 745, 518},
            {257, 390,0 ,228 ,169 ,112 ,196 ,154 ,372 ,262 ,110 ,437 ,191 ,74 ,53 ,472 ,142},
            {91, 661, 228, 0, 383, 120, 77, 105, 175, 476, 324, 240, 27, 182, 239, 237, 84},
            {412, 227, 169, 383, 0, 267, 351, 309, 338, 196, 61, 421, 346, 243, 199, 528, 297},
            {150, 488, 112, 120, 267, 0, 63, 34, 264, 360, 208, 329, 83, 105, 123, 364, 35},
            {80, 572, 196, 77, 351, 63, 0, 29, 232, 444, 292, 297, 47, 150, 207, 332, 29},
            {134, 530, 154, 105, 309, 34, 29, 0, 249, 402, 250, 314, 68, 108, 165 ,349, 36},
            {259,555,372,175,338,264,232,249,0,495,352,95,189,326,383,202,236},
            {505,289,262,476,196,360,444,402,495,0,154,578,439,336,240,685,390},
            {353,282,110,324,61,208,292,250,352,154,0,435,287,184,140,542,238},
            {324,638,437,240,421,329,297,314,95,578,435,0,254,391,448,157,301},
            {70,567,191,27,346,83,47,68,189,439,287,254,0,145,202,289,55},
            {211,466,74,182,243,105,150,108,326,336,184,391,145,0,57,426,96},
            {268,420,53,239,199,123,207,165,383,240,140,448,202,57,0,483,153},
            {246,745,472,237,528,364,332,349,202,685,542,157,289,426,483,0,336},
            {121,518,142,84,297,35,29,36,236,390,238,301,55,96,153,336,0}   
        }; 
    }
    public void mostrarRutaRecorrida(){
        int recorridas = 0;
        int i=0;
        System.out.print("["+i+"]-->");
        while(recorridas < N){
            if(recorridas < N-1)
                System.out.print("["+solucion[i]+"]-->");
            else
                System.out.print("["+solucion[i]+"]");
            i = solucion[i];
            recorridas++;
        }
    }
    public void mostrarConexiones(){
        String espacio = " ";

        for(int i=0; i<N; i++){
            System.out.print("[ ");
            for(int j=0; j<N; j++){
                System.out.print(conexiones[i][j]+espacio);
            }
            System.out.print("]");
            System.out.println();
        }
    }
    public int primeraDisponible(){
        int i = 0;
        int ciudad = -1;
        boolean tomo = false;
        while (i<N && !tomo){
            if(i == ciudades[i]){
                tomo = true;
                ciudad = i;
            }
            i++;
        }
        return ciudad;
    }


    public static void main(String[] args) {
       tsp ejer = new tsp();
    }
    
}
