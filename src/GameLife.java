import java.util.ArrayList;

public class GameLife{
	
	//Change accorging to your screen resolution
	//Tested resolutions
	//1366x768 ---- tamX=40, tamY=150
	//1920x1080 ---- tamX=60, tamY=200
	static final int tamX = 40;
	static final int tamY = 150;
	
	public static int[][] board = new int[tamX][tamY];

	public static void main(String arg[]) throws InterruptedException {
		for(int i=0;i<tamX;i++) {
			for(int j=0;j<tamY;j++) {
				board[i][j]=0;
			}
		}
		
		int x = tamX/2;
		int y = tamY/2;
		board[x][y] = 1;
		board[x-1][y-1] = 1;
		board[x][y+1] = 1;
		board[x+1][y] = 1;
		board[x-1][y] = 1;
		
		
		while(true) {
			ArrayList<int[]> toKill = new ArrayList<int[]>();
			ArrayList<int[]> toLive = new ArrayList<int[]>();
			int vivas = 0;
			paintBoard(board);
			for(int i=0;i<tamX;i++) {
				for(int j=0;j<tamY;j++) {
					if(board[i][j]==1)
						vivas++;
					if(i-1 >= 0 && j-1 >= 0 && i+1 < tamX && j+1<tamY) {
						if(board[i][j]==1 && numVivas(board,i,j)==2) {
							int[] v = {i,j};
							toLive.add(v);
						}		
						if(board[i][j]==1 && numVivas(board,i,j)==3){
							int[] v = {i,j};
							toLive.add(v);
						}
						if(board[i][j]==1 && numVivas(board,i,j)>3){
							int[] v = {i,j};
							toKill.add(v);
						}
						if(board[i][j]==1 && numVivas(board,i,j)<3){
							int[] v = {i,j};
							toKill.add(v);
						}
						if(board[i][j]==0 && numVivas(board,i,j)==3){
							int[] v = {i,j};
							toLive.add(v);
						}
						
					}
				}
			 }
			
			 System.out.println("Alive cells: " + vivas);
			
			 for(int[] i:toKill) {
				 board[i[0]][i[1]]=0;
			 }
			 for(int[] i:toLive) {
				 board[i[0]][i[1]]=1;
			 }
			 
			 //Change the 0 to the number of random cells you want to generate
			 generateRandom(board,3);
			 
			 //Change to slow down or slow up
			 Thread.sleep(100);
		}
	}
	
	//Paint the board
	public static void paintBoard(int[][] board) {
		for(int i=0;i<tamX;i++) {
			for(int j=0;j<tamY;j++) {
				if(board[i][j]==0) {
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
	public static int numVivas(int[][] board, int x, int y) {
		int ret = 0;
		if(board[x-1][y-1]==1)
			ret++;
		if(board[x+1][y+1]==1)
			ret++;
		if(board[x-1][y+1]==1)
			ret++;
		if(board[x+1][y-1]==1)
			ret++;
		if(board[x][y-1]==1)
			ret++;
		if(board[x-1][y]==1)
			ret++;
		if(board[x][y+1]==1)
			ret++;
		if(board[x+1][y]==1)
			ret++;
		
		return ret;
	}
	
	//Generates a number n of sets of random cells if a certain condition is met
	public static void generateRandom(int[][] board, int n){
		if(Math.floor(Math.random()*(200-0+1)+0)%10==0) {
			 for(int i=0;i<n;i++) {
					int x = (int) Math.floor(Math.random()*((tamX-1)-0+1)+0);
					int y = (int) Math.floor(Math.random()*((tamY-1)-0+1)+0);
					board[x][y] = 1;
					
					if(x-1 >= 0 && y-1>=0 && x+1<tamX && y+1<tamY) {
						board[x][y] = 1;
						board[x-1][y-1] = 1;
						board[x][y+1] = 1;
						board[x+1][y] = 1;
						board[x-1][y] = 1;
					}	
			}
		 }
	}
}





























