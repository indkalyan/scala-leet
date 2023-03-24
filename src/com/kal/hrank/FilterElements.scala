package com.kal.leet
package com.kal.hrank

/**
 * @see https://www.hackerrank.com/challenges/filter-elements/problem
 */
object FilterElements {

  def main(args: Array[String]): Unit = {

    val stdin = scala.io.StdIn
    //val resultList = new ListBuffer[String]()
    val output = (1 to stdin.readInt()).map(_ => {
      val element = stdin.readLine().split("\\s+").map(_.trim.toInt).last
      val inputArr = stdin.readLine().split("\\s+").map(_.trim.toInt)

      filterArray(inputArr, element)
    }).map(_.mkString(" "))
    output.map(x => if (x.length > 0) x else "-1").foreach(println)

  }

  def filterArray(arr: Array[Int], element: Int): Array[Int] = {
    //arr.zip(arr.map(x=>(arr.count(_ == x)))).filter(t=> t._2 >=2).distinct.map(t=>t._1).mkString(" ")
    arr
      .zip(
        arr
          .map(x => arr.count(_ == x)))
      .filter(t => t._2 >= element)
      .distinct
      .map(t => t._1)
  }
}
