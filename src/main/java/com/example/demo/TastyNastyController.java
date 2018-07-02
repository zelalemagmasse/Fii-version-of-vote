package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class TastyNastyController {
    @Autowired
    private FoodRepository dishes;

    @Autowired
    private TastyRepository tastyVotes;

    @Autowired
    private NastyRepository nastyVotes;

    @Autowired
    private TastyNastyService foodService;

    @Autowired
    private CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String showIndex(Model model){
        ArrayList<Food>dishList=(ArrayList)dishes.findAll();
        for(Food dish:dishList){
            dish.setLast5minutes(foodService.last5MinuteResult(dish.getId());
        }
        model.addAttribute("dishes",dishList);
        return "listfood";
    }

    @RequestMapping("/list")
    public String showList(Model model){
        ArrayList<Food>dishList=(ArrayList)dishes.findAll();
        for(Food dish:dishList){
            dish.setLast5minutes(foodService.last5MinuteResult(dish.getId());
        }
        model.addAttribute("dishes",dishList);
        return "listfood3";
    }
    @RequestMapping("/tastyvote")
    public String addTastyVote(HttpServletRequest request){
        long foodID= new Long(request.getParameter("id"));
        Tasty tastyNastyVote= new Tasty();
        tastyNastyVote.setTheDish(dishes.findById(foodID).get());
        tastyNastyVote.setVotedAt();
        tastyVotes.save(tastyNastyVote());

        return "redirect:/";

    }

    @RequestMapping("/nastyvote")
    public String addNastyVote(HttpServletRequest request){
        long foodID= new Long(request.getParameter("id"));
        Nasty nastyVote= new Nasty();
        nastyVote.setTheDish(dishes.findById(foodID).get());
        nastyVote.setVotedAt();

        nastyVotes.save(nastyVote);
        return "redirect:/";
    }

    @RequestMapping("/showvotes")
    public @ResponseBody String pizzaVote()
    {
        Food pizza=dishes.findById(new Long(1)).get();
        String tastyNasty=pizza.getTastyVotes().size()>=pizza.getNastyVotes().size()?"tasy":"nasty";
        return pizza.getDescrition()+ "has" + pizza.getTastyVotes().size() +"tasy votes and " + pizza.getNastyVotes().size()+ "nast votes. The balance tilts in favour of :"+ tastyNasty;
    }

}
