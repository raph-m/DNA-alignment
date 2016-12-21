import java.util.ArrayList;
import java.util.List;
/* A R N D C Q E G H I L K M F P S T W Y V */
//un autre arbre qui va nous permettre de générer tous les mots de k lettres et d'évaluer leurs scores par rapport à un chaine de caractères
//on économise donc du temps de calcul à l'aide de la structure d'abre et on élimine au fur et à mesure les branches mortes.

//classe à tester
    class Arbre_Calcul 
    {
      char contenu;
      List<Arbre_Calcul> fils = new ArrayList<Arbre_Calcul>();
      float score = 0;
      
      Arbre_Calcul(char c){
    	  contenu = c;
      }
    	   
      Arbre_Calcul(char c, Arbre_Calcul bb){
    	  contenu = c;
    	  fils.add(bb);
      }
      /* A R N D C Q E G H I L K M F P S T W Y V */
      
      Arbre_Calcul(int k){//cette fonction créé l'arbre de tous les mots possibles
    	  			//ca a l'air de bien marcher
    	  contenu = '0';
    	  for(int i = 0; i<k;i++){
    		  ajouter_etage();
    	  }
    	  
      }
      
      public void ajouter_etage(){
    	  if(fils.isEmpty()){
        	  add("A");
        	  add("R");
        	  add("N");
        	  add("D");
        	  add("C");
        	  add("Q");
        	  add("E");
        	  add("G");
        	  add("H");
        	  add("I");
        	  add("L");
        	  add("M");
        	  add("F");
        	  add("P");
        	  add("S");
        	  add("T");
        	  add("W");
        	  add("Y");
        	  add("V");
    	  }
    	  else{
    		  for(Arbre_Calcul B : fils){
    			  B.ajouter_etage();
    		  }
    	  }
      }
      
      public void add(Arbre_Calcul B){
    	  fils.add(B);
      }
      public void add(String s){
    	  if(s.isEmpty()==false){
    		  char c = s.charAt(0);
    		  boolean indicateur = true;
    		  if (fils.isEmpty()==false){
    			  for(Arbre_Calcul B : fils){
    			  if(B.contenu == c){
    				  indicateur = false;
    				  B.add(s.substring(1, s.length()));
    				  break;
    			  }
    		  }
    		  }
    		  if(indicateur){
    			  add(new Arbre_Calcul(c));
    			  add(s);
    		  
    		  }
    	  
    	  }
      }
      
      
      public void clear_scores(){
    	  if(fils.isEmpty()==false){
    		  for (Arbre_Calcul B : fils){
    			  B.score = 0;
    			  B.clear_scores();
    		  }
    	  }
      }
      
      public void compute_scores(String s){//calcule tous les scores relatifs à ce string s
    	  									//pas de soucis dans les tests
    	  clear_scores();
    	  if(fils.isEmpty()==false){
    		  for (Arbre_Calcul B : fils){
    			  B.score = score + Blosum50.getScore(B.contenu, s.charAt(0));
    			  B.compute_scores(s.substring(1,s.length()));
    		  }
    	  }
      }
      
      public void print(){
    	  List<String> answer = to_list();
    	  for(String s : answer){
    		  System.out.println("string : "+s);
    	  }
      }
      public List<String> to_list(){
    	  List<String> answer = new ArrayList<String>();
    	  to_listAux("", answer);
    	  return answer;
    	  
      }
      
      public void to_listAux(String s, List<String> answer){
    	  if(fils.isEmpty()){
    		  answer.add(s);
    	  }
    	  else{
    		  for(Arbre_Calcul B : fils){
    			  B.to_listAux(s+B.contenu,answer);
    		  }
    	  }
      }
      
      public float score(String s){//cette fonction va chercher le score du string s
    	  							//tout ca a l'air de bien marcher
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
      