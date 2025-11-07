import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String in = br.readLine();
        String exploit = br.readLine();
        int exploitSize = exploit.length();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < in.length(); i++) {
            result.append(in.charAt(i));

            if(result.length() >= exploitSize) {
                if(result.charAt(result.length()-1) == exploit.charAt(exploitSize-1)) {
                    boolean isExplosive = true;

                    for (int j = 0; j < exploitSize; j++) {
                        if(result.charAt(result.length()-exploitSize+j) != exploit.charAt(j)) {
                            isExplosive = false;
                            break;
                        }
                    }

                    if(isExplosive) {
                        result.delete(result.length()-exploitSize, result.length());
                    }
                }
            }
        }

        if(result.length() == 0) {
            System.out.println("FRULA");
        } else {
            System.out.println(result.toString());
        }
    }
}
