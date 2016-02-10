package ca.jakegreene.genderize

import java.util.Locale
import scala.language.higherKinds
import scala.concurrent.Future

/*
 * XXX The API requires:
 * - ISO 3166-1 alpha-2 country codes. Use Locale.getCountry()
 * - ISO 639-1 language codes. Use Locale.getLanguage()
 * XXX Accept an apikey for users who pay for the API
 */
object GenderizeClient {
  def blocking(): BlockingGenderizeClient = new BlockingGenderizeClient()
  def async(): AsyncGenderizeClient = new AsyncGenderizeClient()
}

trait GenderizeClient[F[_]] {
  /**
   * Attempt to determine the gender of `givenName`.
   * It is possible that `givenName` cannot be genderized.
   */
  def name(givenName: String)(implicit locale: Locale): F[Name] = nameWithLocale(givenName, locale)
  
  /**
   * Attempt to determine the gender of `givenName` for the provided [[java.util.Locale]].
   * It is possible that `givenName` cannot be genderized.
   */
  def nameWithLocale(givenName: String, locale: Locale): F[Name]
  
  /**
   * Attempt to determine the genders of the members of `givenNames`.
   * It is possible that a given name cannot be genderized.
   */
  def names(givenNames: Seq[String])(implicit locale: Locale): F[Seq[Name]] = namesWithLocale(givenNames, locale)
  
  /**
   * Attempt to determine the genders of the members of `givenNames` for the provided [[java.util.Locale]].
   * It is possible that a given name cannot be genderized.
   */
  def namesWithLocale(givenNames: Seq[String], locale: Locale): F[Seq[Name]]
  
  /**
   * Attempt to determine the genders of the provided given names
   * It is possible that a given name cannot be genderized.
   */
  def names(givenName: String, others: String*)(implicit locale: Locale): F[Seq[Name]] = names(givenName +: others)
}

class AsyncGenderizeClient extends GenderizeClient[Future] {
  override def nameWithLocale(givenName: String, locale: Locale): Future[Name] = ???
  override def namesWithLocale(givenNames: Seq[String], locale: Locale): Future[Seq[Name]] = ???
}

class BlockingGenderizeClient extends GenderizeClient[Id] {
  override def nameWithLocale(givenName: String, locale: Locale): Name = ???
  override def namesWithLocale(givenNames: Seq[String], locale: Locale): Seq[Name] = ???
}