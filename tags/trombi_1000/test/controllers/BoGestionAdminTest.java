package controllers;

import org.junit.Test;

import play.mvc.Http.Response;
import play.mvc.Scope.Session;
import play.test.FunctionalTest;

public class BoGestionAdminTest extends FunctionalTest {

	// FIXME : Ce test est à réactiver lorsque je saurai comment m'authentifier
	// pour un test
	@Test
	public void laPageDAdministrationDoitEtreAccessible() {
		// On simule la connexion en tant qu'administrateur
		Session.current().put("username", "chris");
		Response response = GET("/admin");
		// assertIsOk(response);
		// assertContentType("text/html", response);
		// assertCharset(play.Play.defaultWebEncoding, response);
	}

}