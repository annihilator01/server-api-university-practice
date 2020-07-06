package com.universitypractice.springapplication.entities;

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
public class StatusEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Status status;
}
