🎓 GedikCampusHub

    An Academic Resource Sharing Platform — A Course Enrollment Microservice built with Spring Boot, MySQL, and Docker.

Java
Spring Boot
MySQL
Docker
Bootstrap
📖 About

GedikCampusHub is a full-stack Spring Boot web application developed as part of the Object-Oriented Programming (MCTS401) course at Istanbul Gedik University, Spring 2025/2026.

The system adapts familiar e-commerce architectural patterns — customers, products, carts, and order statuses — to an academic resource-sharing context:
E-Commerce	CampusHub
Customer	Student
Product	Note / Past Paper
Shopping Cart	Study Basket
Order	StudyBasketItem
Order Status	PLANNED → IN_PROGRESS → COMPLETED
Product Category	Course
Order Capacity Limit	Course maxCapacity
✨ Features

    📚 Student registration with full validation (name, email, department, year)

    📝 Note & past paper upload — PDF/image file upload with MIME validation, sanitized filenames, stored outside web root

    🗂️ Course catalog with classroom session schedules

    🛒 Study Basket — add notes, track progress with PLANNED / IN_PROGRESS / COMPLETED statuses

    🔍 Search & pagination — search notes by title with paginated results

    💬 Comment system — students can discuss notes

    📥 File download — secure download endpoint with Content-Disposition: attachment

    🌐 REST API — full JSON API with proper HTTP status codes
