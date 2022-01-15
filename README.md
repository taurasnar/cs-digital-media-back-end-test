# Cs Digital Media backend Test

## Project creator

| Name  | Email  |
|---|---|
| Tauras Narbutas | taurasnar@gmail.com |

## About the project

This is a Spring Boot application for managing authors and books.

## Setting up and running the project

- Clone the repository to IntelliJ (project uses gradle and java 11, so make sure to have those installed).

- After project loads, run the following command in the terminal:
```
gradle bootRun
```

If gradle version is not 7.3.3, there might be problems running project from terminal. In that case, just find Applications class under src/main/java/csdigitalmediabackendtest/Application.java and run the class manually in IntelliJ.

For testing run the following command in the terminal:
```
gradle test
```

## GraphQL queries and mutations

To run the following queries and mutations go to http://localhost:8080/graphiql in web browser.

Get all books:

```
Example query:

query {
  findAllBooks {
    id,
    title,
    genre,
    authors {
      id,
    	firstName,
    	lastName,
    	birthDate
    }
  }
}

Example result:

{
  "data": {
    "findAllBooks": [
      {
        "id": "101",
        "title": "Title example 1",
        "genre": "FICTION",
        "authors": [
          {
            "id": "101",
            "firstName": "Name1",
            "lastName": "Surname1",
            "birthDate": "1991-01-01"
          }
        ]
      },
      {
        "id": "102",
        "title": "Title example 2",
        "genre": "NONFICTION",
        "authors": [
          {
            "id": "101",
            "firstName": "Name1",
            "lastName": "Surname1",
            "birthDate": "1991-01-01"
          },
          {
            "id": "102",
            "firstName": "Name2",
            "lastName": "Surname2",
            "birthDate": "1992-02-02"
          }
        ]
      }
    ]
  }
}
```

Get a specific book:

```
Example query:

query {
  findBookById(id: 101) {
    id,
    title,
    genre,
    authors {
      id,
    	firstName,
    	lastName,
    	birthDate
    }
  }
}

Example result:

{
  "data": {
    "findBookById": {
      "id": "101",
      "title": "Title example 1",
      "genre": "FICTION",
      "authors": [
        {
          "id": "101",
          "firstName": "Name1",
          "lastName": "Surname1",
          "birthDate": "1991-01-01"
        }
      ]
    }
  }
}
```

Create a new book:

```
Example mutation:

mutation {
  createBook(book: {
      title: "Test",
      genre: FICTION,
      authorsIds: [101]
	}) {
    id,
    title,
    genre,
    authors {
      id,
    	firstName,
    	lastName,
    	birthDate
    }
  }
}

Example result:

{
  "data": {
    "createBook": {
      "id": "1",
      "title": "Test",
      "genre": "FICTION",
      "authors": [
        {
          "id": "101",
          "firstName": "Name1",
          "lastName": "Surname1",
          "birthDate": "1991-01-01"
        }
      ]
    }
  }
}
```

Update an existing book's title, author and genre:

```
Example mutation:

mutation {
  updateBook(book: {
    	id: 1
      title: "updateTest",
      genre: NONFICTION,
      authorsIds: [102]
	}) {
    id,
    title,
    genre,
    authors {
      id,
    	firstName,
    	lastName,
    	birthDate
    }
  }
}

Example result:

{
  "data": {
    "updateBook": {
      "id": "1",
      "title": "updateTest",
      "genre": "NONFICTION",
      "authors": [
        {
          "id": "102",
          "firstName": "Name2",
          "lastName": "Surname2",
          "birthDate": "1992-02-02"
        }
      ]
    }
  }
}
```

Delete existing book:

```
Example mutation:

mutation {
  deleteBook(id: 1)
}

Example result:

{
  "data": {
    "deleteBook": true
  }
}

```

Create a new Author:

```
Example mutation:

mutation {
  createAuthor(author: {
      firstName: "Test",
      lastName: "Test",
      birthDate: "2000-01-01"
	}) {
    id,
    firstName,
    lastName,
    birthDate
  }
}

Example result:

{
  "data": {
    "createAuthor": {
      "id": "1",
      "firstName": "Test",
      "lastName": "Test",
      "birthDate": "2000-01-01"
    }
  }
}
```

Get a specific author:

```
Example query:

query {
  findAuthorById(id: 101){
    id,
    firstName,
    lastName,
    birthDate
  }
}

Example result:

{
  "data": {
    "findAuthorById": {
      "id": "101",
      "firstName": "Name1",
      "lastName": "Surname1",
      "birthDate": "1991-01-01"
    }
  }
}
```
## Database

Project uses h2 in-memory database.
To check database go to http://localhost:8080/h2-console and press connect (connection details can be found in application.properties file under src/main/resources/application.properties).
