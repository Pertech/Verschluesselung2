package key;

import java.util.Random;

public class GenerateKey {
	
	public String[] getRandomKey(){
		
		String str1;
		
		str1  = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzäöüÄÖÜ0123456789 .,:;_%+-$?!'\"";
				
		String[] key = new String[11];
		Random rand = new Random();
		
		for (int i = 1; i < 11; i++) {
			char[] keyline = new char[str1.length()];
			for (int j = 0; j < str1.length(); j++) {
				int pos = 0;
				do{
					pos = rand.nextInt(str1.length());
				}
				while(keyline[pos] != '\u0000');
				keyline[pos] = str1.charAt(j);
			}
			key[i] = new String(keyline);
		}
		
		key[0] = str1;
		
		return key;
		
	}
	
}