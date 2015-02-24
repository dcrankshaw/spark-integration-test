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
import io.dropwizard.Configuration
// import io.dropwizard.Application
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment
import javax.validation.constraints.NotNull
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
import com.massrelevance.dropwizard.ScalaApplication
import com.massrelevance.dropwizard.bundles.ScalaBundle
import com.fasterxml.jackson.annotation.JsonProperty


class TestConf extends Configuration {
  // @NotNull val test: Integer = 4
  val sparkMaster: String = "NoSparkMaster"

}

@Path("/sparkprog")
class SparkProgResource(sparkMaster: String) {

  @GET
  def runSpark: String = {
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
    "Hello World"

  }

}



object TestApp extends ScalaApplication[TestConf] {

  val sparkMaster = "spark://ec2-54-87-179-93.compute-1.amazonaws.com:7077"


    override def initialize(bootstrap: Bootstrap[TestConf]) {
        bootstrap.addBundle(new ScalaBundle)

    }


    override def run(conf: TestConf, env: Environment) {

        // env.jersey().register(new SparkProgResource(conf.sparkMaster))
        env.jersey().register(new SparkProgResource(sparkMaster))
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


