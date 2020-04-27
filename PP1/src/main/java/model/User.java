package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "age")
    private Long age;

    public User(){}

    public User(Long id, String name, String password, Long age){
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public User(String name, String password, Long age){
        this.name = name;
        this.password = password;
        this.age = age;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                name.equals(user.name) &&
                password.equals(user.password) &&
                age.equals(user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, age);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
