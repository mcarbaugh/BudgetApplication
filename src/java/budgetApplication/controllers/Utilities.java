
package budgetApplication.controllers;

import budgetApplication.baseClasses.CategoryEnum;
import budgetApplication.baseClasses.MonthEnum;
import budgetApplication.dataContracts.Item;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Utilities {
    
    public static List<Item> selectGivingIems(List<Item> items) {
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
}