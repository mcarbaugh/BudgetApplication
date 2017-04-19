
package budgetApplication.baseClasses;

import budgetApplication.dataContracts.TransactionHistory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class TransactionUtilities {
    //get first day of current week and next week
    public static Date[] getCurrentWeekPeroid(Date current) {
        if (current == null) {
            return null;
        }
        Date[] res = new Date[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(current);
        
        int b = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (b == 0) {
            b = 7;
        }
        
        Date fdate = new Date();
        Long fTime = current.getTime() - b * 24 * 3600000;
        fdate.setTime(fTime + (1 * 24 * 3600000));
        res[0] = fdate;
        fdate.setTime(fTime + (8 * 24 * 3600000));
        res[1] = fdate;
        
        return res;
    }
    
    //get first day of current month and next month
    public static Date[] getCurrentMonthPeroid(Date current) {
        if (current == null) {
            return null;
        }
        Date[] res = new Date[2];
        Date fdate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);    //set date to be the 1st of the month
        res[0] = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);    //month++ -- next month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        res[1] = calendar.getTime();
        
        return res;
    }
    
    public static List<TransactionHistory> getTransactionsInPeriod(List<TransactionHistory> transactions, Date[] date) {
        if (transactions == null) {
            return null;
        }
        Date start = date[0];
        Date end = date[1];
        Date d;
        List<TransactionHistory> res = new ArrayList<>();
        for (TransactionHistory t : transactions) {
            d = t.getDate();
            if ((d.equals(start) || d.after(start)) && d.before(end)) {
                res.add(t);
            }
        }
        
        return res;
    }
}
