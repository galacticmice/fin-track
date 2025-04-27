# Financial Tracker

### Java Spring Boot Application
- JDBC
- Maven
- Spring Boot 
- Apache Tomcat
- PostgreSQL


### Database Schema Concept
- Users (no auth to save time)
  - _*user_id_
  - username
  - password
  - date_created
- Activity (transactions, renamed to avoid sql confusion)
  - _*activity_id_
  - user_id
  - date_time
  - category_id
  - amount
  - action
      - [D]eposit / [W]ithdraw
  - description
  - recur_days
- Category (static reference table, not modifiable)
  - _*cat_id_
  - type
  - description
- History (stores history of budget set that month)
  - _*user_id_
  - _*month_year_
  - budget

<sup>_*asterisk_ indicates key</sup>