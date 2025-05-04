package com.example.demo.Controller;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Model.Table;

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
        

           model.addAttribute("gameName", nameList);
           model.addAttribute("link", linkList);
           model.addAttribute("imgSrc",imgList);
           model.addAttribute("platform",platformList);
           
        

    }



    @GetMapping("/gamePage")
    public String redirectToGame(Model model, @RequestParam String link, @RequestParam String gameName) throws IOException{
        
        
        Document doc = Jsoup.connect(link).timeout(90000).get();
        Document docDownload = Jsoup.connect(link+"#download").timeout(90000).get();
        Elements tableInfo = doc.select(".gameInfo");
        Elements description = doc.select(".gameDescription");
        if(description.isEmpty()){ 
            description = doc.select(".gameInstruction");
        }
        Elements snapshots = doc.select(".screens");
        String download = doc.select("a[href='#download'] > span").text(); //muito útil

        Table table = new Table();

        table.setAltNames(tableInfo.select("tbody > tr:nth-of-type(1) > td").text());
        table.setYear(tableInfo.select("tbody > tr:nth-of-type(2) > td").text());
        table.setPlatform(tableInfo.select("tbody > tr:nth-of-type(3) > td").text());
        table.setReleased(tableInfo.select("tbody > tr:nth-of-type(4) > td").text());
        table.setGenre(tableInfo.select("tbody > tr:nth-of-type(5) > td").text());
        table.setTheme(tableInfo.select("tbody > tr:nth-of-type(6) > td").text());
        table.setPublisher(tableInfo.select("tbody > tr:nth-of-type(7) > td").text());
        table.setDeveloper(tableInfo.select("tbody > tr:nth-of-type(8) > td").text());
        table.setPerspectives(tableInfo.select("tbody > tr:nth-of-type(9) > td").text());
        table.setTestedOn(tableInfo.select("tbody > tr:nth-of-type(10) > td").text());
        
        model.addAttribute("altName", table.getAltNames());
        model.addAttribute("year", table.getYear());
        model.addAttribute("platform", table.getPlatform());
        model.addAttribute("released", table.getReleased());
        model.addAttribute("genre", table.getGenre());
        model.addAttribute("theme", table.getTheme());
        model.addAttribute("publisher", table.getPublisher());
        model.addAttribute("developer", table.getDeveloper());
        model.addAttribute("perspectives", table.getPerspectives());
        model.addAttribute("testedOn", table.getTestedOn());

        System.out.println(download);

        model.addAttribute("gameDescription", description);
        model.addAttribute("gameName", gameName);
        model.addAttribute("download", download);
        //model.addAttribute("tab", tableInfo);
        //model.addAttribute("links", links );
        //model.addAttribute("snapshots", snapshots);


        return "gamePage";
    }



}
