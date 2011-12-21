package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Administrateur extends Model {
	public String nom;
	public String motDePasse;
	public String email;

	/**
	 * Constructeur basic. <BR>
	 * Rappel : un identifiant est déjà fourni par héritage.
	 * 
	 * @param nom
	 * @param email
	 * @param motDePasse
	 */
	public Administrateur(String nom, String email, String motDePasse) {
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
	}

	/**
	 * Constructeur basic. <BR>
	 * Rappel : un identifiant est déjà fourni par héritage.
	 */
	public Administrateur() {

	}

	/**
	 * Permet d'authentifier les administrateurs
	 */
	public static Object connect(String username, String password) {
		Administrateur admin = Administrateur.find(
				"select a from Administrateur a where a.nom = '" + username
						+ "' and a.motDePasse = '" + password + "'").first();
		return admin;
	}
}
