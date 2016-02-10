package ca.jakegreene.genderize

import java.util.Locale
import scala.concurrent.ExecutionContext.Implicits.global

object Example {
  implicit val canada = Locale.CANADA
  
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
   * Async
   */
  val asyncGenderize = GenderizeClient.async()
  val futureGender = for {
    gendered <- asyncGenderize.name("jake")
  } yield gendered
}