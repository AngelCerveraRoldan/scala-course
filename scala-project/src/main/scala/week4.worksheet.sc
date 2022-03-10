// For some reason this gives issues in IntelliJ, run on VSCode
// Pattern matching
trait Expr

case class Num(n: Int) extends Expr
case class Sum(e1: Expr, e2: Expr) extends Expr
case class Product(e1: Expr, e2: Expr) extends Expr
case class Var(v: String) extends Expr

def eval(e: Expr): Int = e match {
  case Num(n) => n
  case Sum(e1, e2) => eval(e1) + eval(e2)
  case Product(e1, e2) => eval(e1) * eval(e2)
  case Var(v) => throw Error("Variable cannot be evaluated")
}

def show(e: Expr): String = e match {
  case Num(n) => f"$n"
  case Sum(e1, e2) => f"${show(e1)} + ${show(e2)}"
  case Product(e1: Expr, e2: Expr) => f"${showP(e1)} * ${showP(e2)}"
  case Var(v) => v
}

def showP(e: Expr): String = e match {
  case e: Sum => f"(${show(e)})"
  case _ => show(e)
}