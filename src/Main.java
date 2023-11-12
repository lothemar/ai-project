// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    int money = 100000;

    public static void main(String[] args) {
        String s = "";
        String[] splitS = s.split(";");
        int initialProperty = Integer.parseInt(splitS[0]);
        String[] initialResources  = splitS[1].split(",");
        int initFood = Integer.parseInt(initialResources[0]);
        int initMaterials = Integer.parseInt(initialResources[1]);
        int initEnergy = Integer.parseInt(initialResources[2]);
        String[] unitPrice = splitS[2].split(",");
        int unitPriceFood = Integer.parseInt(unitPrice[0]);
        int unitPriceMaterials = Integer.parseInt(unitPrice[1]);
        int unitPriceEnergy = Integer.parseInt(unitPrice[2]);
        String[] amountFoodRequestDelay = splitS[3].split(",");
        int amountFood = Integer.parseInt(amountFoodRequestDelay[0]);
        int delayFood = Integer.parseInt(amountFoodRequestDelay[1]);
        String[] amountMaterialsRequestDelay = splitS[4].split(",");
        int amountMaterials = Integer.parseInt(amountMaterialsRequestDelay[0]);
        int delayMaterials = Integer.parseInt(amountMaterialsRequestDelay[1]);
        String[] amountEnergyRequestDelay = splitS[5].split(",");
        int amountEnergy = Integer.parseInt(amountEnergyRequestDelay[0]);
        int delayEnergy = Integer.parseInt(amountEnergyRequestDelay[1]);
        String[] priceBuild1 = splitS[6].split(",");
        int priceBuild1Int = Integer.parseInt(priceBuild1[0]);
        int foodUseBuild1 = Integer.parseInt(priceBuild1[1]);
        int materialsUseBuild1 = Integer.parseInt(priceBuild1[2]);
        int energyUseBuild1 = Integer.parseInt(priceBuild1[3]);
        int prosperityBuild1 = Integer.parseInt(priceBuild1[4]);
        String[] priceBuild2 = splitS[7].split(",");
        int priceBuild2Int = Integer.parseInt(priceBuild2[0]);
        int foodUseBuild2 = Integer.parseInt(priceBuild2[1]);
        int materialsUseBuild2 = Integer.parseInt(priceBuild2[2]);
        int energyUseBuild2 = Integer.parseInt(priceBuild2[3]);
        int prosperityBuild2 = Integer.parseInt(priceBuild2[4]);







    }
}