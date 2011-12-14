package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 * Classe Parametrage stockant l'ensemble des éléments personnalisables du site. <BR>
 */
@Entity
public class Parametrage extends Model {
	public String titreSite;
	public Blob logoSociete;
	public String adresseSociete;
	public String mentionLegale;

	/**
	 * Constructeur basic. <BR>
	 * Rappel : un identifiant est déjà fourni par héritage.
	 */
	public Parametrage() {
		titreSite = "Bienvenue sur le trombinoscope\nde la SA DND";
		adresseSociete = "SA DND - Zone d'activités Azur 2000 - 14 rue Albert Einstein - 00123 AGILE-EN-FRANCE";
		mentionLegale = "Ce site est géré par le logiciel Trombi";
	}

}
