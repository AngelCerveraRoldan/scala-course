class Rational(x: Int, y: Int) {
    require(y > 0, "The denominator must be a greater than 0")
    
    private def gcf(a: Int, b: Int): Int = {
        if b == 0 then a else gcf(b, a % b)
    }

    val nominator = x / gcf(x.abs, y)
    val denominator = y / gcf(x.abs, y)

    def * (n: Rational): Rational = {
        Rational(nominator * n.nominator, 
                denominator * n.denominator)
    }

    def / (n: Rational): Rational = {
        // Multiply by inverse of n
        this * Rational(n.denominator, n.nominator)
    }

    def + (n: Rational): Rational = {
        Rational(nominator * n.denominator + n.nominator * denominator,
                denominator * n.denominator)
    }

    def - (n: Rational): Rational = {
        this + Rational(- n.nominator, n.denominator)
    }


    override def toString() = s"$nominator/$denominator"
}


val a = Rational(2, 3)
val b = Rational(4, 9)

a + b
a - b
a * b
a / b

2 * (-7) % 2