/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewMapDialog;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.TerrainPanel;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.app.SimpleApplication;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class DialogNewMap_Model
{

    private int nTilesetSizeX = 8;
    private int nTilesetSizeY = 8;
    
    public DialogNewMap_Model()
    {
        

    }
    
    public void setTilesetSize(int nTilesetSizeX, int nTilesetSizeY)
    {
        this.nTilesetSizeX = nTilesetSizeX;
        this.nTilesetSizeY  = nTilesetSizeY;
    }
    public int getTilesetX()
    {
        return nTilesetSizeX;
    }
    public int getTilesetY()
    {
        return nTilesetSizeY;
    }

    
    
}
