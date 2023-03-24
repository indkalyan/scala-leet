package com.kal.leet

object IntToRoman extends App{
  var n:Int=58
  val sb:StringBuffer = new StringBuffer()

  val roman = List(
      (1000, "M"),
      (900, "CM"),
      (500, "D"),
      (400, "CD"),
      (100, "C"),
      (90, "XC"),
      (50, "L"),
      (40, "XL"),
      (10, "X"),
      (9, "IX"),
      (5, "V"),
      (4, "IV"),
      (1, "I"))
  for(i <- 0 to 12){
    while(n-roman(i)._1 >= 0) {    //subtract each roman from n, if its greater than zero, n=n-roman
      n-=roman(i)._1
      sb.append(roman(i)._2)
    }
  }

  println(sb.toString)
}
