# Calculator_GUI

<!-- <img src="https://github.com/iamwatchdogs/Calculator_GUI/actions/workflows/pages/pages-build-deployment/badge.svg" alt="" align="right"> -->

<br>
<div align="center">

[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://github.com/iamwatchdogs?tab=repositories&q=&type=public&language=java&sort=)
![Eclipse](https://img.shields.io/badge/Eclipse-FE7A16.svg?style=for-the-badge&logo=Eclipse&logoColor=white)

<br>

[![Open Source](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/iamwatchdogs?tab=repositories&q=&type=public&language=&sort=)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](https://github.com/iamwatchdogs/Calculator_GUI/pulls)
[![GitHub issues](https://img.shields.io/github/issues/iamwatchdogs/Calculator_GUI.svg)](https://github.com/iamwatchdogs/Calculator_GUI/issues)
[![GitHub pull-requests merged](https://badgen.net/github/merged-prs/iamwatchdogs/Calculator_GUI)](https://github.com/iamwatchdogs/Calculator_GUI.js/pulls?q=is%3Amerged)
[![GitHub release](https://img.shields.io/github/release/iamwatchdogs/Calculator_GUI)](https://GitHub.com/iamwatchdogs/Calculator_GUI/releases/)
[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

</div>
<br>

This an dark-mode GUI-based simple calculator that does all basic arithmetic operation like Add, subtract, multiplication, division and modular division supporting decimal values rounded to 2-floating point precesion. Including to that, it does other basic required operations like Backspace, Clear and neagate value. I have decide to build this small application based on the concept of MVC Architecture.

- [**CalculatorModel**](./src/calculator/CalculatorModel.java "Goto CalculatorModel.java") : This class does the job of **Model** in MVC Architecture i.e., handles all the calculations and operations required for this Calculator application.
- [**CalculatorView**](./src/calculator/CalculatorView.java "Goto CalculatorView.java") : This class acts as the **View** in MVC Architecture, which means that this class is responsible for handling all the GUI-related for this application.
- [**CalculatorController**](./src/calculator/CalculatorController.java "Goto CalculatorController.java") : This class is the controller, according to MVC Architecture, take care of all the event handling without interfering with the GUI-element directly. This class will execute all the calculations and operations with help of [CalculatorModel](./src/calculator/CalculatorModel.java "Goto CalculatorModel.java") class and also handles the exception that might occur while performing calculations and operations.

I have spent quite a while on this small project trying to learn, understand and implement the concepts of the core Java while trying to implement the industrial standards _(Trying my best to close to perfect)_. This project helped me get a good understands on various concepts like MVC Architecture, OOPS, Java AWT, Regular expression, Lambda functions, a bit of generic & collection framework, Event Handling, Exception Handling, annotations, Java Docs, Debugging, PMD, etc. And I'm looking forward to use this small experience to build more greater stuff.

> **Note** :
>
> - This project was built using Eclipse IDE and OpenJDK Temurin-17.0.7+7.
> - The Java Doc for this project is hosted through GitHub pages, So you can visit the pages to get an overview of the project files.
> - I have decide to built this whole project based only on Java AWT instead of swing or other libraries, just to make my fundamentals strong.
> - This project is allowed for personal use, learning and for contribution.
> - Reupload this project as your own or using it as your own project for any kind of submission is strictly forbidden.

## Contribution:

Hi, there fellow developer !!!... I'm happy to see you eager to contribute to this repo/project in anyway possible. Before you proceed with any kind of contribution, please setup your project within your local system and it suggested to use **Eclipse IDE** as this project was developed using the very same IDE. After setting it up within your system, go on and try out stuff...

Found a bug ???... Report it by creating an issue right in the issue section.

Got an idea to improve ???... Create an issue suggesting your idea.

want to work in any of the issue you have created or found in the issue section ???... Ping me within the same chat which you want to work on, and I'll assign you the issue so that other might know that you're working on the issue. When you done create a Pull Reques, so that I could review & merge it and also close the issue.

### Prerequisites

Just make sure your comfortable with the following concepts before you start code-wise contributation:

- **Git & GitHub**: Atleast know how to view issues and make pull requests.
- **Core Java**:
  - **\***Object oriented Programming (Inlcuding Abstract Classes, Interfaces)
  - **\***Java AWT
  - Event Handling
  - Exception Handling
  - **\***Java Docs
  - **\***Documenting your code
- Any other Concept that you want to integrate it with the project.

If you're a begineer or don't want to contribute code-wise, then you can help me in other ways mentioned above.

### Basic steps

Let's get started with the very hands-on fundamental for code-wise contribution...

You can get started by forking this repo and cloning it into your system. Here are some of the git commands you'll be using,

```bash
# Clone your forked repo into your local system
git clone https://github.com/<Your-GitHub-Name>/Calculator_GUI.git

# Creating a new branch
git checkout -b <branch-name>

# Adding all the elements/changes into tracked/staged state
git add .

# Committing your every change
git commit -m "<message>"

# Updating your remote repo (or) pushing your commits to your forked repo
git push origin <branch-name>
```

After you're done with your changes, you can push them back to your remote repository. So that you can send me a pull request to merge the changes into the original Repository.

> Don't know where to start ???... (or) Sounds too Complicated ???... check out [CONTRIBUTING.md](CONTRIBUTING.md "Let's go to CONTRIBUTING.md").

## Final Output:

<div align="center">

![Output](src/images/OP.png)

</div>
