public class Task6 {
	//la Task6 consiste simplement à réappliquer Task5 mais sur tous les sous-mots de s et t
	
	static double best_score(String s, String t,double gap_1, double gap_2){
		//cette fonction est la même que dans Task5 sauf qu'elle renvoie le score maximal plutot que de renvoyer les flèches
		
		int n = s.length();
		int m = t.length();
		double gauche;double haut;double diago;
    	double [][] d = new double[m+1][n+1];
    	int [][] fleches = new int[m+1][n+1];
    	String tiret_en_string = "-";
    	char tiret = tiret_en_string.charAt(0);
    	//-1 = gauche, 0 = diago, 1 = haut
    	for(int j = 0; j <= n; j++){
    		d[0][j] = 0;
    		fleches[0][j]=-1;
    		}
    	for(int i = 0; i <= m; i++){d[i][0] = 0;fleches[i][0]=1;}
    	for(int i = 1; i <= m; i++){
        	for(int j = 1; j <= n; j++){
        		gauche = d[i][j-1]+Blosum50.getScore(s.charAt(j-1),tiret);
        		haut = d[i-1][j]+Blosum50.getScore(t.charAt(i-1),tiret);
        		diago = d[i-1][j-1]+Blosum50.getScore(s.charAt(j-1),t.charAt(i-1));

        		if(i!=m || j!=n){
        			if(j!=1){
        				if(fleches[i][j-1]==1){gauche-=gap_1+gap_2;}
        				if(fleches[i][j-1]==0){gauche-=gap_1;}
        			}
        			if(i!=1){
        			if(fleches[i-1][j]==-1){haut-=gap_1+gap_2;}
        			if(fleches[i-1][j]==0){haut-=gap_1;}
        			}
        		}
        		
        		if(diago>=gauche && diago>=haut){
        			d[i][j]=diago;
        			fleches[i][j]=0;
        		}
        		else{
        			if(gauche>=haut){
        				d[i][j]=gauche;
        				fleches[i][j]=-1;
        			}
        			else{
        				fleches[i][j]=1;
        				d[i][j]=haut;
        			}
        		
        		}
        		}
    	}
        return d[m][n];
        }
	
	static double best_local_score(String s, String t, double gap_1, double gap_2){
		//cette fonction est simplement une application de la fonction ci-dessus best_score à tous les substrings de s et de t
		//on recherche la valeur maximale de best_score et on stocke alors les indices
		
		int n = s.length();
		int m = t.length();
		
		double pourcentage = 0.0;
		int compteur = 0;
		
		System.out.println("longueur de s : "+n+" longueur de t "+m);
		
		double max = 0;
		double candidat;
		
		int jm = 1; int jpm = 1; int im = 0; int ipm = 0;
		
		for(int j = 1; j <= n; j++){
			//on initie ici une quadruple boucle pour faire varier tous les indices
			
			compteur++;
			if(compteur>n/10){
				compteur = 0;
				pourcentage = 100*j/n;
				System.out.println(pourcentage+"% de la tâche effectué");}
			//ce compteur permet d'informer l'utilisateur sur l'avancée des travaux
			
			for(int jp = 1; jp <= m; jp++){
				for(int i = 0; i < j; i++){
					for(int ip = 0; ip < jp; ip++){
						candidat = best_score(s.substring(i, j), t.substring(ip, jp), gap_1, gap_2);
						//on récupère le score pour ces valeurs d'indices
						
						if(max<candidat){
							max = candidat;
							jm = j; jpm = jp; im = i; ipm = ip;
							//dans le cas ou le score est supérieur au maximum on stocke les indices
							
						}
						
					}
				}
			}
			
		}
		System.out.println("");
		System.out.println("le maximum est atteint en i, j, ip, jp = "+im+" "+jm+" "+ipm+" "+jpm);
		System.out.println("");
		Task5.display(s.substring(im, jm), t.substring(ipm, jpm),gap_1,gap_2);
		//on appelle Task5 pour offrir une belle disposition à l'utilisateur
		
		return max;
	}
}