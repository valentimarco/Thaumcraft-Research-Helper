package de.promolitor.tchelper.utils;

import de.promolitor.tchelper.TCHelperMain;
import de.promolitor.tchelper.types.Hexagon;
import lombok.NonNull;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import thaumcraft.api.aspects.Aspect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class Utils {

    public static ArrayList<Hexagon> getResearchAspectHexagons(@NonNull ItemStack research)
        throws NoSuchElementException, NullPointerException {
        var itemName = research.getItem().getUnlocalizedName();

        if (!itemName.equals("item.ItemResearchNotes")) {
            throw new NoSuchElementException("Item holded is not a Thaumcraft Research! It's a " + itemName);
        }


        var hexsTagList = research.getTagCompound().getTagList("hexgrid", 10);
        var researchAspects = new ArrayList<Hexagon>();
        for (int i = 0; i <= hexsTagList.tagCount(); i++) {
            var currentHex = hexsTagList.getCompoundTagAt(i);
            if (currentHex.hasKey("aspect")) {
                var aspect = Aspect.aspects.get(currentHex.getString("aspect"));
                researchAspects.add(new Hexagon(
                    currentHex.getByte("hexq"),
                    currentHex.getByte("hexr"),
                    aspect,
                    currentHex.getByte("type") == 1
                ));
            }
        }

        return researchAspects;
    }

    //Why was written?
    public static HashMap<String, Integer> getResearchAspects(@NonNull ItemStack research)
        throws NoSuchElementException, NullPointerException {

        var itemName = research.getItem().getUnlocalizedName();
        if (!itemName.equals("item.ItemResearchNotes")) {
            throw new NoSuchElementException("Item holded is not a Thaumcraft Research!");
        }

        NBTTagList aspectsTagList = research.getTagCompound().getTagList("aspects", 10);
        var givenAspects = new HashMap<String, Integer>();
        for (int i = 0; i < aspectsTagList.tagCount(); i++) {
            NBTTagCompound tag = aspectsTagList.getCompoundTagAt(i);
            givenAspects.put(tag.getString("key"), tag.getInteger("amount"));
        }

        return givenAspects;
    }
}
