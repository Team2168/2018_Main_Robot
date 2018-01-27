# 2018_Main_Robot
Code for the [FIRST PowerUp](https://youtu.be/HZbdwYiCY74) game. This readme provide all of the information required to get started and programming for the 2018 season. 

## Requirements for Robot
1. RoboRio must be flashed to latest 2018 image using USB (This only needs to be done once for the season): (https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144984-imaging-your-roborio)
2. Radio must be programed (https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/144986-programming-your-radio)
3. Roborio IP on ethernet must be set to static using web dashboard to : 10.21.68.2
4. Any IP Camera must be set to 10.21.68.90
5. Vision Processor: If Tegra must be set to 10.21.68.71 (for "TK1"), if Beablebone must be set to 10.21.68.33 (for "BB"), if android must be set to 10.21.68.46 (for "AD")

## Requirements for Students
1. In order to program you need to set up your Java development environment using eclipse. Follow instructions here: https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599679-installing-eclipse-c-java (If you already had a previous version of the plugin installed you can simply select help->check for updates)
2. Use JDK 8, and try to use latest Eclipse if possible
3. If you would like to have the driverstation on your computer as well then install NI Update suite, but this is not a requirement to run programs, only to flash robot images (https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599677-frc-driver-station-powered-by-ni-labview)
4. For returning students: understand what has changed in WPI Librarary since 2017 season (https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/801080-new-for-2018)
5. Understand how the robot is wired as it affects your code. (https://wpilib.screenstepslive.com/s/currentCS/m/getting_started/l/599673-wiring-the-frc-control-system)
## Cool things to know
### Radio
1. You can access radio web page by logging into http://10.21.68.1 root/admin
2. Roborio should always be plugged into the port on the radio labeled "18-24 vPOE" only!

### Roborio
1. You can access roborio diagnostics webpage by http://roboRIO-2168-FRC.local (using IE web browser) or http://10.21.68.2
2. You can program roborio over ethernet, usb, or wifi (if USB, NI Update suite needs to be installed to get usb drivers)
3. More information on the control system can be found at our controls website at http://controls.team2168.org
4. Files will be logged to /home/lvuser/Logs
5. You can ftp files to/from the roborio using filezilla, winscp, web browser, or your local file explorer at ftp://10.21.68.2:21
6. You can ssh into roborio using putty or console application at ssh 10.21.68.2:22 username:lvuser password: blank

### Dashboard (on driver station)
1. Java dashboard will open if Java is selected from the driverstation menu
2. Python dash (if it installed) will open if "default" dashboard is selected from drivestation menu
3. If smartdashboard doesn't update, but you have robot comms, in smart dash preferences toggle "use mDNS" until it does. 

#Repository Guidelines
##Branches
Our repository and workflow loosely follows the gitflow workflow. This workflow is simple and is one of the most popular workflows when using git with a large number of developers. More info: https://www.atlassian.com/git/tutorials/comparing-workflows#gitflow-workflow
- The master branch contains code that is known-working, has been tested, and can be deployed to a competition ready robot.
- The develop branch is our sandbox for integrating and testing new features and fixing problems. This isi the latests and greatest code, but it may have problems and needs to be checked out on the robot before being pushed into master. 
- Everything else is lumpped under feature/bugfix branches. When we need to add new capabilities, start by branching the latest code  in the develop branch.  

## Checklist for committing/pushing code
- Commit often and create detailed log messages that describe what and why you're making a change. Be specific.
- Review the changes you make before pushsing them. You should look through all the files being added/modified/removed before you commit.
- Always verify your code compiles before pushing to the repo. There shouldn't be any red-underlined text in your commits. Use the build button (Green triangle) at the top of eclipse to verify a build completes without error.
- Push your changes into a branch with a name that identifies what feature it is adding or problem it is addressing in the code.
- Never push to the master branch 
- After pushing your changes to the repo, verify you can see your changes in GitHub from a web browser.

#Robot Design
## Subsystems
### Drivetrain
- 4x VictorSP Motor controllers
..- Positive values move upwards / Negative values move down
- 2x Greyhill Encoders (1x left, 1x right)
- 1x Gyro (ADXRS453)

### Cube Intake
- 2x VictorSP motor controllers
..- Positive values move cube inwards / Negative values move cube outwards
- 1x Single Solenoid valve to open/close intake
..- Koff is intake open / Kon is intake closed
- 1x Double Solenoid valve to pivot/rotate the arm up/down 
..- Kforward is rotated down, Kreverse is rotated up (starting configuration)
- 1x SHARP IR sensor to detect the presence of a cube

### Elevator
- 3x VictorSP motor controllers to drive lift up/down
..- Positive values move upwards / Negative values move down
..- On 30A fuses on the PDP
- 2x hall effect sensors (discrete inputs) for fully raised & fully lowered position indications 
- 1x pneumatic Double Solenoid - shifter for high/low speed (low speed for climbing)
..- KForward is high speed / KReverse is low speed
- 1x Encoder/10 turn potentiometer for lift position

### Climber (currently part of the lift assembly)
- TBD

### Fork lift
- 1x Double Solenoid to raise/lower the arms of the lift
..-Kforward is lift raised, Kreverse is lift lowered
