# Open-Closed principle (OCP) – Nguyên lý đóng mở

Nguyên tắc này được phát biểu như sau:

Có thể thoải mái mở rộng 1 class, nhưng không được sửa đổi bên trong class đó.

Objects or entities should be open for extension, but closed for modification.

Nghe qua thấy nguyên lý có sự mâu thuẫn do thường chúng ta thấy rằng dễ mở rộng là phải dễ thay đổi,
đằng nay dễ mở rộng nhưng không cho thay đổi. Thực sự theo nguyên lý này, chúng ta không được thay
đổi hiện trạng của các lớp có sẵn, nếu muốn thêm tính năng mới, thì hãy mở rộng class cũ bằng cách
kế thừa để xây dựng class mới. Làm như vậy sẽ tránh được các tình huống làm hỏng tính ổn định của
chương trình đang có.

Lợi ích của nguyên lý này là chúng ta không phải lo lắng về code sử dụng các class nguồn bởi vì
chúng ta đã không sửa đổi chúng, vì vậy hành vi của chúng phải giống nhau. Tuy nhiên, chúng ta nên
chú ý vào ý nghĩa của các chức năng, tránh tạo ra quá nhiều class dẫn xuất. Mặc dù những sửa đổi nhỏ
trong class thường không ảnh hưởng, chúng ta cũng cần phải test cẩn thận. Và đó là lý do chính tại
sao chúng ta cần phải viết test case cho các chức năng, để có thể nhận thấy hành vi không mong muốn
xảy ra trong code.

Quay lại với ví dụ ở đoạn code ở trên, nếu các thao tác validation để cùng với logic thì chúng ta có
thể gặp vấn đề sau:

Thêm một validation mới chúng ta phải trực tiếp sửa code bằng if-else condition.
Sửa code nếu validation bị thay đổi logic.
Testing khó khăn, chúng ta phải test cả phần thực hiện logic và validation.
Bây giờ, nếu chúng ta chuyển các thao tác validation sang các lớp khác để xử lý. Cách giải quyết này
được gọi là Dependence Injection. Nếu chúng ta muốn thay đổi cách validate khác cho user, chỉ cần
thay đổi class validator truyền vào.

Thực hiện theo cách này chúng ta đã hoàn thành nguyên tắc Single responsibility principle (chúng ta
đã chuyển trách nhiệm bổ sung sang một class khác). Bây giờ, chúng ta không phải sửa đổi class gốc
nếu chúng ta muốn thêm một class khác để validate dữ liệu. Chúng ta chỉ cần tạo một class thích hợp
mới và gọi nó là tham số trong trường hợp phù hợp.

```java
package com.org.solid.open_closed_principle;

/**
 * OCP - Open/ Closed principle example
 *
 * @author PhungHuynh
 */
class UserServiceV2 {

    private Validator validator;

    public UserServiceV2(Validator validator) {
        this.validator = validator;
    }

    public void saveUser() {
        if (this.validator.isValid()) {
            // Do save
        } else {
            // Show error
        }
    }
}

interface Validator {

    boolean isValid();
}

class UserValidator1 implements Validator {

    @Override
    public boolean isValid() {
        return true;
    }
}

class UserValidator2 implements Validator {

    @Override
    public boolean isValid() {
        return false;
    }
}

public class OCPExample {

    public static void main(String[] args) {
        UserServiceV2 userService1 = new UserServiceV2(new UserValidator1());
        UserServiceV2 userService2 = new UserServiceV2(new UserValidator2());
    }
}
```

Nguyên lý Open-Closed cũng có thể đạt được bằng nhiều cách khác, bao gồm cả việc sử dụng thừa kế (
inheritance) hoặc thông qua các mẫu thiết kế tổng hợp (compositional design patterns) như Strategy
pattern, Factory Pattern. Chúng ta sẽ tìm hiểu các mẫu thiết kế này trong các bài viết tiếp theo.