# Basic-automation-test
This is for automation test in Project

## Getting started
#### Intergration Frameworks
- [Cucumber in java](https://cucumber.io/docs/installation/java/).
- [Selenium 3](https://www.seleniumhq.org/download/).
- [JUnit5](https://junit.org/junit5/).
- [Maven](https://https://maven.apache.org/).


#### Setup Libraries
- You can also use [Maven dependencies](https://mvnrepository.com/) to configure the necessary library.
- Please checks [pom.xml] for more details.

#### Setup Drivers
- The driver is configured in [DriverType class] to choose the driver paths by each OS you are using.By default, this project is configured for browser Chrome for Window OS.
- The format constants for @CucumberOptions is configured in each **Runner** class

## Test Workflows
- The first, **Runner** class in [src/test/java/runner/] will be run. Then it get the feature and glue path to find out **the feature files** need to run. By default, the feature path is **resources/features**.
- Secondly, **the feature files** will be run to match with **the step definition** in java classes in [/src/test/java/stepDefinition].
- After that, cucumber hooks will be called to run **@Before** and after running the step definitions.
- Finally, JUnit annotations **@After** will be run to handle the project report and take screenshot.


## Folder Structure
- **src/test/java/config**: contains and only contains all of the base source code which can be used for main methods to initialize the driver.
- **src/test/java/pageObjects**: contains all class define web element UI pages.
- **src/test/java/stepDefinition**: contains all class define step for each feature files.
- **src/test/java/utility**: contains the other common method in base framework.
- **src/test/java/runner**: contains all class runner for each feature files.


## POM(Page Object Model) Pattern
- This framework are using Page Object Model Pattern to design web UI pages and manage their actions.


## Locator Management
- This framework are using Page Object Model to manage all of the web element locators.
- Please checks [src/test/java/pageObjects]for more details.


## Run Test:
- Using maven command: **mvn clean -Dtest=${file} test**
- **${file}**: Test runner files user expect to execute in **src/test/java/runner** folder.