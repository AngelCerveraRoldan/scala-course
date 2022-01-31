package funsets

/**
 * This class is a test suite for the methods in object FunSets.
 *
 * To run this test suite, start "sbt" then run the "test" command.
 */
class FunSetSuite extends munit.FunSuite:

  import FunSets.*

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets:
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val evens: FunSet = (x: Int) => x % 2 == 0
    val odds: FunSet = (x: Int) => x % 2 != 0

  /**
   * This test is currently disabled (by using @Ignore) because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", remove the
   * .ignore annotation.
   */
  test("singleton set one contains one") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets:
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
  }
  test("union contains all elements of each set") {
    new TestSets:
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
  }

  test("Even and odds test") {
    new TestSets:
      val evenOddUnion = union(evens, odds) // All numbers
      val evenOddIntersection = intersect(evens, odds) // No numbers
      val mutipleOfFour = filter(evens, x => x % 4 == 0) // All multiples of four

      assert(contains(evenOddUnion, 1), "Even or odd")
      assert(contains(evenOddUnion, 2), "Even or odd")
      assert(contains(evenOddUnion, 465), "Even or odd")
      assert(contains(evenOddUnion, 0), "Even or odd")

      assert(!contains(evenOddIntersection, 1), "Even and odd")
      assert(!contains(evenOddIntersection, 2), "Even and odd")
      assert(!contains(evenOddIntersection, 465), "Even and odd")
      assert(!contains(evenOddIntersection, 0), "Even and odd")


      assert(contains(mutipleOfFour, 4), "Multiple of four")
      assert(contains(mutipleOfFour, 16), "Multiple of four")
      assert(!contains(mutipleOfFour, 6), "Multiple of four")

  }

  test("forall function test") {
    new TestSets:
      /*
      * Forall returns true if a predicate is true for all numbers between 1000 and -1000
      * that are in a set s
      * */

      // 0 is an even number not greater than 0
      // Also all even negative numbers
      assert(!forall(evens, x => x > 0), "All even numbers are not larger than 0")

      // Any odd number + 1 is an even number
      assert(forall(odds, (x: Int) => (x + 1) % 2 == 0), "An odd plus one is an even")
      assert(forall(odds, (x: Int) => (2 * x) % 2 == 0), "Two times an odd is an even")
  }

  test("exists function test") {
    new TestSets:
      assert(exists(odds, (x: Int) => x == 3), "Odds contain multiples of 3")
      assert(exists(evens, (x: Int) => x + 1 == 99), "98 is even")
      assert(!exists(evens, (x: Int) => x + 1 == 100), "99 isn't even")
  }

  test("map function test") {
    new TestSets:
      // Should just be even numbers
      val oddsPlusOne = map(odds, (x: Int) => x + 1)
      val multipleSix = map((x: Int) => x > 0, (y: Int) => 6 * y)

      assert(contains(oddsPlusOne, 2))
      assert(!contains(oddsPlusOne, 5))

      assert(contains(multipleSix, 12))
      assert(!contains(multipleSix, 15))
  }

  import scala.concurrent.duration.*
  override val munitTimeout = 10.seconds
