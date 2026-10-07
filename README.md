# Java Selenium E-Commerce Automation Framework

A robust UI automation framework developed using **Java, Selenium WebDriver, TestNG, Maven, Page Object Model, Data-Driven Testing, and Extent Reports**.

The framework automates an e-commerce application and covers login, product validation, cart operations, checkout workflow, order completion, data-driven testing, reporting, and failure screenshots.

---

## 🚀 Project Overview

This project demonstrates a maintainable Selenium WebDriver automation framework following industry-standard automation practices.

The framework provides:

- Page Object Model (POM)
- Selenium WebDriver automation
- TestNG test execution
- Maven dependency management
- Data-Driven Testing using TestNG DataProvider
- Configuration management using properties file
- Explicit waits
- Extent HTML reporting
- Automatic failure screenshots
- Reusable page classes
- Reusable test data
- Centralized WebDriver setup and teardown

---

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Selenium WebDriver 4.35.0 | Web UI Automation |
| TestNG 7.11.0 | Test Framework |
| Maven | Build & Dependency Management |
| WebDriverManager | WebDriver Management |
| Extent Reports 5.1.2 | Test Reporting |
| IntelliJ IDEA | Development Environment |
| Git | Version Control |
| GitHub | Source Code Repository |

---

## 🏗️ Framework Architecture

The framework follows the Page Object Model design pattern.

```text
                    TestNG Test Classes
                            |
                            v
                     Page Object Layer
                            |
                            v
                    Selenium WebDriver
                            |
                            v
                     Web Application
                            |
                            v
                  Extent Reporting Layer
                            |
                            v
                    Failure Screenshots