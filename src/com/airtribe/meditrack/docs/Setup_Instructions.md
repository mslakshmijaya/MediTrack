## Prerequisites
- JDK 8 or above
- Java installed and configured - jdk-25.0.1

# Setup Instructions: Install & Configure Java (JDK/JRE)

This guide walks you through installing the Java Development Kit (JDK) and Java Runtime Environment (JRE), configuring environment variables, and verifying the setup.

---

## 1. Download JDK
- Visit the official Oracle JDK download page: [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)
- Alternatively, use [OpenJDK](https://jdk.java.net/) for an open-source version.
- Choose the correct installer for your operating system (Windows, macOS, Linux).

**Screenshot Placeholder:** ![Alt text](Oracle_website.jpg)


![Alt text](download.jpg)

---

## 2. Install JDK
- Run the installer and follow the prompts.
- By default, JDK installs into:
  - Windows: `C:\Program Files\Java\jdk-25.0.2`
  - macOS/Linux: `/usr/lib/jvm/jdk-25.0.2`

**Screenshot Placeholder:** ![Alt text](folder_jdk_vers.jpg).

---

## 3. Configure Environment Variables

### Windows
1. Open **System Properties → Advanced → Environment Variables**.
2. Add a new variable:
   - `JAVA_HOME = C:\Program Files\Java\jdk-25.0.2`
3. Edit the `PATH` variable and add:
   - `%JAVA_HOME%\bin`

**Screenshot Placeholder:** ![Alt Txt](binPath.jpg).


![Alt Txt](cmd.jpg)


## Sample HelloWorld Program . 
![Alt text](cmdHW.jpg)