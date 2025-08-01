package de.promolitor.tchelper.resolver;

import java.util.*;


import de.promolitor.tchelper.TCHelperMain;
import de.promolitor.tchelper.types.Combination;
import de.promolitor.tchelper.types.Hexagon;
import de.promolitor.tchelper.types.Path;
import thaumcraft.api.aspects.Aspect;

public class AspectCalculation {

    public final static HashMap<Aspect, Integer> cost = new HashMap<>();
    // public static HashMap<String, Aspect> map = new HashMap<String,
    // Aspect>();

    public final static ArrayList<Aspect> solvedIssues = new ArrayList<>();

    private final ArrayList<Combination> combinations = new ArrayList<>();


    public AspectCalculation(ArrayList<Hexagon> researchAspects){
        int dist;
        Hexagon h1 = null,h2 = null, t1, t2;
        //Take only the min arc from the all possible Aspects.
        for (int i = 0; i < researchAspects.size(); i++) {
            t1 = researchAspects.get(i);
            int minDist = Integer.MAX_VALUE;
            for(int j = i + 1; j < researchAspects.size(); j++){
                t2 = researchAspects.get(j);
                dist = t1.compareDistance(t2);
                if(dist < minDist) {
                    minDist = dist;
                    h1 = t1;
                    h2 = t2;
                }
            }
            combinations.add(new Combination(h1,h2));
        }
        //The last couple is inserted 2 times, remove it
        combinations.remove(combinations.size()-1);
    }


    public void completePaths(){
        for( var comb : combinations) {
            completePath(comb);
        }
    }

    private void completePath(Combination comb){
        var start = comb.start.aspect;
        var end = comb.end.aspect;
        int distance = comb.start.compareDistance(comb.end);
        var paths = new ArrayList<>();

        if (!start.isPrimal()) {
            for (Aspect component : start.getComponents()) {
                ArrayList<Aspect> pathSoFar = new ArrayList<>();
                pathSoFar.add(start);
                paths.add(new Path(component, pathSoFar));
            }
        }
        //Add aspect that contains the start aspect
        for (var entry : Aspect.aspects.entrySet()) {
            Aspect as = entry.getValue();
            if (!as.isPrimal()) {
                for (Aspect component : as.getComponents()) {
                    if (component.getTag().equals(start.getTag())) {
                        ArrayList<Aspect> pathSoFar = new ArrayList<Aspect>();
                        pathSoFar.add(start);
                        paths.add(new Path(as, pathSoFar));
                    }
                }
            }
        }
        TCHelperMain.LOG.info(paths);



    }





    public static void createCosts() {
        for (Map.Entry<String, Aspect> entry : Aspect.aspects.entrySet()) {
            if (!entry.getValue().isPrimal()) {
                var costAspect = checkCost(entry.getValue());
                cost.put(entry.getValue(), costAspect);
            }
            else {
                cost.put(entry.getValue(), 1);
            }

        }

    }


    private static int checkCost(Aspect aspect) {
        int totalCost = 0;
        for (Aspect as : aspect.getComponents()) {
            if (as.isPrimal()) {
                totalCost++;
            } else {
                totalCost += checkCost(as);
            }
        }
        return totalCost;
    }


}
