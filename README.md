# kahviguru

This is a Java Springboot project developed for a course assignment. The assignment had specs on what the data model should be like and what functionalaties the website should include (CRUD operations, paging, etc.). There were also some optional features like authorization, but that one I left out becuase I couldn't make it work. 
The frontend of the application is made by using Thymeleaf but my goal is to learn Vue and redevelop the frontend with that.

At the moment, master branch works but I've since then developed the project further in dev branch. Most changes deal with translating everything into English so that there's no language mix. I'm also making preparations for the new frontend there as well so the dev branch only works to a certain degree (I only translated some of the static resources to test that translations didn't break any functionality but I'll be removing all those files once Vue is incorporated).


## Prerequisites to run the project:

1. Install OpenJDK 11
e.g. on Linux Ubuntu with `sudo apt install openjdk-11-jdk`

2. Install Maven
e.g. on Linux Ubuntu with `sudo apt install maven`


## To run project:
From the root project directory run `mvn spring-boot:run`

Project will run in http://localhost:8080/.
