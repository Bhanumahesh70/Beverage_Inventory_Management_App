# Quick Read- AI Book Summary Android App – UI Overview  

This application is designed to search, explore, and save books while generating **AI-powered summaries** using **Gemini AI API**.

Built with **Java**, **Andriod Studios**, **Google Books API**, **Firebase Authentication & Realtime Database**, **Gemini AI API**, and **Material Design**.

Below is a walkthrough of the key UI pages and their functionalities.  

---

## Login Page  
![Login Page](UI_Images/Login_Page.png)  

- Allows users to **log in** with a username and password.  
- Provides options to **sign up for a new account**.  
- Implements **role-based authentication** for Admin, Supplier, and Customer.  

---

## Sign Up Page  
![Sign Up Page](UI_Images/Signup_Page.png)  

- Users can **register as a Customer** by default.  
- Option to **request supplier access** during registration.  
- Captures **username, password, email, and role** details.  

---

## Admin Portal  
![Admin Portal](UI_Images/Admin_Portal.png)  

- Admin can **review and approve supplier access requests**.  
- Displays a list of pending requests with an **Approve button**.  
- Ensures only authorized users are upgraded to **Supplier role**.  

---

## Supplier Dashboard  
![Supplier Dashboard](UI_Images/Supplier_Portal.png)  

- Supplier users see a **welcome page with navigation bar** for quick access.  
- Can view their **inventory of beverages** and manage details.  
- Provides options to **create, edit, or update beverages** with expiry and type info.  

---

## Create Beverage Form  
![Create Beverage Form](UI_Images/Create_Beverage.png)  

- Enables Suppliers to **add new beverages** to the system.  
- Input fields: **Beverage Name, Expiry Date, Alcoholic/Non-Alcoholic flag, Status, Beverage Type**.  
- Ensures proper and validated beverage data entry.  

---

## Supplier Orders View  
![Orders Associated with Supplier](UI_Images/Orders_Associated_With_supplier.png)  

- Suppliers can track **orders placed by customers** for their beverages.  
- Each order includes: **Order ID, Date, Customer, Quantity, and Status**.  
- Status updates include: **Placed, Completed, Cancelled, Deleted**.  

---

## Customer Dashboard  
![Customer Dashboard](UI_Images/Customer_Dashboard.png)  

- Customers view their **personal dashboard** showing all orders.  
- Options to **place new orders, view order details, and delete/cancel orders**.  
- Displays order **status history** for better tracking.  

---

## View Order Page  
![View Order](UI_Images/View_Order.png)  

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
