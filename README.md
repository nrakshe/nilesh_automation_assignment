# nilesh_automation_assignment

NOTE: Description:
I have automated the basic flow of Amazon app. This will help you to cretae a good testing framework. This is just a basic but I will add more features/scenarios/reports in upcoming days. I'll also add how to integrate with Docker/Jenkins/SauceLab" etc.   

Pre-Requisites
--------------

- Java 8 or above
- Command line Appium (1.14.0 or above)
- Desktop Appium (1.13.0 or more) 
- Android studio (3.4.1)
- Mac or Windows 
- Maven 
- Android device (Version 6 or greater)
- Eclipse 
- Amazon apk 



Project Setup
---------------

1. Set up paths

Go to the "Root directry" from terminal
Hit the following command - "open .bash_profile"
Set up the path like below: 

export JAVA_HOME=$(/usr/libexec/java_home)
export PATH=$PATH:$JAVA_HOME/bin

export M2_HOME=/Library/apache-maven-3.5.2
export PATH=$PATH:$M2_HOME/bin

export ANDROID_HOME=/Users/nilesh/Library/Android/sdk
export PATH=${PATH}:$ANDROID_HOME/tools:$ANDROID_HOME/platform-tools

2. Verify path is setup correctly 
3. Start the Eclipse


Execution
----------

1. Connect physical device to Machine 
2. Check device is listed in the terminal using command "adb devices"
3. Start the desktop appium 
3. Go to the project folder using command "cd project_path"
4. Execute the project using following command "mvn clean test -e -Denv.INSTANCE_IP=0.0.0.0 -Denv.OS=Android -Denv.APP_PATH=/Users/nilesh/Downloads/amazon.apk -Denv.suiteName=smokesuite.xml -Denv.OS_VERSION=7.0" 
5. I made it dynamic - just change the .apk path, device version in above command and Androidappiumcapabiliy file


Other features -
-----------------
1. All the XPaths are added in "Android.properties" file 
2. Android properties are added in "AppiumAndroidCapabilities.properties"
3. Driver related utilities are placed under Drivers.class
4. Driver and command line dynamic values used in "SuiteSetup.class"

TODO 
---------------
1. Excel sheet use case
2. Extent report 
