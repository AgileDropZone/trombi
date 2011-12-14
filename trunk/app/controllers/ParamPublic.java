package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import models.Parametrage;
import play.Play;
import play.mvc.Before;
import play.mvc.Controller;

public class ParamPublic extends Controller {

	private static String pathDefaultLogo = "public/images/logoDND.jpg";

	@Before
	static void addDefaults() {
		renderArgs.put("param", ParamPublic.getParam());
	}

	public static Parametrage getParam() {
		// Légitimement, nous ne devons avoir qu'un seul enregistrement dans
		// cette table
		Parametrage param;
		if (Parametrage.count() >= 1) {
			param = Parametrage.find("").first();
		} else {
			param = new Parametrage();
		}
		return param;
	}

	public static void getLogo() {
		Parametrage param = getParam();
		response.setContentTypeIfNotSet(param.logoSociete.type());
		if (param.logoSociete != null && param.logoSociete.type() != null) {
			renderBinary(param.logoSociete.get());
		} else {
			try {
				renderBinary(new FileInputStream(Play.getFile(pathDefaultLogo)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
