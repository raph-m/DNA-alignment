public class Task2 {
	
	static int lsubsq(String s, String t){
		//cette fonction prend en entrée deux String et renvoie la taile de la plus grande sous séquence commune
		
		int n = s.length();
		int m = t.length();
		
    	int [][] d = new int[m+1][n+1];
    	//c'est le tableau dans lequel on effectuera la memoization
    	//ce tableau est de taille (m+1)*(n+1) car il est plus commode d'avoir des bords à zéro
    	
    	for(int j = 0; j <= n; j++){d[0][j] = 0;}
    	//on met les bords à zéro
    	
    	for(int i = 0; i <= m; i++){d[i][0] = 0;}
    	//idem
    	
    	for(int i = 1; i <= m; i++){
    		//on remplit le tableau de haut en bas
    		
        	for(int j = 1; j <= n; j++){
        		//et de gauche à droite
        		
        		if(t.charAt(i-1)==s.charAt(j-1)){
        			d[i][j]=d[i-1][j-1]+1;}
        		//s'il y a deux caractères identiques, ils font donc partie de la
        		//sous-séquence et on connait donc la valeur de la case cherchée
        		
        		else{
        			d[i][j]=Math.max(d[i-1][j],d[i][j-1]);
        			//sinon on regarde dans quelle "direction" il vaut mieux chercher
        			}
        		}
    	}
    	
        return d[m][n];
    	//l'invariant de boucle est que la valeur dans la case [i][j] est la taille maximale obtenue avec les i premières
    	//lettres de s et les j premières lettres de t
    	//donc la valeur recherchée se trouve en d[m][n]
	}
	

}
