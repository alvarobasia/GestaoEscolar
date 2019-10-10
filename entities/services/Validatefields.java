package entities.services;

public abstract class Validatefields {

	public static boolean isAllLettes(String string) {
		return string.matches("[A-Z a-z Çç]{" + string.length() + "}");
	}

	public static boolean isCpfValid(String string) {
		if (string.length() == 15)
			string = string.substring(0, string.length() - 1);

		string = string.replaceAll("[^0-9]", "");

		if (string.equals("00000000000") || string.equals("11111111111") || string.equals("22222222222")
				|| string.equals("33333333333") || string.equals("44444444444") || string.equals("55555555555")
				|| string.equals("66666666666") || string.equals("77777777777") || string.equals("88888888888")
				|| string.equals("99999999999"))
			return false;

		int soma = 0;
		
		int[] numbers = new int[11];
		
		for (int y = 0; y < 11; y++) {
			numbers[y] = (int) string.charAt(y)- '0';
		}

		for (int y = 0, z = 10; y < 9; y++, z--) {
			soma += numbers[y] * z;
		}
		
		
		int result =  soma % 11;
		
		int compatibleNumber = 11 - result;
		
		
		if (compatibleNumber <= 9) {
				if (numbers[9] != compatibleNumber) {
					return false;
				} 
			}else {
				if (numbers[9] != 0)
					return false;
			}
		
			soma = compatibleNumber * 2;

			for (int y = 0, z = 11; y < 9; y++, z--)
				soma += numbers[y] * z;

			result = soma % 11;
			
			compatibleNumber = 11 -  result;

			if (compatibleNumber <= 9) {
					if (numbers[10] != compatibleNumber) {
						return false;
					} 
				}else {
					if (numbers[10] != 0)	
						return false;
				}
		return true;
	}
}
