import java.util.ArrayList;
import java.util.List;
//cette classe permet de créer des arbres de mots
//on peut ainsi chercher facilement des mots en progressant à travers les branches

//classe bien testée, normalement sans erreurs
    class ArbreSeeds 
    {
      Integer provenance;
      char contenu;
      List<ArbreSeeds> fils = new ArrayList<ArbreSeeds>();
      
      ArbreSeeds(char c){
    	  contenu = c;
      }
      ArbreSeeds(char c, Integer prov){
    	  contenu = c;
    	  provenance = prov;
      }
    	   
      ArbreSeeds(char c, ArbreSeeds bb){
    	  contenu = c;
    	  fils.add(bb);
      }
      void add(ArbreSeeds B){
    	  fils.add(B);
      }
      
      void add(String s){
    	  if(s.isEmpty()==false){
    		  char c = s.charAt(0);
    		  boolean indicateur = true;
    		  if (fils.isEmpty()==false){
    			  for(ArbreSeeds B : fils){
    				  if(B.contenu == c){
    					  indicateur = false;
    					  B.add(s.substring(1, s.length()));//dans le cas ou il existe deja une branche correspondant, on rappelle la fonction sur la suite
    					  									//de la chaine de caractère
    					  break;
    			  }
    		  }
    		  }
    		  if(indicateur){//dans le cas ou il n'y avait pas la branche suivante, on la créé
    			  add(new ArbreSeeds(c));
    			  add(s);
    		  
    		  }
    	  
    	  }
      }
      
      void add(String s, Integer prov){
    	  if(s.isEmpty()==false){
    		  char c = s.charAt(0);
    		  boolean indicateur = true;
    		  if (fils.isEmpty()==false){
    			  for(ArbreSeeds B : fils){
    				  if(B.contenu == c){
    					  indicateur = false;
    					  B.add(s.substring(1, s.length()),prov);//dans le cas ou il existe deja une branche correspondant, on rappelle la fonction sur la suite
    					  									//de la chaine de caractère
    					  break;
    			  }
    		  }
    		  }
    		  if(indicateur){//dans le cas ou il n'y avait pas la branche suivante, on la créé
    			  add(new ArbreSeeds(c,prov));
    			  add(s,prov);
    		  
    		  }
    	  
    	  }
      }
      
      
      
      public void print(){
    	  List<String> answer = to_list();
    	  for(String s : answer){
    		  System.out.println("string : "+s);//ca marche bien même si c'est vide
    	  }
      }
      public List<String> to_list(){//tout ceci fonctionne très bien dans les différents tests
    	  List<String> answer = new ArrayList<String>();
    	  to_listAux("", answer);
    	  return answer;
      }
      
      public int size(){
    	  return to_list().size();
      }
      
      public void to_listAux(String s, List<String> answer){
    	  if(fils.isEmpty()){
    		  answer.add(s);
    	  }
    	  else{
    		  for(ArbreSeeds B : fils){
    			  B.to_listAux(s+B.contenu,answer);
    		  }
    	  }
      }
      
      public boolean contains(String s){//cette fonction va chercher si le String s est dans l'arbre
    	  //deja testé, ca marche bien
    	  	if(s.isEmpty()){return true;}
    	  	else{
    	  		for(ArbreSeeds B : fils){
    	  			if(B.contenu==s.charAt(0)){
    	  				return B.contains(s.substring(1,s.length()));
    	  				}
    	  			}
    	  		return false;
    	  		}
}
      public Integer provenance(String s){//cette fonction va chercher la provenance de s
    	  	if(s.isEmpty()){return provenance;}
    	  	else{
    	  		for(ArbreSeeds B : fils){
    	  			if(B.contenu==s.charAt(0)){
    	  				return B.provenance(s.substring(1,s.length()));
    	  				}
    	  			}
    	  		System.out.println("string introuvable");
    	  		return -1;
    	  		}
}
      
    }