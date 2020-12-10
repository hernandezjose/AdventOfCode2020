package days

import scala.io.Source

object Day01 {

  def main(args: Array[String]): Unit = {
    val lines: Iterator[String] = Source.fromResource("Day01_expense_report.txt").getLines
    val numbers = lines.map(_.toInt).toArray

    // part 1
    val numbersSet = numbers.toSet
    val pairs = numbers.filter(n => numbers.contains(2020 - n))
    println("PART ONE")
    println(pairs.mkString("Array(", ", ", ")"))

    // part 2
    println("PART TWO")
    val n = numbers.length
    for (i <- 0.until(n - 2)) {
      for (j <- (i + 1).until(n-1)) {
        for (k <- (j + 1).until(n)) {
          val total = numbers.apply(i) + numbers.apply(j) + numbers.apply(k)
          if (total == 2020) {
            println("Indexes: " + (i, j, k))
            println("Numbers: " + (numbers.apply(i), numbers.apply(j), numbers.apply(k)))
          }
        }
      }
    }
  }
}
