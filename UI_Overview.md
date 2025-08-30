# Beverage Inventory Management System – UI Overview  

This application is designed to manage beverage inventory with **multi-role access (Admin, Supplier, Customer)**.  
It is built using **Jakarta EE, Java 11, JSF, and Payara Server** for backend and provides a responsive UI for seamless user interaction.  

Below is a walkthrough of the key UI pages and their functionalities.  

---

## Login Page  
![Login Page](<img width="473" height="295" alt="Login Page" src="https://github.com/user-attachments/assets/303e4b63-1e73-43b9-a1d3-12660476c148" />
)  
<img width="473" height="295" alt="Login Page" src="https://github.com/user-attachments/assets/4dab983b-9e76-4362-b093-4345e2269212" />

- Allows users to **log in** with a username and password.  
- Provides options to **sign up for a new account**.  
- Implements **role-based authentication** for Admin, Supplier, and Customer.  

---

## Sign Up Page  
![Sign Up Page](<img width="282" height="277" alt="Signup Page" src="https://github.com/user-attachments/assets/422f3a4a-52dd-45b8-a63e-c3652a42c1ce" />
)  

- Users can **register as a Customer** by default.  
- Option to **request supplier access** during registration.  
- Captures **username, password, email, and role** details.  

---

## Admin Portal  
![Admin Portal](<img width="817" height="323" alt="Admin Portal" src="https://github.com/user-attachments/assets/7395eef6-41ac-4967-a423-c20fc3bafd05" />
)  

- Admin can **review and approve supplier access requests**.  
- Displays a list of pending requests with an **Approve button**.  
- Ensures only authorized users are upgraded to **Supplier role**.  

---

## Supplier Dashboard  
![Supplier Dashboard](<img width="819" height="375" alt="Supplier Page" src="https://github.com/user-attachments/assets/a258cbcb-2e87-4783-b4b9-3fc7894c9a47" />
)  

- Supplier users see a **welcome page with navigation bar** for quick access.  
- Can view their **inventory of beverages** and manage details.  
- Provides options to **create, edit, or update beverages** with expiry and type info.  

---

## Create Beverage Form  
![Create Beverage Form](link-to-image)  

- Enables Suppliers to **add new beverages** to the system.  
- Input fields: **Beverage Name, Expiry Date, Alcoholic/Non-Alcoholic flag, Status, Beverage Type**.  
- Ensures proper and validated beverage data entry.  

---

## Supplier Orders View  
![Orders Associated with Supplier](link-to-image)  

- Suppliers can track **orders placed by customers** for their beverages.  
- Each order includes: **Order ID, Date, Customer, Quantity, and Status**.  
- Status updates include: **Placed, Completed, Cancelled, Deleted**.  

---

## Customer Dashboard  
![Customer Dashboard](link-to-image)  

- Customers view their **personal dashboard** showing all orders.  
- Options to **place new orders, view order details, and delete/cancel orders**.  
- Displays order **status history** for better tracking.  

---

## View Order Page  
![View Order](link-to-image)  

- Shows **full order details**: Order ID, Customer Name, Supplier, and Beverages Ordered.  
- Provides a quick transaction summary for Customers and Suppliers.  

---

# Key Features  

- **Role-based access** for Admin, Supplier, and Customer.  
- **Inventory management** for Suppliers to add and manage beverages.  
- **Order lifecycle handling**: Customer places order → Supplier manages order → Admin approves suppliers.  
- **Dynamic dashboards** for each role.  
- **Secure authentication** with login, signup, and role-based permissions.  

---
