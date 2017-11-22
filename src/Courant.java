import java.util.ArrayList;
import java.util.List;

public class Courant {
	
	//des constantes qui serviront pour les couleurs

	static String retourner(String s){
		//cette fonction renvoie le String retourné
		//Pour cela on procède comme pour les piles, il suffit d'empiler la pile vide en même temps qu'on dépile
		
		String answer = "";
		int n = s.length();
		while(answer.length()<=n-1){
			answer += s.charAt(s.length()-1);
			s = s.substring(0, s.length()-1);
	}
		return answer;
	}
	
	
	static void nice_display(String s, String t){
		char sc;
		char tc;
		String x = "";
		String sp = "";
		String tp = "";
		int compteur = 0;
		for(int i = 0; i<s.length();i++){
			compteur ++;
			if(compteur>60){
				System.out.println(sp);
				System.out.println(x);
				System.out.println(tp);
				System.out.println("");
				sp = "";
				tp = "";
				x = "";
				compteur = 0;
			}
			tc = t.charAt(i);
			sc = s.charAt(i);
			tp = tp + tc;
			sp = sp +sc;
			if(sc==tc){
				x = x + "|";
			}
			else{
					x = x + " ";
				}
			
		}
		System.out.println(sp);
		System.out.println(x);
		System.out.println(tp);
	}
	
	public static List<String> Wg(String g, int k){
		//cette fonction permet simplement de renvoyer une liste contenant Wg c'est à dire la liste des sous-mots de g de taille k
		
		List<String> answer = new ArrayList<String>();
		
		for(int i = 0; i<=g.length()-k; i++){
			answer.add(g.substring(i, i+k));
		}
		
		return answer;
	}
	
	public static int [] plus_one(int[] tab){
		//cette fonction prend en argument un tableau représentant un nombre en base 20
		//Il renvoie la représentation en base 20 de ce nombre plus 1
		
		int n = tab.length - 1;
		//on se place à la fin du tableau
		
		while(true){
			
			if(tab[n]<19 || n==0){
				
				tab[n]=tab[n]+1;
				//NB si on a atteint le nombre maximal (rempli de 19) alors on ne rajoute pas de case mais on met la première à 20
				//on se servira de cela pour savoir que l'on a atteint le maximum
				
					for(int i = n+1;i<=tab.length-1;i++){
						tab[i]=0;
						//quand on incrémente une case il faut faire un reset sur toutes celles qui suivent
					}
					
					break;
				}
			n--;
			}
		return tab;
		}
	
	public static double score(String g, String t){
		//renvoie le produit scalaire entre les deux strings (caractère à caractère)
		
		double s = 0;
		
		for(int i = 0; i<g.length();i++){
			s+=Blosum50.getScore(t.charAt(i), g.charAt(i));
		}
		return s;
	}
	
	public static char[] alphabet(){
		//juste une méthode qui permet de récupérer l'alphabet
		
		char[] alphabet = new char[] {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		return alphabet;
	}

}


