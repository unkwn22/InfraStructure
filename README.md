# InfraStructure

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

# API table
| NAME      | METHOD   |   REQUEST | RESPONSE | BODY | DESCRIPTION   |
| ---------- | ---------- |---------- |---------- |---------- | ---------- |
| create user | POST | /api/user | Success: 200 Bad: 400 | { username </br> password </br> email } </br> | |
| get user | GET | /api/user | Success: 200 Bad: 404 / 400 | | dependant on existing users |
| edit user | PUT | /api/user | Success: 200 Bad: 400 | { password } </br> { email } </br> ||
| delete user | DELETE | /api/user | Success: 200 Bad: 500 | { userId } </br> or </br> { username } | dependant if its a User or Admin |
| create post| POST | /api/createPost | Success: 200 | { userId </br> postId </br> postContent </br> postCount }| |
| get post | GET | /api/getPost | Success: 200 Bad: 404 / 400  | | dependant on existing users |
| edit post | PUT | /api/editPost | Success: 200 Bad: 400 | { userId </br> postId } | |
| delete post | DELETE | /api/deletePost | Success: 200 Bad: 400 | { userId </br> postId } | dependant if its a User or Admin |


# Database Relations

## Timeline
| DATE      | PROCEDURE LOG                                                |
| ---------- | ------------------------------------------------------------ |
| 2021.08.20 | 1. Planned project </br> 2. Drew wireframe but since this project is going to be a static website I don't have time to make static webpages. Static webpages to be developed after finishing main back-end goals </br> 3. Planned out API table </br> 4. Drew Database relations |
