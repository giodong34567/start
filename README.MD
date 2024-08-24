
# Giới thiệu

Spring MVC Starter v2 là một dự án khởi động (starter) mạnh mẽ và linh hoạt dành cho các ứng dụng web phát triển dựa trên Spring Framework. Nó cung cấp một cấu trúc thư mục chuẩn, các tính năng cơ bản cần thiết như bảo mật, xử lý template với Thymeleaf, và thực hiện các thao tác CRUD cơ bản. Dự án này phù hợp với cả những lập trình viên mới bắt đầu cũng như những người đã có kinh nghiệm muốn nhanh chóng thiết lập một ứng dụng web Spring MVC.


# Các tính năng chính

- **Cấu hình bảo mật mạnh mẽ**: Sử dụng Spring Security để quản lý việc xác thực và phân quyền người dùng.
- **Tích hợp Thymeleaf**: Sử dụng Thymeleaf làm engine template cho việc xử lý các trang HTML.
- **Cấu trúc MVC chuẩn**: Tuân theo mô hình Model-View-Controller giúp dễ dàng quản lý và mở rộng ứng dụng.
- **Quản lý lỗi toàn cục**: Sử dụng GlobalExceptionHandler để quản lý các ngoại lệ phát sinh trong ứng dụng.
- **Tích hợp cơ sở dữ liệu H2**: Sử dụng H2 database cho việc phát triển và thử nghiệm.
- **Thực hiện CRUD đầy đủ**: Cung cấp ví dụ hoàn chỉnh cho các thao tác Create, Read, Update, Delete trên các thực thể "Class" và "Student".


# Yêu cầu hệ thống

- **Java**: Phiên bản 11 trở lên
- **Maven**: Phiên bản 3.6 trở lên
- **IDE**: IntelliJ IDEA, Eclipse, hoặc bất kỳ IDE nào hỗ trợ Spring.



# Cấu trúc thư mục

```plaintext
F:.
|   .gitignore
|   HELP.md
|   mvnw
|   mvnw.cmd
|   output.txt
|   pom.xml
|   README.MD
|
+---.idea
|   \---inspectionProfiles
|
+---.mvn
|   \---wrapper
|
+---.vscode
|
+---src
|   +---main
|   |   +---java
|   |   |   \---springmvc
|   |   |       \---starter
|   |   |           \---demo
|   |   |               +---config
|   |   |               +---controller
|   |   |               +---dto
|   |   |               +---exception
|   |   |               +---model
|   |   |               +---repository
|   |   |               +---service
|   |   |               \---vo
|   |   \---resources
|   |       +---static
|   |       |   +---css
|   |       |   \---js
|   |       \---templates
|   |           +---fragments
|   |           +---layout
|   |           \---page
|   \---test
|       \---java
|           \---springmvc
|               \---starter
|                   \---demo
\---target
```

## Chi tiết các thư mục

- **`src/main/java`**: Chứa mã nguồn chính của ứng dụng.
  - **`config`**: Cấu hình ứng dụng, bao gồm Spring Security và Thymeleaf.
  - **`controller`**: Các lớp điều khiển, xử lý yêu cầu HTTP và trả về kết quả.
  - **`dto`**: Các lớp Data Transfer Object, dùng để truyền dữ liệu giữa các lớp và tầng.
  - **`exception`**: Các lớp ngoại lệ và bộ xử lý ngoại lệ toàn cục.
  - **`model`**: Các lớp mô hình tương ứng với bảng trong cơ sở dữ liệu.
  - **`repository`**: Các lớp tương tác với cơ sở dữ liệu (DAO).
  - **`service`**: Các lớp xử lý nghiệp vụ, logic của ứng dụng.
  - **`vo`**: Các lớp View Object, dùng để truyền dữ liệu từ server tới client.

- **`src/main/resources`**: Chứa các tài nguyên và cấu hình ứng dụng.
  - **`static`**: Chứa các tài nguyên tĩnh như CSS, JavaScript.
  - **`templates`**: Chứa các file HTML được xử lý bởi Thymeleaf.
    - **`fragments`**: Các phần tử giao diện dùng chung như header, footer.
    - **`layout`**: Các bố cục chính của trang web.
    - **`page`**: Các trang cụ thể như trang chủ, liên hệ, thông tin.

- **`src/test/java`**: Chứa các lớp kiểm thử.

- **`pom.xml`**: Tệp cấu hình Maven, dùng để quản lý các dependency và plugin của dự án.

- **`application.properties`**: Tệp cấu hình ứng dụng, chứa thông tin cấu hình về database, security, và các cài đặt khác.


# Hướng dẫn sử dụng

## Thêm mới một thực thể

1. **Tạo Model:**
   - Thêm lớp mô hình trong thư mục `model`. Ví dụ: `Teacher.java`.
  
2. **Tạo DTO và VO:**
   - Tạo lớp DTO và VO tương ứng trong thư mục `dto` và `vo`.
  
3. **Tạo Repository:**
   - Tạo interface Repository trong thư mục `repository`. Ví dụ: `TeacherRepository.java`.
  
4. **Tạo Service:**
   - Tạo lớp dịch vụ trong thư mục `service`. Ví dụ: `TeacherService.java`.

5. **Tạo Controller:**
   - Tạo controller trong thư mục `controller` để xử lý các yêu cầu từ người dùng.

## Tạo một trang mới với Thymeleaf

1. **Tạo tệp HTML mới:**
   - Tạo tệp HTML trong thư mục `templates/page`. Ví dụ: `teacher.html`.
  
2. **Sử dụng các fragment:**
   - Sử dụng các phần tử chung như header và footer từ thư mục `templates/fragments`.

3. **Kết nối với Controller:**
   - Đảm bảo rằng controller trả về tên của template HTML tương ứng.


# Hướng dẫn sử dụng

## Thêm mới một thực thể

1. **Tạo Model:**
   - Thêm lớp mô hình trong thư mục `model`. Ví dụ: `Teacher.java`.
  
2. **Tạo DTO và VO:**
   - Tạo lớp DTO và VO tương ứng trong thư mục `dto` và `vo`.
  
3. **Tạo Repository:**
   - Tạo interface Repository trong thư mục `repository`. Ví dụ: `TeacherRepository.java`.
  
4. **Tạo Service:**
   - Tạo lớp dịch vụ trong thư mục `service`. Ví dụ: `TeacherService.java`.

5. **Tạo Controller:**
   - Tạo controller trong thư mục `controller` để xử lý các yêu cầu từ người dùng.

## Tạo một trang mới với Thymeleaf

1. **Tạo tệp HTML mới:**
   - Tạo tệp HTML trong thư mục `templates/page`. Ví dụ: `teacher.html`.
  
2. **Sử dụng các fragment:**
   - Sử dụng các phần tử chung như header và footer từ thư mục `templates/fragments`.

3. **Kết nối với Controller:**
   - Đảm bảo rằng controller trả về tên của template HTML tương ứng.


# Giấy phép

Dự án này được phát hành dưới giấy phép MIT. Vui lòng xem tệp LICENSE để biết thêm chi tiết.


# Liên hệ

Nếu bạn có bất kỳ câu hỏi hoặc phản hồi nào, vui lòng mở một Issue trên GitHub hoặc liên hệ trực tiếp với chúng tôi qua email: [nam077.me@gmail.com].


