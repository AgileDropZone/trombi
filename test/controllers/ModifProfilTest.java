package controllers;

import org.junit.Test;

import play.mvc.Http.Response;
import play.mvc.Scope.Session;
import play.test.FunctionalTest;

public class ModifProfilTest extends FunctionalTest {

	@Test
	public void leProfileDeDavidArchimèreDevraitSeCharger() {
		Response response = GET("/modifprofil/chargebyid?id=1");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertContentMatch("David", response);
		assertContentMatch("Archim&egrave;de|ARCHIM&Egrave;DE", response);
	}

	@Test
	public void laPageDeCréationDUnNouveauProfilDoitEtreAccessible() {
		// TODO Supprimer ce flag temporaire
		Session.current().put("modif", "oui");
		Response response = GET("/modifprofil/nouveau");
		assertIsOk(response);
		assertContentType("text/html", response);
	}

	@Test
	public void laPhotoDeDavidArchimèdeDoitEtreAccessible() {
		Response response = GET("/modifprofil/getPicture?id=1");
		assertIsOk(response);
		assertContentType("image/jpg", response);
	}

	@Test
	public void enModificationLeNomDoitApparaitreCommeSaisi() {
		// On force le mode MODIFICATION
		Session.current().put("modif", "O");
		Response response = GET("/modifprofil/chargebyid?id=1");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertContentMatch("David", response);
		assertContentMatch("Archim&egrave;de", response);
	}

	@Test
	public void enLectureSeuleLeNomDoitApparaitreCommeSaisi() {
		// On force le mode LECTURE SEULE
		Session.current().remove("modif");
		Response response = GET("/modifprofil/chargebyid?id=1");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertContentMatch("David", response);
		assertContentMatch("ARCHIM&Egrave;DE", response);
	}
}