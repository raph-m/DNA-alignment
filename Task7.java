import java.util.ArrayList;
import java.util.List;



public class Task7 {
	static double kills;
	public static List<String> Wg(String g, int k){//ca marche bien
		List<String> answer = new ArrayList<String>();
		for(int i = 0; i<=g.length()-k; i++){
			answer.add(g.substring(i, i+k));
		}
		return answer;
	}
	public static double top(String s){//ca marche bien
		//on compute la valeur de th*score(w) pour avoir notre seuil
		double answer = 0;
		for(int i = 0; i<s.length(); i++){
			answer+=Blosum50.getScore(s.charAt(i), s.charAt(i));
		}
		return answer;
	}
	
    public static void get_seeds(Arbre_Calcul A, ArbreSeeds S, String s, double top, int k, Integer compteur,double[] best_score_restant){
  	  if(A.fils.isEmpty()==false){
  		  int depth = s.length();
  		  int taille_restante = k-depth;
  		  if(A.score>=top-best_score_restant[depth]){
  		  if(s.length()==k-1){
  			  for(Arbre_Calcul B : A.fils){
  				  if(B.score>=top){
  					  S.add(s+B.contenu,compteur);
  				  }
  			  }
  		  }
  		  else{
  			  for(Arbre_Calcul B : A.fils){
  					  get_seeds(B,S,s+B.contenu,top,k,compteur,best_score_restant);
  			  }
  		  }
  		  }
  		  
  		  else{
  			  	kills = kills + Math.pow(20, taille_restante);//c'est ici qu'on peut voir combien de branches ont été tuées avant d'être calculées
  				  }
  			  }

  		  }
	
	public static ArbreSeeds Seeds(String g, double th, int k){//ca marche pas mal aussi
		List<String> substrings = Wg(g, k);
		Arbre_Calcul A = new Arbre_Calcul(k);
		double top;
		double pourcentage;
		ArbreSeeds seeds = new ArbreSeeds('o');
		Integer compteur = 0;
		double[] best_score_restant = new double[k];
		for(String s : substrings){
			
			for(int j = 0; j<k;j++){
				best_score_restant[j]=Task8.score(s.substring(j, k),s.substring(j, k));
			}
			
			A.compute_scores(s);
			top=th*top(s);
			kills = 0.0;
			get_seeds(A, seeds, "", top, k, compteur,best_score_restant);
			pourcentage = 100*kills/(Math.pow(20, k));
			System.out.println("le système de destruction de branches a permis d'éliminer "+Math.floor(pourcentage)+"% des calculs");
			compteur++;
		}
		return seeds;
		}
	
	
	public static List<Integer> index(String g, String t, int k, double th){//cette fonction imprime les seeds
		//crées à partir de g et renvoie les indices de debut de perfect match entre un mot de Sg et 
		//un mot de t.
		ArbreSeeds seeds = Seeds(g,th,k);//on commence par récupérer Sg
		System.out.println("nombre de graines : "+seeds.size());
		seeds.print();
		System.out.println("et on obtient un match pour les index suivants: ");
		List<Integer> answer = new ArrayList<Integer>(); 
		for(Integer i = 0; i<=t.length() - k;i++){
			if(seeds.contains(t.substring(i, i+k))){
				answer.add(i);
			}
		}
		return answer;
	}
	
	}

