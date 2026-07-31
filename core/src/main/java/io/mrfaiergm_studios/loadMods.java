package io.mrfaiergm_studios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class loadMods {

    public void loadMods(){
        FileHandle modFile = Gdx.files.local("mods/mod.txt");

        if(!modFile.exists()){
            modFile.parent().mkdirs();
            configMods.modIsAcrived = false;
        }

        if (modFile.exists()){
            String text = modFile.readString();

            configMods.modIsAcrived = true;

            if(text.contains("applesInStart=true")){
                configMods.modLoaderApplesinStart = 100;
            }
            if(text.equalsIgnoreCase("godMod=true")){
                configMods.modLoaderisGodMod = true;
            }
            if(text.contains("basketGigant=true")){
                configMods.modLoaderisGodMod = true;
            }

        }

    }
}
