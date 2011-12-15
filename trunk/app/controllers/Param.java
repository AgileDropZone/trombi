package controllers;

import models.Parametrage;
import play.db.jpa.Blob;
import play.mvc.Before;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Param extends Controller {

	@Before
	static void addDefaults() {
		renderArgs.put("param", ParamPublic.getParam());
	}

	public static void index() {
		render("BoGestionAdmin/param.html");
	}

	public static void uploadLogo(Blob logoSociete) {
		Parametrage param = ParamPublic.getParam();
		if (logoSociete != null) {
			param.logoSociete = logoSociete;
		}
		param.save();
		render("BoGestionAdmin/param.html");
	}

	public static void save(String titreSite, String adresseSociete,
			String mentionLegale, Blob logoSociete) {
		Parametrage param = ParamPublic.getParam();
		param.titreSite = titreSite;
		param.adresseSociete = adresseSociete;
		param.mentionLegale = mentionLegale;
		if (logoSociete != null) {
			param.logoSociete = logoSociete;
		}
		param.save();
		render("BoGestionAdmin/param.html", param);
	}
}
