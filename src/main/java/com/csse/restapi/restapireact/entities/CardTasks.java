package com.csse.restapi.restapireact.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cardTasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "taskText")
    private String taskText;

    @Column( columnDefinition = "boolean default false" )
    private boolean done;

    @Column(name = "addedDate")
    private Date addedDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Cards card;

}
