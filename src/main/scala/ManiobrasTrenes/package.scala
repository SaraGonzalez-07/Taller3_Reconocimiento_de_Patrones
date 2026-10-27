/** Taller 3:  Reconocimiento de patrones
 *
 * @author Margareth Sophya Gamboa Izquierdo
 * 2518629-3743
 * @author Sara Michelle González Posso
 * 2519548-3743
 *
 */

package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  type Maniobra = List[Movimiento]


  def aplicarMovimiento(e: Estado, m: Movimiento): Estado =
    (e, m) match {

      // Uno(n), n > 0, los últimos n de principal pasan al inicio de uno
      case ((principal, uno, dos), Uno(n)) if n > 0 =>
        (
          principal.dropRight(n),
          principal.takeRight(n) ++ uno,
          dos
        )

      // Uno(n), n < 0, los primeros |n| de uno pasan al final de principal
      case ((principal, uno, dos), Uno(n)) if n < 0 =>
        (
          principal ++ uno.take(-n),
          uno.drop(-n),
          dos
        )

      // Movimiento Uno(0), no cambia el estado
      case (estado, Uno(0)) =>
        estado

      // Dos(n), n > 0, los últimos n de principal pasan al inicio de dos
      case ((principal, uno, dos), Dos(n)) if n > 0 =>
        (
          principal.dropRight(n),
          uno,
          principal.takeRight(n) ++ dos
        )

      // Dos(n), n < 0, los primeros |n| de dos pasan al final de principal
      case ((principal, uno, dos), Dos(n)) if n < 0 =>
        (
          principal ++ dos.take(-n),
          uno,
          dos.drop(-n)
        )

      // Movimiento Dos(0), no cambia el estado
      case (estado, Dos(0)) =>
        estado
    }


  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] =
    movs match {

      case Nil => List(e)

      case m :: ms => e :: aplicarMovimientos(aplicarMovimiento(e, m), ms)
    }


  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    // Dados dos trenes t1 (el original) y t2 (el deseado), devuelve la maniobra
    // para pasar de (t1, List(), List()) a (t2, List(), List())
   ???
  }
}