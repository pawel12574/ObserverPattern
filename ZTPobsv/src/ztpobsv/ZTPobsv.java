/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ztpobsv;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;




class Song extends Observable{
    private ArrayList<Observer> observers;
    private boolean changed=false;
    private String title, performer;
    private double price;

    public Song(String title, String performer, double price) {
        this.title = title;
        this.performer = performer;
        this.price = price;
    }

    public void play() {
        System.out.println("Trwa odtwarzanie "+title+" "+performer);
        setChanged();
        notifyObservers();
    }

    public double getPrice() {
        return price;
    }
    
    public void editSong(String title, String performer, double price) {
        this.title = title;
        this.performer = performer;
        this.price = price;
    }
    
    @Override
    public String toString(){
        return title+" "+performer+" "+price+"zl";
    }

}

class Library implements Observer{
  
    private List<Song> library = new LinkedList<Song>();
    double summaryPrice=0;

    public void add(Song s) {
        library.add(s);
        s.addObserver(this);
    }

    public void delete(Song s) {
        library.remove(s);
        s.deleteObserver(this);
    }

    public Song toPlay(int i) {
         return library.get(i);
    }

   
    @Override
    public void update(Observable o, Object arg) {
        int temp = library.indexOf(o);
        summaryPrice = summaryPrice + library.get(temp).getPrice();
        System.out.println("Rachunek: "+summaryPrice+"zl");
    }
}

public class ZTPobsv {

    
    public static void main(String[] args) {
      Song s1=new Song("piaty bieg","Budka Suflera",2);//nowy utwor
      Song s2=new Song("Hurt"," johny cash",7);
      Library l = new Library();// nowa biblioteka
      l.add(s1);//add song to libary
      l.add(s2);
      s1.play();//song.play()
      s1.play();
      s2.play();
      l.delete(s1);
      s1.play();// play again, price not ++;
      System.out.println(s1.toString());
      s1.editSong("GTAVicecityTheme", "Rockstar", 0);
      System.out.println(s1.toString());
        
    }
    
}
