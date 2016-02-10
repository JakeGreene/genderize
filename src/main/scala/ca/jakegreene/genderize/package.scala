package ca.jakegreene

package object genderize {
  /**
   * The Identity type. Id[X] =:= X
   * 
   * Can be used when there is a need for an optional container type.
   * {{{
   * // Example usage
   * trait A[F[_]] {
   *   def create(): F[Int]
   * }
   * 
   * class B[Seq] { ... } // will create Seq[Int]
   * class C[Id] { ... } // will create Int
   * }}}
   */
  type Id[X] = X
}