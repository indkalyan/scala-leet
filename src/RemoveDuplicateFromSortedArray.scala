package com.kal.leet

object RemoveDuplicateFromSortedArray extends App {

  //
  val nums = Array(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
  //val nums = Array(0, 1, 2, 3, 4)
  println(removeDuplicates(nums))
  nums.map(println(_))

  def removeDuplicates(nums: Array[Int]): Int = {
    var slowPointer = 0
    for (fastPointer <- 1 until nums.length) {
      if (nums(slowPointer) < nums(fastPointer)) {
        slowPointer += 1
      }
      nums(slowPointer) = nums(fastPointer)
    }
    slowPointer + 1
  }


  def removeDuplicatesSimplified(nums: Array[Int]): Int = {
    var slow, fast = 0
    while (fast < nums.length) {
      if (nums(slow) == nums(fast)) {
        fast += 1
      }
      else {
        nums(slow + 1) = nums(fast)
        fast += 1
        slow += 1
      }
    }
    slow + 1
  }


  def removeDuplicatesNew(nums: Array[Int]): Int = {
    var i = 1
    var ii = 1

    while (i < nums.size) {
      if (nums(i) != nums(i - 1)) {
        nums(ii) = nums(i)
        ii = ii + 1
      }
      i = i + 1
    }

    return ii
  }
}
