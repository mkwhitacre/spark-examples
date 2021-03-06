# Overview

The purpose of this project is to show an example Spark Application written in the Apache Spark [Scala API](http://spark.apache.org/docs/latest/scala-programming-guide.html).
The project should also demonstrate a way to generate a Spark endstate and how to submit the project to the cluster.

# How to Build

The project is setup to be built using Maven.  The project could be setup to use ```sbt``` but since this is part of
a reactor build it was easy to have it built with Maven.  To build this endstate simply run the

```mvn clean install```

command.  This project does depend on com.mkwhitacre.spark:spark-example-common so it must be built first.

The project actually assembles two output jars.  The first jar spark-scala-example-<version>.jar simply contains the code 
inside the project.  A second jar is generated in the target/scala-2.10 directory and is the assembly jar that should be submitted
to the spark cluster for processing (e.g. target/scala-2.10/spark-scala-example-1.0-SNAPSHOT-hadoop2.jar).

# How to Run

The project can be launched on the cluster using [spark-submit](http://spark.apache.org/docs/latest/submitting-applications.html).

If you do not have a cluster but want to quickly test or run in local mode you can use the following:

```
bin/spark-submit --class com.mkwhitacre.spark.scala.LegoCalculationDriver \
--master local /path/to/spark-scala-example-1.0-SNAPSHOT-hadoop2.jar /path/to/lego-data/*/*
```

If you do have a cluster to submit to the command would look something like this:

```
bin/spark-submit --class com.mkwhitacre.spark.scala.LegoCalculationDriver \
--master spark://localhost:7077 /path/to/spark-scala-example-1.0-SNAPSHOT-hadoop2.jar /path/to/lego-data/*/*
```

There are other means of submitting a jar to a cluster.  The goal of the ```spark-submit``` is that it handles
reading configuration from local files, providing dependencies such as Hadoop and Spark, and connection with whichever
resource manager on which the cluster is deployed.  The required properties you have to specify will depend on where
you are trying to deploy and what values are in the configuration files.