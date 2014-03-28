package com.noto0648.checker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.util.RegistryNamespaced;
import net.minecraft.util.RegistrySimple;

import java.io.File;
import java.util.Map;

/**
 * Created by Noto on 14/03/28.
 */
@Mod(modid = "idchecker", name = "ID Checker", version = "1.7.2")
public class IDChecker
{
    @Mod.EventHandler
    public void postLoad(FMLPostInitializationEvent event)
    {
        try
        {
            String sb = Utils.getListText(GameData.itemRegistry);
            File f = new File(Utils.getGameDir(), "IDChecker");
            if(!f.exists())
            {
                f.mkdirs();
            }
            Utils.export(new File(f, "List.txt"), sb.replaceAll(",", " "));
            Utils.export(new File(f, "List.csv"), sb);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
