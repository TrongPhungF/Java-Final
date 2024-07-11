# Liskov substitution principle (LSP) – Nguyên lý thay thế

Nguyên tắc này được phát biểu như sau:

Trong một chương trình, các object của class con có thể thay thế class cha mà không làm thay đổi
tính đúng đắn của chương trình.
Objects in a program should be replaceable with instances of their subtypes without altering the
correctness of that program.

1. Ví dụ class con thay đổi hành vi class cha

Để minh họa điều này, chúng ta sẽ đi với một ví dụ kinh điển kinh điển về hình vuông và hình chữ
nhật mà mọi người thường dùng để giải thích LSP vì nó rất đơn giản và dễ hiểu.

```java
package com.org.solid;

/**
 * LSP - Liskov substitution principle example
 *
 * @author PhungHuynh
 */
class Rectangle {

    private int width;
    private int height;

    public int calculateArea() {
        return this.width * this.height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}

class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }

}

public class LSPExample1 {

    public void example1() {
        Rectangle rect = new Rectangle();
        rect.setWidth(5);
        rect.setHeight(10);
        System.out.println(rect.calculateArea()); // 50

        Square square = new Square();
        square.setWidth(5);
        square.setHeight(10);
        System.out.println(square.calculateArea()); // 100
    }

}
```

Nhìn ví dụ trên ta thấy mọi tính toán đều rất hợp lý. Do hình vuông có 2 cạnh bằng nhau, mỗi khi set
độ dài 1 cạnh thì ta set luôn độ dài của cạnh còn lại.

Tuy nhiên, Class Square kế thừa từ class Rectangle nhưng class Square có những hình vi khác và nó đã
thay đổi hành vi của của class Rectangle, dẫn đến vi phạm LSP.

Như ví dụ trên, do Class Square kế thừa từ class Rectangle nên chúng ta có thể sử dụng như sau:

```java 
public class LSPExample1 {

    public void example2() {
        Rectangle rect = new Square();
        rect.setWidth(5);
        rect.setHeight(10);
        System.out.println(rect.calculateArea()); // 100
    }
}
```

Rõ ràng kết quả không đúng, diện tích của hình chữ nhật phải là 5*10=50.

Theo nguyên tắc này, chúng ta phải bảo đảm rằng khi một lớp con kế thừa từ một lớp khác, nó sẽ không
làm thay đổi hành vi của lớp đó.

Trong trường hợp này, để code không vi phạm nguyên lý LSP, chúng ta phải tạo 1 class cha là class
Shape, sau đó cho Square và Rectangle kế thừa class Shape này.

2. Ví dụ class con throw exception khi gọi hàm

Một trường hợp khác cũng vi phạm LSP là class con throw exception khi gọi hàm.

```java
package com.org.solid.liskov_substitution_principle;

/**
 * LSP - Liskov substitution principle example
 *
 * @author PhungHuynh
 */
interface FileService {

    void getFiles();

    void deleteFiles();
}

class ImageFileService implements FileService {

    @Override
    public void getFiles() {
        // Load image files
    }

    @Override
    public void deleteFiles() {
        // Delete image files
    }
}

class TempFileService implements FileService {

    @Override
    public void getFiles() {
        // Load temp files
    }

    @Override
    public void deleteFiles() {
        // Delete temp files
    }
}
```

Những class implement ở trên không có vấn đề gì, mọi thứ đều chạy tốt. Bây giờ chúng ta thêm một
class SystemFileService mới. Với yêu cầu là không được xóa các file hệ thống, nên lớp này sẽ quăng
ra lỗi UnsupportedOperationException. Một phương thức được thiết kế nhưng không được sử dụng, đây
cũng không phải là một thiết kế tốt.

Khi thực thi phương thức deleteFiles(), class SystemFileService gây lỗi khi chạy. Nó không thay thế
được class cha của nó là FileService, vì thế nó đã vi phạm LSP.

```java
class SystemFileService implements FileService {

    @Override
    public void getFiles() {
        // Load temp files
    }

    @Override
    public void deleteFiles() {
        throw new UnsupportedOperationException();
    }
}
```

3. Những vi phạm về nguyên lý LSP
   Một số dấu hiệu điển hình có thể chỉ ra rằng LSP đã bị vi phạm:

- Các lớp dẫn xuất có các phương thức ghi đè phương thức của lớp cha nhưng với chức năng hoàn toàn
  khác.
- Các lớp dẫn xuất có phương thức ghi đè phương thức của lớp cha là một phương thức rỗng.
- Các phương thức bắt buộc kế thừa từ lớp cha ở lớp dẫn xuất nhưng không được sử dụng.
- Phát sinh ngoại lệ trong phương thức của lớp dẫn xuất.

4. Lưu ý
   Đây là nguyên lý… dễ bị vi phạm nhất, nguyên nhân chủ yếu là do sự thiếu kinh nghiệm khi thiết kế
   class. Thuông thường, design các class dựa theo đời thật: hình vuông là hình chữ nhật, file nào
   cũng
   là file. Tuy nhiên, không thể bê nguyên văn mối quan hệ này vào code. Hãy nhớ 1 điều:

Trong thực tế, A là B (hình vuông là hình chữ nhật) không có nghĩa là class A nên kế thừa class B.
Chỉ cho class A kế thừa class B khi class A thay thế được cho class B.
File hệ thống cũng là file nhưng không thay thế được cho file, do đó ví dụ này vi phạm LSP.
Nguyên lý này ẩn giấu trong hầu hết mọi đoạn code, giúp cho code linh hoạt và ổn định mà ta không hề
hay biết. Ví dụ như trong Java, ta có thể chạy hàm foreach với List, ArrayList, LinkedList bởi vì
chúng cùng kế thừa interface Iterable. Các class List, ArrayList, … đã được thiết kế đúng LSP, chúng
có thể thay thế cho Iterable mà không làm hỏng tính đúng đắn của chương trình.

