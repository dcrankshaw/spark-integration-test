package edu.berkeley

import java.nio.ByteBuffer
import scala.util._
import java.io.IOException
import java.nio.ByteBuffer
import scala.collection.immutable.HashMap
import java.nio.ByteBuffer
import java.io.ByteArrayOutputStream
import scala.collection.immutable.TreeMap
// import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.SparkContext._
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import io.dropwizard.Configuration
import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import javax.validation.constraints.NotNull
import com.fasterxml.jackson.module.scala.DefaultScalaModule
// import org.hibernate.validator.constraints.NotEmpty
//
// import javax.validation.Valid
import javax.ws.rs.GET
// import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
// import javax.ws.rs.QueryParam
// import javax.ws.rs.Consumes
// import javax.ws.rs.Produces
// import javax.ws.rs.core.MediaType
import java.util._
// import com.massrelevance.dropwizard.ScalaApplication
// import com.massrelevance.dropwizard.bundles.ScalaBundle
import com.fasterxml.jackson.annotation.JsonProperty


class TestConf extends Configuration {
  // @NotNull val test: Integer = 4
  val sparkMaster: String = "NoSparkMaster"

}

@Path("/sparkprog")
class SparkProgResource(sparkMaster: String) {

  @GET
  def runSpark: String = {
    val sparkHome = "/root/spark-1.3.0-bin-hadoop1"
    println("Starting spark context")
    val conf = new SparkConf()
      .setMaster(sparkMaster)
      .setAppName("SparkTestApp")
      .setJars(SparkContext.jarOfObject(this).toSeq)
      .setSparkHome(sparkHome)
      // .set("spark.akka.logAkkaConfig", "true")
    val sc = new SparkContext(sparkMaster, "SparkTestApp", sparkHome,
        SparkContext.jarOfObject(this).toSeq)
    println("Parallelizing data")
    val data = sc.parallelize((0 to 5000))
    println(s"Counting data ${data.count}")
    println(s"Top is: ${data.top(10)}")
    data.saveAsTextFile("hdfs://ec2-54-145-144-138.compute-1.amazonaws.com:9000/writetest/data")
    sc.stop()
    println("Done")
    "Hello World\n"

  }

}

object MyMain {

  final def main(args: Array[String]) {
    new TestApp().run(args)
  }

}

class TestApp extends Application[TestConf] {

  // val sparkMaster = "spark://ec2-54-87-179-93.compute-1.amazonaws.com:7077"



    override def initialize(bootstrap: Bootstrap[TestConf]) {
        // bootstrap.addBundle(new ScalaBundle)
       bootstrap.getObjectMapper.registerModule(new DefaultScalaModule()) 

    }


    override def run(conf: TestConf, env: Environment) {

        // env.jersey().register(new SparkProgResource(conf.sparkMaster))
        env.jersey().register(new SparkProgResource(conf.sparkMaster))
    }

}

// object SparkApp {
//
//   def main(args: Array[String]) {
//
//     val sparkMaster = args(0)
//     val sparkHome = "/root/spark"
//     println("Starting spark context")
//     val sc = new SparkContext(sparkMaster, "SparkTestApp", sparkHome,
//         SparkContext.jarOfObject(this).toSeq)
//     println("Parallelizing data")
//     val data = sc.parallelize((0 to 5000))
//     println(s"Counting data ${data.count}")
//     println(s"Top is: ${data.top(10)}")
//     sc.stop()
//     println("Done")
//     System.exit(0)
//
//   }
// }


