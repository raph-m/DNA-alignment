public class Task6 {
	//la Task6 consiste simplement à réappliquer Task5 mais sur tous les sous-mots de s et t
	
	//cette fonction est la même que dans Task5 sauf qu'elle renvoie le score maximal plutot que de renvoyer les flèches
	
	static double best_score(String s, String t,double gap_1, double gap_2){
		int n = s.length();
		int m = t.length();
		double gauche;double haut;double diago;
    	double [][] d = new double[m+1][n+1];
    	int [][] fleches = new int[m+1][n+1];
    	String tiret_en_string = "-";
    	char tiret = tiret_en_string.charAt(0);
    	//-1 = gauche, 0 = diago, 1 = haut
    	for(int j = 0; j <= n; j++){
    		d[0][j] = 0;//on met le bord à 0
    		fleches[0][j]=-1;//on met des flèches vers l'Ouest dans le bord supérieur
    		}
    	for(int i = 0; i <= m; i++){d[i][0] = 0;fleches[i][0]=1;}//idem
    	for(int i = 1; i <= m; i++){//on remplit le tableau de haut en bas
        	for(int j = 1; j <= n; j++){//et de gauche à droite
        		gauche = d[i][j-1]+Blosum50.getScore(s.charAt(j-1),tiret);//le score si on alignait la lettre pointée de s
        		//avec un tiret
        		haut = d[i-1][j]+Blosum50.getScore(t.charAt(i-1),tiret);//et vice-versa
        		diago = d[i-1][j-1]+Blosum50.getScore(s.charAt(j-1),t.charAt(i-1));//le score si on alignait les deux
        		//caractères actuellement pointées
        		
        		//ici on ajoute le coût d'ouverture de gap
        		//pour traduire les conditions en terme de directions, cela revient à dire qu'un changement de direction de 45° coute gap_1
        		//et un changement de direction  de 90° coute gap_2
        		//par exemple on regarde la case juste à l'Ouest. Si elle-même pointe en direction de l'Ouest alors il n'y pas de pénalité
        		//si en revanche elle vient du Nord alors on met une pénalité gap_2 et si elle vient du Nord-Ouest on met une pénalité de gap_1
        		
        		if(i!=m || j!=n){//ce if permet d'épargner le cas d'ouverture en fin de chaine
        			if(j!=1){//ces deux if permettent d'épargner le cas ou on va se retrouver sur le bord
        				if(fleches[i][j-1]==1){gauche-=gap_1+gap_2;}
        				if(fleches[i][j-1]==0){gauche-=gap_1;}
        			}
        			if(i!=1){
        			if(fleches[i-1][j]==-1){haut-=gap_1+gap_2;}//on applique les pénalités
        			if(fleches[i-1][j]==0){haut-=gap_1;}
        			}
        		}
        		
        		if(diago>=gauche && diago>=haut){//on se déplace dans la direction du meilleur score
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
        return d[m][n];//on renvoie le tableau de fleches
        }
	
	static double best_local_score(String s, String t, double gap_1, double gap_2){
		int n = s.length();
		int m = t.length();
		double pourcentage = 0.0;
		int compteur = 0;
		System.out.println("longueurs : "+n+" "+m);
		double max = 0;
		double candidat;
		int jm = 1; int jpm = 1; int im = 0; int ipm = 0; 
		for(int j = 1; j <= n; j++){
			compteur++;
			if(compteur>n/10){
				compteur = 0;
				pourcentage = 100*j/n;
				System.out.println(pourcentage+"% de la tâche effectué");}
			
			for(int jp = 1; jp <= m; jp++){
				for(int i = 0; i < j; i++){
					for(int ip = 0; ip < jp; ip++){
						candidat = best_score(s.substring(i, j), t.substring(ip, jp), gap_1, gap_2);
						if(max<candidat){
							max = candidat;
							jm = j; jpm = jp; im = i; ipm = ip;
						}
						
					}
				}
			}
			
		}
		System.out.println("le maximum est atteint en i, j, ip, jp = "+im+" "+jm+" "+ipm+" "+jpm);
		Task5.display(s.substring(im, jm), t.substring(ipm, jpm),gap_1,gap_2);
		return max;
	}
}