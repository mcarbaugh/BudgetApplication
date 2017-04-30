
package budgetApplication.baseClasses;

import budgetApplication.dataContracts.DailyTransaction;
import budgetApplication.dataContracts.TransactionHistory;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
    
    public static String convertTransactionsToXML(List<TransactionHistory> transactions) {
        
        try {
            
            String xmlDocument = "";
            StringBuilder buffer;
            for(int i = 0; i < transactions.size(); i++) {
                buffer = new StringBuilder();
                buffer.append("<transaction>");
                buffer.append("<id>" + transactions.get(i).getId() + "</id>");
                buffer.append("<vendor>" + transactions.get(i).getVendor() + "</vendor>");
                buffer.append("<item>" + transactions.get(i).getItem() + "</item>");
                buffer.append("<category>" + transactions.get(i).getCategory() + "</category>");
                buffer.append("<amount>" + transactions.get(i).getAmount() + "</amount>");
                buffer.append("<date>" + transactions.get(i).getDate() + "</date>");
                buffer.append("</transaction>");
                xmlDocument = String.format("%s%s", xmlDocument, buffer.toString());
            }
            
            return String.format("%s%s%s", "<transactions>", xmlDocument, "</transactions>");
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public static String convertTransactionToXML(TransactionHistory transaction) {
        
        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("<transaction>");
                buffer.append("<id>" + transaction.getId() + "</id>");
                buffer.append("<vendor>" + transaction.getVendor() + "</vendor>");
                buffer.append("<item>" + transaction.getItem() + "</item>");
                buffer.append("<category>" + transaction.getCategory() + "</category>");
                buffer.append("<amount>" + transaction.getAmount() + "</amount>");
                buffer.append("<date>" + transaction.getDate() + "</date>");
                buffer.append("</transaction>");

            return String.format("%s%s%s", "<transactions>", buffer.toString(), "</transactions>");
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public static void order(List<TransactionHistory> transactions) {
        
        Collections.sort(transactions, (Object b1, Object b2) -> {
            Date x1 = ((TransactionHistory) b1).getDate();
            Date x2 = ((TransactionHistory) b2).getDate();
            int dComp = x2.compareTo(x1);
            
            if (dComp != 0) {
                return dComp;
            } else {
                String c1 = ((TransactionHistory) b1).getCategory();
                String c2 = ((TransactionHistory) b2).getCategory();
                int cComp = c1.compareTo(c2);
                if (cComp != 0) {
                    return cComp;
                }
                else {
                    String i1 = ((TransactionHistory) b1).getItem();
                    String i2 = ((TransactionHistory) b2).getItem();
                    int iComp = i1.compareTo(i2);
                    if (iComp != 0) {
                        return iComp;
                    }
                    else {
                        String v1 = ((TransactionHistory) b1).getVendor();
                        String v2 = ((TransactionHistory) b2).getVendor();
                        int vComp = v1.compareTo(v2);
                        if (vComp != 0) {
                            return vComp;
                        }
                        else {
                            return ((TransactionHistory) b1).getId() - ((TransactionHistory) b2).getId();
                        }
                    }
                }
            }
        });
    }
    
}
