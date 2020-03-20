# Library Assistant - REST Backend


 ## Describe
 This API is created to help people run library, it provides many basic features which it should have like:

 - creating Member
 - deleting Member
 - Showing info and history of Member
 - borrowing book 
 - releasing book
 - showing      the        history of  the book
 - searching members/books
 - and many others     to simplify working with  this api 

=======
## Language: Java11


## Frameworks

- Spring Boot
- Hibernate
- Maven
- Flyway
- REST Assured
## Database

In production profile is used **MySQL**, in tests **H2**, object's ID is automatically generated by the database.
Individual Reposiory is  **CrudRepository** interface

## Testing

Developed with unit tests (**Junit 5**) and integration tests using **REST Assured**,

 

## Logic
Library Assistant is based on communication by **JSON** files, it receives and send JSON due to endpoint with **CRUD** operations
Example;
Sending **POST** on "localhost:8080/members/" with file as below will create new member.

`"name": "Jack",
 "lastName": "Smith",
 "email": "JackSmith@domain.com"`


##  Project Structure
**Packages**:
  Main part of the Project is devided into 4 packages: 
- controllers (CRUD mapping )
- services (logic)
- respositories (integration with database)
- domain (basic entity classes: book, member, reservation)

