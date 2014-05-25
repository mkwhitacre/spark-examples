# Overview

The common jar holds a basic model and parsing logic for the Lego Data.  

# Using in Spark Shell

The functionality of the common project could be useful for anyone wanting to play around with the data in Spark Shell.  To make the functionality available inside of the shell, the jar needs to be proactively be added to the classpath.  This can be done with the following command line.

```
ADD_JARS=/path/to/examplejar/spark-example-common-1.0-SNAPSHOT.jar bin/spark-shell
```
