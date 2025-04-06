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

```
# 🤖 Hướng Dẫn Sử Dụng Ollama Server bằng Docker

Tài liệu này hướng dẫn cách chạy **Ollama AI model** trên Docker và cách tương tác với API `generate`.

---

## 🚀 Bước 1: Khởi động Ollama lần đầu

### Chạy lệnh sau trong terminal:

```bash
docker run --name ollama_server -p 11434:11434 ollama/ollama:latest
docker exec -it ollama_server ollama run llama3.2:1b
```

### Lần Chạy Sau
* Sau khi đã chạy thành công lần đầu, chỉ cần chạy lệnh sau:

``` bash
docker start ollama_server
docker exec -it ollama_server ollama run llama3.2:1b
```

### Dừng Ollama Server
* Khi không sử dụng nữa, dừng server bằng lệnh:

``` bash
docker stop ollama_server
```

### Payload request 
* BE sẽ nhận Api request từ FE và tiếp tục call đến api của model để generate response về lại phía FE.

* API request to model Ollama 
```
http://localhost:11434/api/generate
```

* API request from FE
```
{
  "model": "name_model",
  "prompt": "quetsion",
  "stream": false
}
```
* Ví dụ:
```
{
  "model": "llama3.2:1b",
  "prompt": "Why is the sky blue?",
  "stream": false
}
```

* Đây là 1 response mẫu khi model tiếp nhận request trên
``` 
{
  "model": "llama3.2:1b",
  "created_at": "2025-03-23T03:43:58.156Z",
  "response": "The sky appears blue to us because of a phenomenon called Rayleigh scattering...",
  "done": true,
  "done_reason": "stop",
  "context": [128006, 9125, 128007, ...],
  "total_duration": 27529455478,
  "load_duration": 11925463866,
  "prompt_eval_count": 31,
  "prompt_eval_duration": 2604000000,
  "eval_count": 261,
  "eval_duration": 12962000000
}
```



