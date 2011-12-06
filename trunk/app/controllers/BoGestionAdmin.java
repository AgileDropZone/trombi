package controllers;

import java.util.List;

import models.Administrateur;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class BoGestionAdmin extends Controller {

	public static void index() {

		List<Administrateur> lstAdmin = Administrateur.find(
				"from Administrateur order by nom").fetch();
		render("BoGestionAdmin/gestion-admin.html", lstAdmin);
	}

	public static void save(long id, String nom, String email, String motDePasse) {
		Administrateur admin;
		if (id > 0) {
			admin = Administrateur.findById(id);
		} else {
			admin = new Administrateur();
		}
		admin.nom = nom;
		admin.email = email;
		if (motDePasse != null && motDePasse.length() > 0) {
			admin.motDePasse = motDePasse;
		}
		admin.save();

		List<Administrateur> lstAdmin = Administrateur.find(
				"from Administrateur order by nom").fetch();
		render("BoGestionAdmin/gestion-admin.html", lstAdmin);
	}

	public static void delete(long id) {
		Administrateur admin;
		if (id > 0) {
			admin = Administrateur.findById(id);
			admin.delete();
		}
		List<Administrateur> lstAdmin = Administrateur.find(
				"from Administrateur order by nom").fetch();
		render("BoGestionAdmin/gestion-admin.html", lstAdmin);
	}
}
