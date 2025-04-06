# Read Me First

Làm theo các bước sau để chạy project này trên máy bạn.

> **Lưu ý:** Package ban đầu được định nghĩa là `com.se-project.be` là không hợp lệ, do đó project sử dụng `com.se_project.be` thay thế.

---

## Getting Started

### Yêu cầu hệ thống

- **Java 17+**
- **Maven 3.8+**
- **MySQL Server**
- **HeidiSQL** (hoặc bất kỳ công cụ quản lý MySQL nào bạn quen dùng)
- IDE: IntelliJ IDEA, Eclipse, VS Code, v.v.

---

## Cài đặt Database bằng HeidiSQL

1. **Mở HeidiSQL**
2. Ở góc dưới bên trái, chọn `New` để tạo kết nối mới.
3. Đặt tên kết nối tùy ý (VD: `MyLocalDB`)
4. Thiết lập thông tin:
    - **Hostname/IP:** `127.0.0.1`
    - **User:** `root`
    - **Password:** `admin123` (hoặc mật khẩu bạn đã đặt cho MySQL)
    - **Port:** `3306`
5. Bên phía BE, chọn kết nối database (Logo bên góc phải nếu dung IntelliJ)
6. Nhấn **Open**
7. Click chuột phải vào tên kết nối (bên trái), chọn **Create new → Database**
8. Nhập tên database là: `dbname`, nhập User và password đã thiết lập từ trước => nhấn **OK**
9. Kiểm tra lại để đảm bảo database đã được tạo và có thể kết nối

---

## Cấu hình kết nối trong Spring Boot

Trong file: `src/main/resources/application.properties`

```properties
spring.application.name=be

# MySQL Properties
spring.datasource.url=jdbc:mysql://localhost:3306/dbname?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=admin123
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
