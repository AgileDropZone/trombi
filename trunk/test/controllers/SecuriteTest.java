package controllers;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class SecuriteTest extends FunctionalTest {

	@Test
	public void laPageDAccueilDoitContenirConnexionOUDeconnexion() {
		Response response = GET("/");
		assertIsOk(response);

		String strReponse = getContent(response);
		if (strReponse.indexOf("Connexion") >= 0) {
			assertTrue(
					"Si nous ne sommes pas connecté, nous ne devrions pas avoir le lien Nouveau",
					strReponse.indexOf("Nouveau") == -1);
		} else if (strReponse.indexOf("Déconnexion") >= 0) {
			assertTrue(
					"Si nous sommes connecté, nous devrions avoir le lien Nouveau",
					strReponse.indexOf("Nouveau") >= 0);
		}
	}
}