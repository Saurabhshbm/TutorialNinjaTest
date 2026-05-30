Feature: Tutorial Ninja 

Scenario: Tutorial Ninja Flow 


When User registers new account
And User login into application
And User search product in search box
And User adds product into cart
And User proceeds to checkout
And User deletes product from cart
Then User logout from application

Examples:
| firstname | lastname | email               | password | product |
| saurabh   | Kumar    | saurabh@gmail.com   | test123  | iPhone |
| Jon       | Snow     | jon@gmail.com       | admin123 | Samsung |
| Rahul     | Kumar    | rahul@gmail.com     | rahul123 | MacBook |