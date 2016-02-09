# Genderize
Genderize is a Scala client for the http://genderize.io web service. Simply provide a given name and genderize will guess the gender

### Usage
```scala
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
```
