package days

import scala.io.Source

object Day03 {

  def main(args: Array[String]): Unit = {
    val lines: Array[String] = Source.fromResource("Day03_trees_map.txt").getLines.toArray
    val (numRows, numCols) = (lines.length, lines.head.length)

    val slopes = List((1, 1), (3, 1), (5, 1), (7, 1), (1, 2))

    val encountersBySlope = slopes.map(slope => {
      val (rightCols, downRows) = slope
      val encounters = (0.until(numRows / downRows)).count(steps => {
        val col = (steps * rightCols) % numCols
        val row = steps * downRows
        // collided into a tree
        lines.apply(row).charAt(col).toString.equals("#")
      })
      (slope, encounters.toLong)
    })
    println(encountersBySlope)
    println(encountersBySlope.map(_._2).product)
  }
}
