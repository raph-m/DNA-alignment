import java.util.ArrayList;
import java.util.List;

public class Task7bis {
	static double kills;
	
	
	public static List<String> Wg(String g, int k){//ca marche bien
		List<String> answer = new ArrayList<String>();
		for(int i = 0; i<=g.length()-k; i++){
			answer.add(g.substring(i, i+k));
		}
		return answer;
	}
	
	
	
	
	public static int [] plus_one(int[] tab){
		int n = tab.length - 1;
		while(true){
			if(tab[n]<19 || n==0){
				tab[n]=tab[n]+1;
					for(int i = n+1;i<=tab.length-1;i++){
						tab[i]=0;
					}
					break;
				}
			n--;
			}
		return tab;
		}
	
	
	
	public static ArbreSeeds Seeds(String g, double th, int k, char[] alphabet){//ca marche pas mal aussi
		List<String> substrings = Wg(g, k);
		ArbreSeeds seeds = new ArbreSeeds('o');
		Integer compteur = 0;
		for(String s : substrings){
			get_seeds(seeds, s, alphabet, th, k, compteur);
			compteur++;
		}
		return seeds;
		}




	private static void get_seeds(ArbreSeeds seeds, String s, char[] alphabet, double th, int k, Integer compteur) {
		int[] tab = new int[k];
		double palier = th*Task8.score(s, s);
		for(int i = 0; i<k; i++){
			tab[i]=0;
		}
		String a = "";
		while(true){
			if(tab[0]==20){
				break;
			}
			a="";
			for(int i = 0; i<k;i++){
				a = a + alphabet[tab[i]];
			}
			if(Task8.score(a,s)>=palier){
				seeds.add(a,compteur);
			}
			tab = plus_one(tab);
		}
	}
	
	
	public static List<Integer> index(String g, String t, int k, double th, char[] alphabet){//cette fonction imprime les seeds
		//crées à partir de g et renvoie les indices de debut de perfect match entre un mot de Sg et 
		//un mot de t.
		ArbreSeeds seeds = Seeds(g,th,k, alphabet);//on commence par récupérer Sg
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
	




	



//	public static List<Integer> index(String g, String t, int k, double th){//cette fonction imprime les seeds
//		//crées à partir de g et renvoie les indices de debut de perfect match entre un mot de Sg et 
//		//un mot de t.
//		ArbreSeeds seeds = Seeds(g,th,k);//on commence par récupérer Sg
//		System.out.println("nombre de graines : "+seeds.size());
//		seeds.print();
//		System.out.println("et on obtient un match pour les index suivants: ");
//		List<Integer> answer = new ArrayList<Integer>(); 
//		for(Integer i = 0; i<=t.length() - k;i++){
//			if(seeds.contains(t.substring(i, i+k))){
//				answer.add(i);
//			}
//		}
//		return answer;
	
	
	
	
	
	

