package com.kal.leet

import scala.collection.mutable.ListBuffer

object PermutationOfString extends App {
  def permutations(str: String): List[String] = {
    if (str.length == 1) {
      List(str)
    } else {
      val result = ListBuffer[String]()
      for (i <- 0 until str.length) {
        val first = str.charAt(i)
        val rest = str.substring(0, i) + str.substring(i + 1)
        for (permutation <- permutations(rest)) {
          result.append(first + permutation)
        }
      }
      result.toList
    }
  }

  permutations("love").map(println _) //4! elements

}
