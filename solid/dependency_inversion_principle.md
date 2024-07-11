# Dependency Inversion Principle (DIP) – Nguyên lý đảo ngược phụ thuộc

Nguyên tắc này được phát biểu như sau:

Các module cấp cao không nên phụ thuộc vào các modules cấp thấp. Cả 2 nên phụ thuộc vào abstraction.
Interface (abstraction) không nên phụ thuộc vào chi tiết, mà ngược lại. ( Các class giao tiếp với
nhau thông qua interface, không phải thông qua implementation.)
High-level modules should not depend on low-level modules. Both should depend on abstractions.
Abstractions should not depend upon details. Details should depend upon abstractions.

Với cách code thông thường, các module cấp cao sẽ gọi các module cấp thấp. Module cấp cao sẽ phụ
thuộc và module cấp thấp, điều đó tạo ra các dependency. Khi module cấp thấp thay đổi, module cấp
cao phải thay đổi theo. Một thay đổi sẽ kéo theo hàng loạt thay đổi, giảm khả năng bảo trì của code.

Nếu tuân theo Dependendy Inversion principle, các module sẽ cùng phụ thuộc vào một interface không
đổi. Nghĩa là thay vì để các module cấp cao sử dụng các interface do các module cấp thấp định nghĩa
và thực thi, thì nguyên lý này chỉ ra rằng các lớp module cấp cao sẽ định nghĩa ra các interface,
sau đó các lớp module cấp sẽ thực thi các interface đó. Khi đó, ta có thể dễ dàng thay thế, sửa đổi
module cấp thấp mà không ảnh hưởng gì tới module cấp cao.

Ví dụ:

```java
package com.org.solid.dependency_inversion_principle;

/**
 * DIP – Dependency inversion principle example
 *
 * @author PhungHuynh
 */
interface DBConnection {

    void connect();
}

class OracleConnection implements DBConnection {

    @Override
    public void connect() {
        System.out.println("Oracle connected");
    }
}

class MySQLConnection implements DBConnection {

    @Override
    public void connect() {
        System.out.println("MySQL connected");
    }
}

class PostgreSQLConnection implements DBConnection {

    @Override
    public void connect() {
        System.out.println("PostgreSQL connected");
    }
}

class DbConnectionFactory {

    private final DBConnection dbConnection;

    public DbConnectionFactory(DBConnection dbConnection) {
        this.dbConnection = dbConnection;
        this.dbConnection.connect();
    }

    public DBConnection getConnection() {
        return this.dbConnection;
    }

}

public class DIPExample {

    public static void main(String[] args) {
        DBConnection conn = new OracleConnection();
        DbConnectionFactory factory = new DbConnectionFactory(conn);
    }

}
```

Như bạn thấy, các module của chúng ta không phụ thuộc vào nhau.Việc thay đổi code của một module này
không ảnh hưởng đến các module còn lại. Nếu muốn hỗ trợ thêm SQLServer chỉ việc tạo thêm class mới,
implement từ DbConnection. Nếu muốn đổi kết nối sang Oracle chỉ việc thay đổi trong config, …