import ManiobrasTrenes._

val e1 = (List('a','b','c','d'), Nil, Nil)
val e2 = aplicarMovimiento(e1, Uno(2))
val e3 = aplicarMovimiento(e2, Dos(3))
val e4 = aplicarMovimiento(e3, Dos(-1))
val e5 = aplicarMovimiento(e4, Uno(-2))

val e = (List('a', 'b'), List('c'), List('d'))
aplicarMovimientos(e, List(Uno(1), Dos(1), Uno(-2)))

// CASOS DE PRUEBA PROPIOS
// aplicarMovimiento
val c1 = (List('a','b','c','d','e'), List('x'), List('z'))
aplicarMovimiento(c1, Uno(2)) == (List('a','b','c'), List('d','e','x'), List('z'))

val c2 = (List('m','n'), List('q'), List('r'))
aplicarMovimiento(c2, Dos(8)) == (Nil, List('q'), List('m','n','r'))

val c3 = (List('p'), List('a','b','c'), List('d'))
aplicarMovimiento(c3, Uno(-2)) == (List('p','a','b'), List('c'), List('d'))

val c4 = (List('p'), List('a'), List('d'))
aplicarMovimiento(c4, Uno(-5)) == (List('p','a'), Nil, List('d'))

val c5 = (List('a','b','c','d'), List('x'), List('y'))
aplicarMovimiento(c5, Dos(2)) == (List('a','b'), List('x'), List('c','d','y'))
aplicarMovimiento(c5, Dos(-5)) == (List('a','b','c','d','y'), List('x'), Nil)

val c6 = (List('a','b'), List('c'), List('d'))
aplicarMovimiento(c6, Uno(0)) == c6
aplicarMovimiento(c6, Dos(0)) == c6

// aplicarMovimientos
val m1 = (List('a','b','c','d'), List('x'), Nil)
aplicarMovimientos(m1, List(Uno(1), Uno(2))) ==
  List(
    (List('a','b','c','d'), List('x'), Nil),
    (List('a','b','c'), List('d','x'), Nil),
    (List('a'), List('b','c','d','x'), Nil)
  )

val m2 = (List('m','n'), List('q'), List('r'))
aplicarMovimientos(m2, List(Uno(8), Dos(1))) ==
  List(
    (List('m','n'), List('q'), List('r')),
    (Nil, List('m','n','q'), List('r')),
    (Nil, List('m','n','q'), List('r'))
  )

val m3 = (List('p'), List('a','b','c'), List('d','e'))
aplicarMovimientos(m3, List(Uno(-2), Dos(-1))) ==
  List(
    (List('p'), List('a','b','c'), List('d','e')),
    (List('p','a','b'), List('c'), List('d','e')),
    (List('p','a','b','d'), List('c'), List('e'))
  )

val m4 = (List('a','b','c'), List('x'), List('y'))
aplicarMovimientos(m4, List(Dos(2), Dos(-2))) ==
  List(
    (List('a','b','c'), List('x'), List('y')),
    (List('a'), List('x'), List('b','c','y')),
    (List('a','b','c'), List('x'), List('y'))
  )

val m5 = (List('a','b'), List('c'), List('d'))
aplicarMovimientos(m5, List(Uno(0), Dos(0))) ==
  List(
    (List('a','b'), List('c'), List('d')),
    (List('a','b'), List('c'), List('d')),
    (List('a','b'), List('c'), List('d'))
  )

aplicarMovimientos(m5, Nil) ==
  List((List('a','b'), List('c'), List('d')))

// definirManiobra