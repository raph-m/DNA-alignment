import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
//		String t = "CGAACCTGTTTCGAAAGGCTCAAGTGGCCTCTATCCTACACGACAGACCACCCAGAACAAAGAGGATGTGGCCAT";
//		String s = "GGCGGCTATATTTAGCACACACAATTTTTAGGTCGCACGATCGGGATGGCGGCGCGCGATCAACAGCCTGCACACTTCTAAGGAAAATAGTCACACTCCC";
		String t = "CGAACACAAAGAGGATGTGGCCAT";
		String s = "GGCGGACAGCCTGCACACTTCTAAGGAAAATAGTCACACTCCC";
		
		
		char[] alphabet = new char[] {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		
		
		//Introduction
		
		System.out.println("Une série de test effectués sur les chaînes de caractères suivantes: ");
		System.out.println("Chaîne t: "+t);
		System.out.println("Chaîne s: "+s);
		System.out.println("");
		
		//Task2:
		System.out.println("****Task2*********************************************************");
		
		
		System.out.println("La plus grande sous-séquence commune entre s et t est de taille "+Task2.lsubsq(s, t));
		System.out.println("");
		
		//Task3:
		System.out.println("****Task3*********************************************************");
		
		
		System.out.println("Voici un visuel d'un alignement correspondant au nombre optimal de caractères communs entre s et t ");
		Task3.display(s, t);
		System.out.println("");
		
		//Task4:
		System.out.println("****Task4*********************************************************");
		
		
		System.out.println("Voici un visuel d'un alignement de s et de t maximisant le score par rapport à la matrice Blosum 50: ");
		Task4.display(s, t);
		System.out.println("");
		
		//Task5:
		System.out.println("****Task5*********************************************************");
		
		
		System.out.println("On tient maintenant compte des gap, c'est à dire que l'on va sanctionner l'ouverture d'une séquence d'insertion ou de délétion.");
		System.out.println("On sanctionne encore plus si deux séquences insertions/délétion s'enchainent.");
		System.out.println("Cela permet de se rapprocher du cas réel ou les tirets ne sont que rarement isolés");
		System.out.println("");
		
		double gap_1 = 10;
		double gap_2 = 1;
		System.out.println("Les paramètres utilisés sont: gap_1 = "+gap_1+" et gap_2 = "+gap_2);
		Task5.display(s, t,gap_1,gap_2);
		System.out.println("");
		
		gap_1=1;
		gap_2=0;
		System.out.println("Les paramètres utilisés sont: gap_1 = "+gap_1+" et gap_2 = "+gap_2);
		Task5.display(s, t,gap_1,gap_2);
		System.out.println("");
		
		gap_1=5;
		gap_2=5;
		System.out.println("Les paramètres utilisés sont: gap_1 = "+gap_1+" et gap_2 = "+gap_2);
		Task5.display(s, t,gap_1,gap_2);
		System.out.println("");
		
		//Task6:
		System.out.println("****Task6*********************************************************");
		
		
		Task6.best_local_score(s,t,gap_1,gap_2);
		
		//Task7:
		System.out.println("****Task7*********************************************************");
		
		
		int k = 6;
		String g = "AACACG";
		double th;
		
		//on fait d'abord une série de test sur la fonction seeds. On s'attarde aussi sur le fait que l'on s'épargne des
		//calculs en coupant des branches prématurément
		th=0.99999;
		ArbreSeeds U = Task7bis.Seeds(g, th, k, alphabet);
		System.out.println("nombre de graines pour th = "+th+" : "+U.size());
		U.print();
		System.out.println("");
		
		th = 0.98;
		U = Task7bis.Seeds(g, th, k, alphabet);
		System.out.println("nombre de graines pour th = "+th+" : "+U.size());
		System.out.println("");
		
		th=0.8;
		U.print();
		U = Task7bis.Seeds(g, th, k, alphabet);
		System.out.println("nombre de graines pour th = "+th+" : "+U.size());
		U.print();
		System.out.println("");
		
		th = 0.2;
		U = Task7bis.Seeds(g, th, k, alphabet);
		System.out.println("nombre de graines pour th = "+th+" : "+U.size());
		System.out.println("");
		
		th=0.9;
		List<Integer> index = Task7bis.index(g, t, k, th, alphabet);
		for(Integer j : index){
			System.out.println("numéro : "+j);
			}
		
		//Task8:
		System.out.println("****Task8**********************************************************");
		double thl = 0.5;
		th=0.5;
		Task8.match(g,t,k,th,thl);

		
	}
}


