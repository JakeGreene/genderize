package ca.jakegreene.genderize

import java.util.Locale
import scala.language.higherKinds
import scala.concurrent.Future
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl._
import akka.http.scaladsl.model._
import akka.util.ByteString

/*
 * XXX The API requires:
 * - ISO 3166-1 alpha-2 country codes. Use Locale.getCountry()
 * - ISO 639-1 language codes. Use Locale.getLanguage()
 */
object GenderizeClient {
  def blocking(apiKey: Option[String] = None)(implicit system: ActorSystem, materializer: ActorMaterializer): BlockingGenderizeClient = new BlockingGenderizeClient(apiKey)
  def async(apiKey: Option[String] = None)(implicit system: ActorSystem, materializer: ActorMaterializer): AsyncGenderizeClient = new AsyncGenderizeClient(apiKey)
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

class AsyncGenderizeClient(apiKey: Option[String])(implicit system: ActorSystem, materializer: ActorMaterializer) extends GenderizeClient[Future] {
  import system.dispatcher
  
  override def nameWithLocale(givenName: String, locale: Locale): Future[Name] = {
    val countryId = locale.getCountry
    val languageId = locale.getLanguage
    var optionalParams = ""
    val request = HttpRequest(uri = s"https://api.genderize.io/?name=$givenName&country_id=${}&language_id=${}")
    val response = Http().singleRequest(request)
    response
      .flatMap {
        case HttpResponse(code, headers, entity, _) =>
          // Decode JSON into GenderedName or GenderlessName
          entity.dataBytes.runFold(ByteString(""))(_ ++ _).map(_.decodeString("UTF-8"))
      }
      .map { name =>
        GenderlessName(name)
      }
  }
  
  override def namesWithLocale(givenNames: Seq[String], locale: Locale): Future[Seq[Name]] = ???
}

class BlockingGenderizeClient(apiKey: Option[String])(implicit system: ActorSystem, materializer: ActorMaterializer) extends GenderizeClient[Id] {
  override def nameWithLocale(givenName: String, locale: Locale): Name = ???
  override def namesWithLocale(givenNames: Seq[String], locale: Locale): Seq[Name] = ???
}