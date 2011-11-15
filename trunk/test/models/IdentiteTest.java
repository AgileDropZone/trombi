package models;

import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;

public class IdentiteTest {

	@Test
	public void creationDUneNouvelleIdentite() {
		// HAVING
		Identite id = new Identite("MARTIN", "Henry");
		id.telFixe = "(+33)505050505";
		id.telPortable = "(+33)606060606";
		id.email = "Henry@MARTIN.fr";
		id.save();

		// WHEN
		List<Identite> lstIdentite = Identite
				.find("from Identite order by nom").fetch();

		// THEN
		assertFalse("Une identité aurait dû être créée", lstIdentite.isEmpty());
	}

}
