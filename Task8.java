import java.util.ArrayList;
import java.util.List;

public class Task8 {
	public static double score(String g, String t){
		double s = 0;
		for(int i = 0; i<g.length();i++){
			s+=Blosum50.getScore(t.charAt(i), g.charAt(i));
		}
		return s;
	}
	
	public static void match(String g, String t, int k, double th,double thl){//cette fonction imprime les seeds
		//crées à partir de g et renvoie les indices de debut de perfect match entre un mot de Sg et 
		//un mot de t.
		char[] alphabet = new char[] {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		List<String> substrings = Task7bis.Wg(g, k);
		String current;
		ArbreSeeds seeds = Task7bis.Seeds(g,th,k, alphabet);//on commence par récupérer Sg
		System.out.println("nombre de graines : "+seeds.size());
		System.out.println("et on obtient un match pour les index suivants: ");
		List<Integer> index = new ArrayList<Integer>();
		List<Integer> provenances = new ArrayList<Integer>();
		List<String> matchings = new ArrayList<String>();
		List<Integer> index_stock = new ArrayList<Integer>();
		List<String> t_stock = new ArrayList<String>();
		List<String> g_stock = new ArrayList<String>();
		String g_current;
		String t_current;
		double current_score;
		List<Integer> g_index = new ArrayList<Integer>();
		List<Integer> t_index = new ArrayList<Integer>();
		List<Integer> size = new ArrayList<Integer>();
		
		for(Integer i = 0; i<=t.length() - k;i++){
			current = t.substring(i, i+k);
			if(seeds.contains(current)){
				index.add(i);
				provenances.add(seeds.provenance(current));
				matchings.add(current);
			}
		}
		int compteur = 0;
		for(String a : matchings){
			System.out.println("matching "+a+" with "+substrings.get(provenances.get(compteur))+" at index "+index.get(compteur));
			compteur++;
		}
		
		System.out.println("");
		System.out.println("on va maitenant tâcher d'étendre ces matchings");
		double palier = thl*score(g,g);
		System.out.println("le score à atteindre est donc de "+palier);
		System.out.println("");
		//la suite semble bien focntionner
		compteur = -1;
		int j;
		int l;
		int x;
		int y;
		for(String a : matchings){
			compteur++;
			j=index.get(compteur);//c'est l'endroit du premier terme dans le séquence  dans t
			l=provenances.get(compteur);//c'est l'endroit du premier terme dans g
			while(true){//on remonte le plus possible
				if(j<=0){break;}
				if(l<=0){break;}
				if(Blosum50.getScore(t.charAt(j-1), g.charAt(l-1))<0){
					break;
				}
				j--;
				l--;
			}
			x=index.get(compteur)+k-1;//c'est l'endroit du dernier terme dans le séquence  dans t
			y=provenances.get(compteur)+k-1;//c'est l'endroit du dernier terme dans g
			while(true){//on remonte le plus possible
				if(x>=t.length()-1){break;}
				if(y>=g.length()-1){
					break;}
				if(Blosum50.getScore(t.charAt(x+1), g.charAt(y+1))<0){
					break;
				}
				x++;
				y++;
			}
			t_current = t.substring(j,x+1);
			g_current = g.substring(l, y+1);
			current_score = score(g_current, t_current);
			System.out.println("matching "+t_current+" with "+ g_current+" at index "+j+" le score atteint est alors de "+current_score);
			if(current_score>=palier){
				System.out.println("accepté");
				g_stock.add(g_current);
				t_stock.add(t_current);
				index_stock.add(j);
				t_index.add(j);
				g_index.add(l);
				size.add(g_current.length());
			}
			else{System.out.println("refusé");}
			}
		
		}
	//return t_index,g_index,size;//à voir comment on fait
	}
	
