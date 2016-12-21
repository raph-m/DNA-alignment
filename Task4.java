
public class Task4 {
	//cette fonction permet de récupérer un tableau fléché
	//cette fonction construit à la fois le tableau des scores maximales de sous-séquences (memoization)
	//et en même temps il retient de quelle "direction" il est venu pour aller dans chaque case
	//c'est-à-dire à partir de quelle case il a calculé sa valeur
	
	//on peut remarquer qu'il est "gratuit" de mettre des tirets au début ou à la fin (cf les bords à zéro)
	//on se place dans cette hypothèse au vu de la Task5 ou on apprend qu'il est possible que les séquences ne commencent
	//pas au même endroit
	
	//ceci nous servira dans la suite pour retrouver l'alignement facilement !
	static int[][] best_alignment_tab(String s, String t){
		int n = s.length();
		int m = t.length();
		float gauche;float haut;float diago;
    	float [][] d = new float[m+1][n+1];
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
        		if(diago>=gauche && diago>=haut){//on se déplace dans la direction du meilleur score
        			d[i][j]=diago;
        			fleches[i][j]=0;
        		}
        		else{
        			if(gauche>=haut){
        				d[i][j]=d[i][j-1];
        				fleches[i][j]=-1;
        			}
        			else{d[i][j]=d[i-1][j];
        				fleches[i][j]=1;
        			}
        		
        		}
    	}
    	}
    	System.out.println("le score obtenu est de "+d[m][n]);
        return fleches;//on renvoie le tableau de fleches
        }
	
	//cette méthode permet de visualiser l'alignement qui donne le plus gros score
	//comme dans Task3 on va remonter le tableau jusqu'a la case 0*0 en recopiant les caractères comme il le faut
	
	static String[] display(String s, String t){
		int[][] fleches = best_alignment_tab(s,t);//on récupère le tableau de flèche
		
		int j = s.length();//on se place tout au Sud-Est du tableau
		int i = t.length();
		String sd = "";//ces strings vont permettre de stocker les strings alignés
		String td ="";

		while(i>0 || j>0){//on va suivre les flèches jusqu'a arriver à la case départ (donc au Nord-Ouest)
			if(fleches[i][j]==0){//dans le cas d'une fleche diagonale on recopie les deux lettres qui sont alignées
				//et on se déplace dans cette direction
				td+= t.charAt(i-1);
				sd+= s.charAt(j-1);
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
		System.out.println("");
		System.out.println("Ce score est obtenu pour cet alignement: ");
		System.out.println(answer_s_2);
		System.out.println(answer_t_2);
		
		return lesdeux;//on renvoie les deux séquences avec tiret (ça servira peut etre plus tard)
	}



}