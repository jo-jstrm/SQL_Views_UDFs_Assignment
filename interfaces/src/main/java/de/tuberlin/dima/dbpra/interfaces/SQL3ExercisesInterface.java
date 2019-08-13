package de.tuberlin.dima.dbpra.interfaces;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Moritz on 15.05.2015.
 */
public interface SQL3ExercisesInterface {
	/**
	 * Funktion um die Tabelle KundenKontaktDaten zu erstellen. Falls die Tabelle
	 * bereits existiert, soll ihr Inhalt gel�scht werden.
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt. Es soll keine(!) Exception
	 *                      geworfen werden, falls die Tabelle bereits existiert!
	 */
	void initKundenKontaktTable(Connection con) throws SQLException;

	/**
	 * Funktion um die Tabelle KundenKontaktDaten zu l�schen.
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt. Es soll keine(!) Exception
	 *                      geworfen werden, falls die Tabelle nicht existiert!
	 */
	void dropKundenKontaktTable(Connection con) throws SQLException;

	/**
	 * Funktion um der Tabelle KundenKontaktDaten ein referential constraint
	 * mit dem Namen KundenKontaktDaten_Kunden_FK auf Kunde hinzuzuf�gen.
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt.
	 */
	void addRefConstraint(Connection con) throws SQLException;

	/**
	 * Funktion um der Tabelle KundenKontaktDaten ein check constraint
	 * mit dem Namen TwitterID_Check auf das Attribut Twitter_ID hinzuzuf�gen.
	 * <p/>
	 * Das Check Constraint soll sicher stellen dass:
	 * - Alle Twitter_IDs mit @ anfangen.
	 * - Und nach dem @ eine Zeichenkette folgt, die
	 * o Maximal 31 Zeichen lang ist
	 * o Nur aus kleinen lateinische Buchstaben, Zahlen, und '_' besteht.
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt.
	 */
	void addCheckTwitterConstraint(Connection con) throws SQLException;

	/**
	 * Funktion um der Tabelle KundenKontaktDaten ein check constraint
	 * mit dem Namen GoogleUndFacebookID_CHECK auf die Attribute Google_ID und
	 * Facebook_ID hinzuzuf�gen.
	 * <p/>
	 * Das Check Constraint soll sicher stellen dass:
	 * - Alle Google und FacebookIDs im Intervall [10000000,99999999] liegen.
	 * - Keine verbotenen IDs verwendet werden.
	 * o Google verbietet: 10000042, 10000007, 10000666
	 * o Facebook verbietet: 10004321, 10097242, 12345678
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt.
	 */
	void addCheckFacebookGoogleConstraint(Connection con) throws SQLException;

	/**
	 * Funktion um der Tabelle KundenKontaktDaten ein check constraint
	 * mit dem Namen Telefonnummer_CHECK auf das Attribut Telefonnummer
	 * hinzuzuf�gen.
	 * <p/>
	 * Das Check Constraint soll sicher stellen dass:
	 * - Jede Nummer die Landvorwahl f�r Deutschland +49 hat
	 * - Nach der +49 Vorwahl eine Zahl zwischen 150 und 180 folgt
	 * - Nach der Netzvorwahl zwischen 8 und 10 Ziffern kommen
	 * - Landesvorwahl, Netzvorwahl und Nummer durch ein Leerzeichen getrennt
	 * werden
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt.
	 */
	void addTelefonNummerConstraint(Connection con) throws SQLException;

	/**
	 * Funktion um der Tabelle KundenKontaktDaten ein check constraint
	 * mit dem Namen MindestensEinKontakt_CHECK hinzuzuf�gen.
	 * <p/>
	 * Das Check Constraint soll sicher stellen dass mindestens eines der Attribute
	 * TWITTER_ID, GOOGLE_ID, FACEBOOK_ID, SKYPE_ID, TELEFONNUMMER nicht NULL ist.
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt.
	 */
	void addMindestensEinsConstraint(Connection con) throws SQLException;

	/**
	 * Funktion um der Tabelle KundenKontaktDaten einen Trigger hinzuzuf�gen.
	 * Die Funktion soll zun�chst die Tabelle TelefonnummerAenderungen
	 * (Definition siehe Aufgabenstellung) anlegen. Falls die Tabelle bereits
	 * existiert, soll der Inhalt der Tabelle gel�scht werden.
	 * <p/>
	 * Anschlie�end soll die Funktion Trigger f�r die Tabelle Kundenkontakdaten
	 * anlegen, so dass:
	 * - Eine �nderung der Telefonnummer f�r einen Kunden kann fr�hestens
	 * 15 Sekunden nach der letzten �nderungen vorgenommen werden.
	 * o Ansonsten wird ein SIGNAL SQLSTATE '700001' Statement geworfen.
	 * - Bei einer erfolgreichen �nderung wird ein entsprechender Log-Eintrag
	 * in die Tabelle KundenKontaktDaten vorgenommen.
	 * - Sind alte und neue Telefonnummer identisch, wird keine Aktion
	 * vorgenommen.
	 *
	 * @param con Connection Objekt f�r die Datenbankverbindung.
	 * @throws SQLException Falls ein Fehler auftritt.
	 */
	void triggerAnlegen(Connection con) throws SQLException;
}
