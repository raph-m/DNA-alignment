import java.util.ArrayList;
import java.util.List;



public class Task7 {
	
	public static ArbreSeeds Seeds(String g, double th, int k){
		//c'est la même fonction que dans Task7bis à la différence que l'on utilise les arbres de calculs
		//tous les calculs se font au sein de la classe Arbre_Calcul 
		
		List<String> substrings = Courant.Wg(g, k);
		//on récupère Wg
		
		Arbre_Calcul A;
		ArbreSeeds seeds = new ArbreSeeds('o');
		Integer compteur = 0;
		//le compteur nous permet de memoriser de quel mot vient telle ou telle graine
		
		List<String> X = new ArrayList<String>();

		for(String s : substrings){
			
			A = new Arbre_Calcul(s, th);
			//on créé l'arbre de calcul qui se construit en fonction de s et de th
			//tous les calculs sont alors déjà fait
			
			X = A.to_list();
			//il suffit de récupérer les mots dans l'arbre avec un score suffisant
			
			for(String e : X){
				seeds.add(e, compteur);
				//on les ajoute à l'arbre des graines
			}
			compteur++;
		}
		return seeds;
		}
	
	
	public static List<Integer> index(String g, String t, int k, double th){
		//cette fonction est exactement la même que dans Task7bis
		
		ArbreSeeds seeds = Seeds(g,th,k);
		System.out.println("nombre de graines : "+seeds.size());
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

