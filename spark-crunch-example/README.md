# Overview

The [Apache Crunch](http://crunch.apache.org/) project is a great framework for creating processing pipelines in an easy to
understand API.  It helps to eliminate some of the headaches or cognitive friction developer face when learning or approaching
scalable processing problems typically solved with Hadoop or MapReduce.  It addition to supporting MapReduce as an
execution engine it also supports distributing the processing to an Apache Spark cluster with [very minimal code changes](http://crunch.apache.org/user-guide.html#pipelines).
The purpose of this project is to demonstrate how the same processing code and assembly could be made to run on either
Hadoop's MapReduce platform or an Apache Spark cluster with only a single line of code difference.


It should be noted that at the version of dependencies being used in this project have not been completely certified.
To demonstrate the MR example we are utilizing Cloudera's distribution of Crunch which has not been certified against
the 1.0 version of Apache Spark.  Additionally Cloudera's latest release does not include some fixes to Apache Crunch
for Apache Spark.  As a whole this project is just for demonstration purposes only and not necessarily an ideal production
level assembly.

# Building

The project is setup to be built using Maven.  The project could be setup to use ```sbt``` but since this is part of
a reactor build it was easy to have it built with Maven.  To build this endstate simply run the

```mvn clean install```

command.  This project does depend on com.mkwhitacre.spark:spark-example-common so it must be built first.

The project actually assembles two output jars.  The first jar spark-crunch-example-<version>.jar simply contains the code
inside the project.  A second jar is generated in the target/scala-2.10 directory and is the assembly jar that should be submitted
to the spark cluster for processing (e.g. target/scala-2.10/spark-crunch-example-1.0-SNAPSHOT-hadoop2.jar).

# Standard Crunch MapReduce Example

The Crunch MapReduce Example is here to simply show how a common MapReduce based Pipeline that could be executed over the data.

## How to Run

This Crunch MapReduce is ran as any other normal MapReduce job is executed using the ```hadoop jar``` command.

```﻿
   hadoop jar path/to/jar/spark-crunch-example-1.0-SNAPSHOT-hadoop2.jar \
   com.mkwhitacre.spark.crunch.LegoCalculationHadoopDriver \
   file:///home/spark-examples/lego_data/*/*
```

The output of the jar should display the output should be printed to the screen.  This is not ideal for real processes but gives
the use immediate feedback that the calculations actually occurred.

# Crunch Spark Example

This example alters the Crunch Pipeline implementation only to use the SparkPipeline instead of the standard MRPipeline.

## How to Run

The project can be launched on the cluster using [spark-submit](http://spark.apache.org/docs/latest/submitting-applications.html).

If you do not have a cluster but want to quickly test or run in local mode you can use the following:

```
bin/spark-submit --class com.mkwhitacre.spark.scala.LegoCalculationSparkDriver \
--master local /path/to/spark-crunch-example-1.0-SNAPSHOT-hadoop2.jar /path/to/lego-data/*/*
```

If you do have a cluster to submit to the command would look something like this:

```
bin/spark-submit --class com.mkwhitacre.spark.scala.LegoCalculationSparkDriver \
--master spark://localhost:7077 /path/to/spark-crunch-example-1.0-SNAPSHOT-hadoop2.jar /path/to/lego-data/*/*
```

There are other means of submitting a jar to a cluster.  The goal of the ```spark-submit``` is that it handles
reading configuration from local files, providing dependencies such as Hadoop and Spark, and connection with whichever
resource manager on which the cluster is deployed.  The required properties you have to specify will depend on where
you are trying to deploy and what values are in the configuration files.
