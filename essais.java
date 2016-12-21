import java.util.ArrayList;
import java.util.List;
/* A R N D C Q E G H I L K M F P S T W Y V */
public class essais {

	public static void main(String[] args) {
//		String t = "CGAACCTGTTTCGAAAGGCTCAAGTGGCCTCTATCCTACACGACAGACCACCCAGAACAAAGAGGATGTGGCCAT";
//		String s = "GGCGGCTATATTTAGCACACACAATTTTTAGGTCGCACGATCGGGATGGCGGCGCGCGATCAACAGCCTGCACACTTCTAAGGAAAATAGTCACACTCCC";
		String t = "CGAACACAAAGAGGATGTGGCCAT";
		String s = "GGCGGACAGCCTGCACACTTCTAAGGAAAATAGTCACACTCCC";
		
		char[] alphabet = new char[] {'A', 'R', 'N', 'D', 'C', 'Q', 'E', 'G', 'H', 'I', 'L', 'K', 'M', 'F', 'P', 'S', 'T', 'W', 'Y', 'V'};
		
		int k = 4;
		String g = "AACACG";
		double th;

		

		th=0.8;
		ArbreSeeds U = Task7bis.Seeds(g, th, k, alphabet);
		System.out.println("nombre de graines pour th = "+th+" : "+U.size());
		U.print();
		System.out.println("");
		
		}
	}


