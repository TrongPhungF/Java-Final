# Single Responsibility Principle (SRP)

Nguyên tắc đơn trách nhiệm được phát biểu như sau:

Một class chỉ nên có một lý do duy nhất để thay đổi, có nghĩa là một class chỉ nên đảm nhiệm một
công việc duy nhất.
Để hiểu nguyên lý này, hãy xem ví dụ về một class vi phạm nguyên lý:

```java
package com.org.solid.single_responsibility_principle;

/**
 * SRP – Ví dụ về nguyên tắc đơn trách nhiệm
 *
 * @author PhungHuynh
 */
public class UserService {

    // Lấy dữ liệu từ cơ sở dữ liệu
    public User getUser() {
        return null;
    }

    // Kiểm tra tính hợp lệ
    public boolean isValid() {
        return true;
    }

    // Hiển thị thông báo
    public void showNotification() {
    }

    // Ghi log
    public void logging() {
        System.out.println("...");
    }

    // Phân tích dữ liệu
    public User parseJson(String json) {
        return null;
    }
}
```

Như bạn thấy, class này thực hiện nhiều trách nhiệm khác nhau: lấy dữ liệu từ cơ sở dữ liệu, kiểm
tra tính hợp lệ, hiển thị thông báo, ghi log, phân tích dữ liệu, v.v. Nếu chúng ta cần thay đổi
cách lấy dữ liệu từ cơ sở dữ liệu hoặc thay đổi logic kiểm tra tính hợp lệ, chúng ta sẽ phải sửa
đổi class này, khiến nó trở nên lớn hơn và khó bảo trì, nâng cấp, sửa lỗi, và kiểm thử theo thời
gian.

Theo nguyên tắc này, chúng ta nên tách class này thành nhiều class nhỏ hơn, mỗi class chỉ xử lý một
trách nhiệm duy nhất. Mặc dù số lượng class sẽ tăng lên, việc sửa đổi sẽ đơn giản hơn, tái sử dụng
dễ dàng hơn, và các class ngắn hơn, dẫn đến ít lỗi hơn.

Ví dụ, chúng ta có thể tách chương trình trên thành các class: UserRepository, UserValidator,
SystemLogger, JsonConverter, v.v.

Một số ví dụ về các trách nhiệm cần được tách riêng bao gồm: Lưu trữ, Kiểm tra tính hợp lệ, Thông
báo, Xử lý lỗi, Ghi log, Khởi tạo class, Định dạng, Phân tích, Ánh xạ, v.v.