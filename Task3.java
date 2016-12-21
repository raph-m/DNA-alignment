public class Task3 {
	//cette fonction permet de récupérer un tableau fléché
	//cette fonction construit à la fois le tableau des tailles maximales de sous-séquences (memoization)
	//et en même temps il retient de quelle "direction" il est venu pour aller dans chaque case
	//c'est-à-dire à partir de quelle case il a calculé sa valeur
	
	//ceci nous servira dans la suite pour retrouver la sous-séquence facilement !
	static int[][] lsubsq_tab(String s, String t){
		int n = s.length();
		int m = t.length();
    	int [][] d = new int[m+1][n+1];
    	int [][] fleches = new int[m+1][n+1];
    	//-1 = Ouest, 0 = Nord-Ouest, 1 = Nord
    	for(int j = 0; j <= n; j++){
    		d[0][j] = 0;//on met le bord à 0
    		fleches[0][j]=-1;//on met des flèches vers l'Ouest dans le bord supérieur
    		}
    	for(int i = 0; i <= m; i++){d[i][0] = 0;fleches[i][0]=1;}//idem
    	
    	for(int i = 1; i <= m; i++){//on remplit le tableau de haut en bas
        	for(int j = 1; j <= n; j++){//et de gauche à droite
        		if(t.charAt(i-1)==s.charAt(j-1)){//s'il y a deux caractères identiques, ils font donc partie de la
        			//sous-séquence et on connait donc la valeur de la case cherchée
        			d[i][j]=d[i-1][j-1]+1;
        			fleches[i][j]=0;//on retient cette direction (Nord-Ouest)
        		}
        		else{//sinon on regarde dans quelle "direction" il vaut mieux chercher
        			if(d[i-1][j]<=d[i][j-1]){
        				d[i][j]=d[i][j-1];
        				fleches[i][j]=-1;//Ouest
        			}
        			else{d[i][j]=d[i-1][j];
        				fleches[i][j]=1;//Nord
        			}
        		
        		}
    	}
    	}
        return fleches;
	}
	//le programme suivant permet de "display nicely" l'alignement optimal
	static String[] display(String s, String t){
		int[][] fleches = lsubsq_tab(s,t);//on récupère le tableau fléché
		int j = s.length();
		int i = t.length();//on se place tout au bout du tableau (Sud-Est)
		String sd = "";//ces deux strings vont permettre de recopier les séquences adéquates avec les tirets
		String td ="";
		String seq = "";//le string qui va nous permettre de stocker la sous-séquence commune

		while(i>0 || j>0){//on va suivre les flèches jusqu'a arriver à la case départ (donc au Nord-Ouest)
			if(fleches[i][j]==0){//dans le cas d'une fleche diagonale on recopie les deux lettres qui sont alignées
				//et on se déplace dans cette direction
				td+= t.charAt(i-1);
				sd+= s.charAt(j-1);
				seq+= s.charAt(j-1);//ce caractère fait partie la sous-séquence commune donc on le stocke
				i--;
				j--;
			}
			if(fleches[i][j]==1){//si on se déplace vers le Nord alors on laisse passer un charactère de t
				//il faut donc mettre en tiret en face de ce charactère dans s
				td+= t.charAt(i-1);
				sd+="-";
				i--;
				
			}
			if(fleches[i][j]==-1){//et vice-versa
				sd+= s.charAt(j-1);
				td+="-";
				j--;
			}
		}
		//le probème est que l'on a recopié dans l'ordre inverse
		//il faut donc retourner nos chaînes de caractères
		//pour cela on procède comme pour les piles
		String answer_s_2 = "";
		int n = sd.length();
		while(answer_s_2.length()<=n-1){
			answer_s_2 += sd.charAt(sd.length()-1);
			sd = sd.substring(0, sd.length()-1);
		}
		String answer_t_2 = "";
		n = td.length();
		while(answer_t_2.length()<=n-1){
			answer_t_2 += td.charAt(td.length()-1);
			td = td.substring(0, td.length()-1);
		}
		String[] lesdeux = new String[2];
		lesdeux[0]=answer_s_2;
		lesdeux[1]=answer_t_2;
		//on imprime la plus grande sous-séquence commune
		System.out.println("la plus grande sous séquence commune est "+seq+" elle est de taille "+Task2.lsubsq(s, t));
		//on imprime l'alignement
		System.out.println("l'alignement optimal:");
		System.out.println(answer_s_2);
		System.out.println(answer_t_2);
		return lesdeux;//on renvoie les deux séquences avec tiret (ça servira peut etre plus tard)
	}
	
//je laisse en dessous l'ancien code mais je pense qu'il ne nous servira plus
	
//
//	static String best_subseq(String s, String t){
//		int [][] fleches = lsubsq_tab(s,t);
//		int j = s.length();
//		int i = t.length();
//		String answer = "";
//		while(i>0 & j>0){
//			if(fleches[i][j]==0){
//				answer = answer + t.charAt(i-1);
//				i--;
//				j--;
//			}
//			if(fleches[i][j]==1){
//				i--;
//			}
//			if(fleches[i][j]==-1){
//				j--;
//			}
//		}
//		String answer2 = "";
//		int n = answer.length();
//		while(answer2.length()<=n-1){
//			answer2 += answer.charAt(answer.length()-1);
//			answer = answer.substring(0, answer.length()-1);
//		}
//		if (answer2 == ""){System.out.println("pas de sous-séquence commune trouvée");}
//		return answer2;
//	}
//	
//	static String[] nice_display(String s, String t){
//		String sub = best_subseq(s,t);
//		char c;boolean a;boolean b; String tirets = "";int x = 0; int y = 0;
//		for(int i = 0; i<=sub.length()-1;i++){
//			c = sub.charAt(i);
//			a = true; b = true;
//			while(a || b){
//				if(s.charAt(x)==c){a=false;}
//				if(t.charAt(y)==c){b=false;}
//				if(a==false && b){s=s.substring(0,x)+"-"+s.substring(x,s.length());}
//				if(b==false && a){t=t.substring(0,y)+"-"+t.substring(y,t.length());}
//				if(a==false && b==false){tirets+="|";}else{tirets+=" ";}
//				x++;y++;
//			}
//		}
//		String [] answer = new String [3];
//		answer[0]=s;
//		answer[1]=t;
//		answer[2]=tirets;
//		String commentaire = "";
//		if(sub==""){commentaire = "(no common subsequence found)";}
//		System.out.println("best subsequence found (length "+sub.length()+") : "+sub+commentaire);
//		System.out.println(s);
//		System.out.println(tirets);
//		System.out.println(t);
//		return answer;
//	}
	}
