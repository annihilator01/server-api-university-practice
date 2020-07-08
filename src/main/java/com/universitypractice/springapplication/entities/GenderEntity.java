package com.universitypractice.springapplication.entities;

import com.universitypractice.springapplication.entities.interfaces.JPAEntity;
import com.universitypractice.springapplication.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "gender")
@Entity
public class GenderEntity implements JPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    @NonNull
    private Gender gender;
}
