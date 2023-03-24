package com.kal.leet

import scala.math.Integral.Implicits._

object LinkedListAddTwoNumber extends App {

  var l1: ListNode = ListNode(9)
  l1.next = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9))))))

  var l2: ListNode = ListNode(9)
  l2.next = new ListNode(9, new ListNode(9, new ListNode(9)))

  //var result = addTwoNumbers(l1, l2)
  var result = addTwoNumbersRecusion(l1.next, l2.next)

  while (result != null) {
    println(result.x)
    result = result.next
  }


  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    var n1 = l1
    var n2 = l2
    var sum: Int = 0
    var temp, result: ListNode = ListNode()
    result = temp

    while (n1 != null || n2 != null || sum > 0) {
      if (n1 != null) {
        sum += n1.x
        n1 = n1.next
      }

      if (n2 != null) {
        sum += n2.x
        n2 = n2.next
      }
      temp.next = ListNode(sum % 10)
      sum = sum / 10 //carry
      temp = temp.next
    }
    result.next
  }

  def addTwoNumbersRecusion(l1: ListNode, l2: ListNode): ListNode = {
    return helper(l1,l2,0)
  }


  def helper(l1: ListNode, l2: ListNode, carry: Int): ListNode = {
    if(l1==null && l2==null && carry==0){
      return null
    }
    val (quotient, remainder) = ((if(l1 != null) l1.x else 0) +(if(l2 != null) l2.x else 0)+carry) /% 10
    var result = new ListNode(remainder)
    result.next = helper(if(l1 != null) l1.next else null,if(l2 != null) l2.next else null ,quotient)
      result
  }
}
  case class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
  }