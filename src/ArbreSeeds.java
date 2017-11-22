import java.util.ArrayList;
import java.util.List;
//cette classe permet de créer des arbres de mots
//on peut ainsi chercher facilement des mots en progressant à travers les branches
//le fonctionnement est simple: un arbre peut contenir autant d'enfants qu'il y a de lettres de l'alphabet
//ses enfants sont des arbres aussi
//chaque arbre est aussi muni d'un contenu:

	//contenu qui correspond au caractère de l'alphabet qu'il représente
	
	//provenance qui est une variable qui est utile par la suite
	//celle-ci permet de mémoriser à partir de quellle mot on a généré la graine


//find : attention problème ici !!! Il est possible qu'une graine provienne de plusieurs mots ou bien du même mot dans
//differents endroits de g. Pourtant dans la Task8, on ne va pas tous les chercher...

    class ArbreSeeds 
    {
      List<Integer> provenance = new ArrayList<Integer>();
      char contenu;
      List<ArbreSeeds> fils = new ArrayList<ArbreSeeds>();
      
      ArbreSeeds(char c){
    	  contenu = c;
      }
      ArbreSeeds(char c, Integer prov){
    	  contenu = c;
    	  provenance.add(prov);
      }
    	   
      ArbreSeeds(char c, ArbreSeeds bb){
    	  contenu = c;
    	  fils.add(bb);
      }
      
      void add(ArbreSeeds B){
    	  fils.add(B);
      }
      
      void add(String s){
    	  //méthode essentielle permettant de rajouter un mot à l'arbre
    	  //on parcourt l'arbre tant que les premières lettres existent déjà
    	  //si elles n'y sont pas, on les créé
    	  
    	  if(s.isEmpty()==false){
    		  char c = s.charAt(0);
    		  boolean indicateur = true;
    		  if (fils.isEmpty()==false){
    			  for(ArbreSeeds B : fils){
    				  if(B.contenu == c){
    					  indicateur = false;
    					  B.add(s.substring(1, s.length()));
    					  //dans le cas ou il existe deja une branche correspondant,
    					  //on rappelle la fonction sur la suite de la chaine de caractère
    					  
    					  break;
    			  }
    		  }
    		  }
    		  if(indicateur){
    			  //dans le cas ou il n'y avait pas la branche suivante, on la créé
    			  
    			  add(new ArbreSeeds(c));
    			  add(s);
    		  
    		  }
    	  
    	  }
      }
      
      void add(String s, Integer prov){
    	  //la même chose mais en mémorisant la provenance
    	  
    	  if(s.isEmpty()==false){
    		  char c = s.charAt(0);
    		  boolean indicateur = true;
    		  if (fils.isEmpty()==false){
    			  for(ArbreSeeds B : fils){
    				  if(B.contenu == c){
    					  indicateur = false;
    					  B.add(s.substring(1, s.length()),prov);
    					  break;
    			  }
    		  }
    		  }
    		  if(indicateur){
    			  add(new ArbreSeeds(c,prov));
    			  add(s,prov);
    		  
    		  }
    	  
    	  }
      }
      
      
      
      public void print(){
    	  List<String> answer = to_list();
    	  for(String s : answer){
    		  System.out.println("string : "+s);
    		  //ca marche bien même si c'est vide
    	  }
      }
      public List<String> to_list(){
    	  //une méthode qui permet de passer sous forme de liste
    	  //on s'aide de la fonction auxiliaire ci-dessous
    	  
    	  List<String> answer = new ArrayList<String>();
    	  to_listAux("", answer);
    	  return answer;
      }
      

      
      public void to_listAux(String s, List<String> answer){
    	  //la fonction auxiliaire
    	  
    	  if(fils.isEmpty()){
    		  answer.add(s);
    		  //dans le cas de l'absence d'enfants, on est sur une feuille donc on retient ce mot
    	  }
    	  else{
    		  for(ArbreSeeds B : fils){
    			  //sinon on rappelle la fonction sur les enfants
    			  //on prend garde de retenir "d'ou l'on vient" à l'aide du string en argument
    			  
    			  B.to_listAux(s+B.contenu,answer);
    		  }
    	  }
      }
      
      public int size(){
    	  return to_list().size();
      }
      
      public boolean contains(String s){
    	  //cette fonction va chercher si le String s est dans l'arbre
    	  
    	  	if(s.isEmpty()){return true;}
    	  	else{
    	  		for(ArbreSeeds B : fils){
    	  			if(B.contenu==s.charAt(0)){
    	  				return B.contains(s.substring(1,s.length()));
    	  				//dans la cas on on trouve le caractère suivant, on rappelle la fonction sur le reste du mot
    	  				
    	  				}
    	  			}
    	  		return false;
    	  		}
}
      public List<Integer> provenance(String s){
    	  //cette fonction va chercher la provenance de s
    	  //cette méthode fonctionne exactement comme la méthode ci dessus
    	  
    	  	if(s.isEmpty()){return provenance;}
    	  	else{
    	  		for(ArbreSeeds B : fils){
    	  			if(B.contenu==s.charAt(0)){
    	  				return B.provenance(s.substring(1,s.length()));
    	  				}
    	  			}
    	  		System.out.println("string introuvable");
    	  		return new ArrayList<Integer>();
    	  		}
}
      
    }