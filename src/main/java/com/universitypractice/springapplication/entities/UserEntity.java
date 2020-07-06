package com.universitypractice.springapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "users")
@Entity
public class UserEntity {

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

    @Email
    private String email;

    @Column(name = "last_status_changed_time")
    private Long lastStatusChangedTime;

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
        lastStatusChangedTime = new Date().getTime();
    }
}
