# Project Summary

A complete platform created to promote smooth interactions between suppliers and customers in the beverage sector is the BeverageInventory system. Customers and suppliers may effectively handle their transactions in an environment with two interfaces, each adapted to their unique responsibilities and requirements, thanks to this reliable technology.

## For Customers:
Consumers can use the internet and peruse an extensive assortment of drinks divided into categories including soda, wine, liquor, and water. Customers can use the system to order beverages of their choice, indicate how much they want to buy, and pick from a list of providers. Customers can monitor the status of their orders—which can be marked as "Placed" or "Cancelled"—after they are placed. Additionally, they can easily check comprehensive order details and remove orders as needed. To ensure that order processing is simple and consistent, once an order is placed, it cannot be changed.

## For Suppliers:
Suppliers are able to manage their beverage listings through a dedicated interface. This includes adding new drinks to the inventory, changing the details of already-existing drinks, and eliminating drinks as necessary. In order to fulfill or cancel orders depending on inventory levels or other operational factors, suppliers can monitor orders placed by customers that are linked to them. This direct communication with the orders guarantees customer pleasure and contributes to the upkeep of an effective supply chain.

## System Objectives:
The BeverageInventory system's main objective is to simplify the beverage industry's order placement and fulfillment process by cutting complexity and improving user experience for suppliers and customers alike. Through its intuitive interface and instantaneous order status updates, the system fosters efficiency and transparency, resulting in enhanced customer satisfaction and service delivery. Strong data management and user-specific features supported by the system's design guarantee that both parties can perform efficiently and promote a responsive and dependable beverage transaction marketplace. The BeverageInventory system gives suppliers wishing to increase market share and improve operational efficiency—or customers searching for a quick and simple way to stock up on beverages—the resources they need to accomplish these goals precisely and easily.

# Implemented Features

## User Interface for Customers:
- **See the selection of drinks that are ready to be served:**
  - Clients have the option to view a selection of all the drinks that are ready for purchase, organized by groups like soft drinks, alcoholic beverages, and mineral water. Suppliers offer these drinks, which are only visible if they are not labeled as deleted.
- **Make purchases:**
  - Customers have the option to choose beverages, indicate the amounts, and select a supplier for placing the order.
- **Check Order Details:**
  - Once customers have made a purchase, they have the ability to access in-depth details regarding their orders, such as the types of beverages, quantities, and total expenses.
- **Abort Orders:**
  - If orders are in the 'Placed' status, customers can choose to cancel them.

## Interface for Suppliers:
- **Handle Drinks:**
  - Suppliers have the ability to add new beverages, modify information of current beverages, and remove beverages from inventory. Removed beverages can't be seen by clients but are still stored in the database.
- **Monitor and Handle Orders:**
  - Suppliers have the ability to see customers' orders and can update the status of these orders to either completed or cancelled based on inventory availability and other operational considerations.

## Flow of Direction:

### Viewing things from the customer's point of view:
- **Main Page ➔ Browse beverages:**
  - Clients access their dashboard and go to the beverages category to see all the items on offer.
- **Choose Drinks ➔ Make Purchase:**
  - Customers on the beverages page pick their preferred items, select quantities, and then choose a supplier to place the order with.
- **Order Confirmation ➔ Check Order Details/Cancel Order:**
  - After an order is finalized, clients have the option to either check the specific details of the order or delete it through their order history section.

### Seen from the supplier's point of view:
- **Login for Suppliers ➔ Beverage Management:**
  - Upon entering the supplier portal, suppliers have the ability to access the beverage management section to make modifications to beverages by adding, editing, or deleting them.
- **Changes to Drinks ➔ Managing Orders:**
  - Suppliers transition from handling beverages to observing associated orders, allowing them to mark each order as finished or canceled.

## Factors to Consider When Designing a System:
- **Retention of data for deleted items:**
  - The database stores information on beverages and orders even after they are deleted or cancelled, maintaining data integrity and enabling analysis of historical data.
