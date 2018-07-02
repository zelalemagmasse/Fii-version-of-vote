package com.example.demo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Tasty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getVotedAt() {
        return votedAt;
    }

    public void setVotedAt(LocalDateTime votedAt) {
        this.votedAt = votedAt;
    }

    public String getTastyOrNasty() {
        return tastyOrNasty;
    }

    public void setTastyOrNasty(String tastyOrNasty) {
        this.tastyOrNasty = tastyOrNasty;
    }

    public Food getTheDish() {
        return theDish;
    }

    public void setTheDish(Food theDish) {
        this.theDish = theDish;
    }

    private LocalDateTime votedAt;
    private  String tastyOrNasty;

    @ManyToMany()
    private Food theDish;

}
