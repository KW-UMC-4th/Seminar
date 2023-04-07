package kw.seminar.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Job {

    @Id @GeneratedValue
    @Column(name = "JOB_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private Member member;
}
