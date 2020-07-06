package com.universitypractice.springapplication.entities;

import com.universitypractice.springapplication.enums.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Table(name = "gender")
@Entity
public class GenderEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Gender gender;
}
