package com.kal.leet
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.spark.sql.SparkSession

object LivyRest {


  val livyUrl = "http://<livy_server>:<livy_port>/"
  val spark = SparkSession.builder().appName("Livy Example").getOrCreate()

  // Define the Spark job to be submitted
  val code =
    """
      val data = Seq(("Alice", 1), ("Bob", 2), ("Charlie", 3))
      val df = spark.createDataFrame(data).toDF("name", "age")
      df.show()
      spark.stop()
  """

  // Build the request to submit the job
  val postRequest = new HttpPost(livyUrl + "sessions/0/statements")
  postRequest.addHeader("Content-Type", "application/json")
  val requestBody = s"""{"code": "$code"}"""
  postRequest.setEntity(new StringEntity(requestBody))

  // Submit the job to the Livy server
  val httpClient = HttpClientBuilder.create().build()
  val response = httpClient.execute(postRequest)

  // Get the job result
  val resultUrl = livyUrl + "sessions/0/statements/" + response.getFirstHeader("Location").getValue().split("/").last
  val getResultRequest = new HttpGet(resultUrl)
  getResultRequest.addHeader("Content-Type", "application/json")
  val getResultResponse = httpClient.execute(getResultRequest)
  val result = scala.io.Source.fromInputStream(getResultResponse.getEntity().getContent()).mkString

  println(result)


}
