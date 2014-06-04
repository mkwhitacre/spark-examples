# Overview

The purpose of this project is to serve as a companion project to a conference project about [Apache Spark](http://spark.apache.org/).

The talk is intended to give a general overview to the project, its capabilities as well as how it fits into the current
Big Data ecosystem, specifically with regard to real time and batch processing.

# Data

The data included in this project was acquired from [Bricksets](http://brickset.com/sets) in their provided CSV format.
The purpose of the data is simply to give a simple and easy to understand dataset for a consumer to play around and experiment.

Visit Brickset to show your appreciation for the data and to get updates to the Lego data.

# Example Problem

Part of the purpose of the project is to allow consumers to review and experiment solving problems with Spark and contrast
the development effort inside of the various frameworks.  For that purpose a derived problem was created based on the available
data.  For the problem specifically the code must accomplish the following:

1. Read the raw CSV files
1. Filter out the header line from each file
1. Convert the CSV line into a model object (LegoSet)
1. Filter out values with missing US Price data
1. Filter out values with missing Pieces data
1. Calculate the price per piece for each set (e.g. USPrice/Number of Pieces)
1. Display the results to the driver.

# Spark Example

The [Spark Scala Example](spark-scala-example/README.md) is intended to demonstrate how the problem can be solved using
the available Spark Scala API.

# Crunch And Spark Example

[Apache Crunch](http://crunch.apache.org/) is a nice abstraction layer on top of MapReduce.  Additionally those abstractions
can be ran on top of Spark with minimal changes to the code.  The [spark-crunch-example](spark-crunch-example/README.md)
demonstrates how processing through Crunch can easily transfer from one runtime to the other.

# Starting Spark

The work done for this example uses a [standalone Spark cluster](http://spark.apache.org/docs/latest/spark-standalone.html) running on [Cloudera's CDH4 quickstart VM](http://www.cloudera.com/content/cloudera-content/cloudera-docs/DemoVMs/Cloudera-QuickStart-VM/cloudera_quickstart_vm.html).  After the distribution was installed manually the cluster was started with the following commands:

```
sbin/start-all.sh
```

Some problems were encountered with starting the workers/slaves.  Specifically the worker was reporting a NoClassDefFoundError for Scala classes. The reason for this problem was not found but has to do with either the installed JDK or the assembly on the classpath.  A work around for starting the cluster:


```
sbin/start-master.sh
./bin/spark-class org.apache.spark.deploy.worker.Worker spark://localhost.localdomain:7077
```

When viewing the master at ```http://localhost:8080``` you should see the connected worker and resources.

# See Also

This project is intended to be a companion to an [intro to intermediate level presentation](https://docs.google.com/presentation/d/1ESPDmvru-DOMCi4TLwzSVQ2IWjvhTY-8DPloivP2xd4/edit?usp=sharing) on Apache Spark.