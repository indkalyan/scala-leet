package com.kal.leet

import com.cloudera.livy._

object LivyClient {



  // Create a new LivyClient instance
  val livyUrl = "http://<livy_server>:<livy_port>/"
  val client = new LivyClientBuilder().setURI(new URI(livyUrl)).build()

  // Submit a Spark job to the Livy server
  val job = client.submit(new LivyScalaJob {
    def call(livyContext: LivyScalaContext): Any = {
      val data = Seq(("Alice", 1), ("Bob", 2), ("Charlie", 3))
      val df = livyContext.sparkSession.createDataFrame(data).toDF("name", "age")
      df.show()
      livyContext.sparkSession.stop()
    }
  })

  // Wait for the job to complete
  val result = job.get()

  println(result)


}
