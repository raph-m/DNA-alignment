import java.util.Arrays;

public class camboui {
	
	public static void main(String[] args) {
	
	int[] tab = new int[5];
	for(int i = 0; i<=4; i++){
		tab[i]=0;
	}
	for(int i =0; i<=1000;i++){
		System.out.println(Arrays.toString(tab));
		tab=Task7bis.plus_one(tab);
		
	}
}
}
