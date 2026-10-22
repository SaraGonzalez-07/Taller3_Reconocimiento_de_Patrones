package object ManiobrasTrenes {
  type Vagon = Any
  type Tren = List[Vagon]
  type Estado = (Tren, Tren, Tren)

  trait Movimiento
  case class Uno(n: Int) extends Movimiento
  case class Dos(n: Int) extends Movimiento

  type Maniobra = List[Movimiento]

  def aplicarMovimiento(e: Estado, m: Movimiento): Estado = {
    ???
  }

  def aplicarMovimientos(e: Estado, movs: Maniobra): List[Estado] = {
    ???
  }

  def definirManiobra(t1: Tren, t2: Tren): Maniobra = {
    // Dados dos trenes t1 (el original) y t2 (el deseado), devuelve la maniobra
    // para pasar de (t1, List(), List()) a (t2, List(), List())
    ???
  }
}