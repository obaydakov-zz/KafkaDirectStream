package kafka_stream

import kafka.serializer.StringDecoder
import org.apache.hadoop.yarn.util.RackResolver
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Durations, StreamingContext}
import org.apache.kafka.common.serialization.StringDeserializer
import org.spark_project.guava.eventbus.Subscribe

class KafkaDirectStream {

}

object KafkaDirectStream {

  def main(args: Array[String]): Unit = {

    Logger.getLogger(classOf[RackResolver]).getLevel
    Logger.getLogger("org").setLevel(Level.OFF)
    Logger.getLogger("akka").setLevel(Level.OFF)

    val spark: SparkSession = SparkSession.builder()
      .appName("Consume Kafka topics")
      .master("spark://127.0.01:7077")
      .getOrCreate()

    val sc = spark.sparkContext
    val sqlContext = spark.sqlContext
    val ssc = new StreamingContext(sc, Durations.seconds(2))


    // ******************



    // ******************

    // sc.parallelize(Seq(1,2,3)).collect().foreach(println)

    val brokers = "localhost:9092"
    val topics = "carriers"

    // val sparkConf = new SparkConf().setMaster("local").setAppName("DirectKafkaWordCount")


    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String,String]("metadata.broker.list" -> brokers)

    val stream = KafkaUtils
      .createDirectStream[String,String,StringDecoder,StringDecoder](ssc,kafkaParams, topicsSet)


    val lines = stream.map(_._2)

    //val words = lines.flatMap(_.split(" "))

    //val lines = stream.map(_.value)

    stream.foreachRDD(rdd => {
      rdd.saveAsTextFile("hdfs://10.200.99.197:8020/data/raw/carriers")
    })

    //val wordCounts = words.map(x=>(x,1)).reduceByKey(_+_)
    lines.dstream().saveAsTextFiles("hdfs://10.200.99.197:8020/user/chanchal.singh/wordCounts", "result");
    lines.print()
    ssc.start()
    ssc.awaitTermination()*/


  }

}

