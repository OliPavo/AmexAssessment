# Amex Assessment
For this Assessment, I will be making an Orders Service using Java/Spring Boot that will store data in a MySQL database.

## Step 1: Build an Orders Service 
Build a service that’s able to receive simple orders of shopping goods via a REST API 
* Apples cost 60 cents and oranges cost 25 cents 
* The service should return a summary of the order, including the cost of the order
* Add unit tests that validate your code

## Step 2: Simple offer 
* The shop decides to introduce two new offers 
    * buy one get one free on Apples 
    * 3 for the price of 2 on Oranges 
* Update your functions & unit tests accordingly

## Step 3: Store and retrieve orders
* The service should now store the orders that a customer submits
    * There should be an endpoint to get a particular order based on its ID
    * There should be an endpoint to get all orders
    * This store does not have to be to disk
* Update your functions & unit tests accordingly

## Installation

Use any Java IDE to open both the 'eureka-service-registry' project and 'Orders-Service' project.

Make sure to right click each of the projects from within your IDE and select 'Maven > Update Project' in order to properly install dependencies.

Run the schema.sql script found in "Orders-Service\src\main\resources" from an instance of MySQL WorkBench to create database required in the final step(Step 3).
```SQL
create schema if not exists orderservice;
use orderservice;

create table if not exists orders (
    order_id int(11) not null auto_increment primary key,
    item_id int(11),
    discount_total decimal(5,2),
    total decimal(5,2)
);

create table if not exists item (
    item_id int(11) not null auto_increment primary key,
    item_name varchar(30) not null,
    unit_price decimal(5,2) not null,
    quantity int(11) not null
);
```


Even though I left my MySQL Server with its default settings, you should check in "Orders-Service\src\main\resources\Application.Properties" file to verify that your settings match.

```
spring.datasource.url=jdbc:mysql://localhost:3306/orderservice?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=rootroot
```
## Usage

Run the 'eureka-service-registry' project first then run 'Orders-Service'. In your browser, visit http://localhost:8761/ to verify that an instance of Orders-Service is registered with Eureka Service Registry.

### Creating an Order
Using Postman or Curl, make a POST HTTP Request at http://localhost:1889/order with JSON formatted as shown below:
```JSON
{
    "items": [
        {
            "itemName": "Apple",
            "itemQuantity": 9
        },
        {
            "itemName": "Orange",
            "itemQuantity": 19
        }
    ]
}
```
 This request above should result in an HTTP response formatted like this:
```JSON
{
    "order": {
        "orderId": 4,
        "items": [
            {
                "itemName": "Apple",
                "itemPrice": 0.60,
                "itemQuantity": 9
            },
            {
                "itemName": "Orange",
                "itemPrice": 0.25,
                "itemQuantity": 19
            }
        ],
        "discountCost": 3.90,
        "totalCost": 6.25
    },
    "message": "Total cost of the order: $6.25 Total discount applied: $3.90"
}
```
### Retrieving All Orders
Using Postman or Curl, make a GET HTTP Request at http://localhost:1889/orders 
The response should look like this:
```JSON
[
    {
        "orderId": 1,
        "items": [
            {
                "itemName": "Apple",
                "itemPrice": 0.60,
                "itemQuantity": 11
            },
            {
                "itemName": "Orange",
                "itemPrice": 0.25,
                "itemQuantity": 3
            }
        ],
        "discountCost": 3.25,
        "totalCost": 4.10
    },
    {
        "orderId": 2,
        "items": [
            {
                "itemName": "Apple",
                "itemPrice": 0.60,
                "itemQuantity": 3
            },
            {
                "itemName": "Orange",
                "itemPrice": 0.25,
                "itemQuantity": 6
            }
        ],
        "discountCost": 1.10,
        "totalCost": 2.20
    }
]
```
### Retrieving an order using an Order Id
Using Postman or Curl, make a GET HTTP Request at http://localhost:1889/order/{orderId}

Use a known orderId in the place of the curly braces. For example, if you have an order to look up where the orderId = 2 then you would make the GET request using http://localhost:1889/order/2

The response should look like this:
```JSON
{
    "orderId": 2,
    "items": [
        {
            "itemName": "Apple",
            "itemPrice": 0.60,
            "itemQuantity": 3
        },
        {
            "itemName": "Orange",
            "itemPrice": 0.25,
            "itemQuantity": 6
        }
    ],
    "discountCost": 1.10,
    "totalCost": 2.20
}
```

## Additional Notes:
Tags have been created for StepOne, StepTwo and StepThree.


