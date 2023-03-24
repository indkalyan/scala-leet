package com.kal.leet

object RomanToInt extends App {


  println(romanToInt(("IV")))
  println(romanToInt(("MCMXCIV")))
  println(romanToInt(("LVIII")))
  println(romanToInt(("III")))


  def romanToIntSlower(s: String): Int = {
    val m: Map[Char, Int] = Map('I' -> 1,
      'V' -> 5,
      'X' -> 10,
      'L' -> 50,
      'C' -> 100,
      'D' -> 500,
      'M' -> 1000)
    var result: Int = 0
    for (i <- 0 to s.length - 1) {
      if (i < s.length - 1 && m(s.charAt(i)) < m(s.charAt(i + 1))) result = result - m(s.charAt(i)) else result = result + m(s.charAt(i))
    }
    result
  }

  def romanToInt(s: String): Int = {
    var result: Int = 0
    for (i <- 0 to s.length - 1) {
      s.charAt(i) match {
        case 'I' => result = result + 1
        case 'V' => if (i > 0 && s.charAt(i - 1) == 'I') result = result + 3 else result = result + 5
        case 'X' => if (i > 0 && s.charAt(i - 1) == 'I') result = result + 8 else result = result + 10
        case 'L' => if (i > 0 && s.charAt(i - 1) == 'X') result = result + 30 else result = result + 50
        case 'C' => if (i > 0 && s.charAt(i - 1) == 'X') result = result + 80 else result = result + 100
        case 'D' => if (i > 0 && s.charAt(i - 1) == 'C') result = result + 300 else result = result + 500
        case 'M' => if (i > 0 && s.charAt(i - 1) == 'C') result = result + 800 else result = result + 1000
      }
    }
    result
  }
}
