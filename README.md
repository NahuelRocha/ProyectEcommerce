<h1 align="center">
  <br>
  E-Commerce
  <br>
</h1>

<h4 align="center">The project consists of an ecommerce backend developed in Java using the Spring Boot framework.
  The system allows users to register, authenticate and perform various actions related to purchases and orders.
  Users can create new orders, add products to their orders and update shipping information.
  Administrators have access to the full functionality of the system. In addition, the system has complete stock control.
<br>
<b><a href="http://atl.academy" target="_blank">ATL ACADEMY</a></b>.</h4>

<p align="center">
   <a href="#endpoints">ENDPOINTS</a> •
  <a href="#HOW TO RUN">HOW TO RUN</a>
</p>

<br>

<h3 align="center">Flow of the API: </h3>
<br>
<H5 align="center">AuthController:</H5>
<p>
We will attempt to register a new user in the database. It will be verified that the chosen username is not currently in use.
If everything goes well, their password will be hashed, and we will generate a JWT token for subsequent authentication.
Once authenticated, the user's registration will be stored in the database. Upon authentication, a new token will be issued, granting access to the business logic for 24 hours.
This token will be automatically associated with the user. By default, all new users are assigned the common user role, which provides access to the corresponding endpoints.
There is a method available to grant administrator privileges to common users, enabling access to protected endpoints.
</p> 
<H5 align="center">UserController:</H5>
<p>
With this preparation, access to the business logic becomes available. It is possible to retrieve all users from the database and implement paging.
By specifying a page number and size, a specific number of users can be retrieved from the database. Users can be retrieved based on their ID, email, first name, and username.
Additionally, user data can be updated or deleted. These methods are only accessible to users with an ADMIN role.
</p>
<H5 align="center">PurchaseController:</H5>
<p>
We can create purchases that will be associated with the user making them (this method can be accessed by users with the roles of USER and ADMIN).
Additionally, we will have the capability to retrieve a purchase using its ID or the associated username.
Furthermore, methods for updating and deleting purchases will be available, but these actions will only be accessible to users with the ADMIN role.
</p>
<H5 align="center">OrderDetailController:</H5>
<p>
We will have the ability to create, delete/cancel, and update our orders. Additionally, we can search for an order based on the associated purchase.
When updating an order or canceling it, the stock of the respective product or products will also be updated.
</p>
<br>

## ENDPOINTS

### AuthController

| Method   | Route                                 | Description                                        |
| -------- | ------------------------------------- | -------------------------------------------------- |
| POST     | /api/auth/register                    | Register a new user and receive a JWT token       |
| POST     | /api/auth/authenticate                | Use the generated token to authenticate the user   |
| PUT      | /api/auth/promote-to-admin/{username} | Promote a user to an admin role                    |

### UserController

| Method   | Route                                 | Description                                        |
| -------- | ------------------------------------- | -------------------------------------------------- |
| GET      | /api/user/get-all?page=0&size=4       | Retrieve all users, with optional pagination       |
| GET      | /api/user/{id}                        | Get a user by ID                                   |
| DELETE   | /api/user/{id}                        | Delete a user by ID                                |
| PUT      | /api/user/{id}                        | Update a user by ID                                |
| GET      | /api/user/by-username/{username}      | Get a user by username                             |
| GET      | /api/user/by-name/{name}              | Get a user by name                                 |
| GET      | /api/user/by-email/{email}            | Get a user by email                                |

### PurchaseController

| Method   | Route                                 | Description                                        |
| -------- | ------------------------------------- | -------------------------------------------------- |
| POST     | /api/purchase/create                  | Create a new purchase                              |
| GET      | /api/purchase/{id}                    | Get a purchase by ID                               |
| GET      | /api/purchase/user/{username}         | Get purchases by username                          |
| DELETE   | /api/purchase/{id}                    | Delete a purchase by ID                            |
| PUT      | /api/purchase/{id}                    | Update a purchase by ID                            |

### OrderDetailsController

| Method   | Route                                 | Description                                        |
| -------- | ------------------------------------- | -------------------------------------------------- |
| POST     | /api/order-detail/create              | Create a new order detail                          |
| DELETE   | /api/order-detail/{id}                | Delete an order detail by ID                       |
| PUT      | /api/order-detail/update              | Update an order detail                             |
| GET      | /api/order-detail/by-purchase/{id}    | Get order details by purchase ID                  |

## HOW TO RUN

- Create a database in MySQL Workbench or PgAdmin 4.

- Configure the `application.properties` file with the correct information for your database.

- Run Spring Boot.
