import java.io.BufferedReader;
import java.util.Date;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main_Interactif {
	private static final String FILENAME = "/Users/gabrielmisrachi/IdeaProjects/Alignment_Project/src/sequences.txt";

	
	public static void main(String[] args) {
        String dna1 = "";
	    String dna2 = "";
		String aa1 = "";
		String aa2 = "";
		String aa3 = "";
		final String FILENAME = System.getProperty("user.dir") + "/sequences.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
		    dna1 = br.readLine();
		    dna2 = br.readLine();
			aa1 = br.readLine();
			aa2 = br.readLine();
			aa3 = br.readLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		
		String reponse;
		long startTime7;
		long startTime7bis;
		long endTime7;
		long endTime7bis;
		long totalTime7 = 0;
		long totalTime7bis = 0;
		double gap_1;
		double gap_2;
		int k;
		double th;
		double thl;
		
		
		//Introduction
		
		System.out.println("");
		System.out.println("Voici une série de tests effectués sur les séquences ADN suivantes: ");
		System.out.println("ADN 1 : "+dna1);
		System.out.println("ADN 2 : "+dna2);
		System.out.println("");
		
		//Task2:
		System.out.println("****Task2*********************************************************");
		System.out.println("Voulez-vous lancer la Task 2 qui calcule la taille de la plus longue sous-séquence commune entre deux séquences ?");
		Scanner reader = new Scanner(System.in);
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o') {
			System.out.println("La plus grande sous-séquence commune entre s et t est de taille " + Task2.lsubsq(dna1, dna2));
			System.out.println("");
		}
		
		//Task3:
		System.out.println("****Task3*********************************************************");
		System.out.println("Voulez-vous lancer la Task 3 qui retourne un alignement optimal entre deux séquences?");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o') {
			System.out.println("Voici un visuel d'un alignement correspondant au nombre optimal de caractères communs entre s et t ");
			Task3.display(dna1, dna2);
			System.out.println("");
		}
		
		//Task4:
		System.out.println("****Task4*********************************************************");
		System.out.println("Voulez-vous lancer la Task 4 qui retourne un alignement optimal selon Blosum 50 ?");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o') {
			System.out.println("Voici un visuel d'un alignement de s et de t maximisant le score par rapport à la matrice Blosum 50: ");
			Task4.display(dna1, dna2);
			System.out.println("");
		}

        System.out.println("Voici une série de tests effectués sur les séquences d'acides aminés suivantes: ");
        System.out.println("Acides Aminés 1 : "+aa1);
        System.out.println("Acides Aminés 2 : "+aa2);
        System.out.println("Acides Aminés 3 : "+aa3);
        System.out.println("");
        //Task5:
		System.out.println("****Task5*********************************************************");
		System.out.println("Voulez-vous lancer la Task 5 qui retourne un alignement optimal entre Acides Aminés 1 et 2 selon Blosum 50 avec pénalités ?");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o') {
			System.out.println("On tient maintenant compte des gap, c'est à dire que l'on va sanctionner l'ouverture d'une séquence d'insertion ou de délétion.");
			System.out.println("On sanctionne encore plus si deux séquences insertions/délétion s'enchainent.");
			System.out.println("Cela permet de se rapprocher du cas réel ou les tirets ne sont que rarement isolés");
			System.out.println("");
			System.out.println("Nous vous proposons de tester notre algorithme avec trois paramétrages différents");
			System.out.println("");

			System.out.println("Premier essai :");
			System.out.println("");
			System.out.println("Entrez gap_penalty: ");
			gap_1 = reader.nextDouble();
			System.out.println("Entrez increasing_gap_penalty: ");
			gap_2 = reader.nextDouble();
			System.out.println("");
			System.out.println("Les paramètres utilisés sont: gap_penalty = " + gap_1 + " et increasing_gap_penalty = " + gap_2);
			Task5.display(aa1, aa2, gap_1, gap_2);
			System.out.println("");


			System.out.println("Deuxième essai :");  // Va lire les paramètres rentrés par l'utilisateur
			System.out.println("");
			System.out.println("Entrez gap_penalty: ");
			gap_1 = reader.nextDouble();
			System.out.println("Enter increasing_gap_penalty: ");
			gap_2 = reader.nextDouble();
			System.out.println("");
			System.out.println("Les paramètres utilisés sont: gap_penalty = " + gap_1 + " et increasing_gap_penalty = " + gap_2);
			Task5.display(aa1, aa2, gap_1, gap_2);
			System.out.println("");


			System.out.println("Troisième essai :");  // Va lire les paramètres rentrés par l'utilisateur
			System.out.println("");
			System.out.println("Entrez gap_penalty: ");
			gap_1 = reader.nextDouble();
			System.out.println("Entrez increasing_gap_penalty: ");
			gap_2 = reader.nextDouble();
			reader.nextLine();
			System.out.println("");
			System.out.println("Les paramètres utilisés sont: gap_penalty = " + gap_1 + " et increasing_gap_penalty = " + gap_2);
			Task5.display(aa1, aa2, gap_1, gap_2);
			System.out.println("");
		}
		
		
		
		
		//Task6:
		System.out.println("****Task6*********************************************************");
		System.out.println("Voulez-vous lancer la Task 6 qui retourne un alignement localement optimal des mêmes séquences selon Blosum 50 avec pénalités ? Celle-ci peut prendre un certain temps pour de grandes chaînes de caractères.");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o'){
			System.out.println("Entrez gap_penalty: ");
			gap_1 = reader.nextDouble();
			System.out.println("Entrez increasing_gap_penalty: ");
			gap_2 = reader.nextDouble();
			System.out.println("");
			reader.nextLine();
			Task6.best_local_score(aa1, aa2, gap_1, gap_2);
		}
		else{System.out.println("Passons à la tâche suivante.");}
		
		System.out.println("");
		
		
		//Task7bis:
		System.out.println("****Task7bis*********************************************************");
		System.out.println("Voulez-vous lancer la Task 7bis qui retourne les perfects matchs entre les seeds d'Acides Aminés 3 et les sous-séquences d'Acides Aminés 2 ? Il s'agit d'une méthode naïve qui peut prendre un certain temps pour de longues séquences.");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o'){
		
			System.out.println("Entrez k: (la taille de k démultiplie le temps de calcul, nous conseillons de garder k<6).");
			k = reader.nextInt();


			System.out.println("Entrez th: ");
			th = reader.nextDouble();
			reader.nextLine();
			System.out.println("");

			startTime7bis = System.currentTimeMillis();
			
			List<Integer> index = Task7bis.index(aa3, aa2, k, th);
			
			System.out.println("");
			System.out.println("et on obtient un match pour les index suivants: ");
			if (index.size() == 0)
                System.out.println("pas de matchs trouvés");
            for(Integer j : index){
				System.out.println("index : "+j);
			}

			endTime7bis = System.currentTimeMillis();
			totalTime7bis = endTime7bis - startTime7bis;
			System.out.println("Le calcul a mis "+totalTime7bis/1000.0+" secondes à s'executer");

		}
		System.out.println("");

		
		
		//Task7:
		System.out.println("****Task7*********************************************************");
		System.out.println("Voulez-vous lancer la même tâche mais cette fois optimisée à l'aide d'arbres? Cette méthode est nettement plus rapide.");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o'){
		
			System.out.println("Entrez k: ");
			k = reader.nextInt();


			System.out.println("Entrez th: ");
			th = reader.nextDouble();
			reader.nextLine();
			System.out.println("");
			
			startTime7 = System.currentTimeMillis();
			
			System.out.println("voici les indices de perfect match dans t: ");
			System.out.println("");
			List<Integer> index = Task7.index(aa3, aa2, k, th);
			for(Integer j : index){
				System.out.println("index : "+j);
				}
			if(index.isEmpty()){
				System.out.println("pas de matchs trouvés");
			}
			System.out.println("");
			endTime7 = System.currentTimeMillis();
			totalTime7 = endTime7 - startTime7;
			System.out.println("Le calcul a mis "+totalTime7/1000.0+" secondes à s'executer");
			
		}
		System.out.println("");

		if(totalTime7!=0&&totalTime7bis!=0) {
            System.out.println("le rapport entre les deux temps de calcul est alors de:");
            System.out.println((float) totalTime7 / (float) totalTime7bis);
            System.out.println("");
        }
		//Task8:
		System.out.println("****Task8**********************************************************");
		System.out.println("Voulez-vous lancer la Task 8 qui retourne les même perfect match que Task 7 et essaye de les étendre selon un critère de score seuil?");
		reponse = reader.nextLine();
		if(reponse.charAt(0) == 'y' || reponse.charAt(0) == 'o') {
			System.out.println("Enter k: ");
			k = reader.nextInt();
			System.out.println("Enter th: ");
			th = reader.nextDouble();
			System.out.println("Enter thl: ");
			thl = reader.nextDouble();
			System.out.println("");

			Task8.match(aa3, aa2, k, th, thl);
		}
		reader.close();

		
	}
	}

