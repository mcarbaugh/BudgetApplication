
package budgetApplication.baseClasses;

import budgetApplication.dataContracts.DailyTransaction;
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
        
        Date fdate1 = new Date();
        Date fdate2 = new Date();
        Long fTime = current.getTime() - b * 24 * 3600000;
        fdate1.setTime(fTime + (1 * 24 * 3600000));
        res[0] = fdate1;
        fdate2.setTime(fTime + (8 * 24 * 3600000));
        res[1] = fdate2;
        return res;
    }
    
    //get first day of current month and next month
    public static Date[] getCurrentMonthPeroid(Date current) {
        if (current == null) {
            return null;
        }
        Date[] res = new Date[2];
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
        Long sl = start.getTime();
        Date end = date[1];
        Long el = end.getTime();
        Date d;
        Long dl;
        List<TransactionHistory> res = new ArrayList<>();
        for (TransactionHistory t : transactions) {
            d = t.getDate();
            dl = d.getTime();
            if (dl >= sl  && dl < el) {
                res.add(t);
            }
        }        
        return res;
    }
    
    public static List<DailyTransaction> getDailyTransactions(List<TransactionHistory> transactions, Date[] date) {
        List<DailyTransaction> res = new ArrayList<>();
        Long[] days = new Long[31];
        Long day = date[0].getTime();
        DailyTransaction d;
        Long hl;
        for (int i = 0; i < 31; i++) {
            days[i] = day + i * 24 * 3600000;
            if (days[i] >= date[1].getTime()) {
                break;
            }
            else {
                d = new DailyTransaction();
                d.setDay(days[i]);
                d.setAmount(0.0);
                res.add(d);
            }
        }
        
        for (TransactionHistory h : transactions) {
            hl = h.getDate().getTime();
            for (DailyTransaction t : res) {
                if (t.getDay().equals(hl)) {
                    double amount = t.getAmount() + h.getAmount();
                    t.setAmount(amount);
                }
            }
        }
        
        return res;
    }
}
