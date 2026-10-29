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
      case (e, Uno(0)) => e

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
      case (e, Dos(0)) => e
    }


  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] =
    movs match {

      case Nil => List(e)

      case m :: ms => e :: aplicarMovimientos(aplicarMovimiento(e, m), ms)
    }


  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    // Dados dos trenes t1 (el original) y t2 (el deseado), devuelve la maniobra
    // para pasar de (t1, List(), List()) a (t2, List(), List()) }
    val n = t1.length

    val primeraManiobra: Maniobra = n match {
      case 0 => Nil

      case _ => List(Uno(n), Uno(-(n - 1)), Dos(n - 1))
    }

    val estadoInicial = primeraManiobra.foldLeft((t1, List(), List()))(aplicarMovimiento)

    def solucion(e: Estado, obj: Tren, mn: Maniobra): Maniobra = obj match {
      case Nil =>

        val mnDos = (0 until e._3.length).foldLeft((e, mn)) {
          case ((est, a), _) =>
            val m = Dos(-1)
            (aplicarMovimiento(est, m), a :+ m)
        }
        val mnUno = e._2.length match {
          case 0 => mnDos._2
          case len => mnDos._2 :+ Uno(-len)
        }
        mnUno
      case vagonBus :: vagonR =>
        (e._2.contains(vagonBus), e._3.contains(vagonBus)) match {
          case (true, _) =>
            val m = Uno(-1)
            val nuevoEst = aplicarMovimiento(e, m)
            solucion(nuevoEst, vagonR, mn :+ m)

          case (_, true) =>
            val casoConflicto = e._1.nonEmpty && e._1 != t2.take(e._1.length)
            val (ePrevio, mnPrevio) = casoConflicto match {
              case true =>
                val mU = Uno(1)
                (aplicarMovimiento(e, mU), mn :+ mU)
              case false =>
                (e, mn)
            }
            val mD = Dos(-1)
            val nuevoEst = aplicarMovimiento(ePrevio, mD)
            solucion(nuevoEst, vagonR, mnPrevio :+ mD)

          case _ =>
            solucion(e, vagonR, mn)
        }
    }
    solucion(estadoInicial, t2, primeraManiobra)
  }
}

