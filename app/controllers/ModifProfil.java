package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import models.Identite;
import play.Play;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class ModifProfil extends Controller {

	private static String pathMrX = "public/images/MrX.jpg";

	public static void index() {
		render();
	}

	public static void chargeById(Long id) {
		Identite identite = Identite.findById(id);
		render("Profil/modif-profil.html", identite);
	}

	public static void nouveau() {
		Identite identite = new Identite();
		render("Profil/modif-profil.html", identite);
	}

	public static void uploadPicture(long id, Blob photo) {
		Identite identite = Identite.findById(id);
		if (photo != null) {
			identite.photo = photo;
		}
		identite.save();
		render("Profil/modif-profil.html", identite);
	}

	public static void getPicture(long id) {
		Identite identite = Identite.findById(id);
		response.setContentTypeIfNotSet(identite.photo.type());
		if (identite.photo != null && identite.photo.type() != null) {
			renderBinary(identite.photo.get());
		} else {
			try {
				renderBinary(new FileInputStream(Play.getFile(pathMrX)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	public static void save(long id, String prenom, String nom, String telFixe,
			String telPortable, String email, Blob photo, String departement,
			String service, String poste, String localisation) {
		Identite identite;
		if (id > 0) {
			identite = Identite.findById(id);
		} else {
			identite = new Identite();
		}
		identite.nom = nom;
		identite.prenom = prenom;
		identite.telFixe = telFixe;
		identite.telPortable = telPortable;
		identite.email = email;
		if (photo != null) {
			identite.photo = photo;
		}
		identite.departement = departement;
		identite.service = service;
		identite.poste = poste;
		identite.localisation = localisation;
		identite.save();
		render("Profil/modif-profil.html", identite);
	}
}
