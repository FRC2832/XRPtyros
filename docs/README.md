# Topics

1. [Initialization of the XRP Robot](<initialization.md>)
2. [Controlling the Robot](<control-robot.md>)

TODO
* Robot Code Structure
* Sensors and NetworkTables
* PID Control
  * Drive distance
  * Turn with Gyro
* Velocity Control
* Line Following
* Drive to sensor distance
* Command Framework
  * Commands and Subsystems
  * Sequence combinations
* SysID
* PathPlanner
* Vision?
* Git

# FAQs
### Why did we make this project?
In our research, there is no documentation to help students learn how to use WpiLib with the XRP robots.  There are actually some really good guides out there, but they all use alternative languages.  This adapts those guides to use the programming style that FRC robots use.

Also, we recognize WpiLib has an example project for XRP, but it just has everything implemented with little explanation what each part does.  It's just a finished example.  We thought we would implement something that explains as many lines of code as possible, and shows how the logic builds on itself.

### Why are all the code examples images?
I feel like students would learn more from typing out the lessons to make the code work.  That way, they actually think about each character they type, and think why all the lines are needed.

### Why are their "errors" in some of the examples?
Like the last question, I've found that you learn more from failing before succeeding.  By adding in a couple problems where the lesson "fails", there is a chance to diagnose and fix the errors.  This might also lead to students learning to think more critically, as everyone makes mistakes, but the real lessons are learning from them.

# Resources
* https://docs.wpilib.org/en/stable/docs/xrp-robot/getting-to-know-xrp.html
* https://introtoroboticsv2.readthedocs.io/en/latest/course/delivery_challenge/index.html - Inspiration for robot challenge, also a great guide for these robots in Python
* https://bovlb.github.io/frc-tips/