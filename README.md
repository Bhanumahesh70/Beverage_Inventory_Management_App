# Beverage Inventory Management Application

A complete platform created to promote smooth interactions between suppliers and customers in the beverage sector is the BeverageInventory system. Customers and suppliers may effectively handle their transactions in an environment with two interfaces, each adapted to their unique responsibilities and requirements, thanks to this reliable technology.

---
# Table of Contents
1. [Project Objectives](#project-objectives)   
2. [What's in the application](#whats-in-the-application)
      - [For Customers](#for-customers)
      - [For Supplier](#for-supplier)
      - [For Admin](#for-admin)
        
4. [Interaction With the Application](#interaction-with-the-application)
   - [User Interface for Customers](#user-interface-for-customers)
   - [User Interface for Suppliers](#user-interface-for-suppliers)
   - [Flow of Direction](#flow-of-direction)
   
6. [Factors Considered While Designing the System](#factors-considered-while-designing-the-system)
7. [Other Features](#other-features)
   - [Improved Method of Managing Errors and Providing Information to Users](#improved-method-of-managing-errors-and-providing-information-to-users)
   - [Comprehensive Logging and Messaging with Specific Details](#comprehensive-logging-and-messaging-with-specific-details)
8. [Requirements](#requirements)
   - [Prerequisites](#Prerequisites)
   - [Installation and Setup](#installation-and-setup)
   - [Building the Project](#building-the-project)
   - [Deploying to Payara Server](#deploying-to-payara-server)
   - [Running the Application](#running-the-application)
   - [Versions of Tools, Libraries, and APIs](#versions-of-tools-libraries-and-apis)
      
9. [Display of Fronted Ui of the Application](#display-of-fronted-ui-of-the-application)
10. [Key Findings in Development](#key-findings-in-development)
    - [Lessons I Gained](#lessons-i-gained)
    - [Topics to Explore Further](#topics-to-explore-further)
    - [Preferences and Aversions](#preferences-and-aversions)
11. [Conclusion](#conclusion)




---
# Project Objectives:
The BeverageInventory system's main objective is to simplify the beverage industry's order placement and fulfillment process by cutting complexity and improving user experience for suppliers and customers alike. Through its intuitive interface and instantaneous order status updates, the system fosters efficiency and transparency, resulting in enhanced customer satisfaction and service delivery. Strong data management and user-specific features supported by the system's design guarantee that both parties can perform efficiently and promote a responsive and dependable beverage transaction marketplace. The BeverageInventory system gives suppliers wishing to increase market share and improve operational efficiency—or customers searching for a quick and simple way to stock up on beverages—the resources they need to accomplish these goals precisely and easily.

---
# What's in the application:

## For Customers:

### Customer Features  
- **Extensive Beverage Selection**  
  - Browse a wide variety of drinks categorized as:  
    - Soda  
    - Wine  
    - Liquor  
    - Water  

- **Order Management**  
  - Place orders for beverages of choice  
  - Specify the desired quantity  
  - Choose from a list of available providers  

- **Order Tracking**  
  - Monitor order status:  
    - **Placed** – Order successfully submitted  
    - **Cancelled** – Order has been canceled  
  - View detailed order information  
  - Remove orders if needed  

- **Order Processing Rules**  
  - Orders cannot be modified once placed to ensure smooth processing  

## For Supplier:

### Supplier Features:
- **Beverage Management**  
  - Add new drinks to the inventory  
  - Update details of existing drinks  
  - Remove drinks when necessary  

- **Order Management**  
  - Monitor customer orders linked to them  
  - Fulfill or cancel orders based on:  
    - Inventory levels  
    - Operational factors  

- **Efficient Supply Chain Maintenance**  
  - Ensure smooth order fulfillment  
  - Maintain direct communication with orders for better customer satisfaction

## For Admin:
### Admin Features  

- **User Management**  
  - Customers can sign up freely  
  - Supplier access requires approval  

- **Supplier Approval**  
  - Admin receives and reviews supplier requests  
  - Only the admin can approve supplier access
    
---

# Interaction With the Application

## User Interface for Customers:
- **See the selection of drinks that are ready to be served:**
  - Clients have the option to view a selection of all the drinks that are ready for purchase, organized by groups like soft drinks, alcoholic beverages, and mineral water. Suppliers offer these drinks, which are only visible if they are not labeled as deleted.
- **Make purchases:**
  - Customers have the option to choose beverages, indicate the amounts, and select a supplier for placing the order.
- **Check Order Details:**
  - Once customers have made a purchase, they have the ability to access in-depth details regarding their orders, such as the types of beverages, quantities, and total expenses.
- **Abort Orders:**
  - If orders are in the 'Placed' status, customers can choose to cancel them.

## User Interface for Suppliers:
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

### Viewing things from the supplier's point of view:
- **Login for Suppliers ➔ Beverage Management:**
  - Upon entering the supplier portal, suppliers have the ability to access the beverage management section to make modifications to beverages by adding, editing, or deleting them.
- **Changes to Drinks ➔ Managing Orders:**
  - Suppliers transition from handling beverages to observing associated orders, allowing them to mark each order as finished or canceled.
    
---
# Factors Considered While Designing the System:
- **Retention of data for deleted items:**
  - The database stores information on beverages and orders even after they are deleted or cancelled, maintaining data integrity and enabling analysis of historical data.
- **Actions are limited based on the status of the order:**
  - Orders can solely be cancelled or removed if they are in the 'Placed' status to avoid conflicts and inconsistencies in order handling.
- **Functionalities tailored for individual users:**
  - The system offers customized features for both customers and suppliers, improving user experience and productivity for various user roles.
---

# Other Features

## Improved Method of Managing Errors and Providing Information to Users

Special focus was placed on robust error handling and interactive user feedback in the creation of the BeverageInventory system to guarantee a smooth user experience. The following information outlines how these improvements have been integrated:

## Comprehensive Logging and Messaging with Specific Details

### Logging Errors with JSF FacesContext

In the application, especially in important areas like beverage management, JSF's FacesContext is utilized for error logging and giving instant feedback to users. This can be seen in the `saveBeverage()` function, which logs and shows errors to the user that occur while saving the beverage.

This technique records in-depth error messages through Java's logging system to track and analyze encountered problems while operating.

### Prompt Feedback on the Results of a Procedure

By making use of `FacesContext.getCurrentInstance().addMessage()`, the system offers instant feedback on the results of operations. If there is an error caused by entering incorrect data when saving a drink, the user will be promptly notified with a detailed error message, which helps avoid confusion and assists in addressing the input problem.

### Error Handling Proactively

The system is created to predict and handle possible mistakes. For instance, the app looks for potential issues that might occur while making a drink and deals with them smoothly by ensuring the user stays on the same page and sees an error message. This method guarantees that users understand the causes of operational failures and can make necessary changes without having to leave their current task.

---

# Requirements

This section outlines the necessary steps to install, build, and run the BeverageInventory project. The project is built using Jakarta EE with Mavan, Payara Server and connects to an SQL database.

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
---

# Display of Fronted Ui of the Application 
For an in-depth overview of the application's user interface — including core screens, layout design, navigation structure, and key visual components — refer to the following file:

- [`UI_OVERVIEW.md`](UI_OVERVIEW.md)

 
## Enter User Credentials

- **Username:** `customer1`
- **Password:** `customer1`

- **Username:** `customer2`
- **Password:** `customer2`

- **Username:** `salesManager1`
- **Password:** `salesManager1`

- **Username:** `salesManager2`
- **Password:** `salesManager2`
  
- **Username:** `admin1`
- **Password:** `admin1`
---

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
---

# Conclusion

The creation of the BeverageInventory system provided a thorough educational journey, encountering both difficulties and successes. It offered a hands-on use of theoretical knowledge and introduced me to new technologies and methodologies which will definitely help with my upcoming projects.

