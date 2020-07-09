package com.universitypractice.springapplication.entities.logentities;

import com.universitypractice.springapplication.entities.StatusEntity;
import com.universitypractice.springapplication.entities.interfaces.JPAEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Table(name = "user_statuses_request_log")
@Entity
public class UserStatusesRequestRecord implements JPAEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "status_id_filter", referencedColumnName = "id")
    private StatusEntity statusEntityFilter;

    @Column(name = "changed_after_timestamp")
    private Long changedAfterTimestamp;

    @Column(name = "request_timestamp")
    private Long requestTimestamp;

    public UserStatusesRequestRecord(StatusEntity statusEntityFilter, Long changedAfterTimestamp) {
        this.statusEntityFilter = statusEntityFilter;
        this.changedAfterTimestamp = changedAfterTimestamp;
    }

    @PrePersist
    protected void onCreate() {
        requestTimestamp = new Date().getTime();
    }
}
