
package budgetApplication.controllers;

import budgetApplication.baseClasses.CategoryEnum;
import budgetApplication.dataContracts.Item;
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
}