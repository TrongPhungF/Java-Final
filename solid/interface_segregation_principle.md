# Interface segregation principle (ISP) – Nguyên lý phân tách

Nguyên tắc này được phát biểu như sau:

Thay vì dùng 1 interface lớn, ta nên tách thành nhiều interface nhỏ, với nhiều mục đích cụ thể.

Many client-specific interfaces are better than one general-purpose interface.

Nguyên lý này khá dễ hiểu. Hãy tưởng tượng chúng ta có 1 interface lớn, khoảng 100 methods. Việc
implements sẽ khá cực khổ, ngoài ra còn có thể dư thừa vì 1 class không cần dùng hết 100 method. Khi
tách interface ra thành nhiều interface nhỏ, gồm các method liên quan tới nhau, việc implement và
quản lý sẽ dễ hơn.

Ví dụ:

```java
package com.org.solid.interface_segregation_principle.;

/**
 * ISP – Interface segregation principle example
 *
 * @author PhungHuynh
 */
interface Repository<T, ID> {

    Iterable<T> findAll();

    T findOne(ID id);

    T save(T entity);

    void update(T entity);

    void delete(ID id);

    Page<T> findAll(Pageable pageable);

    Iterable<T> findAll(Sort sort);
}
  ```

Như bạn thấy interface Repository, class này bao gồm các rất nhiều phương thức: lấy danh sách, lấy
theo id, insert, update, delete, lấy danh sách có phân trang, sắp xếp, … Việc implement tất cả các
phương thức này hết sức cực khổ, đôi khi không cần thiết do chúng ta không sử dụng hết.

Thay vào đó chúng ta có thể rách ra như sau:

```java
interface CrudRepository<T, ID> {

    Iterable<T> findAll();

    T findOne(ID id);

    T save(T entity);

    void update(T entity);

    void delete(ID id);

}

interface PagingAndSortingRepository<T, ID> extends CrudRepository<T, ID> {

    Page<T> findAll(Pageable pageable);

    Iterable<T> findAll(Sort sort);

}
```

Đối với một số chức năng đặc biệt chúng ta mới cần implement từ interface
PagingAndSortingRepository, với chức năng thông thường chỉ cần CrudRepository là đủ.

Một vi phạm khác cũng thường gặp trong dự án là class CommonUtil. Lớp này bao gồm rất nhiều phương
thức: xử lý ngày giờ, số, chuỗi, chuyển đổi định dạng JSON, … tất cả mọi thức liên quan đến xử lý
common đều được đặt vào đây. Càng ngày số lượng các phương thức càng lớn, khi đó sẽ phát sinh rất
nhiều vấn đề như: trùng code, nhiều phương thức giống nhau không biết sử dụng phương thức nào, khi
phát sinh bug rất khó bảo trì. Đối với những trường hợp này chúng ta nên chia nhỏ theo chức năng của
nó, ví dụ như: DateTimeUtil, StringUtil, NumberUtil, JsonUtil, ReflectionUtil, … như vậy sẽ dễ dàng
quản lí và sử dụng hơn.