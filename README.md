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
  <a href="#runit">RUN IT</a>
</p>

<br>

<h3 align="center">Flow of the API: </h3>
<br>
<H5 align="center">UserController:</H5>
<p>
We will attempt to register a new user in the database. It will be verified that the chosen username is not currently in use.
If everything goes well, their password will be hashed, and we will generate a JWT token for subsequent authentication.
Once authenticated, the user's registration will be stored in the database. Upon authentication, a new token will be issued, granting access to the business logic for 24 hours.
This token will be automatically associated with the user. By default, all new users are assigned the common user role, which provides access to the corresponding endpoints.
There is a method available to grant administrator privileges to common users, enabling access to protected endpoints.
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


| Method   | Route                                 | Description                                        |
| -------- | ------------------------------------- | ------------------------------------------------   |

--------------------------------------  AuthController  -------------------------------------------------

| POST     | /api/auth/register                    | Register a new user and return a JWT token         |
| POST     | /api/auth/authenticate                | Take the generated token and authenticate the user |
| PUT      | /api/auth/promote-to-admin/{username} | Promote to admin role                              |

---------------------------------------  UserController  ------------------------------------------------

| GET      | /api/user/get-all?page=0&size=4       | You get all users, you can set pagination          |
| GET      | /api/user/{id}                        | Get a User by ID                                   |
| DELETE   | /api/user/{id}                        | Delete a User by ID                                |
| PUT      | /api/user/{id}                        | Update a User by ID                                |
| GET      | /api/user/by-username/{username}      | Get a User by Username                             |
| GET      | /api/user/by-name/{name}              | Get a User by Name                                 |
| GET      | /api/user/by-email/{email}            | Get a User by Email                                |

--------------------------------------  PurchaseController  ---------------------------------------------

| POST     | /api/purchase/create                  | Create a new Purchase                              |
| GET      | /api/purchase/{id}                    | Get a Purchase by ID                               |
| GET      | /api/purchase/user/{username}         | Get a Purchase by Username                         |
| DELETE   | /api/purchase/{id}                    | Delete a Purchase by ID                            |
| PUT      | /api/purchase/{id}                    | Update a Purchase by ID                            |

--------------------------------------  OrderDetailsController  ---------------------------------------------

| POST     | /api/order-detail/create              | Create a new OrderDetail                           |
| DEL      | /api/order-detail/{id}                | Delete a Order by ID                               |
| PUT      | /api/order-detail/update              | Update a Order                                     |
| GET      | /api/order-detail/by-purchase/{id}    | Get a Order by Purchase ID                         |



## RUNIT

- create a database in MySQL Workbench or PgAdmin 4.


- set the  `application.properties` file correctly with the data of your DB


- run Springboot
