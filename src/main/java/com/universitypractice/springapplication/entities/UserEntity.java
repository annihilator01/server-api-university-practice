package com.universitypractice.springapplication.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ManyToOne
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    private GenderEntity genderEntity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer age;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Email
    private String email;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "last_status_changed_time")
    private Long lastStatusChangedTime;

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
        lastStatusChangedTime = new Date().getTime();
    }
}
