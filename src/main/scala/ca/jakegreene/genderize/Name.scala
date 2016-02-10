package ca.jakegreene.genderize

trait Name {
  def text(): String
}

/**
 * A `GenderedName` is a name which has been assigned a gender with a provided confidence level
 */
case class GenderedName(text: String, gender: Gender, probability: Double, count: Int) extends Name

/**
 * A `GenderlessName` is a name that could not be assigned a gender
 */
case class GenderlessName(text: String) extends Name