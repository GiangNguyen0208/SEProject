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

## Tải Docker và thực hiện các thao tác sau:
### run cmd first : 
* docker run --name ollama_server -p 11434:11434 ollama/ollama:latest ; docker exec -it ollama_server ollama run llama3.2:1b

### Nếu như chạy lệnh  trên thành công rồi thì lần kế tiếp  chạy  lệnh dưới :

* docker start ollama_server; docker exec -it ollama_server ollama run llama3.2:1b

### dừng ollama server 
* docker stop ollama_server 

==> phía dưới là  các api và domain để gọi 
// 
api : http://localhost:11434/api/generate


// payload request 
{
  "model": "name_model",
  "prompt": "quetsion",
  "stream": false
} 


// for example

{
  "model": "llama2",
  "prompt": "Why is the sky blue?",
  "stream": false
} 

============================================== 

// respone
{"model":"model_name",
"created_at":"time",
"response":"answer",
"done":true,
"done_reason":"stop",
"context":[ " num int "],
"total_duration":"numebr",
"load_duration":"number",
"prompt_eval_count":"number",
"prompt_eval_duration":"number",
"eval_count":"number",
"eval_duration":"number"
}

// for instance

{"model":"llama3.2:1b",
"created_at":"2025-03-23T03:43:58.156304162Z",
"response":"The sky appears blue to us because of a phenomenon called Rayleigh scattering, named after the British physicist Lord Rayleigh. He explained that when sunlight enters Earth's atmosphere, it encounters tiny molecules of gases such as nitrogen and oxygen.\n\nThese gas molecules scatter the light in all directions, but they scatter shorter (blue) wavelengths more than longer (red) wavelengths. This is because blue light has a lower energy level, which means it requires less energy to overcome the scattering process.\n\nAs a result, the blue light is dispersed throughout the atmosphere, giving the sky its blue color. The other colors of the visible spectrum, like red and orange, are scattered in different ways and reach our eyes from a more direct path, making them appear reddish or brownish.\n\nIt's worth noting that the color of the sky can vary depending on atmospheric conditions, such as:\n\n* Dust particles: Tiny particles in the atmosphere can scatter light, giving the sky a hazy or gray appearance.\n* Water vapor: High levels of water vapor in the air can scatter light, making the sky appear more opaque and blue.\n* Pollution: Air pollution can scatter light, reducing the intensity of the blue color.\n\nHowever, under normal conditions, with clear skies and no significant atmospheric interference, the sky typically appears a brilliant blue.",
"done":true,
"done_reason":"stop",
"context":[128006,9125,128007,271,38766,1303,33025,2696,25,6790,220,2366,18,271,128009,128006,882,128007,271,10445,374,279,13180,6437,30,128009,128006,78191,128007,271,791,13180,8111,6437,311,603,1606,315,264,25885,2663,13558,64069,72916,11,7086,1306,279,8013,83323,10425,13558,64069,13,1283,11497,430,994,40120,29933,9420,596,16975,11,433,35006,13987,35715,315,45612,1778,439,47503,323,24463,382,9673,6962,35715,45577,279,3177,304,682,18445,11,719,814,45577,24210,320,12481,8,93959,810,1109,5129,320,1171,8,93959,13,1115,374,1606,6437,3177,706,264,4827,4907,2237,11,902,3445,433,7612,2753,4907,311,23075,279,72916,1920,382,2170,264,1121,11,279,6437,3177,374,77810,6957,279,16975,11,7231,279,13180,1202,6437,1933,13,578,1023,8146,315,279,9621,20326,11,1093,2579,323,19087,11,527,38067,304,2204,5627,323,5662,1057,6548,505,264,810,2167,1853,11,3339,1124,5101,63244,819,477,14198,819,382,2181,596,5922,27401,430,279,1933,315,279,13180,649,13592,11911,389,45475,4787,11,1778,439,1473,9,33093,19252,25,49074,19252,304,279,16975,649,45577,3177,11,7231,279,13180,264,305,13933,477,18004,11341,627,9,10164,38752,25,5234,5990,315,3090,38752,304,279,3805,649,45577,3177,11,3339,279,13180,5101,810,47584,323,6437,627,9,96201,25,6690,25793,649,45577,3177,11,18189,279,21261,315,279,6437,1933,382,11458,11,1234,4725,4787,11,449,2867,50393,323,912,5199,45475,32317,11,279,13180,11383,8111,264,20333,6437,13],
"total_duration":27529455478,
"load_duration":11925463866,
"prompt_eval_count":31,
"prompt_eval_duration":2604000000,
"eval_count":261,
"eval_duration":12962000000
}

