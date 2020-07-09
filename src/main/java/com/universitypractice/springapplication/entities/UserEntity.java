package com.universitypractice.springapplication.entities;

import com.universitypractice.springapplication.entities.interfaces.JPAEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity implements JPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @NonNull
    private StatusEntity statusEntity;

    @Column(length = 50)
    @NonNull
    private String username;

    @Column(name = "first_name", length = 50)
    @NonNull
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private GenderEntity genderEntity;

    private Integer age;

    @Email(message = "invalid email format")
    private String email;

    @Column(name = "last_status_changed_time")
    private Long lastStatusChangedTime;

    public UserEntity(StatusEntity statusEntity, String username, String firstName, String lastName,
                      GenderEntity genderEntity, Integer age, @Email String email) {
        this.statusEntity = statusEntity;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.genderEntity = genderEntity;
        this.age = age;
        this.email = email;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
        lastStatusChangedTime = new Date().getTime();
    }
}
