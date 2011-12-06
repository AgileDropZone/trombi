package controllers;

import controllers.Secure.Security;

public class Securite extends Security {

	static boolean authenticate(String username, String password) {
		return true;
	}

	public static void logout() throws Throwable {
		session.clear();
		response.removeCookie("rememberme");
		flash.success("secure.logout");
		// Redirection vers l'URL d'origine (à défaut /)
		Secure.redirectToOriginalURL();
	}
}
