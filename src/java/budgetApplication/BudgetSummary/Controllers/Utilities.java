
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.dataContracts.Income;
import budgetApplication.dataContracts.Item;
import java.util.List;

public class Utilities {
    
    public static String convertItemsToXML(List<Item> items) {
        
        try {
            
            String xmlDocument = "";
            StringBuilder buffer;
            for(int i = 0; i < items.size(); i++) {
                buffer = new StringBuilder();
                buffer.append("<item>");
                buffer.append("<id>" + items.get(i).getId() + "</id>");
                buffer.append("<name>" + items.get(i).getName()+ "</name>");
                buffer.append("<category>" + items.get(i).getCategory().name()+ "</category>");
                buffer.append("<amount>" + items.get(i).getAmount()+ "</amount>");
                buffer.append("<spent>" + items.get(i).getSpent()+ "</spent>");
                buffer.append("</item>");
                xmlDocument = String.format("%s%s", xmlDocument, buffer.toString());
            }
            
            return String.format("%s%s%s", "<items>", xmlDocument, "</items>");
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public static String convertItemToXML(Item item) {
        
        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("<item>");
            buffer.append("<id>" + item.getId() + "</id>");
            buffer.append("<name>" + item.getName()+ "</name>");
            buffer.append("<category>" + item.getCategory().name()+ "</category>");
            buffer.append("<amount>" + item.getAmount()+ "</amount>");
            buffer.append("<spent>" + item.getSpent()+ "</spent>");
            buffer.append("</item>");

            return String.format("%s%s%s", "<items>", buffer.toString(), "</items>");
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public static String convertIncomesToXML(List<Income> income) {
        
        try {
            
            String xmlDocument = "";
            StringBuilder buffer;
            for(int i = 0; i < income.size(); i++) {
                buffer = new StringBuilder();
                buffer.append("<income>");
                buffer.append("<id>" + income.get(i).getId() + "</id>");
                buffer.append("<name>" + income.get(i).getName()+ "</name>");
                buffer.append("<amount>" + income.get(i).getAmount()+ "</amount>");
                buffer.append("</income>");
                xmlDocument = String.format("%s%s", xmlDocument, buffer.toString());
            }
            
            return String.format("%s%s%s", "<incomes>", xmlDocument, "</incomes>");
        }
        catch(Exception ex) {
            throw ex;
        }
    }
    
    public static String convertIncomeToXML(Income income) {
        
        try {
            StringBuilder buffer = new StringBuilder();
            buffer.append("<income>");
            buffer.append("<id>" + income.getId() + "</id>");
            buffer.append("<name>" + income.getName()+ "</name>");
            buffer.append("<amount>" + income.getAmount()+ "</amount>");
            buffer.append("</income>");

            return String.format("%s%s%s", "<incomes>", buffer.toString(), "</incomes>");
        }
        catch(Exception ex) {
            throw ex;
        }
    }
}