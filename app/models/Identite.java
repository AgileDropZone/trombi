package models;

import javax.persistence.Entity;

import play.db.jpa.Blob;
import play.db.jpa.Model;

/**
 * Classe Identite représentant les informations de base sur un individu. <BR>
 * Rappel : la gestion d'un identifiant est déjà fourni par héritage.
 */
@Entity
public class Identite extends Model {
	public String nom;
	public String prenom;

	public String telFixe;
	public String telPortable;
	public String email;

	public Blob photo;

	// TODO Gérer un référentiel de départements
	public String departement;

	// TODO Gérer un référentiel de services
	public String service;

	// TODO Gérer un référentiel de postes
	public String poste;

	public String localisation;

	public String hasPhoto = "FALSE";

	/**
	 * Constructeur basic. <BR>
	 * Rappel : un identifiant est déjà fourni par héritage.
	 * 
	 * @param nom
	 * @param prenom
	 */
	public Identite(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;
	}

	/**
	 * Constructeur basic. <BR>
	 * Rappel : un identifiant est déjà fourni par héritage.
	 */
	public Identite() {
	}

}
