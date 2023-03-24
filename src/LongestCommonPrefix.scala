package com.kal.leet

object LongestCommonPrefix extends App {

  var strs: Array[String] = Array("flower", "flower", "flowgr")

  println(longestCommonPrefix(strs))

  def longestCommonPrefix(strs: Array[String]): String = {
    var count: Int = 0
    scala.util.control.Breaks.breakable {
      while (true) {
        for (i <- 0 to strs.length - 2) {
          if (!(strs(i).length > count &&
            strs(i + 1).length > count &&
            (strs(i).charAt(count) == strs(i + 1).charAt(count))))
            scala.util.control.Breaks.break
        }
        count += 1
      }
    }
    return (strs(0).substring(0, count))
  }


}
