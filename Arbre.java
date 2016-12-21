import java.util.ArrayList;
import java.util.List;

    class Arbre  
    {
      char contenu;
      List<Arbre> fils = new ArrayList<Arbre>();
      
      
      public Arbre[] concat(Arbre[] a, Arbre[] b) {
    	   int aLen = a.length;
    	   int bLen = b.length;
    	   Arbre[] c= new Arbre[aLen+bLen];
    	   System.arraycopy(a, 0, c, 0, aLen);
    	   System.arraycopy(b, 0, c, aLen, bLen);
    	   return c;
      }
      
      Arbre(char c){
    	  contenu = c;
      }
    	   
      Arbre(char c, Arbre bb){
    	  contenu = c;
    	  fils.add(bb);
      }
      public void add(Arbre B){
    	  fils.add(B);
      }
      public void add(String s){
    	  if(s.isEmpty()==false){
        	  System.out.println("String s  "+s);
    		  char c = s.charAt(0);
    		  boolean indicateur = true;
    		  if (fils != null){
    			  for(Arbre B : fils){
    			  if(B.contenu == c){
    				  indicateur = false;
    				  B.add(s.substring(1, s.length()));
    				  break;
    			  }
    		  }
    		  }
    		  if(indicateur){
    			  add(new Arbre(c));
    			  add(s);
    		  
    		  }
    	  
    	  }
      }
      
      public void print(){
    	  for(Arbre B : fils){
    		  System.out.println(B.contenu);
    		  B.print();
    	  }
      }
      
    }
