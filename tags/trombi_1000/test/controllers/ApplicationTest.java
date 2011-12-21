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

		String strReponse = getContent(response);
		assertTrue("Henry MARTIN ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("Henry MARTIN") == -1);

	}

	@Test
	public void laPageAccueilNeDoitAfficherQue15Profils() {
		Response response = GET("/");
		assertIsOk(response);
		assertContentType("text/html", response);

		// Vis-à-vis du jeu de données de test, nous devons avoir
		// sur la première page de Céline Ampère à Carine Lavoisier
		// Les profils de Henry MARTIN à Naomie WATT doivent
		// apparaitre en page 2

		assertContentMatch("C&eacute;line AMP&Egrave;RE", response);
		assertContentMatch("Carine LAVOISIER", response);

		String strReponse = getContent(response);
		assertTrue("Henry MARTIN ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("Henry MARTIN") == -1);
		assertTrue("Naomie WATT ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("Naomie WATT") == -1);
	}

	@Test
	public void laSecondePageDoitAfficherLesProfilsDe16a30() {
		Response response = GET("/page2");
		assertIsOk(response);
		assertContentType("text/html", response);

		// Vis-à-vis du jeu de données de test, nous devons avoir
		// sur la seconde page de Henry MARTIN à Naomie WATT
		// Les profils de Céline Ampère à Carine Lavoisier doivent
		// apparaitre en page 1

		assertContentMatch("Henry MARTIN", response);
		assertContentMatch("Naomie WATT", response);

		String strReponse = getContent(response);
		assertTrue("Céline AMPÈRE ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("C&eacute;line AMP&Egrave;RE") == -1);
		assertTrue("Carine LAVOISIER ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("Carine LAVOISIER") == -1);
	}

	@Test
	public void laRechercheNeDoitAfficherQue15Profils() {
		Response response = GET("/page1/a");
		assertIsOk(response);
		assertContentType("text/html", response);

		// Vis-à-vis du jeu de données de test, nous devons avoir
		// sur la première page de Céline Ampère à Henry MARTIN
		// -> Robert DUPOND ne doit pas remonter car il ne répond
		// pas aux critères de recherche
		// Les profils de Anaïs MENDELEÏEV à Naomie WATT doivent
		// apparaitre en page 2

		assertContentMatch("C&eacute;line AMP&Egrave;RE", response);
		assertContentMatch("Henry MARTIN", response);

		String strReponse = getContent(response);
		assertTrue("Robert DUPOND ne répond pas aux critères de recherche",
				strReponse.indexOf("Robert DUPOND") == -1);
		assertTrue("Anaïs MENDELEÏEV ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("Ana&iuml;s MENDELE&Iuml;EV") == -1);
	}

	@Test
	public void laSecondepageDeRechercheDoitAfficherDesProfilsFiltrés() {
		Response response = GET("/page2/a");
		assertIsOk(response);
		assertContentType("text/html", response);

		// Vis-à-vis du jeu de données de test et du critère recherché,
		// nous devons avoir sur la seconde page de Anaïs MENDELEÏEV à
		// Naomie WATT
		// Les profils de Céline Ampère à Henry MARTIN doivent
		// apparaitre en page 1

		assertContentMatch("Ana&iuml;s MENDELE&Iuml;EV", response);
		assertContentMatch("Naomie WATT", response);

		String strReponse = getContent(response);
		assertTrue("Céline AMPÈRE ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("C&eacute;line AMP&Egrave;RE") == -1);
		assertTrue("Henry MARTIN ne devrait pas apparaitre sur cette page",
				strReponse.indexOf("Henry MARTIN") == -1);

	}
}