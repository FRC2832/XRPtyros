# Sensors and NetworkTables
In this section, we are going to talking about the sensors on the XRP robot, and also how to display data from the robot on a dashboard so we can see what is happening on the robot.

## Introduction to NetworkTables
Simplifying a lot of things, NetworkTables is a distributed key-value dictionary.  What this means, data is stored using a String that is the name of the object (aka the "key"), and it return the value repesented, which could be a number, string, array, or a class.  All of the participants in the NetworkTables protocol see all the changes (the distributed part).

Keys are organized into "Directories" using the / seperator, but for these examples, we will not be using that.  We will be using the simplier `SmartDashboard.putNumber()` interfaces, which will automatically put all our data in the /SmartDashboard/ directory.  When the robots have more data, it would be appropriate to organize the data in folders, but that is beyond the scope of this lesson.

For further reading, you can follow the official WpiLib documentation at: 