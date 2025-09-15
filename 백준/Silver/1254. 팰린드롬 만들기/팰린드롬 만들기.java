import java.util.*;
import java.io.*;

public class Main {

	public static boolean isPalindrome(String str) {
		int start = 0, end = str.length()-1;
		
		while(start <= end) {
			if(str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String inputString = br.readLine();
		int ans = inputString.length();
		for(int i = 0; i < inputString.length(); i++) {
			if(isPalindrome(inputString.substring(i))) {
				break;
			}
			ans++;
		}
		
		System.out.println(ans);
	}

}
