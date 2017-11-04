import java.util.ArrayList;

public class GameLife{
	
	//Change accorging to your screen resolution
	//Tested resolutions
	//1366x768 ---- tamX=40, tamY=150
	//1920x1080 ---- tamX=60, tamY=200
	static final int tamX = 40;
	static final int tamY = 150;
	
	public static int[][] tablero = new int[tamX][tamY];

	public static void main(String arg[]) throws InterruptedException {
		for(int i=0;i<tamX;i++) {
			for(int j=0;j<tamY;j++) {
				tablero[i][j]=0;
			}
		}
		
		int x = tamX/2;
		int y = tamY/2;
		tablero[x][y] = 1;
		tablero[x-1][y-1] = 1;
		tablero[x][y+1] = 1;
		tablero[x+1][y] = 1;
		tablero[x-1][y] = 1;
		
		
		while(true) {
			ArrayList<int[]> morir = new ArrayList<int[]>();
			ArrayList<int[]> vivir = new ArrayList<int[]>();
			int vivas = 0;
			pintaTablero(tablero);
			for(int i=0;i<tamX;i++) {
				for(int j=0;j<tamY;j++) {
					if(tablero[i][j]==1)
						vivas++;
					if(i-1 >= 0 && j-1 >= 0 && i+1 < tamX && j+1<tamY) {
						if(tablero[i][j]==1 && numVivas(tablero,i,j)==2) {
							int[] v = {i,j};
							vivir.add(v);
						}		
						if(tablero[i][j]==1 && numVivas(tablero,i,j)==3){
							int[] v = {i,j};
							vivir.add(v);
						}
						if(tablero[i][j]==1 && numVivas(tablero,i,j)>3){
							int[] v = {i,j};
							morir.add(v);
						}
						if(tablero[i][j]==1 && numVivas(tablero,i,j)<3){
							int[] v = {i,j};
							morir.add(v);
						}
						if(tablero[i][j]==0 && numVivas(tablero,i,j)==3){
							int[] v = {i,j};
							vivir.add(v);
						}
						
					}
				}
			 }
			
			 System.out.println("Celulas vivas: " + vivas);
			
			 for(int[] i:morir) {
				 tablero[i[0]][i[1]]=0;
			 }
			 for(int[] i:vivir) {
				 tablero[i[0]][i[1]]=1;
			 }
			 
			 generaAleatorias(tablero,0);
			 
			 Thread.sleep(100);
		}
	}
	
	//Paint the board
	public static void pintaTablero(int[][] tablero) {
		for(int i=0;i<tamX;i++) {
			for(int j=0;j<tamY;j++) {
				if(tablero[i][j]==0) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			System.out.println();
		}
		System.out.flush();
	}
	
	//Calculate the number of live cells around one
	public static int numVivas(int[][] tablero, int x, int y) {
		int retorno = 0;
		if(tablero[x-1][y-1]==1)
			retorno++;
		if(tablero[x+1][y+1]==1)
			retorno++;
		if(tablero[x-1][y+1]==1)
			retorno++;
		if(tablero[x+1][y-1]==1)
			retorno++;
		if(tablero[x][y-1]==1)
			retorno++;
		if(tablero[x-1][y]==1)
			retorno++;
		if(tablero[x][y+1]==1)
			retorno++;
		if(tablero[x+1][y]==1)
			retorno++;
		
		return retorno;
	}
	
	//Generates a number n of sets of random cells if a certain condition is met
	public static void generaAleatorias(int[][] tablero, int n){
		if(Math.floor(Math.random()*(200-0+1)+0)%10==0) {
			 for(int i=0;i<n;i++) {
					int x = (int) Math.floor(Math.random()*(49-0+1)+0);
					int y = (int) Math.floor(Math.random()*(199-0+1)+0);
					tablero[x][y] = 1;
					
					if(x-1 >= 0 && y-1>=0 && x+1<tamX && y+1<tamY) {
						tablero[x][y] = 1;
						tablero[x-1][y-1] = 1;
						tablero[x][y+1] = 1;
						tablero[x+1][y] = 1;
						tablero[x-1][y] = 1;
					}	
			}
		 }
	}
}





























