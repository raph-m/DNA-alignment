import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class Task7bis {
	
	public static ArbreSeeds Seeds(String g, double th, int k){
		//cette méthode récupère les graines pour chaque mot de Wg
		
		List<String> substrings = Courant.Wg(g, k);
		//on récupère Wg (voir dans la classe Courant)
		
		ArbreSeeds seeds = new ArbreSeeds('o');
		//l'arbre dans lequel on va stocker les graines
		
		Integer compteur = 0;
		//ce compteur va permettre de stocker la provenance des graines
		//cela permet de mémoriser à partir de quel élément de Wg a été générée telle ou telle graine
		
		double pourcentage;
		
		int n = substrings.size();
		
		for(String s : substrings){
			
			pourcentage = Math.floor(100.0*compteur/n);
			System.out.println(pourcentage+"% de la tache effectuée");
			//un message qui permet de savoir ou en est dans le calcul
			
			get_seeds(seeds, s, th, k, compteur);
			//on récupère les graines générées par le mot s
			
			compteur++;
		}
		
		return seeds;
		}




	private static void get_seeds(ArbreSeeds seeds, String s, double th, int k, Integer compteur) {
		//cette fonction récupère les graines engendrées par le string s
		//celle-ci sont alors ajoutées à l'arbre seeds
		
		//la méthode utilisée est naïve: on teste toutes les combinaisons
		
		int[] tab = new int[k];
		//le tableau qui va nous indiquer à quelle étape de calcul on en est
		
		double palier = th*Courant.score(s, s);
		//le score à partir duquel on accepte le mot
		
		char[] alphabet = Courant.alphabet();
		
		for(int i = 0; i<k; i++){
			tab[i]=0;
			//on se place à 00000...
			//cela correspond donc à AAAAA...
		}
		
		String a = "";
		while(true){
			if(tab[0]==20){
				break;
				//on sort de la boucle quand on a réalisé toutes les combinaisons possibles
			}
			a="";
			for(int i = 0; i<k;i++){
				a = a + alphabet[tab[i]];
				//on construit le mot lettre à lettre à partir du tableau
			}
			if(Courant.score(a,s)>=palier){
				seeds.add(a,compteur);
				//stockage du mot si le critère est respecté
				
			}
			tab = Courant.plus_one(tab);
			//on incrémente le tableau. celui-ci nous permet de tester toutes les combinaisons sans répétitions
		}
	}
	
	
	public static List<Integer> index(String g, String t, int k, double th){
		//cette fonction renvoie les indices de perfect match entre des graines et des sous mots de t
		
		long startTime;
		long endTime;
		long totalTime;
		
		startTime = System.currentTimeMillis();
		
		ArbreSeeds seeds = Seeds(g,th,k);
		//on commence par récupérer les graines
		
		
		endTime   = System.currentTimeMillis();
		totalTime = endTime - startTime;
		System.out.println("Le temps de calcul est de "+totalTime/1000.0+" secondes");
		
		System.out.println("nombre de graines: "+seeds.size());
		seeds.print();
		System.out.println("");
		
		List<Integer> answer = new ArrayList<Integer>(); 
		for(Integer i = 0; i<=t.length() - k;i++){
			if(seeds.contains(t.substring(i, i+k))){
				answer.add(i);
				//pour chaque sous-mot de t on regarde si ce sous-mot fait partie de l'arbre des graines
			}
		}
			return answer;
		}
	}
	
	
	
	
	
	
	

