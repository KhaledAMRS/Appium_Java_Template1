# Project roadmap
- Create Maven project on IntelliJ.
- pom.xml - Add necessary dependencies: Appium, Cucumber, etc
- configuration.properties - Add parameters such as: executionMode(e.g: Android_local, IOS_local, Android_cloud, IOS_cloud), appiumUrl, udid, platformName, testRunName, appPackage, appActivity, username, password
- utilities - ConfigurationReader class: implement get & set methods
- Hooks class - @Before: DesiredCapabilities, executionMode statement, implicitWait
- Hooks class - @After: log failure reason, terminate driver
- POM Pattern - BasePage: Implement Appium Commands such as click on button, type inside field, wait For Visibility of elements
- Resources - features: Create first feature file & add first scenario
