# Employee Reimbursment System (ERS)

## Executive Summary
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.



## Technical Requirements

* Java
* JDBC
* PostgreSQL
* AWS RDS
* HTML/CSS/JS

## Features

List of features ready and TODOs for future development
* Runs as multiple page application
* Uses JDBC to connect AWS RDS PostgreSQL Database
* Middle tier uses Servlet technology for dynamic web application.
* Passwords are entrypted and Passwords stored in the database
* Middle tier follows DAO Design pattern 
* Implements Logback for appropriate loggin. 


To-do list:
* Create a single page application 


**State-chart Diagram (Reimbursement Statuses)** 

![](./imgs/state-chart.jpg)

**Reimbursement Types**

Employees must select the type of reimbursement as: LODGING, TRAVEL, FOOD, or OTHER.

**Logical Model**

![](./imgs/logical.jpg)

**Physical Model**

![](./imgs/physical.jpg)

**Use Case Diagram**

![](./imgs/use-case.jpg)

**Activity Diagram**

![](./imgs/activity.jpg)


The back-end system shall use JDBC to connect to an AWS RDS PostgreSQL database. The application shall deploy onto a Tomcat Server. The middle tier shall use Servlet technology for dynamic Web application development. The front-end view shall use HTML/JavaScript to make an application that can call server-side components. Passwords shall be encrypted in Java and securely stored in the database. The middle tier shall follow proper layered architecture and implement Logback for appropriate logging. 
