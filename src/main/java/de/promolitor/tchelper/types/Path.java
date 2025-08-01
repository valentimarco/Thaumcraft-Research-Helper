package de.promolitor.tchelper.types;

import de.promolitor.tchelper.TCHelperMain;
import de.promolitor.tchelper.resolver.AspectCalculation;
import lombok.AllArgsConstructor;
import lombok.ToString;
import thaumcraft.api.aspects.Aspect;

import java.util.ArrayList;
import java.util.stream.Collectors;

@AllArgsConstructor
public class Path {
    public Aspect currentAspect;
    public ArrayList<Aspect> pathSoFar;

    public int getCost() {

        return pathSoFar.stream()
            //.peek((as) -> TCHelperMain.LOG.info(as.getTag() + "," + AspectCalculation.cost.get(as)) )
            .mapToInt(AspectCalculation.cost::get)
            .sum() + AspectCalculation.cost.get(currentAspect);
    }

    @Override
    public String toString() {
        return "Path{" +
            "currentAspect=" + currentAspect.getTag() +
            ", pathSoFar=" + pathSoFar.stream().map(Aspect::getTag).collect(Collectors.toList()) +
            '}';
    }
}
