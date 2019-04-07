
package act04tsp;


public class tsp {
    
    int N = 5;
    
    int[][] conexiones = new int[][]{
        {0,1,3,4,5},
        {1,0,1,4,8},
        {3,1,0,5,1},
        {4,4,5,0,2},
        {5,8,1,2,0}
    };
    int [] ciudadesVisitadas = new int[]{-1,-1,-1,-1,-1};
    int ciudadesRecorridas = 0;
    int indice_ciudad = 0;
    int ciudad_origen;
    boolean movio_de_ciudad = false;
    int masCercana;
    
    public tsp(){
        
        while(ciudadesRecorridas<N){
            masCercana = primeraQuePuede();
            ciudad_origen = indice_ciudad;
            System.out.println(masCercana);
            for(int i=1; i<N; i++){
                if(conexiones[i][indice_ciudad]!=0 && i !=  ciudadesVisitadas[i] ){
                    if(conexiones[i][indice_ciudad] < masCercana){
                        masCercana = conexiones[i][indice_ciudad];
                        indice_ciudad = i;
                    }
                    movio_de_ciudad = true;
                }
            }
            if(movio_de_ciudad){
                ciudadesVisitadas[ciudad_origen] = indice_ciudad;
                movio_de_ciudad = false;
            }
            ciudadesRecorridas++;
            
        }
        for(int j=0; j<N; j++){
            System.out.println(j + "-> "+ciudadesVisitadas[j]);
        }
        
    }
    public int primeraQuePuede(){
        int i =0;
        boolean tomo = false;
        int primera = -1;
        while(i<N && !tomo){
            if(conexiones[i][indice_ciudad]!=0 && i !=  ciudadesVisitadas[i]){
                primera = conexiones[i][indice_ciudad];
                tomo = true;
            }
            i++;
        }
        return primera;
    }

    
    public static void main(String[] args) {
       tsp ejer = new tsp();
    }
    
}
