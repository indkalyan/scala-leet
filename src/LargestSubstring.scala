package com.kal.leet

object LargestSubstring extends App {

  val s: String = "abcdabcdekdhasabb"

  var sub = ""
  var res = 0
  var duplicate: Int = -1

  for (c <- s) {
    if (sub.contains(c)) {
      duplicate = sub.indexOf(c) //find the duplicate
      sub = sub.substring(duplicate + 1) + c //slide the window
    } else {
      sub += c //add the char
      res = Math.max(res, sub.length) //count the max
    }
  }
  println(res)

}
