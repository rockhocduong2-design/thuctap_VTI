📦 Hệ Thống Quản Lý Sản Phẩm (Product Management System)
Mô hình kiến trúc: 3-Tier Architecture (Controller - Service - DAO)
Nguyên tắc thiết kế: SOLID Principles

🚀 Giới thiệu dự án
Ứng dụng quản lý sản phẩm hoàn chỉnh (CRUD) cho phép người dùng thực hiện các thao tác: Thêm, Xem, Sửa, Xóa sản phẩm. 


🛠 Tech Stack
Backend: Java Spring Boot 3.x
Database: MySQL 8.x
Frontend: HTML5, CSS3 (Bootstrap 5), JavaScript (Vanilla JS - Fetch API)

Quản lý kết nối: JDBC (Java Database Connectivity)

Kiến trúc: RESTful API

🏗 Kiến trúc hệ thống & SOLID
Dự án được tổ chức theo mô hình 3 lớp (3-Tier) để đảm bảo tính minh bạch và tách biệt trách nhiệm:

Presentation Layer (Controller): Tiếp nhận các HTTP Request, điều hướng dữ liệu và trả về phản hồi cho client.

Business Logic Layer (Service): Xử lý các nghiệp vụ logic (như in hoa tên sản phẩm, kiểm tra dữ liệu đầu vào) trước khi gửi xuống Database.

Data Access Layer (DAO): Chịu trách nhiệm tương tác trực tiếp với MySQL bằng các câu lệnh SQL.

Nguyên tắc SOLID được áp dụng:
Single Responsibility (S): Mỗi Class chỉ đảm nhận một nhiệm vụ duy nhất (Controller không xử lý logic, DAO không điều hướng).

Dependency Inversion (D): Sử dụng Interface và Dependency Injection (@Autowired) để giảm sự phụ thuộc trực tiếp giữa các lớp, giúp hệ thống linh hoạt hơn khi thay đổi công nghệ lưu trữ.

📂 Cấu trúc thư mục
Plaintext
src/main/java/com/example/
├── controller/     # Điều hướng API
├── service/        # Xử lý logic nghiệp vụ (Interface & Impl)
├── dao/            # Truy xuất cơ sở dữ liệu (Interface & Impl)
├── model/          # Định nghĩa đối tượng (POJO)
└── App.java        # File chạy chính của dự án

src/main/resources/static/
├── js/             # File JavaScript tách riêng (product.js)
├── css/            # Các file định dạng giao diện
└── index.html      # Giao diện chính của ứng dụng
⚙️ Hướng dẫn cài đặt
Database: * Tạo cơ sở dữ liệu tên: project_2.

Tạo bảng product với các cột: productID (AI), Name, order, isactive.

Cấu hình: * Mở file ProductDAO.java và cập nhật thông tin url, user, pass của MySQL trên máy bạn.

Chạy ứng dụng:

Mở project bằng Visual Studio Code.

Nhấn Run file App.java.

Truy cập: http://localhost:8080 để trải nghiệm.

🌟 Các tính năng nổi bật
✅ CRUD hoàn chỉnh: Thêm, xóa, sửa sản phẩm trực tiếp trên giao diện Modal.

✅ Sắp xếp thông minh: Dữ liệu tự động sắp xếp theo cột order (ASC/DESC).

✅ Giao diện Responsive: Hiển thị mượt mà trên nhiều thiết bị nhờ Bootstrap 5.

✅ Xử lý bất đồng bộ: Sử dụng Fetch API để cập nhật dữ liệu mà không cần tải lại trang.
