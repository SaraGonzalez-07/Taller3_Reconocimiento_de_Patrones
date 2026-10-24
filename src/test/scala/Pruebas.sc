import ManiobrasTrenes._

val e1 = (List('a','b','c','d'), Nil, Nil)
val e2 = aplicarMovimiento(e1, Uno(2))   // (ab, cd, Nil)
val e3 = aplicarMovimiento(e2, Dos(3))   // (Nil, cd, ab)
val e4 = aplicarMovimiento(e3, Dos(-1))  // (a, cd, b)
val e5 = aplicarMovimiento(e4, Uno(-2))  // (acd, Nil, b)
