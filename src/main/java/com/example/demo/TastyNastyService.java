package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class TastyNastyService {

    @Autowired
    TastyRepository tasties;

    @Autowired
    NastyRepository nasties;

    public String last5MinuteResult(Long id){
        String tastyOrNasty="";
        ArrayList<Tasty>dishTastyVote=(ArrayList)tasties.findAllByTheDish_IdAndVoteAtAfter(id, LocalDateTime.now().minusMinutes(5));
        System.out.println("Tasty Count: " + dishTastyVote.size());

        ArrayList<Nasty>dishNastyVote=(ArrayList<Nasty>)nasties.findAllByTheDish_IdAndVoteAfter(id,LocalDateTime.now().minusMinutes(5));
        System.out.println("Nasty Count: " + dishNastyVote.size());

        if(dishTastyVote.size()>dishNastyVote.size()){
            tastyOrNasty="tasty";
        }
        else if(dishTastyVote.size()==dishNastyVote.size()){
            tastyOrNasty="undefined";
        }
        else
            tastyOrNasty="nasty";
        System.out.println(tastyOrNasty);

        return tastyOrNasty;
    }
}
