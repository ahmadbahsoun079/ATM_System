
package comparator;

import Controllers.SQLTable;
import java.util.Comparator;

/**
 *
 * @author lenovo
 */
public  class sortbyamount implements Comparator<SQLTable> {
     
    

    @Override
    public int compare(SQLTable o1, SQLTable o2) {
         return o2.getAmount()-o1.getAmount();}
}

