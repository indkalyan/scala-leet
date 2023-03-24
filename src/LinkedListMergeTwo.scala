package com.kal.leet

object LinkedListMergeTwo extends App {

  var l1: ListNode = ListNode(1, ListNode(31, ListNode(51, ListNode(171, ListNode(192, ListNode(211, ListNode(313)))))))
  var l2: ListNode = ListNode(2, ListNode(41, ListNode(116, ListNode(228))))

  var list1: ListNode = ListNode(5)
  var list2: ListNode = ListNode(1, ListNode(2, ListNode(4)))

  var result = mergeTwoRecursion(list1, list2)
  while (result != null) {
    println(result.x)
    result = result.next
  }

  def mergeTwo(list1: ListNode, list2: ListNode): ListNode = {
    var n1 = list1
    var n2 = list2
    var temp, result: ListNode = ListNode()
    result = temp

    while (n1 != null && n2 != null) {
      if (n1.x < n2.x) {
        temp.next = ListNode(n1.x)
        n1 = n1.next
      } else {
        temp.next = ListNode(n2.x)
        n2 = n2.next
      }
      temp = temp.next
    }
    if (n1 != null || n2 != null)
      temp.next = if (n1 != null) n1 else n2

    result.next
  }

  def mergeTwoRecursion(l1: ListNode, l2: ListNode): ListNode = {
    if (l1 == null) return l2
    if (l2 == null) return l1
    if (l1.x < l2.x) {
      l1.next = mergeTwoRecursion(l1.next, l2) //both the lists are sorted on condition
      l1
    } else {
      l2.next = mergeTwoRecursion(l1, l2.next)
      l2
    }

  }
}
