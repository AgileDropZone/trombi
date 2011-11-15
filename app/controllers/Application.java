package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Identite;
import models.PaginationVerticale;
import play.db.jpa.Model;
import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		// Fixtures.deleteDatabase();
		List<Identite> lstIdentite = Identite
				.find("from Identite order by nom").fetch();
		if (lstIdentite.isEmpty()) {

			// Création d'un jeu de donnée initial
			Identite id = new Identite("MARTIN", "Henry");
			id.save();
			lstIdentite.add(id);
			id = new Identite("DUPOND", "Robert");
			id.save();
			lstIdentite.add(id);
			id = new Identite("DUPONT", "Marc");
			id.save();
			lstIdentite.add(id);

			for (int i = 0; i < 30; i++) {
				Identite idG = new Identite("ID" + i, "Prenom" + i);
				idG.save();
				lstIdentite.add(idG);
			}
		}

		List<PaginationVerticale> lstPaginee = Application.paginer(lstIdentite,
				5);
		render(lstPaginee);
	}

	public static List<PaginationVerticale> paginer(List<Identite> lstAPaginer,
			int nbColonnes) {
		List<PaginationVerticale> lstPaginee = new ArrayList<PaginationVerticale>();
		int numCol = 0;
		PaginationVerticale elementPagine = new PaginationVerticale();
		for (Model element : lstAPaginer) {
			if (numCol == 0) {
				elementPagine.col1 = element;
			}
			if (numCol == 1) {
				elementPagine.col2 = element;
			}
			if (numCol == 2) {
				elementPagine.col3 = element;
			}
			if (numCol == 3) {
				elementPagine.col4 = element;
			}
			if (numCol == 4) {
				elementPagine.col5 = element;
				lstPaginee.add(elementPagine);
				elementPagine = new PaginationVerticale();
			}
			numCol = (numCol + 1) % nbColonnes;
		}
		if (numCol != 0)
			lstPaginee.add(elementPagine);

		return lstPaginee;
	}

}