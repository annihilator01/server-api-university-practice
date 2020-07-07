package com.universitypractice.springapplication.entities;

import com.universitypractice.springapplication.entities.interfaces.JPAEntity;
import com.universitypractice.springapplication.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "statuses")
@Entity
public class StatusEntity implements JPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;
}
