package com.kal.leet

object MergeTwoSortedArray extends App {

  val arr1 = Array(1, 3, 5, 9, 19, 35, 94)
  val arr2 = Array(11, 31, 51, 91, 191, 351, 941)


  val result = mergeArrays(arr1, arr2)

  result.map(println _)


  def mergeArrays(arr1: Array[Int], arr2: Array[Int]): Array[Int] = {
    val result = new Array[Int](arr1.length + arr2.length)
    var pointer1 = 0
    var pointer2 = 0
    var resultPointer = 0
    while (pointer1 < arr1.length && pointer2 < arr2.length) {
      if (arr1(pointer1) < arr2(pointer2)) {
        result(resultPointer) = arr1(pointer1)
        pointer1 += 1
      } else {
        result(resultPointer) = arr2(pointer2)
        pointer2 += 1
      }
      resultPointer += 1
    }
    while (pointer1 < arr1.length) {
      result(resultPointer) = arr1(pointer1)
      pointer1 += 1
      resultPointer += 1
    }
    while (pointer2 < arr2.length) {
      result(resultPointer) = arr2(pointer2)
      pointer2 += 1
      resultPointer += 1
    }
    result
  }

}
