import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException{
        String[] keyboard = new String[4];
        for (int i = 0; i < 4; i++) {
            keyboard[i] = br.readLine();
        }
        
        String target = br.readLine();
        Set<Character> targCharacters = new HashSet<>();

        for (Character c : target.toCharArray()) {
            targCharacters.add(c);
        }
        
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 8; j++) {
                boolean ok = true;
                check: for (int r = i-1; r <= i+1; r++) {
                    for (int c = j-1; c <= j+1; c++) {
                        if(!targCharacters.contains(keyboard[r].charAt(c))) {
                            ok = false;
                            break check;
                        }
                    }
                }
                if(ok) {
                    System.out.println(keyboard[i].charAt(j));
                    return;
                }
            }
        }

    }
}
