/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.CustomFiles;

import com.jme3.asset.AssetInfo;
import com.jme3.asset.AssetLoader;
import com.jme3.export.binary.BinaryImporter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author K
 */
public class TilesetLoader implements AssetLoader
{

    public Object load(AssetInfo assetInfo) throws IOException
    {
        BinaryImporter binaryImporter = BinaryImporter.getInstance();
        Object obj = binaryImporter.load(assetInfo);
        
        return obj;
        
    }
    
}
