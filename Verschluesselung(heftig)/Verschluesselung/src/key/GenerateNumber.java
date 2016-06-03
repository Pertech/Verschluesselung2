package key;

import java.util.Random;

public class GenerateNumber {

	public String getRandomNumber(){

		Random rand = new Random();
		String res = "";
		int random = rand.nextInt(10);
		for (int i = 0; i < random + 5; i++) {
			res += String.valueOf(rand.nextInt(9) + 1);
		}


		return res;
	}

}
