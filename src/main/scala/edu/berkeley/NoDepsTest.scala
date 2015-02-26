// package edu.berkeley
//
// import org.apache.spark.rdd.RDD
// import org.apache.spark.SparkContext._
// import org.apache.spark.SparkContext
// import org.apache.spark.SparkConf
//
// object RunSpark {
//
//   def main(args: Array[String]) {
//     val sparkMaster = "spark://ec2-54-205-168-96.compute-1.amazonaws.com:7077"
//     val sparkHome = "/root/spark"
//     println("Starting spark context")
//     val conf = new SparkConf()
//       .setMaster(sparkMaster)
//       .setAppName("SparkTestApp")
//       .setJars(SparkContext.jarOfObject(this).toSeq)
//       .setSparkHome(sparkHome)
//       .set("spark.akka.logAkkaConfig", "true")
//     val sc = new SparkContext(sparkMaster, "SparkTestApp", sparkHome,
//         SparkContext.jarOfObject(this).toSeq)
//     println("Parallelizing data")
//     val data = sc.parallelize((0 to 5000))
//     println(s"Counting data ${data.count}")
//     println(s"Top is: ${data.top(10)}")
//     sc.stop()
//     println("Done")
//     "Hello World"
//
//
//   }
//
// }
