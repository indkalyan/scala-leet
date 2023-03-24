package com.kal.leet

object TwoPointerPalindrom extends App {
  print(checkPalindrom("abvba"))

  def checkPalindrom(test: String): Boolean = {
    var left: Int = 0
    var right: Int = test.length - 1
    while (left < right) {
      if (test.charAt(left) != test.charAt(right))
        return false
      left += 1
      right -= 1
    }
    true
  }
}
