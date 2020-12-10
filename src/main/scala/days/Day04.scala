package days

import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.util.Try

object Day04 {

  def main(args: Array[String]): Unit = {
    val lines: Iterator[String] = Source.fromResource("Day04_passports.txt").getLines

    var passportsBuffer = new ListBuffer[Map[String,String]]()
    val passport = new ListBuffer[(String, String)]()

    lines.foreach(line => {
      if (line.trim().isEmpty) {
        passportsBuffer += (passport.toMap)
        passport.clear()
      } else {
        val pairs = line.split(" ").map(_.split(":")).map(kv => (kv.head, kv.last))
        passport.appendAll(pairs)
      }
    })
    passportsBuffer += (passport.toMap)

    // check validity
    val requiredKeys = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid") // "cid"
    val validPassports = passportsBuffer.toList.filter(m => {
      m.keySet.intersect(requiredKeys).size == requiredKeys.size
    })

    println(validPassports.length)

    // part 2
    val checks = new ListBuffer[Boolean]()
    val extraValidPassports = validPassports.filter(p => {
      checks.clear()
      val byr = p.apply("byr").toInt
      checks += ((1920 <= byr && byr <= 2002))
      val iyr = p.apply("iyr").toInt
      checks += ((2010 <= iyr && iyr <= 2020))
      val eyr = p.apply("eyr").toInt
      checks += ((2020 <= eyr && eyr <= 2030))
      val hgt = p.apply("hgt")
      val isHgtValid: Boolean =
        if (hgt.endsWith("cm")) {
          val v = Try(hgt.dropRight(2).toInt)
          v.isSuccess && 150 <= v.get && v.get <= 193
        } else if (hgt.endsWith("in")) {
          val v = Try(hgt.dropRight(2).toInt)
          v.isSuccess && 59 <= v.get && v.get <= 76
        } else {
          false
        }
      checks += (isHgtValid)
      val hcl = p.apply("hcl")
      checks += (hcl.matches("#[0-9a-f]{6}"))
      val ecl = p.apply("ecl")
      checks += (Set("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(ecl))
      val pid = p.apply("pid")
      checks += (pid.matches("[0-9]{9}"))
      // ensure all are true
      checks.forall(_ == true)
    })

    println(extraValidPassports.take(10))
    println(extraValidPassports.length)
  }
}
