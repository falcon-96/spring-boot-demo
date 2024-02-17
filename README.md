# Sample Spring Boot Application with H2, Java Record, JdbcClient

- Java Version: 18
- Spring Boot Version[Updated]: 3.2.2
- Database: H2

**Summary**:

This is a sample Spring Boot application to serve as a simple CRUD (Create, Read, Update, Delete) application.

With the help of exposed APIs, we can:
- Create a new droid
- Update its model
- Show all droids registered
- Delete a droid

### Exposed APIs:

| API                | Description                            | Input type    | Input            |
| ------------------ | -------------------------------------- | ------------- | ----------------- |
| `/api/createDroid` | Create a new Droid with name and model | RequestBody   | Droid droid      |
| `/api/showAllDroids`| Display all droids                    | None          | None              |
| `/api/editDroid`   | Update a droid's model based on name  | RequestBody   | Droid droid      |
| `/api/deleteDroid` | Delete a droid                         | RequestParam  | name              |
---
#### *Please refer to com.self.probe.model.Droid.java to find an implementation of a fairly new feature of Java, i.e., record.*

