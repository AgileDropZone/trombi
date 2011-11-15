package tools;

import org.junit.Test;

import play.test.UnitTest;

public class StringUtilsLightTest extends UnitTest {

	@Test
	public void cetteChaineDevraitEtreEnMajuscules() {
		// HAVING
		String chaineTest = "Coucou";
		String chaineAttendue = "COUCOU";

		// WHEN
		String chaineResultat = StringUtilsLight.normalize(chaineTest);

		// THEN
		assertEquals(chaineAttendue, chaineResultat);
	}

	@Test
	public void cetteChaineNeDevraitPlusAvoirDAccent() {
		// HAVING
		String chaineTest1 = "être";
		String chaineAttendue1 = "ETRE";
		String chaineTest2 = "été";
		String chaineAttendue2 = "ETE";
		String chaineTest3 = "près";
		String chaineAttendue3 = "PRES";
		String chaineTest4 = "Noël";
		String chaineAttendue4 = "NOEL";

		// WHEN
		String chaineResultat1 = StringUtilsLight.normalize(chaineTest1);
		String chaineResultat2 = StringUtilsLight.normalize(chaineTest2);
		String chaineResultat3 = StringUtilsLight.normalize(chaineTest3);
		String chaineResultat4 = StringUtilsLight.normalize(chaineTest4);

		// THEN
		assertEquals(chaineAttendue1, chaineResultat1);
		assertEquals(chaineAttendue2, chaineResultat2);
		assertEquals(chaineAttendue3, chaineResultat3);
		assertEquals(chaineAttendue4, chaineResultat4);
	}

	@Test
	public void cetteChaineNeDevraitPlusContenirDeTiret() {
		// HAVING
		String chaineTest = "Jean-Marie";
		String chaineAttendue = "JEAN MARIE";

		// WHEN
		String chaineResultat = StringUtilsLight.normalize(chaineTest);

		// THEN
		assertEquals(chaineAttendue, chaineResultat);
	}

	@Test
	public void cetteChaineNeDevraitPlusContenirDUnderscode() {
		// HAVING
		String chaineTest = "ref_01";
		String chaineAttendue = "REF 01";

		// WHEN
		String chaineResultat = StringUtilsLight.normalize(chaineTest);

		// THEN
		assertEquals(chaineAttendue, chaineResultat);
	}
}
