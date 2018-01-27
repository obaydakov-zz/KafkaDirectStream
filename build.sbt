import sbt.Resolver

name := "KafkaDirectStream"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-core_2.11" % "2.2.0" ,
  "org.apache.spark" % "spark-sql_2.11" % "2.2.0" ,
  "org.apache.spark" % "spark-streaming_2.11" % "2.2.0",
  "org.apache.spark" %% "spark-mllib" % "2.2.0",

  "org.apache.spark" % "spark-streaming-kafka_2.11" % "1.6.3"
)

resolvers += "Akka Repository" at "http://repo.akka.io/releases/"
resolvers += "Hortonworks Repository" at "http://repo.hortonworks.com/content/repositories/releases/"
resolvers += "Hortonworks Jetty Maven Repository" at "http://repo.hortonworks.com/content/repositories/jetty-hadoop/"
resolvers += Resolver.mavenLocal
resolvers += "central maven" at "https://repo1.maven.org/maven2/"
        