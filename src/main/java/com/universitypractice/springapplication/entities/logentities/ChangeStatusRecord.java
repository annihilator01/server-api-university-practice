package com.universitypractice.springapplication.entities.logentities;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.UserEntity;
import com.universitypractice.springapplication.entities.interfaces.JPAEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "change_status_log")
@Entity
public class ChangeStatusRecord implements JPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @NonNull
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "old_status_id", referencedColumnName = "id")
    @NonNull
    private StatusEntity oldStatusEntity;

    @ManyToOne
    @JoinColumn(name = "new_status_id", referencedColumnName = "id")
    @NonNull
    private StatusEntity newStatusEntity;

    @Column(name = "status_changed_time")
    @NonNull
    private Long statusChangedTime;
}
