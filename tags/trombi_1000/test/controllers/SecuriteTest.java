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

	@Test
	public void lAuthentificationDoitReconnaitreLeCompteParDefaut() {
		// Vis-à-vis du jeu de test remonté, nous devrions avoir
		// l'administrateur suivant de paramétré :
		// chris / 123
		assertTrue(
				"Le compte administrateur chris / 123 aurait dû être reconnu",
				Securite.authenticate("chris", "123"));
	}

	@Test
	public void lAuthentificationDoitReconnaitreUneErreurDeMotDePasse() {
		// Vis-à-vis du jeu de test remonté, nous devrions avoir
		// l'administrateur suivant de paramétré :
		// chris / 123
		assertFalse(
				"Le mot de passe ne correspond pas au compte administrateur chris",
				Securite.authenticate("chris", "55555"));
	}

	@Test
	public void lAuthentificationDoitReconnaitreUneErreurDeCompte() {
		// Vis-à-vis du jeu de test remonté, nous devrions avoir
		// l'administrateur suivant de paramétré :
		// chris / 123
		assertFalse("Le compte toto n'existe pas",
				Securite.authenticate("toto", "123"));
	}
}