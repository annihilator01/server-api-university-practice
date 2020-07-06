package com.universitypractice.springapplication.entities;

import com.universitypractice.springapplication.enums.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
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
