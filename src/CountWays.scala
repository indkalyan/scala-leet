package com.kal.leet

object CountWays extends App {


  //Write an algorithm that finds the number of ways in which you can move N meters
  // by doing jumps of 1, 2, 3, 4, or 5 meter lengths. Assume that
  // N can be a very large number. What is the resulting complexity?

  println(countWays(5))


  def countWays(n: Int): Int = {
    val dp = Array.fill(n + 1)(0)
    dp(0) = 1
    for (i <- 1 to n) {
      for (j <- 1 to 5) {
        if (i >= j) {
          dp(i) += dp(i - j)
        }
      }
    }
    dp(n)
  }

}
