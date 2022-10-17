Hotel Management System is a full-stack web application that allows hotels to manage front-office capabilities. Backend using Spring Boot and Frontend using Thymeleaf.
There are two roles in the application: Admin and User.
Anyone can register and then log in to the application. Role User will be automatically assigned to each new user.
Each User can view the list of available hotels, as well as the list of rooms within a specific hotel, and then make a reservation. 
The list is organized by pagination and sorting.
The User's reservation will be sent directly to the Admin side, who further processes the request and informs the User about possible updates.
On the Admin side, we have pages that manage:
  1. All guests (basic information about guests, check-in and check-out);
  2. All employees (information about employees, as well as control of employment contracts);
  3. All registered users;
  4. All hotels;
  5. All rooms, as well as rooms sorted by specific hotel.
The lists are organized by pagination and sorting and all CRUD operations are enabled (create, read, update, delete).
On the server side, application is conntected to the MySQL database.
Spring Security is used for security and passwords of the all Users and Admins are encoded.
