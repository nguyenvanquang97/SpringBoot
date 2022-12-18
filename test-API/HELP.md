## Câu 1

-Thuộc tính name trong Entity sẽ thay đổi cả tên bảng và tên entity
-Còn thuộc tính name trong Table chỉ thay đổi tên bảng,không thay đổi tên entity

## Câu 2

Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh :
spring.jpa.show-sql=true
vào file application.properties

## Câu 3

-thuộc tính name trong @Colum sẽ đổi tên cột nếu muốn khác với thuộc tính
-tham số unique định nghĩa yêu cầu duy nhất,không được trùng lặp dữ liệu
-tham số nullable để định nghĩa có cho phép column null hay không

## Câu 4

-Ngay trước khi đổi tượng Entity lưu xuống cơ sở dữ liệu,ta có thể dùng
@PrePersist
-Ngay trước khi đổi tượng Entity cập nhật xuống cơ sở dữ liệu,ta có thể dùng
@PreUpdate

## Câu 5

-JpaRepository kế thừa từ interface CrudRepository

## Câu 6

```java
public interface PostRepository extends JpaRepository<Post, Long> {
}
```

## Câu 7

-Khi đã chọn một cột là Identity dùng @Id để đánh dấu,không cần phải dùng xác định unique dùng annotation @Column(unique=true) 

## Câu 8

Tìm tất cả các Employee theo emailAddress và lastName
```java
List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);
```

-Tìm tất cả các Employee khác nhau theo firstName hoặc lastName

```java
    Set<Employee> findByFirstNameOrLastName(String firstName, String lastName);
```

-Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần

```java
 List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
```
-Tìm tất cả các Employee theo fistName không phân biệt hoa thường

```java
  List<Employee> findByFirstNameIgnoreCase(String firstName);
```

## Câu 9

-Cách sử dụng  @NamedQuery :
```java
@NamedQuery(name = "Employee.fetchByLastNameLength",
        query = "SELECT e FROM Employee e WHERE CHAR_LENGTH(e.lastname) =:length "
)
```
-Cách sử dụng @Query:
```java
 @Query("select employee from Employee employee where employee.firstName = ?1")
    Employee findByFirstName(String firstName);
```

## Câu 10

Tạo phương thức ở EmployeeRepository
```java
List<Employee> findByFirstNameContains(String firstName, Sort sort);
Page<Employee> findByLastNameContains(String lastName, Pageable pageable);
```

Sử dụng sorting và paging khi query đối tượng Employee ở trên
```java
@Test
void sortByFirstName() {
List<Employee> employees = employeeRepository.findByFirstNameContains("a", Sort.by("firstName"));
employees.forEach(System.out::println);
}
```
```java
 @Test
    void findByLastNameContains() {
        Page<Employee> pages = employeeRepository.findByLastNameContains("er", PageRequest.of(1,10));
        pages.getContent().forEach(System.out::println);
        System.out.println(pages.getTotalPages());
        System.out.println(pages.getTotalElements());
    }
```
   
## Câu 11

Bổ sung định nghĩa quan hệ One to Many giữa bảngCategory (One) – Product
(Many):
-Bổ sung ở Entity Product
```java
   @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
```
-Bổ sung ở Entity Category:
```java
 @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    @PreRemove
    public void preRemove() {
    products.forEach(p->p.setCategory(null));
    }
```
Bổ sung định nghĩa quan hệ Many to Many giữa bảng Tag(Many) – Product(Many):
-Bổ sung ở Entity Product:
```java
 @ManyToMany
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "product_null"),
            inverseJoinColumns = @JoinColumn(name = "tags_id"))
    private Set<Tag> tags = new LinkedHashSet<>();
```
## Câu 12
```java

```

## Câu 13
Thêm dependencies:
```java
 <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.12.0</version>
        </dependency>
```
Tạo class MyGenerater:
```java
public class MyGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return RandomStringUtils.random(10,true,true);
    }
}
```
Sửa cột id của Entity blog:
```java
 @Id
    @GeneratedValue(generator = "generator")
    @GenericGenerator(name = "generator",
            strategy = "com.example.testapi.MyGenerator")
    @Column(name = "id", nullable = false)
    private String id;
```


