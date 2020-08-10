##Тестовый проект для команды MeSports


В проекте используются Spring Boot, Spring Data, H2, Spring Hal Browser.

Сервер использует HATEOAS, по этому после поднятия приложения постучитесь в localhost:8080 и вы увидите Hal Browser.

Чтобы создать тестового юзера: 
1. GET: http://localhost:8080/users
2. NON-GET: POST: username
3. готово

Чтобы создать тестовый турнир:
1. GET: http://localhost:8080/tournaments
2. NON-GET: POST: paused = false
3. готово

Чтобы добавить в турнир игроков (турнир должен быть приостановлен (**paused**: true)):
1. GET: http://localhost:8080/tournaments/{id}
2. NON-GET: PATCH: "users": [url_to_user]
3. GET: http://localhost:8080/tournaments/{id}/users
4. Вы видите список участников

Чтобы добавить матч:
1. Создать юзеров
2. GET: /
3. NON-GET: POST: http://localhost:8080/matches 
    - tour >= 1
4. NON-GET: PATCH: http://localhost:8080/matches/{id}
    - leftUser: [url_to_user]
    - rightUser: [url_to_user]
5. Вы создали матч с участниками

##Задача 
### Summary

A web service (HTTP REST API) to work with tournaments.

### Glossary

A *Tournament* is an object consists of number of participants multiples of 8 (8, 16, 32, etc...) and number of [single-elimination](https://en.wikipedia.org/wiki/Single-elimination_tournament) matches that will be played.

A *Match* is an object consist of two participants, start time, finish time and participant scores.

### Details

Operations to be provided by the web service:

- Creating a tournament.
- Getting a tournament by its identifier.
- Starting a tournament (including when there are an odd number of participants, e.g. 7/8 or 15/16 participants).
- Adding participants in tournament.
- Removing participants from tournament while it's on hold.
- Summarizing results in match.

### Requirements

- The API must conform to the REST architecture.
- Do only the server side, you don't need to do visualization.
- It should be a Spring Boot application.
- Maven or Gradle should be used as a build tool.
- Data should only be stored in RDBMS.
- Al least 30% of the code should be covered by tests.
- Submit sources via a public git repository.

