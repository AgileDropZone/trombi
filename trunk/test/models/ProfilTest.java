package models;

import org.junit.Test;

import play.test.UnitTest;

public class ProfilTest extends UnitTest {

	@Test
	public void creationDUneNouvelleIdentite() {
		// HAVING
		long nbProfilsAvant = Profil.count();

		// WHEN
		Profil id = new Profil("MARTIN", "Henry");
		id.telFixe = "(+33)505050505";
		id.telPortable = "(+33)606060606";
		id.email = "Henry@MARTIN.fr";
		id.save();

		// THEN
		assertEquals("Le nombre de profils aurait dûe être incrémenté",
				nbProfilsAvant + 1, Profil.count());

		Profil profilEnBase = Profil.find("from Profil where nom = ?", id.nom)
				.first();
		assertNotNull("L'identité aurait dû être créée en base", profilEnBase);
	}

}
