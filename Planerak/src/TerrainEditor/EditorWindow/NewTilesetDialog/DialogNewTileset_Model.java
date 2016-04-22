/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewTilesetDialog;

import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
import java.io.File;

/**
 *
 * @author Jordan
 */
public class DialogNewTileset_Model
{
    String tilesetPathFull;
    File imageFile;
    private AssetManager assetManager;
    String userTilesetPathRelative;
    String userTilesetPathFull;
    Texture tilesetTexture;
    int nPixels;
    int nFrames;
    int nRows;
    int nDefaultRow = 0;
    int nDefaultFrame = 0;
    
    public int getDefaultRow()
    {
        return nDefaultRow;
    }

    public void setDefaultRow(int nDefaultRow)
    {
        this.nDefaultRow = nDefaultRow;
    }

    public int getDefaultFrame()
    {
        return nDefaultFrame;
    }

    public void setDefaultFrame(int nDefaultFrame)
    {
        this.nDefaultFrame = nDefaultFrame;
    }
    
    public int getFrames()
    {
        return nFrames;
    }

    public void setFrames(int nFrames)
    {
        this.nFrames = nFrames;
    }
    


    public int getRows()
    {
        return nRows;
    }

    public void setRows(int nRows)
    {
        this.nRows = nRows;
    }
    
    
    
    public DialogNewTileset_Model(AssetManager assetManager)
    {
        if (DEV_MODE)
            {
                userTilesetPathRelative = "\\Tilesets\\";
                userTilesetPathFull = System.getProperty("user.dir") + "\\assets\\Tilesets";
            }
            else
            {
                userTilesetPathRelative = "\\Tilesets\\";
                userTilesetPathFull = System.getProperty("user.home") + "\\Planerrak\\Tilesets";
            }
        this.assetManager = assetManager;
    }
    public Texture loadTilesetTexture(String imageLocation)
    {
        Texture texture = null;
        try
        {
            texture = assetManager.loadTexture(imageLocation);
        }
        catch (Exception e)
        {
            System.err.println("Texture could not be loaded from: " + imageLocation);
        }
        
        
        this.tilesetTexture = texture;
        return texture;
    }
    public Texture getTilesetTexture()
    {
        return tilesetTexture;
    }
    public String getTilesetPathRelative()
    {
        return userTilesetPathRelative;
    }
    public String getTilesetPathFull()
    {
        return userTilesetPathFull;
    }
    public void setImageFile(File imageFile)
    {
        this.imageFile = imageFile;
    }
    public File getImageFile()
    {
        return imageFile;
    }
    public void setPixels(int nPixels)
    {
        this.nPixels = nPixels;
    }
    public int getPixels()
    {
        return nPixels;
        
    }
}
