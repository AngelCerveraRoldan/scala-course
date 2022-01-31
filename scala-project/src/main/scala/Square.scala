import scala.annotation.tailrec

object Square extends App {
  def squareRoot(x: Double) = {
    def goodEnough(x: Double, guess: Double): Boolean = abs(square(guess) - x) < 0.01

    def improveGuess(x: Double, guess: Double) = (guess + (x / guess)) / 2

    @tailrec
    def squareRootIter(x: Double, guess: Double): Double =
      println(f"Update: " + guess)
      if goodEnough(x, guess) then guess
      else squareRootIter(x, improveGuess(x, guess))

    squareRootIter(x, 1)
  }

  def square(x: Double) = x * x  
  def abs(x: Double) = if x > 0 then x else -x 


  println(squareRoot(12))
}
