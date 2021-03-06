# InfraStructure
- Blog/Guides : https://www.notion.so/InfraStructure-project-7bb46d48d6e84cbaaa989db6f7d28183

# Tech Stack
- Planning:
  - Wireframe: Adobe CS6
  - Database Relations table: draw.io
- Develop Environment: Jdk 1.8.0_301-b09
- Framework: Spring-boot 2.5.4
  - Dependencies: Spring Security, Spring Jpa, Spring H2, Spring Web, Lombok, mySql, Spring Validation, JUnit5 (Jupiter API/Engine : 5.8.0-RC1)
  - Plug-in/Add-on: Yaml Editor 1.8.0, SQL Editor
- Deployment Tools
  - Gradle, Travis CI
- Database
  - mySQL 8.0.26, RDS

# Project introduction
- This project isn't unique, it's going to be a simple project that has an User entity with user information in datatbase.
- A CRUD mechanism with one to many database structure
- Basic Client to Server communication using Spring MVC Restful API
- Using Amazon web services for deployments
- (Will be added in the future if anything comes to mind)

# Goal/Aim
- Understanding Spring - boot and out of the box framework and tools
- Understanding back-end project structure
- Understanding database transaction
- Understanding Client to Server communications
- Understanding the concept of engaging steps in order to build a website
- Understanding Infrastructure settings / Amazon services / CI, CD / Automatic Deployments
- Understanding Testing
- Understanding everything that is needed in order to become a web developer
- Will be done in 2 weeks

# Wireframe
- Home page (Unlogged)
![](InfraStructure/PIC/HOME.jpg)
- Home page (logged)
![](InfraStructure/PIC/USER.jpg)
- User page
![](InfraStructure/PIC/MYPAGE.jpg)
- ADMIN page
![](InfraStructure/PIC/ADMIN.jpg)

# API table
| NAME      | METHOD   |   REQUEST | RESPONSE | BODY | PARAM | DESCRIPTION   |
| ---------- | ---------- |---------- |---------- |---------- | ---------- | ---------- |
| create user | POST | /api/signup | Success: 200 Bad: 400 | { username </br> password </br> email } </br> | ||
| get user | POST | /api/user/ | Success: 200 Bad: 404 / 400 | { username } || dependant on existing users |
| get all users | GET | /api/users | Success: 200 Bad: 404 / 400 | || dependant on existing users |
| edit user | PUT | /api/user/{id} | Success: 200 Bad: 400 | { username </br> password </br> email } |||
| delete user | DELETE | /api/user | Success: 200 Bad: 500 ||| dependant if its a User or Admin |
| create post| POST | /api/createPost | Success: 200 | { userId </br> postId </br> postContent </br> postCount }|| |
| get post | GET | /api/getPost | Success: 200 Bad: 404 / 400  | || dependant on existing users |
| edit post | PUT | /api/editPost | Success: 200 Bad: 400 | { userId </br> postId } || |
| delete post | DELETE | /api/deletePost | Success: 200 Bad: 400 | { userId </br> postId } | |dependant if its a User or Admin |

# Database Relations
![](InfraStructure/PIC/relations2.JPG)

## Timeline
| DATE      | PROCEDURE LOG                                                |
| ---------- | ------------------------------------------------------------ |
| 2021.08.20 | 1. Planned project </br> 2. Drew wireframe but since this project is going to be a static website I don't have time to make static webpages. Static webpages to be developed after finishing main back-end goals </br> 3. Planned out API table </br> 4. Drew Database relations </br> 5. pushed Spring project |
| 2021.08.21 | 1. Filled out Tech stack in readMe </br> 2. implemented Validation, JDBC dependencies </br> 3. Created Controller, Repository, SecurityConfiguration for h2 database testing </br> 4. implemented JUnit Jupiter API and Engine for testing purposes </br> 5. Database relations needed changes </br> 6. Created Timestamp and CreatedBy class </br> 7. Mapped Entity classes </br> 8. Created users request/response dto (In progress) </br> 9. created user Service (In progress) |
| 2021.08.23 | 1. Controller, Repository, UserService refactored for userCreate </br> 2. finished userDtos </br> 3. Entity Users class added authentication method |
| 2021.08.24 | 1. UserController Test progressed </br> 2. Created user signup api |
| 2021.08.27 | 1. Refactored api get users table </br> 2. Created user getOne api </br> 3. Created user getAll api </br> 4. Created updateUser Api </br> 5. Created userDelete Api |
| 2021.08.28 | 1. getAllUsers test case passed </br> 2. get specific user api test case passed </br> 3. update user api test case passed </br> 4. Delete user api test case passed |
| 2021.09.02 | 1. Connected mySQL with Spring boot </br>|
| 2021.09.03 | 1. Connected with Travis CI but facing UTF-8 problems </br> 2. solved all Travis problems </br> 3. Created RDS Instance and connected it to project |
