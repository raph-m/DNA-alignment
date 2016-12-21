public class Task5 { //attention: à revoir, faut il que les bords soient à 0 ?? je pense que  non...
	//le sujet n'est pas clair à ce sujet mais je pense qu'il faut se dire que les séquences ne commencent pas forcément au
	//même endroit donc mettre des tirets au début ou  à la fin
	//cette fonction permet de récupérer un tableau fléché
	//cette fonction construit à la fois le tableau des scores maximales de sous-séquences (memoization)
	//et en même temps il retient de quelle "direction" il est venu pour aller dans chaque case
	//c'est-à-dire à partir de quelle case il a calculé sa valeur
	
	//attention problème ici surement des conversions double/int qui se font mal
	//(on ne récupère que des valeurs entières même si on rentre des gaps avec des décimales)
	//en fait ce n'est pas un problème si on se limite à des valeurs entières
	
	//ceci nous servira dans la suite pour retrouver l'alignement facilement !
	static int[][] best_alignment_tab(String s, String t,double gap_1, double gap_2){
		int n = s.length();
		int m = t.length();
		double gauche;double haut;double diago;
    	double [][] d = new double[m+1][n+1];
    	int [][] fleches = new int[m+1][n+1];
    	String tiret_en_string = "-";
    	char tiret = tiret_en_string.charAt(0);
    	//-1 = gauche, 0 = diago, 1 = haut
    	for(int j = 0; j <= n; j++){
    		d[0][j] = 0.0;//on met le bord à 0
    		fleches[0][j]=-1;//on met des flèches vers l'Ouest dans le bord supérieur
    		}
    	for(int i = 0; i <= m; i++){d[i][0] = 0.0;fleches[i][0]=1;}//idem
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
        		
        		if(i!=m || j!=n){//ce if permet d'épargner le cas d'ouverture en fin de chaine (il faudra pas mettre and and plutot ?
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
    	System.out.println("le score obtenu est alors de "+d[m][n]);
        return fleches;//on renvoie le tableau de fleches
        }
	
	//cette méthode permet de visualiser l'alignement qui donne le plus gros score
	//comme dans Task3 on va remonter le tableau jusqu'a la case 0*0 en recopiant les caractères comme il le faut
	
	static String[] display(String s, String t,double gap_1, double gap_2){
		int[][] fleches = best_alignment_tab(s,t,gap_1,gap_2);//on récupère le tableau de flèche
		
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
			if(fleches[i][j]==1){//si on se déplace vers le Nord alors on laisse passer un caractère de t
				//il faut donc mettre en tiret en face de ce caractère dans s
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
		//le problème est que l'on a recopié dans l'ordre inverse
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
		System.out.println("l'alignement optimal est alors le suivant:");
		System.out.println(answer_s_2);
		System.out.println(answer_t_2);
		
		//on rajoute ici une partie qui permet de compter le nombre de gap1 et 2 qu'on utilise
		i=0;
		int compteur_1 = 0;
		int compteur_2 = 0;
		boolean tiret_s = false;
		boolean tiret_t = false;
		if(answer_s_2.charAt(0)=='-'){
			while(answer_s_2.charAt(i)=='-'){
				i++;
			}
			tiret_s=true;
		}
		else{
			if(answer_t_2.charAt(0)=='-'){
				while(answer_t_2.charAt(i)=='-'){
					i++;
				}
				tiret_t=true;
			}
		}
		
		while(true){
			if(i==answer_s_2.length()){break;}
			if(answer_s_2.charAt(i)=='-'){
				if(tiret_s==false){
					compteur_1++;
					if(tiret_t){
						compteur_2++;
					}
				}
				tiret_s = true;
					
				}
			else{tiret_s=false;}
			if(answer_t_2.charAt(i)=='-'){
				if(tiret_t==false){
					compteur_1++;
					if(tiret_s){
						compteur_2++;
					}
				}
				tiret_t = true;
				}
			else{tiret_t=false;}
			i++;
			}
		if(tiret_s || tiret_t){
			compteur_1--;
			if(compteur_2>compteur_1){compteur_2--;}
		}
		
		System.out.println("nombre de pénalités gap_1 = "+compteur_1+"   nombre de pénalités gap_2 = "+compteur_2);
		
		
		return lesdeux;//on renvoie les deux séquences avec tiret (ça servira peut etre plus tard)
	}
}