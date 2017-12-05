import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Problema42 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int total = 0;
		while(in.hasNext()) {
			HashSet<String> hash = new HashSet<>();
			String line = in.nextLine();
			String[] values = line.split(" ");
			for (int i =0; i< values.length; i++) {
				char[] v = values[i].toCharArray();
				Arrays.sort(v);

				String result = new String(v);
				if(hash.contains(result)) {
					break;
				}hash.add(result);
				if(i == values.length-1) {
					total++;
				}
			}

		}
		System.out.println(total);

	}

}
