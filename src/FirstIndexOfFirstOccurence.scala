package com.kal.leet

//Input: haystack = "sadbutsad", needle = "sad"
object FirstIndexOfFirstOccurrence extends App{




  val p1 = "abcdabeabf" //0000120120
  val p2 = "abcdeabfabc" //00000120123
  val p3 = "aabcadaabe" //0100101230
  val p4 = "aaaabaacd" //012301200
  val p5 = "abcdbcdbe"
  val p6 = "aabaabaaa" //010123452

  val result = kmpPreprocess(p6)
  result.map(print _)

  def kmpPreprocess(pattern: String): Array[Int] = {
    val table = new Array[Int](pattern.length())
    var i = 0
    var j = 1
    table(0) = 0

    while (j < pattern.length()) {
      if (pattern.charAt(i) == pattern.charAt(j)) {
        // If characters match, increment both i and j
        i += 1
        table(j) = i
        j += 1
      } else {
        // If characters don't match, backtrack i based on the partial match table
        if (i != 0) {
          i = table(i - 1)
        } else {
          table(j) = 0
          j += 1
        }
      }
    }

    table
  }

  def kmpSearch(text: String, pattern: String): List[Int] = {
    val m = pattern.length()
    val n = text.length()
    val table = kmpPreprocess(pattern)
    var i = 0
    var j = 0
    var matches = List[Int]()

    while (i < n) {
      if (pattern.charAt(j) == text.charAt(i)) {
        i += 1
        j += 1
      }
      if (j == m) {
        // Found a match
        matches = matches :+ (i - j)
        j = table(j - 1)
      } else if (i < n && pattern.charAt(j) != text.charAt(i)) {
        // Use partial match table to skip ahead in text
        if (j != 0) {
          j = table(j - 1)
        } else {
          i += 1
        }
      }
    }

    matches
  }


}
