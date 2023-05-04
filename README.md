# SpringBoot-MySQL-CRUD
CRUD operation using springboot as backend framework and MySQL as database

INSTRUCTIONS

IDE for SpringBoot, MySQL and API testing tool are required for this project

1. Import SQL

API TESTING ( Open your API Testing Tool )

1. Add Article

        POST METHOD
        localhost:8080/api/post

        {
          "article": {
            "title": "title",
            "description": "this is my description",
            "author": "john doe"
          }
        }


2. Update Article by Title

        UPDATE METHOD
        localhost:8080/api/title

        {
          "article": {
            "title": "books",
            "description": "this is my new description",
            "author": "kathrine"
          }
        }


3. Delete Article by Title

        DELETE METHOD
        localhost:8080/api/books

4. View Article by Title

        GET METHOD
        localhost:8080/api/books
        
5. View All Articles

        GET METHOD
        localhost:8080/api/all







