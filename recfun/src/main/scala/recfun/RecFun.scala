package recfun

import scala.annotation.tailrec

object RecFun extends RecFunInterface:

  def main(args: Array[String]): Unit =
    println("Pascal's Triangle")
    for row <- 0 to 10 do
      for col <- 0 to row do
        print(s"${pascal(col, row)} ")
      println()

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int =
    // If the row is 0 or c, then it must be 1 as the first and last numbers in each col are 1
    if c == 0 || c == r then 1 else pascal(c, r - 1) + pascal(c - 1, r - 1)


  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean =
    @tailrec
    def brackets(count: Int, chars: List[Char]): Boolean =
      val newCount =
        if chars.head == '(' then count + 1
        else if chars.head == ')' then count - 1
        else count

      if newCount < 0 then return false

      if chars.tail.nonEmpty then brackets(newCount, chars.tail)
      else newCount == 0

    brackets(0, chars)


  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int =
    val sortedCoins = coins.sorted
    println(s"$sortedCoins")

    var count = 0

    // Run function for every it in the coins list
    def add(total: Int, possibleCoins: List[Int], lastAdded: Int): Unit =
      if total == money then count += 1
      else if total < money then possibleCoins.foreach(x => {
        if x >= lastAdded then add(total + x, possibleCoins, x)
      })

    add(0, sortedCoins, 0)

    count