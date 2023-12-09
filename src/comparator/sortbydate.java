/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comparator;

import Controllers.SQLTable;
import java.util.Comparator;

/**
 *
 * @author lenovo
 */
public class sortbydate implements Comparator<SQLTable>{

    //here we use comparator to make our own edited comparision depends on the date
    @Override
    public int compare(SQLTable o1, SQLTable o2) {
         return  o1.dateProperty().get().compareTo(o2.dateProperty().get()); }
    
}
