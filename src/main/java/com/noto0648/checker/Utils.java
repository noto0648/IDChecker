package com.noto0648.checker;

import com.sun.xml.internal.stream.buffer.stax.StreamWriterBufferCreator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistryNamespaced;

import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Noto on 14/03/28.
 */
public class Utils
{
    public static File getGameDir()
    {
        return Loader.instance().getConfigDir().getParentFile();
    }

    public static <T, E> T getPrivateValue(Class<? super E> classToAccess, E instance, int fieldIndex)
    {
        return ObfuscationReflectionHelper.getPrivateValue(classToAccess, instance, fieldIndex);
    }

    public static String getListText(RegistryNamespaced registry)
    {
        StringBuilder sb = new StringBuilder("ItemID,RegisterName,SystemName\r\n");
        Iterator iterator = registry.iterator();
        String[] nameArray = (String[])registry.getKeys().toArray(new String[0]);

        for (Iterator i = iterator; i.hasNext();)
        {
            Object obj = i.next();
            if(obj != null)
            {
                ItemStack is = null;
                String originalName = null;

                if(obj instanceof Item)
                {
                    is = new ItemStack((Item)obj);
                    originalName = registry.getNameForObject(is.getItem());
                }
                else if(obj instanceof Block)
                {
                    is = new ItemStack((Block)obj);
                }

                if(is != null && originalName != null)
                {
                    sb.append(Item.getIdFromItem(is.getItem()));
                    sb.append(",");
                    sb.append(originalName);
                    sb.append(",");
                    sb.append(is.getUnlocalizedName());
                    sb.append("\r\n");
                }
            }
        }

        return sb.toString();
    }

    public static void export(File f, String contents)
    {
        try
        {
            FileWriter fw = new FileWriter(f);
            fw.write(contents);
            fw.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
