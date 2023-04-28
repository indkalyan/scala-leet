package com.kal.leet




object RemoveElement extends App{

  var pointer = 0
  var nums: Array[Int]= Array(0,1,1,2,3,3,3,3,5,5,5,6)
  val value: Int =5

  for(index <- nums.indices){
    if(nums(index) != value){
      nums(pointer) =nums(index)
      pointer+=1
    }
  }
}
