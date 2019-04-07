
package act04tsp;

public class tsp {
    
    int N = 5;
    
    int[][] conexiones = new int[N][N];

    int ciudadesRecorridas = 0;
    int indice_ciudad = 0;
    int[] solucion = new int[N];
    int[] ciudades = new int[N];
    int masCerca;
    int distanciaTotal = 0;
    
    public tsp(){
        inicializrConexiones();
        System.out.println("Conexiones entre ciudades: ");
        mostrarConexiones();
        inicializar_Ciudades_y_Solucion();  
        //Comienza el algoritmo greedy
        while(ciudadesRecorridas<N){
           //toma el primero que puede
           masCerca = primeraDisponible();
           if(masCerca!=-1){//Significa que tomo una ciudad
               //tomo la realmente mas cercana
               for(int i=0; i<N; i++){
                   if(conexiones[i][indice_ciudad] < conexiones[masCerca][indice_ciudad] && ciudades[i]!=-1){
                       masCerca = i;    
                   }        
               }
               distanciaTotal = distanciaTotal+ conexiones[masCerca][indice_ciudad];
               solucion[indice_ciudad] = masCerca;
               indice_ciudad = masCerca;
               ciudades[masCerca] = -1;
               
           }
               
           ciudadesRecorridas++;  
        }
        distanciaTotal = distanciaTotal + conexiones[0][indice_ciudad]; //le sumo a distancia la distancia de la ultima ciudad a la de origen
 
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
    public void inicializrConexiones(){
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
        for(int i=0; i<N; i++){
            System.out.print("[ ");
            for(int j=0; j<N; j++){
                System.out.print(conexiones[i][j]+" ");
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