- **Actions are limited based on the status of the order:**
  - Orders can solely be cancelled or removed if they are in the 'Placed' status to avoid conflicts and inconsistencies in order handling.
- **Functionalities tailored for individual users:**
  - The system offers customized features for both customers and suppliers, improving user experience and productivity for various user roles.

...

#For Extra Credit

# Improved Method of Managing Errors and Providing Information to Users

Special focus was placed on robust error handling and interactive user feedback in the creation of the BeverageInventory system to guarantee a smooth user experience. The following information outlines how these improvements have been integrated:

## Comprehensive Logging and Messaging with Specific Details

### Logging Errors with JSF FacesContext

In the application, especially in important areas like beverage management, JSF's FacesContext is utilized for error logging and giving instant feedback to users. This can be seen in the `saveBeverage()` function, which logs and shows errors to the user that occur while saving the beverage.

This technique records in-depth error messages through Java's logging system to track and analyze encountered problems while operating.

### Prompt Feedback on the Results of a Procedure

By making use of `FacesContext.getCurrentInstance().addMessage()`, the system offers instant feedback on the results of operations. If there is an error caused by entering incorrect data when saving a drink, the user will be promptly notified with a detailed error message, which helps avoid confusion and assists in addressing the input problem.

### Error Handling Proactively

The system is created to predict and handle possible mistakes. For instance, the app looks for potential issues that might occur while making a drink and deals with them smoothly by ensuring the user stays on the same page and sees an error message. This method guarantees that users understand the causes of operational failures and can make necessary changes without having to leave their current task.



.............................................................................
# Requirements

This section outlines the necessary steps to install, build, and run the BeverageInventory project. The project is built using Jakarta EE with Payara Server and connects to an SQL database.

## Prerequisites

Before you begin, ensure you have the following installed:
- Java JDK 11 or higher
- Maven 3.8.1 or higher
- Payara Server 5 or higher
- MySQL Server 8.0.33 or a compatible version

## Installation and Setup

