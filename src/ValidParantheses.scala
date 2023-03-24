package com.kal.leet

import java.util

object ValidParantheses extends App {

  var s: String = "[({})]"

  println(isValid(s))

  def isValid(s: String): Boolean = {
    val stk: util.Stack[Char] = new util.Stack
    for (i <- 0 to s.length - 1) {
      s.charAt(i) match {
        case '(' => stk.push(')')
        case '[' => stk.push(']')
        case '{' => stk.push('}')
        case _ => if (stk.isEmpty || stk.pop() != s.charAt(i)) return false
      }
    }
    stk.isEmpty

  }


}
