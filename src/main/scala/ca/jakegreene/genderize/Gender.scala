package ca.jakegreene.genderize

object Gender {
  def from(s: String): Option[Gender] = Option(s) collect {
    case Male() => Male
    case Female() => Female
  }
}

abstract class Gender(name: String) {
  def unapply(s: String): Boolean = s == name
  override def toString(): String = name  
}

case object Male extends Gender("male")
case object Female extends Gender("female")
