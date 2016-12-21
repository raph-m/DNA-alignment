import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main_Interactif {
	private static final String FILENAME = "/Users/gabrielmisrachi/IdeaProjects/Alignment_Project/src/test320.txt";

	public static void main(String[] args) {
//		String t = "CGAACCTGTTTCGAAAGGCTCAAGTGGCCTCTATCCTACACGACAGACCACCCAGAACAAAGAGGATGTGGCCAT";
//		String s = "GGCGGCTATATTTAGCACACACAATTTTTAGGTCGCACGATCGGGATGGCGGCGCGCGATCAACAGCCTGCACACTTCTAAGGAAAATAGTCACACTCCC";
		String t = "CGAACACAAAGAGGATGTGGCCAT";
		String s = "GGCGGACAGCCTGCACACTTCTAAGGAAAATAGTCACACTCCC";
		/*String s = "non initialisée";
		String t = "non initialisée";
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
			s = br.readLine();
			t = br.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}*/
		
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

		Scanner reader = new Scanner(System.in);  // Va lire les paramètres rentrés par l'utilisateur
		System.out.println("Enter gap_penalty: ");
		double gap_1 = reader.nextDouble();
		Scanner readergap2 = new Scanner(System.in);  // Va lire les paramètres rentrés par l'utilisateur
		System.out.println("Enter increasing_gap_penalty: ");
		double gap_2 = reader.nextDouble();

		System.out.println("Les paramètres utilisés sont: gap_penalty = "+gap_1+" et increasing_gap_penalty = "+gap_2);
		Task5.display(s, t,gap_1,gap_2);
		System.out.println("");
		
		int gap_1bis=1;
		int gap_2bis=0;
		System.out.println("Les paramètres utilisés sont: gap_penalty = "+gap_1bis+" et increasing_gap_penalty = "+gap_2bis);
		Task5.display(s, t,gap_1bis,gap_2bis);
		System.out.println("");
		
		int gap_1ter=5;
		int gap_2ter=5;
		System.out.println("Les paramètres utilisés sont: gap_penalty = "+gap_1ter+" et increasing_gap_penalty = "+gap_2ter);
		Task5.display(s, t,gap_1ter,gap_2ter);
		System.out.println("");
		
		//Task6:
		System.out.println("****Task6*********************************************************");
		
		
		Task6.best_local_score(s,t,gap_1,gap_2);
		
		//Task7bis:
		System.out.println("****Task7bis*********************************************************");
		//Scanner readerk = new Scanner(System.in);  // Va lire les paramètres rentrés par l'utilisateur
		System.out.println("Enter k: ");
		int k = reader.nextInt();

		String g = "AACACG";

		//Scanner readerth = new Scanner(System.in);  // Va lire les paramètres rentrés par l'utilisateur
		System.out.println("Enter th: ");
		double th = reader.nextDouble();
		
		
		//on fait d'abord une série de test sur la fonction seeds. On s'attarde aussi sur le fait que l'on s'épargne des
		//calculs en coupant des branches prématurément

		ArbreSeeds U = Task7bis.Seeds(g, th, k, alphabet);
		System.out.println("nombre de graines pour th = "+th+" : "+U.size());
		U.print();
		System.out.println("");
		
		
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

