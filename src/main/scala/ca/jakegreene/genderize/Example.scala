package ca.jakegreene.genderize

import java.util.Locale
import scala.concurrent.ExecutionContext.Implicits.global

object Example {
  implicit val canada = Locale.CANADA
  
  /*
   * Blocking API
   */
  val genderize = GenderizeClient.blocking()
  genderize.name("jake") match {
    case GenderlessName(name) =>
      println(s"Could not determine the gender of $name for $canada")
    case GenderedName(name, gender, probability, count) =>
      println(s"A $name in $canada is $probability likely to be a $gender")
  }
  
  /*
   * Other examples
   */
  genderize.names("jake", "jack")
  genderize.names(Seq("jake", "jack"))
  
  /*
   * Async API
   */
  val asyncGenderize = GenderizeClient.async()
  val futureGenderedNames = for {
    genderedJake <- asyncGenderize.name("jake")
    genderedJackie <- asyncGenderize.name("jackie")
  } yield (genderedJake, genderedJackie)
}