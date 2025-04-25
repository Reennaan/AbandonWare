package com.example.demo.Controller;


import com.example.demo.Model.Game;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    public String url  = "https://www.myabandonware.com";

    //o spring injeta esse Model automaticamente serve como meio de transporte
    //do codigo para a view.
    @GetMapping("/")
    public String index (Model model) throws IOException {
        getAllGames(model);
        return "index";
    }

    public void getAllGames(Model model) throws IOException {
        Document doc = Jsoup.connect(url+"/game/").timeout(90000).get();
        Elements listOfGame = doc.select(".itemListGame");
        Elements gameImgList = listOfGame.select(".c-thumb__img");
        Elements gameNameList = listOfGame.select(".c-item-game__name");
        Elements gamePlatformsList = listOfGame.select(".c-item-game__platforms");
        Elements gameYearList = listOfGame.select(".c-item-game__year");




        List<String> nameList = new ArrayList<>();
        List<String> linkList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<String> imgList = new ArrayList<>();
        List<String> platformList = new ArrayList<>();


       for(int i = 1; i < gameNameList.size() ; i++){

           nameList.add(gameNameList.get(i).text());
           imgList.add(url+gameImgList.get(i).attr("src"));
           linkList.add(url+gameNameList.get(i).attr("href"));
           dateList.add(gameYearList.get(i).text());
           platformList.add(gamePlatformsList.get(i).text());



       }
            System.out.println(imgList);

           model.addAttribute("gameName", nameList);
           model.addAttribute("imgSrc",imgList);
           model.addAttribute("platform",platformList);












    }


}
