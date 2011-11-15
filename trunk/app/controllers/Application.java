package controllers;

import java.util.ArrayList;
import java.util.List;

import models.PaginationVerticale;
import models.Profil;
import play.db.jpa.Model;
import play.mvc.Controller;

public class Application extends Controller {

	public static void index() {
		// Fixtures.deleteDatabase();
		List<Profil> lstProfils = Profil.find("from Profil order by nom")
				.fetch();

		List<PaginationVerticale> lstPaginee = Application.paginer(lstProfils,
				5);
		render(lstPaginee);
	}

	public static void search(String searchTexte) {
		// On sort tout ce qui pourrait parasiter la recherche
		String textePourLaRecherche = "%" + searchTexte.toUpperCase() + "%";
		List<Profil> lstProfils = Profil
				.find("from Profil where upper(nom)||upper(prenom) like ? or upper(prenom)||upper(nom) like ? order by nom",
						textePourLaRecherche, textePourLaRecherche).fetch();

		List<PaginationVerticale> lstPaginee = Application.paginer(lstProfils,
				5);

		render("Application/index.html", lstPaginee);
	}

	public static List<PaginationVerticale> paginer(List<Profil> lstAPaginer,
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