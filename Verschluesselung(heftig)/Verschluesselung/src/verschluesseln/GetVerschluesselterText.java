package verschluesseln;

import java.io.UnsupportedEncodingException;
import java.util.List;

import Zahlensystem.GetZahlensystem;
import key.GenerateKey;
import key.GenerateNumber;

public class GetVerschluesselterText {

    public String verschluesseln(String text, String secretNum, String[] key){


    	GetZahlensystem gz = new GetZahlensystem();
    	//String[] secNum = secretNum.split("-");
    	List<Character> lc = gz.get32erSystem();
    	GenerateKey genKey = new GenerateKey();
        GenerateNumber genNumb = new GenerateNumber();
        if(key == null){
        	key = genKey.getRandomKey();
        }
        if(secretNum == null){
        	secretNum = genNumb.getRandomNumber();
        }
        else{
        	String num = "";
        	for (int i = 0; i < secretNum.length(); i++) {
        		String secNum = String.valueOf(lc.indexOf(secretNum.charAt(i)));//secretNum.substring(i, i*2 + 2);
        		double test1 = Math.pow(Double.valueOf(secNum), 7);
        		double test2 = test1 % 33;
    			num += String.valueOf(test2);
    			num = num.substring(0, num.length() - 2);
    		}
        	secretNum = num;
        }


        int textlength = text.length();
        int textlength2 = text.length();
        int numlength = secretNum.length();

        String verschluesselterText = "";

        int pos = -1;
        int numpos = 0;

        for (int i = 0; i < textlength2; i++) {

            if (numpos > numlength - 1){
                numpos = 0;
            }
            char c = secretNum.charAt(numpos);
            String test = String.valueOf(c);
            pos += Integer.valueOf(test);
            while (pos >= textlength){
                pos -= textlength;
            }

            verschluesselterText += text.charAt(pos);
            text = changeCharInPosition(pos,'/',text);
            text = text.replaceAll("/","");
            textlength = text.length();
            
            pos--;
            
            numpos++;
        }
        String[] newKey = new String[secretNum.length()];
        newKey[0] = key[0];
        for (int i = 1; i < secretNum.length(); i++) {
        	char c = secretNum.charAt(i - 1);
            String test = String.valueOf(c);
            pos = Integer.valueOf(test);
        	newKey[i] = key[pos];
        }
        verschluesselterText = verschluesselnWithKey(verschluesselterText, newKey);
        GetZahlensystem zs = new GetZahlensystem();
        java.util.List<Character> zl = zs.get32erSystem();
    	char c = secretNum.charAt(0);
        String test = String.valueOf(c);
        String secNum = String.valueOf(Math.pow(Double.valueOf(test), 3) % 33);
        String test1 = secNum.substring(0, secNum.length() - 2);
        int index = Integer.valueOf(test1);
        secNum = String.valueOf(zl.get(index));
        
        for (int i = 1; i < secretNum.length(); i++) {
        	c = secretNum.charAt(i);
            test = String.valueOf(c);
            String zahlVer  = String.valueOf(Math.pow(Double.valueOf(test), 3) % 33);
            test1 = zahlVer.substring(0, zahlVer.length() - 2);
            index = Integer.valueOf(test1);
            secNum += String.valueOf(zl.get(index));
		}
        verschluesselterText = spezialVerschluesselung(verschluesselterText, secretNum);
        secretNum = secNum;
        String keyList = key[0] + "\n" + key[1] + "\n" + key[2] + "\n" + key[3] + "\n" + key[4] + "\n" + key[5] + "\n" + key[6] + "\n" + key[7] + "\n" + key[8] + "\n" + key[9] + "\n";
        return verschluesselterText + "\n" + "\n" + "Start Key" + "\n" + keyList + secretNum;
    }
    
    private String spezialVerschluesselung(String text, String geheimN){
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
    	String verText = "";
    	for (int i = 0; i < tl; i++) {
    		int i1 = 0;
			int i2 = 0;
			if(add && i == 0){
				i1 = text.codePointAt(i) * sn1 + sn2;
				//i2 = text.codePointAt(i) + 1 * sn2;
				unicodeCodeP[i] = i1;
			} else{
				i1 = text.codePointAt(i) * sn1 + text.codePointAt(text.length() - i - 1 + addToI) * sn2;
				i2 = text.codePointAt(i) * sn2 + text.codePointAt(text.length() - i - 1 + addToI) * sn1;
				unicodeCodeP[i] = i1;
				unicodeCodeP[text.length() - i - 1 + addToI] = i2;
			}
		}
		for (int i = 0; i < unicodeCodeP.length; i++) {
			char[] c = Character.toChars(unicodeCodeP[i]);
			verText += new String(c);
		}
		return verText;
    }

    private String verschluesselnWithKey(String text, String[] key){
        int pos = 1;
        int textpos = 0;
        char ch;
        for (int i = 0; i < text.length(); i++) {

            if(pos > key.length - 1){
                pos = 1;
            }
            int i2 = key[pos].indexOf(text.charAt(textpos));
            if (i2 != -1){ 
            	ch = key[0].charAt(i2);
            }else{
            	ch = text.charAt(textpos);
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
