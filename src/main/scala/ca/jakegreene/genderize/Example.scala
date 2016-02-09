package ca.jakegreene.genderize

import java.util.Locale

object Example {
  implicit val canada = Locale.CANADA
  
  val maybeGendered = Genderize.name("jake")
  maybeGendered.foreach { gendered =>
    println(s"Jake is likely to be a ${gendered.gender} with ${gendered.probability} certainty")  
  }

  /*
   * Other examples
   */
  Genderize.names("jake", "jack")
  Genderize.names(Seq("jake", "jack"))
  Genderize.names(Seq(("jake", canada), ("jack", Locale.CANADA_FRENCH)))
}