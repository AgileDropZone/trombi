package controllers;

import org.junit.Test;

import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

	@Test
	public void testThatIndexPageWorks() {
		Response response = GET("/");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
	}

	@Test
	public void leTitreDuSiteDoitEtrePresentSurLaPageAccueil() {
		Response response = GET("/");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertContentMatch("Bienvenue sur le trombinoscope", response);
	}

	@Test
	public void laPageAccueilDoitContenirRobertDupond() {
		Response response = GET("/");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertContentMatch("Robert DUPOND", response);
	}

	@Test
	public void laRechercheDoitRemonterRobertDupond() {
		Response response = GET("/recherche/dupo");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertContentMatch("Robert DUPOND", response);
	}
}