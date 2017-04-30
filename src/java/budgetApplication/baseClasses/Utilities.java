
package budgetApplication.baseClasses;

import budgetApplication.dataContracts.Budget;
import budgetApplication.dataContracts.Item;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Utilities {
    
    public static void order(List<Budget> budgets) {
        
        Collections.sort(budgets, (Object b1, Object b2) -> {
            int x1 = ((Budget) b1).getYear();
            int x2 = ((Budget) b2).getYear();
            int sComp = x2 - x1;
            
            if (sComp != 0) {
                return sComp;
            } else {
                MonthEnum m1 = ((Budget) b1).getMonth();
                MonthEnum m2 = ((Budget) b2).getMonth();
                return m2.compareTo(m1);
            }
        });
    }
     
    public static List<Item> selectGivingItems(List<Item> items) {
            return items.stream()
                .filter(x -> x.getCategory() == CategoryEnum.GIVING)
                .collect(Collectors.toList());
        }
    
    public static List<Item> selectFoodItems(List<Item> items) {
        return items.stream()
                .filter(x -> x.getCategory() == CategoryEnum.FOOD)
                .collect(Collectors.toList());
    }
    
    public static List<Item> selectHousingItems(List<Item> items) {
        return items.stream()
                .filter(x -> x.getCategory() == CategoryEnum.HOUSING)
                .collect(Collectors.toList());
    }
    
    public static List<Item> selectInsuranceItems(List<Item> items) {
        return items.stream()
                .filter(x -> x.getCategory() == CategoryEnum.INSURANCE_TAX)
                .collect(Collectors.toList());
    }
    
    public static List<Item> selectLifestyleItems(List<Item> items) {
        return items.stream()
                .filter(x -> x.getCategory() == CategoryEnum.LIFESTYLE)
                .collect(Collectors.toList());
    }
    
    public static List<Item> selectTransportationItems(List<Item> items) {
        return items.stream()
                .filter(x -> x.getCategory() == CategoryEnum.TRANSPORTATION)
                .collect(Collectors.toList());
    }
    
    public static int getCurrentYear() {
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();
        return currentYear;
    }
    
    public static MonthEnum getCurrentMonth() {
        MonthEnum currentMonth;
        LocalDateTime now = LocalDateTime.now();
        
        switch(now.getMonthValue() ) {
                            case 1:
                                currentMonth = MonthEnum.JANUARY;
                                break;
                            case 2:
                                currentMonth = MonthEnum.FEBRUARY;
                                break;
                            case 3:
                                currentMonth = MonthEnum.MARCH;
                                break;
                            case 4:
                                currentMonth = MonthEnum.APRIL;
                                break;
                            case 5:
                                currentMonth = MonthEnum.MAY;
                                break;
                            case 6:
                                currentMonth = MonthEnum.JUNE;
                                break;
                            case 7:
                                currentMonth = MonthEnum.JULY;
                                break;
                            case 8:
                                currentMonth = MonthEnum.AUGUST;
                                break;
                            case 9:
                                currentMonth = MonthEnum.SEPTEMBER;
                                break;
                            case 10:
                                currentMonth = MonthEnum.OCTOBER;
                                break;
                            case 11:
                                currentMonth = MonthEnum.NOVEMBER;
                                break;
                            case 12:
                                currentMonth = MonthEnum.DECEMBER;
                                break;
                            default:
                                currentMonth = MonthEnum.NONE;
                                break;
                        }
        
        return currentMonth;
    }
    
    public static boolean isInteger(String value) {
        int size = value.length();
        
        for(int i = 0; i < size; i++) {
            if(!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        
        return size > 0;
    }
    
    public static boolean isDouble(String value) {
        
        value = value.indexOf(".") < 0 ? value : value.replaceAll("0*$", "").replaceAll("\\.$", "");
        
        int size = value.length();
        boolean hasDecimal = false;
        
        char character;
        for(int i = 0; i < size; i++) {
            character = value.charAt(i);
            
            if(character == '.') {
                if(hasDecimal || size == 1) {
                    return false;
                }
                else {
                    hasDecimal = true;
                }
            }
            else if(!Character.isDigit(character)) {
                return false;
            } 
        }
        
        return size > 0;
    }
    
    private static Set<String> dates = new HashSet<String>();
    static {
        for (int year = 1900; year < 2050; year++) {
            for (int month = 1; month <= 12; month++) {
                Calendar monthStart = new GregorianCalendar(year, month - 1, 1);
                int days = monthStart.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int day = 1; day <= days; day++) {
                    StringBuilder date = new StringBuilder();
                    date.append(String.format("%04d", year));
                    date.append(String.format("%02d", month));
                    date.append(String.format("%02d", day));
                    dates.add(date.toString());
                }
            }
        }
    }
    
    public static boolean isDate(String value) {
        return dates.contains(value);
    }
    
    public static MonthEnum getMonthAsEnum(String monthInput) {
        
        MonthEnum month = MonthEnum.NONE;
        for(MonthEnum monthEnum : MonthEnum.values()) {
            if(monthInput.equals(monthEnum.name())) {
                month = monthEnum;
                break;
            }
        }
        
        return month;
    }
    
    public static CategoryEnum getCategoryAsEnum(String categoryInput) {
        
        CategoryEnum category = CategoryEnum.NONE;
        for(CategoryEnum categoryEnum : CategoryEnum.values()) {
            if(categoryInput.equals(categoryEnum.name())) {
                category = categoryEnum;
                break;
            }
        }
        
        return category;
    }
}
