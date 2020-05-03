package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Long age;

    @Column(name = "role")
    private String role;

    public User(){}

    public User(Long id, String name, String password, Long age, String role){
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public User(String name, String password, Long age, String role){
        this.name = name;
        this.password = password;
        this.age = age;
        this.role = role;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setAge(Long age){
        this.age = age;
    }

    public Long getAge(){
        return age;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                password.equals(user.password) &&
                age.equals(user.age) &&
                role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, age, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
