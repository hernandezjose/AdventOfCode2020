package days

import com.sun.org.apache.xpath.internal.functions.FuncFalse

import scala.io.Source

object Day02 {

  def main(args: Array[String]): Unit = {
    val lines: Array[String] = Source.fromResource("Day02_passwords_list.txt").getLines.toArray

    // part 1
    val goodPasswordsPart1 = lines.count(line => {
      val tokens = line.split(" ")
      val letter = tokens.apply(1).dropRight(1)
      val occurrences = tokens.apply(2).count(_.toString.equals(letter))
      val minMax = tokens.apply(0).split("-").map(_.toInt)
      minMax.head <= occurrences && occurrences <= minMax.last
    })
    println(goodPasswordsPart1)

    // part 2
    val goodPasswordsPart2 = lines.filter(line => {
      val tokens = line.split(" ")
      val letter: Char = tokens.apply(1).dropRight(1).toCharArray.head
      val minMax = tokens.apply(0).split("-").map(_.toInt - 1)

      val password = tokens.apply(2)
      val headMatch = (password.charAt(minMax.head) == letter)
      val lastMatch = (password.charAt(minMax.last) == letter)

      headMatch ^ lastMatch
    })
    println(goodPasswordsPart2.length)
  }
}
