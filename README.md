Lab3 lab4 readme file
**Lab5 Screenshot**
Screenshot of passing all test cases:
![Screenshot (1651)](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/86c7d2a0-64ca-4dd2-8131-51b3e28a1276)

..........................................................................................
**lab4**

**1. Paragraph that describes the business domain you have chosen to work with, and why?**

The domain I have chosen to work with is managing beverage inventory. I have chosen this domain as it can be a part of a large inventory managing system in a store, where we can tackle the beverages sales, and beverage types, and can distinguish between alcohol and non-alcohol beverages. Furthermore, the domain of choice may correspond with business prospects in the food and beverage sector, where effective inventory and sales management can result in lower costs and higher levels of customer satisfaction.

**2. Write a second paragraph answering the following questions: There is only one entity required for Lab 4, but what other entities from your business domain can you think of? How might they relate to one another? You can answer this in narrative form, or you can answer it with a database diagram.**

The beverage business domain may also include the Supplier, Customer, Order, and Inventory entities in addition to the Beverage entity. There would be a variety of relationships between these entities like a supplier can offer a variety of beverage types, the relationship between the Supplier and Beverage entities, for instance, would be one to many. Similar to this, since customers can buy various kinds of beverages and multiple customers can buy beverages, there may be a many-to-many relationship between the Customer and Beverage entities. Customers' purchases would be connected to the Order entity, which would stand in for the beverage-related transactions. Furthermore, because it would monitor each drink's stock levels, the Inventory entity and the Beverage entity could have a one-to-one or one-to-many relationship.

**OutPut Screenshots**

Bean Validation and JPA test was done. In bean Vaildation test the bean constraints set as not null for data types Beverge name, isAlcoholic, Beverage type, expiry data was tested. Additionaly, the boolean datatype isNonAlcoholic beverage which was constrained to have only true|false as data types entry was tested.
validateNameNotBlank() :verifies whether the validator finds a breach in the event that the beverage name is left blank.

When the expiry date is null, validateExpiryDateNotNull() confirms that the validator has found a violation.

validateIsNonAlcoholicPattern(): Verifies whether the isNonAlcoholic attribute violates the specified pattern when the validator finds a violation.

When the beverage type is null, the validateBeverageTypeNotNull() function makes sure the validator notices the infraction.

validateValidBeverage(): Confirms that when all of the beverage object's attributes are valid, the validator does not report any violations.

![Screenshot (1649)](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/03fdd928-ab9a-46fd-9a9c-0f0cf8677c51)

**Maven Terminal output**
![Screenshot (1650)](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/4154f9f1-2b99-45d4-bcd8-1712f28cfba0)

...............................................................................................................................................................................................................................

**Lab3:**

**Form before valid submission:**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/f71432aa-4986-40de-bfad-a24f9331344c)




**Confirmation page after valid submission:**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/a56e7d4c-af57-4ae0-8fb5-78d9adb0ee6f)


**Payars Serve console output for valid submission**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/07eac57f-2607-4dc8-8041-2d1195db58d8)



**Form before bad input submission**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/f279c483-62f2-4107-b8f8-ed796a547e07)



**Form after bad input submission displaying error messages**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/99cd98f4-74d3-441f-a622-1870801c3a2d)



**Parayara server console output for invalid submission**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/385cc0e3-e491-42d6-bedf-ffac7bb80aa5)



**1) Your understanding of the difference between the forward and redirect operations.** 
 forward:
A request that is internally routed to another resource is an example of an internal server activity. involves just one request-response cycle, and the internal procedure is hidden from the client. The browser's URL stays the same. frequently employed for an application's internal navigation.
Redirect:
involves telling the client to submit a new request to a different URL through communication. involves two cycles of requests and responses, and the browser modifies the URL in accordance. frequently employed in situations when material has been transferred permanently or for external redirects.

**2) How would you be validating user submissions without the Bean Validation API standard?**
Developers must provide their own unique validation logic inside the application's codebase if the Bean Validation API is not available. This means developing custom validation techniques to make sure user submissions meet predetermined standards. This method adds more lines of code and may present maintenance issues, but it also allows for customization of validations to meet unique requirements. Code redundancy could occur in the absence of a consistent validation API, therefore careful management is essential to preserving consistency throughout the application's various components. Custom logic offers flexibility, but it also may result in code duplication and require more work during development and maintenance.

**3) How do you think this approach would scale to a real application with 100's of entities?**
Scalability issues could arise if hundreds of entities in a real application had specific validation logic implemented. Errors, redundant code, and complicated maintenance are increased when validations are created and managed manually for every entity. The effort needed to maintain efficient and consistent validation across several entities increases significantly as the program grows. By offering a uniform and declarative approach, easing the load of manual validation implementation, and improving maintainability in large-scale applications, a standardized validation API—like the Bean Validation API—offers a more scalable alternative.


**4) Why didn't we need to include any additional dependencies (i.e. Bean Validation, JDBC) in this project?**
It doesn't provide the exact project context, but it offers some options if you didn't want extra requirements like JDBC or Bean Validation. First off, since it doesn't call for in-depth validation or direct database interfaces, the project might be rather straightforward. As an alternative, it's possible that the project is making use of a platform or framework that manages some functions by default, reducing the need for explicit dependencies. The lack of further dependencies could also point to a purposeful design decision, like depending on language characteristics or specially made implementations that meet the demands of the project. In general, the objectives and nature of the project determine if external dependencies are required.
