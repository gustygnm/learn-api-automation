## Learn API Automation
### Pekerjaan rumah 18 | Module 20 | Profession QA Engineer | Jayjay

Dalam kasus kali ini, saya akan mencoba untuk membuat Automation Testing Framework untuk melakukan API
Testing untuk situs https://reqres.in

Tools yang akan dipakai:
- Language: Java
- Framework : TestNG
- Testing Library : RestAssured
- Reporting Library : Allure

### Struktur Project:
Struktur project Gradle akan terlihat seperti ini:

![Image](https://github.com/gustygnm/learn-api-automation/blob/master/Screenshot%202024-08-18%20at%2021.43.42.png)


### Setup Gradle Project: 
Berikut adalah file build.gradle yang digunakan untuk pengaturan dependencies:

```
plugins {
    id 'java'
    id("io.qameta.allure") version "2.11.0"
}

group = 'org.example'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation group: 'org.testng', name: 'testng', version: '7.7.1'
    testImplementation group: 'org.json', name: 'json', version: '20231013'
    testImplementation group: 'io.rest-assured', name: 'rest-assured', version: '5.3.0'
    testImplementation group: 'io.rest-assured', name: 'json-schema-validator', version: '5.3.0'

    testImplementation platform("io.qameta.allure:allure-bom:2.26.0")
    testImplementation "io.qameta.allure:allure-testng"
}

test {
    useJUnitPlatform()
    useTestNG {
        suites 'src/test/resources/testng.xml'
    }
    testLogging {
        events "PASSED", "FAILED", "SKIPPED"
    }
}

```

### Hasil Running Test:

![Image](https://github.com/gustygnm/learn-api-automation/blob/master/Screenshot%202024-08-18%20at%2022.06.12.png)
