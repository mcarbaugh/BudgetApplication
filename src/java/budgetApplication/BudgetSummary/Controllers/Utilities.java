
package budgetApplication.BudgetSummary.Controllers;

import budgetApplication.dataContracts.Item;

public class Utilities {
    
    
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
}
