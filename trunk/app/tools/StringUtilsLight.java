package tools;

import java.text.Normalizer;

public class StringUtilsLight {

	/**
	 * Retourne une chaine normalisée selon les critères suivants : <br>
	 * <ul>
	 * <li>en majuscule</li>
	 * <li>sans accents</li>
	 * </ul>
	 * 
	 * @param chaine
	 * @return chaine normalisée
	 */
	public static String normalize(String chaine) {
		String res = Normalizer.normalize(chaine, Normalizer.Form.NFD)
				.replaceAll("[\u0300-\u036F]", "") //
				.replace("-", " ") //
				.replace("_", " ") //
				.toUpperCase();

		return res;
	}
}
