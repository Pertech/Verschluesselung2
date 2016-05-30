package entschlüsseln;

import java.io.UnsupportedEncodingException;
import java.util.List;

import Zahlensystem.GetZahlensystem;
import entschlüsseln.GetEntschluesselterText;

public class GetEntschluesselterText {

    public String entschluesseln(String text, String secretNum, String[] key){
    	
    	GetZahlensystem gz = new GetZahlensystem();
    	//String[] secNum = secretNum.split("-");
    	List<Character> lc = gz.get32erSystem();
    	double d = 7;
    	double pz1 = 3;
    	double pz2 = 11;
    	double N = pz1 * pz2;
    	String num = "";
    	
    	for (int i = 0; i < secretNum.length(); i++) {
    		String secNum = String.valueOf(lc.indexOf(secretNum.charAt(i)));//secretNum.substring(i, i*2 + 2);
    		double test1 = Math.pow(Double.valueOf(secNum), d);
    		double test2 = test1 % N;
			num += String.valueOf(test2);
			num = num.substring(0, num.length() - 2);
		}
    	secretNum = num;


        int numlength = secretNum.length();

        String entschluesselterText = text;

        int pos = - 1;
        int numpos = 0;
        
        String[] newKey = new String[secretNum.length()];
        newKey[0] = key[0];
        for (int i = 1; i < secretNum.length(); i++) {
        	char c = secretNum.charAt(i - 1);
            String test = String.valueOf(c);
            pos = Integer.valueOf(test);
        	newKey[i] = key[pos];
        }
        entschluesselterText = spezialEntschluesselung(entschluesselterText);
        entschluesselterText = entschluesselnWithKey(entschluesselterText, newKey);

        int textlength = entschluesselterText.length();
        
        char[] entschText = new char[entschluesselterText.length()]; 
        pos = -1;
        for (int i = 0; i < textlength; i++) {

            if (numpos > numlength - 1){
                numpos = 0;
            }
            char c = secretNum.charAt(numpos);
            String test = String.valueOf(c);
            int zus = 0;
            for (int j = 0; j < Integer.valueOf(test) + zus; j++) {
				int actPos = pos + j + 1;
	            while (actPos >= textlength){
	            	actPos -= textlength;
	            }
				if (entschText[actPos] != '\u0000') {
					zus++;
				}
			}
            pos += Integer.valueOf(test);
            pos += zus;
            while (pos >= textlength){
                pos -= textlength;
            }

            entschText[pos] = entschluesselterText.charAt(0);
            entschluesselterText = changeCharInPosition(0,'/',entschluesselterText);
            entschluesselterText = entschluesselterText.replaceAll("/","");
            numpos++;
        }
        
        return new String(entschText);
    }

    private String spezialEntschluesselung(String text){
    	boolean add = false;
    	int addToI = 0;
    	int tl = 0;
    	if(text.length() % 2 != 0){
    		add = true;
    		tl = (text.length() + 1) / 2;
    		addToI = 1;
    	}
    	else{
    		tl = text.length() / 2;
    	}
    	int[] unicodeCodeP = new int[text.length()];
    	String entText = "";
    	for (int i = 0; i < tl; i++) {
			int i1 = text.codePointAt(i);
			int i2 = 0;
			if(add && i == 0){
				int x = (text.codePointAt(i) - 1) / 3;
				//int y = (i2 - i1) / 8;
				//int x = i2 / 3 - 3 * y;
				unicodeCodeP[i] = x;
			} else{
				i2 = text.codePointAt(text.length() - i - 1 + addToI) * 3;
				int y = (i2 - i1) / 8;
				int x = i2 / 3 - 3 * y;
				unicodeCodeP[i] = x;
				unicodeCodeP[text.length() - i - 1 + addToI] = y;
			}
		}
		for (int i = 0; i < unicodeCodeP.length; i++) {
			char[] c = Character.toChars(unicodeCodeP[i]);
			entText += new String(c);
		}
		/*if(add){
			entText = entText.substring(0, entText.length() - 1);
		}*/
		return entText;
    }
    
    private String entschluesselnWithKey(String text, String[] key){
        int pos = 1;
        int textpos = 0;
        char ch;
        for (int i = 0; i < text.length(); i++) {

            if(pos > key.length - 1){
                pos = 1;
            }
            int i2 = key[0].indexOf(text.charAt(textpos));
            if(pos < 0){
            	ch = text.charAt(textpos);
            }else{
            	try{
            		ch = key[pos].charAt(i2);
            	}catch(Exception e){
            		ch = text.charAt(textpos);
            	}
            }
            text = changeCharInPosition(textpos, ch, text);

            pos++;
            textpos++;
        }

        return text;
    }

    private String changeCharInPosition(int position, char ch, String str){
        char[] charArray = str.toCharArray();
        charArray[position] = ch;
        return new String(charArray);
    }
}