### Step 1: Install Java Development Kit (JDK)
- Download and install the Java JDK 11 from [Oracle's JDK Download Page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).
- Set up the JAVA_HOME environment variable to point to your JDK installation.

### Step 2: Install Apache Maven
- Download Apache Maven from [Maven Official Website](https://maven.apache.org/download.cgi).
- Extract the files and add the `bin` directory to your PATH environment variable.
- Verify the installation by running `mvn -version` in your command prompt or terminal.

### Step 3: Install Payara Server
- Download Payara Server from [Payara Download Page](https://www.payara.fish/downloads/payara-platform-community-edition/).
- Unzip the Payara Server package to a suitable location on your system.

### Step 4: Set Up MySQL Database
- Install MySQL Server if not already installed.
- Create a new database for the project.
- Ensure the MySQL service is running.

## Building the Project

1. Clone the repository or download the source code.
2. Navigate to the project directory where the `pom.xml` file is located.
3. Run the following command to build the project:
   ```bash
   mvn clean install
4. The above command will compile the project and generate a `.war` file in the `target` directory.

## Deploying to Payara Server

1. Start the Payara Server if it is not already running.
2. Open the Payara Admin Console (typically available at `http://localhost:4848`).
3. Navigate to **Applications** in the left menu.
4. Click **Deploy**, and select the `.war` file generated from the build process.
5. Follow the on-screen instructions to deploy the application.

## Running the Application

Once deployed, the application can be accessed via a web browser:

- Open your browser and navigate to `http://localhost:8080/bpasham-fp`.

## Versions of Tools, Libraries, and APIs

- **Java JDK:** 11
- **Jakarta EE:** 10.0.0
- **Maven:** 3.8.1
- **Payara Server:** 5.x
- **MySQL Connector/J:** 8.0.33
- **JUnit Jupiter Engine:** 5.10.1 (for testing)
- **EclipseLink JPA:** 4.0.2 (for testing)
- **Hibernate Validator:** 8.0.1.Final (for validation in testing)
- **Expressly:** 5.0.0 (for testing)

## Enter User Credentials

- **Username:** `customer1`
- **Password:** `customer1`

- **Username:** `customer2`
- **Password:** `customer2`

- **Username:** `salesManager1`
- **Password:** `salesManager1`

- **Username:** `salesManager2`
- **Password:** `salesManager2`

# Key Findings in Development

This part provides a firsthand account of the evolution of the BeverageInventory system, covering important insights, potential areas for deeper investigation, and both rewarding and difficult aspects of the project.

## Lessons I Gained

- **Jakarta EE and Payara Server:** My comprehension of enterprise-level web development was enriched by getting practical experience with Jakarta EE and deploying applications on Payara Server.
- **Enhanced Error Handling:** Utilizing JSF's `FacesContext` for comprehensive error handling offered a better approach in maintaining stability and enhancing the user experience.
- **Managing Databases:** It was essential to work with SQL databases in order to ensure data integrity and persistence, particularly in grasping transactional operations and data consistency.

## Topics to Explore Further

- **Microservices Architecture:** I want to investigate restructuring the BeverageInventory system into a microservices architecture to enhance scalability and maintainability.
- **Utilizing Docker for Containerization:** Deploying the application with Docker could simplify development and minimize differences between development and production setups.

## Preferences and Aversions

### The Things I Enjoyed

- **User Interaction Feedback:** Including interactive feedback systems proved to be beneficial by enhancing user satisfaction and system usability.
- **Developing User Interfaces:** Making user interfaces intuitive and responsive with JSF was extremely fun, providing instant visual feedback and a feeling of concrete advancement.

### My Dislikes

- **Complicated Error Management:** Despite being efficient, dealing with complex error handling situations could be difficult at times because of the detailed dependencies and the requirement for careful debugging.
- **Improving Performance:** Difficulties in enhancing the system's performance, especially in terms of database interactions, were challenging and brought attention to areas requiring efficiency enhancements.

## Conclusion

The creation of the BeverageInventory system provided a thorough educational journey, encountering both difficulties and successes. It offered a hands-on use of theoretical knowledge and introduced me to new technologies and methodologies which will definitely help with my upcoming projects.

.............................................................
Lab3 lab4 lab5 lab 8 readme file

.............................................................................

**Lab8 Screenshots**

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/03ccda17-382e-4904-af80-43cbfeeec678)

//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/f3e8f9ef-5eb2-4823-98f4-9cf00deec1e5)

//////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

**Inventory Manager1 has access to inventoryManager role, admin role and customer role. Screenshots of the access are added below:**

//////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/cd6b30d4-fcd5-4bb1-927f-9aee46a44990)

///////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/59906865-2abe-4672-a2ac-5bbd07259dc7)

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/67a4ecc0-8e77-4fdc-baf1-2d210130828b)

//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/d6cba810-2359-4af3-86af-23ff9e1b0d7a)

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////

![image](https://github.com/itmd4515/itmd4515-s24-fp-Bhanumahesh70/assets/144741762/d5b6d7ae-994d-4da3-a009-f60965f60c5a)

//////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////


**User names and Passwords**

UserName: admin1

Password: admin1 


UserName: admin2

Password: admin2


UserName: customer1

Password: customer1


UserName: customer2

Password: customer2


UserName: inventoryManager1

Password: inventoryManager1


UserName: inventoryManager2

Password: inventoryManager2


UserName: salesManager1

Password: salesManager1


UserName: salesManager2

Password: salesManager2

**Users and access roles**

admin1 has access to ADMIN_GROUP

admin2 has access to ADMIN_GROUP

inventoryManager1 has access to ADMIN_GROUP, CUSTOMER_GROUP, INVENTORYMANAGER_GROUP

inventoryManager2 has access to CUSTOMER_GROUP,  INVENTORYMANAGER_GROUP

salesManager1 has access to  SALESMANAGER_GROUP

salesManager2 has access to  SALESMANAGER_GROUP

customer1 has access to CUSTOMER_GROUP

customer2 has access to CUSTOMER_GROUP

 


..............................................................................
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
