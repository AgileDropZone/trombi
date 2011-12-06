package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import models.Profil;
import play.Play;
import play.db.jpa.Blob;
import play.mvc.Controller;

public class ModifProfil extends Controller {

	private static String pathMrX = "public/images/MrX.jpg";

	public static void chargeById(Long id) {
		Profil identite = Profil.findById(id);
		render("Profil/modif-profil.html", identite);
	}

	public static void nouveau() {
		Profil identite = new Profil();
		render("Profil/modif-profil.html", identite);
	}

	public static void uploadPicture(long id, Blob photo) {
		Profil identite = Profil.findById(id);
		if (photo != null) {
			identite.photo = photo;
		}
		identite.save();
		render("Profil/modif-profil.html", identite);
	}

	public static void getPicture(long id) {
		Profil identite = Profil.findById(id);
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
		Profil identite;
		if (id > 0) {
			identite = Profil.findById(id);
		} else {
			identite = new Profil();
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
