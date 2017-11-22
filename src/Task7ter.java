import java.util.ArrayList;
import java.util.List;

/**
 * Created by gabrielmisrachi on 21/12/2016.
 */
public class Task7ter {
    public static List[] Wg(String g, int k){//ca marche bien
        List[] answer = new ArrayList[2];
        answer[0] = new ArrayList<String>();
        answer[1] = new ArrayList<Integer>();
        for(int i = 0; i<=g.length()-k; i++){
            answer[0].add(g.substring(i, i+k));
            answer[1].add(i);
        }
        return answer;
    }
    public static double scalar (String u, String v){//ca marche bien
        //on compute la valeur de th*score(w) pour avoir notre seuil
        double answer = 0;
        for(int i = 0; i<u.length(); i++){
            answer+=Blosum50.getScore(u.charAt(i), v.charAt(i));
        }
        return answer;
    }
    public static List[] seeds_from_t(String g, String t, double th, int k ){
        List[] answer = new ArrayList[3];
        answer[0] = new ArrayList<String>();
        answer[1] = new ArrayList<String>();
        answer[2] = new ArrayList<Integer>();
        List<String> pre_seeds_from_t = Wg( t, k)[0];//on récupère les sous-mots de t
        //System.out.println(pre_seeds_from_t);
        List<Integer> index_from_t = Wg( t, k)[1];//avec leurs index
        //System.out.println(index_from_t);
        List<String> g_substrings = Wg(g, k)[0];// on récupère les sous-mots de g
        //System.out.println(g_substrings);
        int index = 0;
        for(String u : pre_seeds_from_t){//pour chaque sous mot u de t
            for(String v : g_substrings){//pour chaque sous mot v de g
                if(scalar(u,v) >= th*scalar(v,v)){//on vérifié si u est une graine acceptable de v
                    answer[0].add(u);// si c'est le cas on stocke u, v et l'indice de u
                    answer[1].add(v);
                    answer[2].add(index_from_t.get(index));
                }
            }
            index += 1;
        }
        return answer;
    }


}
