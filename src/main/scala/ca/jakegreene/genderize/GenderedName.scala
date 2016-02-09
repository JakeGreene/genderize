package ca.jakegreene.genderize

trait GenderedName {
  def name(): String
  def gender(): Gender
  def probability(): Double
  def count(): Int
}