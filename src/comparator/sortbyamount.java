
package comparator;

import Controllers.SQLTable;

import java.util.Comparator;



    


    public  class sortbyamount implements Comparator<SQLTable> {
     
    //here we use comparator to make our own edited comparision depends on the ammount

    @Override
    public int compare(SQLTable o1, SQLTable o2) {
         return o2.getAmount()-o1.getAmount();}
}
    
