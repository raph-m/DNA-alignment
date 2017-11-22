import java.util.ArrayList;
import java.util.List;

//un autre arbre qui va nous permettre de générer tous les mots de k lettres et d'évaluer leurs scores par rapport à un chaine de caractères
//on économise donc du temps de calcul à l'aide de la structure d'abre et on élimine au fur et à mesure les branches mortes.
//c'est à dire qu'on ne continue pas à explorer une branche si on sait que tous ses descendants ne respecteront pas le 
//critère

    class Arbre_Calcul {
      char contenu;
      List<Arbre_Calcul> fils = new ArrayList<Arbre_Calcul>();
      float score = 0;
      int depth;
      //nous permettra de nous repérer dans l'abre
      
      String s;
      double th;
      double[] best_possible;
      //il est plus simple d'avoir ces informations en mémoire
      
      Arbre_Calcul(char c){
    	  contenu = c;
    	  depth = -1;
      }
      
      Arbre_Calcul(char c, int d){
    	  contenu = c;
    	  depth = d;
      }
    	   
      Arbre_Calcul(char c, Arbre_Calcul bb){
    	  contenu = c;
    	  fils.add(bb);
      }
      /* A R N D C Q E G H I L K M F P S T W Y V */
      
      Arbre_Calcul(String f, double tha){
    	  score = 0;
    	  s = f;
    	  th = tha;
    	  depth = -1;
    	  int k = f.length();
    	  best_possible = new double[k];
    	  for(int i=0; i<k;i++){
    		  best_possible[i]=Courant.score(s.substring(i,k),s.substring(i,k));
    	  }
    	  
    	  for(int i = 0; i<k;i++){
    		  ajouter_etage(i-1);
    	  }
    	  
      }
      
      public void ajouter_etage(int d){
    	  //cette fonction permet d'ajouter un cran en profondeur à l'arbre
    	  //l'arbre ne se développe que la ou il "y a de l'espoir"
    	  
    	  if(depth == d ){
    		  if(score+best_possible[depth+1]>=th*best_possible[0]){
    			  //on vérifie que ce n'est pas une branche morte
    			  //on ajoute alors une nouvelle profondeur à cette branche
    			  
    			  char[] alphabet = Courant.alphabet();
    			  
    			  for(int i = 0; i<alphabet.length;i++){
    				  add(alphabet[i]);
    			  }
    		  }
    	  }
    	  else{
    		  if(depth<d){
    			  //sinon on continue de parcourir les branches jusqu'a arriver à la bonne profondeur
    			  
    		  for(Arbre_Calcul B : fils){
    			  B.ajouter_etage(d);
    			  //on rappelle la fonction sur les enfants
    			  
    		  }
    		  }
    	  }
      }
      
      public void add(char c){
    	  add(new Arbre_Calcul(c));
      }
      
      public void add(Arbre_Calcul B){
    	  B.depth = depth + 1;
    	  B.score = score + Blosum50.getScore(B.contenu, s.charAt(B.depth));
    	  //on calcule alors la profondeur de B et son score à partie de son père
    	  
    	  B.s=s;
    	  B.best_possible = best_possible;
    	  B.th = th;
    	  //on recopie ces valeurs
    	  
    	  fils.add(B);
      }
      
      
      public void add(String f){
    	  if(f.isEmpty()==false){
    		  char c = f.charAt(0);
    		  boolean indicateur = true;
    		  if (fils.isEmpty()==false){
    			  for(Arbre_Calcul B : fils){
    			  if(B.contenu == c){
    				  indicateur = false;
    				  B.add(f.substring(1, f.length()));
    				  break;
    			  }
    		  }
    		  }
    		  if(indicateur){
    			  add(new Arbre_Calcul(c));
    			  add(f);
    		  
    		  }
    	  
    	  }
      }
      
      
      
      public List<String> to_list(){
    	  //cette méthode récupère tous les mots de l'arbre de taille suffisante et répondant au critère de score
    	  
    	  List<String> answer = new ArrayList<String>();
    	  to_listAux("", answer);
    	  
    	  return answer;
    	  
      }
      
      public void to_listAux(String f, List<String> answer){
    	  if(f.length() == s.length()){
    		  if(score>=best_possible[0]*th){
    			  answer.add(f);
    		  }
    	  }
    	  else{

    		  for(Arbre_Calcul B : fils){
    			  B.to_listAux(f+B.contenu,answer);
    		  }
    	  }
      }
      
      public float score(String s){
    	  //cette fonction va chercher le score du string s
    	  
    	  if(s.isEmpty()){return score;}
    	  else{
    		  for(Arbre_Calcul B : fils){
    			  if(B.contenu==s.charAt(0)){
    				  return B.score(s.substring(1,s.length()));
    			  }
    		  }
    		  System.out.println("le String "+s+" n'est pas dans l'arbre");
    		  return -100000000;
    			  }
    		  }
      }
      
