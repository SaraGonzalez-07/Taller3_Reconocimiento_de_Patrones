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
val c1 = (List('a','b','c','d','e'), List('f'), List('g'))
aplicarMovimiento(c1, Uno(2)) == (List('a','b','c'), List('d','e','f'), List('g'))

val c2 = (List('a','b'), List('c'), List('d'))
aplicarMovimiento(c2, Dos(8)) == (Nil, List('c'), List('a','b','d'))

val c3 = (List('a'), List('b','c','d'), List('f'))
aplicarMovimiento(c3, Uno(-2)) == (List('a','b','c'), List('d'), List('f'))

val c4 = (List('a'), List('b'), List('c'))
aplicarMovimiento(c4, Uno(-5)) == (List('a','b'), Nil, List('c'))

val c5 = (List('a','b','c','d'), List('e'), List('f'))
aplicarMovimiento(c5, Dos(2)) == (List('a','b'), List('e'), List('c','d','f'))
aplicarMovimiento(c5, Dos(-5)) == (List('a','b','c','d','f'), List('e'), Nil)

val c6 = (List('a','b'), List('c'), List('d'))
aplicarMovimiento(c6, Uno(0)) == (List('a','b'), List('c'), List('d'))
aplicarMovimiento(c6, Dos(0)) == (List('a','b'), List('c'), List('d'))


// aplicarMovimientos
val m1 = (List('a','b','c','d'), List('e'), Nil)
aplicarMovimientos(m1, List(Uno(1), Uno(2))) ==
  List((List('a','b','c','d'), List('e'), Nil),
    (List('a','b','c'), List('d','e'), Nil),
    (List('a'), List('b','c','d','e'), Nil))

val m2 = (List('a','b'), List('c'), List('d'))
aplicarMovimientos(m2, List(Uno(8), Dos(1))) ==
  List((List('a','b'), List('c'), List('d')),
    (Nil, List('a','b','c'), List('d')),
    (Nil, List('a','b','c'), List('d')))

val m3 = (List('a'), List('b','c','d'), List('e','f'))
aplicarMovimientos(m3, List(Uno(-2), Dos(-1))) ==
  List((List('a'), List('b','c','d'), List('e','f')),
    (List('a','b','c'), List('d'), List('e','f')),
    (List('a','b','c','e'), List('d'), List('f')))

val m4 = (List('a','b','c'), List('d'), List('e'))
aplicarMovimientos(m4, List(Dos(2), Dos(-2))) ==
  List((List('a','b','c'), List('d'), List('e')),
    (List('a'), List('d'), List('b','c','e')),
    (List('a','b','c'), List('d'), List('e')))

val m5 = (List('a','b'), List('c'), List('d'))
aplicarMovimientos(m5, List(Uno(0), Dos(0))) ==
  List((List('a','b'), List('c'), List('d')),
    (List('a','b'), List('c'), List('d')),
    (List('a','b'), List('c'), List('d')))


// definirManiobra