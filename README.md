# Customer-Orders

This service uses an in-memory H2 database. Once restarted all old data is lost.
It has an ITEMS table which is loaded with 3 records initially.
H2 database dashboard can be access at http://localhost:8080/h2
and give this field for jdbc url 'jdbc:h2:mem:testdb'

It supports two operation

<h3>Add order </h3>
For adding order, it requires customer id and list of items with item id and quantity. Service using already existing ITEMS tables, it calculates the total amount for a particular item in order and add it in the database.
If successfully added, it returns the order number.

<h3> Get order</h3>
It requires order number which is obtained in add order method response. It returns all the order information such as total amount, items including quantity and price of each item.