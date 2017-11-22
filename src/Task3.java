public class Task3 {

	static int[][] lsubsq_tab(String s, String t){
		//cette fonction permet de récupérer un tableau fléché
		//cette fonction construit à la fois le tableau des tailles maximales de sous-séquences (memoization)
		//et en même temps il retient de quelle "direction" il est venu pour aller dans chaque case
		//c'est-à-dire à partir de quelle case il a calculé sa valeur
		
		//ceci nous servira dans la suite pour retrouver la sous-séquence facilement.
		
		int n = s.length();
		int m = t.length();
		
    	int [][] d = new int[m+1][n+1];
    	int [][] fleches = new int[m+1][n+1];
    	//les deux tableaux qui vont permettre la memoization
    	//-1 = Ouest, 0 = Nord-Ouest, 1 = Nord
    	
    	for(int j = 0; j <= n; j++){
    		d[0][j] = 0;
    		//on met le bord à 0
    		fleches[0][j]=-1;
    		//on met des flèches vers l'Ouest dans le bord supérieur
    		}
    	
    	for(int i = 0; i <= m; i++){d[i][0] = 0;fleches[i][0]=1;}
    	//idem
    	
    	for(int i = 1; i <= m; i++){
    		//on remplit le tableau de haut en bas
        	
    		for(int j = 1; j <= n; j++){
    			//et de gauche à droite
        		
    			if(t.charAt(i-1)==s.charAt(j-1)){
    				//s'il y a deux caractères identiques, ils font donc partie de la
        			//sous-séquence et on connait donc la valeur de la case cherchée
        			
    				d[i][j]=d[i-1][j-1]+1;
        			fleches[i][j]=0;
        			//on retient cette direction (Nord-Ouest)
        		}
        		else{
        			//sinon on regarde dans quelle "direction" il vaut mieux chercher
        			
        			if(d[i-1][j]<=d[i][j-1]){
        				d[i][j]=d[i][j-1];
        				fleches[i][j]=-1;
        				//Ouest
        			}
        			else{d[i][j]=d[i-1][j];
        				fleches[i][j]=1;
        				//Nord
        			}
        		
        		}
    	}
    	}
        return fleches;
	}
	

	static String[] display(String s, String t){
		//le programme suivant permet de "display nicely" l'alignement optimal
		
		int[][] fleches = lsubsq_tab(s,t);
		//on récupère le tableau fléché
		
		int j = s.length();
		int i = t.length();
		//on se place tout au bout du tableau (Sud-Est)
		
		String sd = "";
		String td ="";
		//ces deux strings vont permettre de recopier les séquences adéquates avec les tirets
		
		String seq = "";
		//le string qui va nous permettre de stocker la sous-séquence commune

		while(i>0 || j>0){
			//on va suivre les flèches jusqu'a arriver à la case départ (donc au Nord-Ouest)
			switch(fleches[i][j]){
				case 0:
				//dans le cas d'une fleche diagonale on recopie les deux lettres qui sont alignées
				//et on se déplace dans cette direction
				
				    td+= t.charAt(i-1);
					sd+= s.charAt(j-1);
					seq+= s.charAt(j-1);
				//ce caractère fait partie la sous-séquence commune donc on le stocke
				
					i--;
					j--;
					break;
				case 1:
				//si on se déplace vers le Nord alors on laisse passer un charactère de t
				//il faut donc mettre en tiret en face de ce charactère dans s
				
					td+= t.charAt(i-1);
					sd+="-";
				
					i--;
					break;
			    case -1:
				//et vice-versa
				
					sd+= s.charAt(j-1);
					td+="-";
				
					j--;
					break;
			}
		}
		
		String answer_s_2 = Courant.retourner(sd);
		String answer_t_2 = Courant.retourner(td);
		//le probème est que l'on a recopié dans l'ordre inverse
		//il faut donc retourner nos chaînes de caractères
		//pour cela on procède comme pour les piles
		

		String[] lesdeux = new String[2];
		lesdeux[0]=answer_s_2;
		lesdeux[1]=answer_t_2;
		
		System.out.println("la plus grande sous séquence commune est "+Courant.retourner(seq)+" elle est de taille "+Task2.lsubsq(s, t));
		//on imprime la plus grande sous-séquence commune
		
		System.out.println("");
		System.out.println("l'alignement optimal est le suivant:");
		System.out.println("");
		Courant.nice_display(answer_s_2, answer_t_2);
		//on imprime l'alignement
		
		return lesdeux;
		//on renvoie les deux séquences avec tiret (ça servira peut etre plus tard)
	}

	}
