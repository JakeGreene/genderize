package ca.jakegreene.genderize

import java.util.Locale

trait Genderize {
  /**
   * Attempt to determine the gender of `givenName`.
   * It is possible that `givenName` cannot be genderized.
   */
  def name(givenName: String)(implicit locale: Locale): Option[GenderedName] = nameWithLocale(givenName, locale)
  
  /**
   * Attempt to determine the gender of `givenName` for the provided `Locale`.
   * It is possible that `givenName` cannot be genderized.
   */
  def nameWithLocale(givenName: String, locale: Locale): Option[GenderedName]
  
  /**
   * Attempt to determine the genders of the members of `givenNames`.
   * It is possible that a given name cannot be genderized.
   */
  def names(givenNames: Seq[String])(implicit locale: Locale): Seq[Option[GenderedName]]
  
  /**
   * Attempt to determine the genders of the members of `givenNames` for the provided `Locale`s.
   * It is possible that a given name cannot be genderized.
   */
  def names(givenNames: Seq[(String, Locale)]): Seq[Option[GenderedName]]
  
  /**
   * Attempt to determine the genders of the provided given names
   * It is possible that a given name cannot be genderized.
   */
  def names(givenName: String, others: String*)(implicit locale: Locale): Seq[Option[GenderedName]] = names(givenName +: others)
}

/*
 * XXX The API requires:
 * - ISO 3166-1 alpha-2 country codes. Use Locale.getCountry()
 * - ISO 639-1 language codes. Use Locale.getLanguage() 
 */
object Genderize extends Genderize {
  override def nameWithLocale(givenName: String, locale: Locale): Option[GenderedName] = ???
  override def names(givenNames: Seq[String])(implicit locale: Locale): Seq[Option[GenderedName]] = ???
  override def names(givenNames: Seq[(String, Locale)]): Seq[Option[GenderedName]] = ???
}