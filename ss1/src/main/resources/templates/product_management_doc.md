
# 📘 Tài Liệu Chức Năng Quản Lý Sản Phẩm (Product)

Ứng dụng hỗ trợ các chức năng cơ bản để quản lý sản phẩm bao gồm: thêm, sửa, xoá và xem danh sách sản phẩm. Tất cả các chức năng này được triển khai bằng Spring Boot + Thymeleaf.

---

## 🔄 Danh sách chức năng

### 1. 📋 Hiển thị danh sách sản phẩm
- **URL:** `/products`
- **Phương thức:** `GET`
- **Mô tả:**  
  Lấy danh sách tất cả sản phẩm và hiển thị dưới dạng bảng. Người dùng có thể chọn chỉnh sửa hoặc xóa sản phẩm từ danh sách này.

---

### 2. ➕ Hiển thị form thêm sản phẩm
- **URL:** `/products/add`
- **Phương thức:** `GET`
- **Mô tả:**  
  Hiển thị biểu mẫu để thêm một sản phẩm mới. Gồm các trường: tên và giá. Có kiểm tra dữ liệu đầu vào (validation).

---

### 3. ✅ Xử lý thêm sản phẩm
- **URL:** `/products/add`
- **Phương thức:** `POST`
- **Mô tả:**  
  Xử lý dữ liệu từ form thêm sản phẩm. Nếu hợp lệ, lưu vào database và chuyển về trang danh sách. Nếu không hợp lệ, hiển thị lại form kèm lỗi.

---

### 4. 🛠 Hiển thị form sửa sản phẩm
- **URL:** `/products/{id}`
- **Phương thức:** `GET`
- **Mô tả:**  
  Hiển thị biểu mẫu để sửa thông tin sản phẩm có `id` tương ứng. Dữ liệu ban đầu được điền sẵn. Dùng chung form với thêm.

---

### 5. 📝 Xử lý cập nhật sản phẩm
- **URL:** `/products/{id}`
- **Phương thức:** `POST`
- **Mô tả:**  
  Cập nhật thông tin sản phẩm có `id` tương ứng bằng dữ liệu từ form. Kiểm tra lỗi logic (trùng tên, không tìm thấy sản phẩm...). Trả lại form nếu có lỗi.

---

### 6. ❌ Xoá sản phẩm
- **URL:** `/products/delete/{id}`
- **Phương thức:** `POST`
- **Mô tả:**  
  Xóa sản phẩm theo `id`. Sau khi xoá thành công, chuyển hướng về trang danh sách. Nếu sản phẩm không tồn tại, ném lỗi và xử lý tại controller.

---

## 🧪 Ghi chú Validation
- **Tên sản phẩm (`name`)**:
  - Không được để trống
  - Không được trùng với sản phẩm khác

- **Giá (`price`)**:
  - Phải lớn hơn 0

---

## 🔒 Gợi ý bảo mật
- Nên bổ sung CSRF Token nếu sử dụng Spring Security.
- Có thể xác thực trước khi cho phép sửa/xóa nếu có phân quyền người dùng.
