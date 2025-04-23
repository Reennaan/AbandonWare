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
    @GetMapping("/index")
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



        List<Game> games = new ArrayList<>();
       for(int i = 1; i < gameNameList.size() ; i++){
           Game game = new Game();
           game.setId(i);
           game.setImg(url+gameImgList.get(i).attr("src"));
           game.setName(gameNameList.get(i).text());
           game.setLink(url+gameNameList.get(i).attr("href"));
           game.setDate(gameYearList.get(i).text());
           game.setPlatform(gamePlatformsList.get(i).text());
           games.add(game);
       }



        //model.addAttribute("popularGames", ListOfGame);

        System.out.println(games);


    }


}
