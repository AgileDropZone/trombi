package jobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import models.Profil;
import play.Logger;
import play.Play;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import tools.StringUtilsLight;

/**
 * Ce jos est exécuté au lancement du serveur. <br>
 * ! : En configuration de dev, le serveur ne se lance réellement qu'à la
 * première requête, et peut redémarrer n'importe quand.
 */
@OnApplicationStart
public class Bootstrap extends Job {

	@Override
	public void doJob() {
		if (Play.runingInTestMode()) {
			if (Profil.count() == 0) {
				reinitJeuDeTests();
			}
		}
	}

	public void reinitJeuDeTests() {
		Logger.info("Purge de la base de tests");
		Fixtures.deleteDatabase();
		Logger.info("Chargement des profils de tests");
		Fixtures.loadModels("profils-test.yml");

		Logger.info("Affectation des photos aux profils de tests");
		List<Profil> lstProfils = Profil.findAll();
		for (Profil profil : lstProfils) {
			String nomFichier = StringUtilsLight
					.normalize(profil.prenom + " " + profil.nom)
					.replace(" ", ".").toLowerCase()
					+ ".jpg";
			File fichierPhoto = Play.getFile("test/photos/" + nomFichier);

			if (fichierPhoto != null && fichierPhoto.exists()) {
				try {
					profil.photo.set(new FileInputStream(fichierPhoto),
							"image/jpg");
					profil.save();
				} catch (FileNotFoundException e) {
					Logger.info("Il n'a pas été possible d'associer une photo au profil de "
							+ profil.prenom + " " + profil.nom);
				}
			}
		}
	}
}