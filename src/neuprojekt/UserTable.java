/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuprojekt;

/**
 *
 * @author tugce
 */
public class UserTable {
    
    String name, title, level;
    
    public UserTable(String name, String title, String level) {
        
        this.name = name;
        this.title = title;
        this.level = level;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(){
        this.name = name;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(){
        this.title = title;
    }
    
    public String getLevel(){
        return level;
    }
    
    public void setLevel(){
        this.level = level;
    }
    
}
