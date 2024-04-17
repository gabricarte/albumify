# Albumify

Albumify is a RESTful API developed in Java using the Spring Boot framework.

The main purpose of the API is to allow the registration and management of music albums in an in-memory database (H2).

It provides endpoints for CRUD (Create, Read, Update, Delete) operations, enabling users to perform various operations related to music albums.

## Functionalities

- Album Registration: Users can send HTTP POST requests to create and register new albums in the database.

- Album Querying: GET requests enable users to query registered albums, returning information such as title, artist, release year, and a summary of the artist.

- Album Updating: Using PUT requests, users can update the information of already registered albums in the database.

- Album Deletion: Through DELETE requests, users can remove albums from the database.

## Branches:

- v01: Basic internal operations without security.
- v02: Implementation of external operations.
- v03: Implementation of security on the HTTP requests.
