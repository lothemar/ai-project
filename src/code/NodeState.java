package code;
public class NodeState{
    int money;
    String action2;
    int prosperity;
    int buildTime;
    int requestTime;
    String requestType;
    int initFood;
    int initMaterials;
    int initEnergy;
    int unitPriceFood;
    int unitPriceMaterials;
    int unitPriceEnergy;
    int amountFood;
    int delayFood;
    int amountMaterials;
    int delayMaterials;
    int amountEnergy;
    int delayEnergy;
    int priceBuild1Int;
    int foodUseBuild1;
    int materialsUseBuild1;
    int energyUseBuild1;
    int prosperityBuild1;
    int priceBuild2Int;
    int foodUseBuild2;
    int materialsUseBuild2;
    int energyUseBuild2;
    int prosperityBuild2;
    public boolean canBuild(int i){
        switch(i){
            case 1:
                int totalPrice1 = priceBuild1Int + foodUseBuild1 * unitPriceFood + materialsUseBuild1 * unitPriceMaterials + energyUseBuild1 * unitPriceEnergy;
                return initFood > foodUseBuild1 && initMaterials > materialsUseBuild1 && initEnergy > energyUseBuild1 && money > totalPrice1;
            case 2:
                int totalPrice2 = priceBuild2Int + foodUseBuild2 * unitPriceFood + materialsUseBuild2 * unitPriceMaterials + energyUseBuild2 * unitPriceEnergy;
                return initFood > foodUseBuild2 && initMaterials > materialsUseBuild2 && initEnergy > energyUseBuild2 && money > totalPrice2;
            default:
                return false;
        }
    }
    public NodeState(String initialState){
        String[] splitS = initialState.split(";");
        money = 100000;
        requestTime = 0;
        prosperity = Integer.parseInt(splitS[0]);
        String[] initialResources  = splitS[1].split(",");
        initFood = Integer.parseInt(initialResources[0]);
        initMaterials = Integer.parseInt(initialResources[1]);
        initEnergy = Integer.parseInt(initialResources[2]);
        String[] unitPrice = splitS[2].split(",");
        unitPriceFood = Integer.parseInt(unitPrice[0]);
        unitPriceMaterials = Integer.parseInt(unitPrice[1]);
        unitPriceEnergy = Integer.parseInt(unitPrice[2]);
        String[] amountFoodRequestDelay = splitS[3].split(",");
        amountFood = Integer.parseInt(amountFoodRequestDelay[0]);
        delayFood = Integer.parseInt(amountFoodRequestDelay[1]);
        String[] amountMaterialsRequestDelay = splitS[4].split(",");
        amountMaterials = Integer.parseInt(amountMaterialsRequestDelay[0]);
        delayMaterials = Integer.parseInt(amountMaterialsRequestDelay[1]);
        String[] amountEnergyRequestDelay = splitS[5].split(",");
        amountEnergy = Integer.parseInt(amountEnergyRequestDelay[0]);
        delayEnergy = Integer.parseInt(amountEnergyRequestDelay[1]);
        String[] priceBuild1 = splitS[6].split(",");
        priceBuild1Int = Integer.parseInt(priceBuild1[0]);
        foodUseBuild1 = Integer.parseInt(priceBuild1[1]);
        materialsUseBuild1 = Integer.parseInt(priceBuild1[2]);
        energyUseBuild1 = Integer.parseInt(priceBuild1[3]);
        prosperityBuild1 = Integer.parseInt(priceBuild1[4]);
        String[] priceBuild2 = splitS[7].split(",");
        priceBuild2Int = Integer.parseInt(priceBuild2[0]);
        foodUseBuild2 = Integer.parseInt(priceBuild2[1]);
        materialsUseBuild2 = Integer.parseInt(priceBuild2[2]);
        energyUseBuild2 = Integer.parseInt(priceBuild2[3]);
        prosperityBuild2 = Integer.parseInt(priceBuild2[4]);
    }
    public String toString(){
        if (this == null )return null;
        return String.format("%d %d %d", initFood, initMaterials, initEnergy);
    }
    public int h1Value(){
        return 100 - prosperity;
    }
    public int h2Value() {
        return money;
    }
    public void setFood(int food){
        int newFood = food + initFood;
        if (newFood >= 50){
            initFood = 50;
        }
        else{
            initFood = newFood;
        }
    }
    public void setMaterials(int materials){
        int newMaterials = materials + initMaterials;
        if (newMaterials >= 50){
            initMaterials = 50;
        }
        else{
            initMaterials = newMaterials;
        }
    }
    public void setEnergy(int energy){
        int newEnergy = energy + initEnergy;
        if (newEnergy >= 50){
            initEnergy = 50;
        }
        else{
            initEnergy = newEnergy;
        }
    }
    public NodeState(NodeState state){
        this.money = state.money;
        this.requestType = state.requestType;
        this.buildTime = state.buildTime;
        this.requestTime = state.requestTime;
        this.prosperity = state.prosperity;
        this.initFood = state.initFood;
        this.initMaterials = state.initMaterials;
        this.initEnergy = state.initEnergy;
        this.unitPriceFood = state.unitPriceFood;
        this.unitPriceMaterials = state.unitPriceMaterials;
        this.unitPriceEnergy = state.unitPriceEnergy;
        this.amountFood = state.amountFood;
        this.delayFood = state.delayFood;
        this.amountMaterials = state.amountMaterials;
        this.delayMaterials = state.delayMaterials;
        this.amountEnergy = state.amountEnergy;
        this.delayEnergy = state.delayEnergy;
        this.priceBuild1Int = state.priceBuild1Int;
        this.foodUseBuild1 = state.foodUseBuild1;
        this.materialsUseBuild1 = state.materialsUseBuild1;
        this.energyUseBuild1 = state.energyUseBuild1;
        this.prosperityBuild1 = state.prosperityBuild1;
        this.priceBuild2Int = state.priceBuild2Int;
        this.foodUseBuild2 = state.foodUseBuild2;
        this.materialsUseBuild2 = state.materialsUseBuild2;
        this.energyUseBuild2 = state.energyUseBuild2;
        this.prosperityBuild2 = state.prosperityBuild2;
    }
    public int getCost(NodeState otherNode, int cost){
        if(otherNode == null){
            return 0;
        }
        return cost + (this.money - otherNode.money);
    }
    public boolean decr(){

        this.initFood -=1;
        this.initEnergy -=1;
        this.initMaterials -=1;
        this.money -= (1 * this.unitPriceFood) + (1 * this.unitPriceEnergy) + 1 * (this.unitPriceMaterials);
        if(this.initFood <= 0 || this.initEnergy <= 0 || this.initMaterials <= 0 || this.money <= 0){
            return false;
        }
        return true;
    }
    public String getHash(String prevAc, String method){
        switch(method){
            case "ID":
                return String.format("%d%d%d%ds", initFood, initEnergy, initMaterials, prosperity);
            default:
                return prevAc + String.format("%d%d%d%s", initFood, initEnergy, initMaterials, action2);
        }
    }
    public void incr(){
        if(requestTime != 0) {
            requestTime -= 1;

            if (requestTime == 0){
                switch(requestType){
                    case "food":
                        setFood(amountFood);
                        break;
                    case "energy":
                        setEnergy(amountEnergy);
                        break;
                    case "materials":
                        setMaterials(amountMaterials);
                        break;
                }
            }
        }
    }
    public NodeState newNode(String action){
        NodeState newNodeState = new NodeState(this);
        newNodeState.action2 = action;

        if(newNodeState.initFood <= 0 || newNodeState.initEnergy <= 0 || newNodeState.initMaterials <= 0 || newNodeState.money <= 0){
            return null;
        }
        int cost;
        newNodeState.incr();
        switch(action){
            case "requestfood":
                if(!newNodeState.decr())  return null;
                if(newNodeState.requestTime == 0) {
                    newNodeState.requestTime = newNodeState.delayFood;
                    newNodeState.requestType = "food";
                }
                else{
                    return null;
                }
                break;
            case "requestenergy":
                if(!newNodeState.decr())  return null;
                if(newNodeState.requestTime == 0) {
                    newNodeState.requestTime = newNodeState.delayEnergy ;
                    newNodeState.requestType = "energy";
                }
                else{
                    return null;
                }
                break;
            case "requestmaterials":
                if(!newNodeState.decr())  return null;
                if(newNodeState.requestTime == 0) {
                    newNodeState.requestTime = newNodeState.delayMaterials ;
                    newNodeState.requestType = "materials";
                }
                else{
                    return null;
                }
                break;
            case "build1":
                if (newNodeState.canBuild(1)) {
                    newNodeState.initFood -= newNodeState.foodUseBuild1;
                    newNodeState.initMaterials -= newNodeState.materialsUseBuild1;
                    newNodeState.initEnergy -= newNodeState.energyUseBuild1;
                    int costResource1 =( newNodeState.foodUseBuild1 * newNodeState.unitPriceFood) + (newNodeState.materialsUseBuild1 * newNodeState.unitPriceMaterials) + (newNodeState.energyUseBuild1 * newNodeState.unitPriceEnergy);
                    newNodeState.prosperity += newNodeState.prosperityBuild1;

                    newNodeState.money -= newNodeState.priceBuild1Int + costResource1;
                }
                else{
                    return null;
                }

                break;
            case "build2":
                if (newNodeState.canBuild(2)){

//                    System.out.println("building2");
                    newNodeState.initFood -= newNodeState.foodUseBuild2;
                    newNodeState.initMaterials -= newNodeState.materialsUseBuild2;
                    newNodeState.initEnergy -= newNodeState.energyUseBuild2;
                    newNodeState.prosperity += newNodeState.prosperityBuild2;
                    int costResource2 = (newNodeState.foodUseBuild2 * newNodeState.unitPriceFood) + (newNodeState.materialsUseBuild2 * newNodeState.unitPriceMaterials) + (newNodeState.energyUseBuild2 * newNodeState.unitPriceEnergy);
                    newNodeState.money -= newNodeState.priceBuild2Int + costResource2;
                }
                else{
                    return null;
                }
                break;
            case "wait":
                if(!newNodeState.decr())  return null;
                break;
        }

        return newNodeState;
    }
}