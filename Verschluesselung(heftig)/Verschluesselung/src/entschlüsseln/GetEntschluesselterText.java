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
    		double test2 = Math.pow(Double.valueOf(secNum), d) % N;
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
        entschluesselterText = spezialEntschluesselung(entschluesselterText, secretNum);
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

    private String spezialEntschluesselung(String text, String geheimN){
    	boolean add = false;
    	int addToI = 0;
    	int tl = 0;
    	int sn1 = 1;//Integer.valueOf(geheimN.substring(0, 1));
    	int sn2 = 0;
    	for (int i = 0; i < geheimN.length(); i++) {
    		sn2 += Integer.valueOf(geheimN.substring(i, i+1));
    		i += 2;
		}
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
			int i1 = 0;
			int i2 = 0;
			if(add && i == 0){
				i1 = text.codePointAt(i);
				int x = (text.codePointAt(i) - sn2) / sn1;
				unicodeCodeP[i] = x;
			} else{
				i1 = text.codePointAt(i) * sn2;
				i2 = text.codePointAt(text.length() - i - 1 + addToI) * sn1;
				int y = 0;
				int x = 0;
				if (sn1 > sn2){
					y = (i2 - i1) / ((sn1 * sn1) - (sn2 * sn2));
					if(i2 > i1){
						x = i2 / sn1 - sn1 * y;
					}
					else{
						x = i2 / sn1 - sn1 * y;
					}
				}
				else{
					y = (i1 - i2) / ((sn2 * sn2) - (sn1 * sn1));
					if(i2 > i1){
						x = i1 / sn2 - sn2 * y;
					}
					else{
						x = i1 / sn2 - sn2 * y;
					}
				}
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
