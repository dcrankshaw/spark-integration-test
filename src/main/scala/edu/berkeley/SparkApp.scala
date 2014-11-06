package edu.berkeley

import java.nio.ByteBuffer
import scala.util._
import java.io.IOException
import java.nio.ByteBuffer
import scala.collection.immutable.HashMap
import java.nio.ByteBuffer
import java.io.ByteArrayOutputStream
import scala.collection.immutable.TreeMap
import org.apache.spark._
import org.apache.spark.rdd._
import org.apache.spark.SparkContext._

object SparkApp {

  def main(args: Array[String]) {

    val sparkMaster = args(0)
    val sparkHome = "/root/spark"
    println("Starting spark context")
    val sc = new SparkContext(sparkMaster, "SparkTestApp", sparkHome,
        SparkContext.jarOfObject(this).toSeq)
    println("Parallelizing data")
    val data = sc.parallelize((0 to 5000))
    println(s"Counting data ${data.count}")
    println(s"Top is: ${data.top(10)}")
    sc.stop()
    println("Done")
    System.exit(0)

  }
}


