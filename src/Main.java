import DAO.*;
import Service.*;
import common.Database;
import model.*;
import MenuPackage.*;

public class Main {
    public static void main(String[] args)  {
        try{Database.databaseconfig();}catch (Exception e){e.printStackTrace();}
        //System.out.println("Hello world!");
        Menu.MenuStart();
    }
}