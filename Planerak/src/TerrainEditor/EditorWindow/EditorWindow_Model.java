/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.TerrainPanel;
import com.jme3.app.SimpleApplication;
import java.awt.image.BufferedImage;

/**
 *
 * @author Jordan
 */
public class EditorWindow_Model
{
    private TerrainPanel terrainPanel;
    private TilesetData tilesetData;
    private BufferedImage tilesetImage;
    private int selected_Tile_X;
    private int selected_Tile_Y;
    
    public EditorWindow_Model()
    {
        
    }
    public void setSelectedTiles(int selected_Tile_X, int selected_Tile_Y)
    {
        this.selected_Tile_X = selected_Tile_X;
        this.selected_Tile_Y = selected_Tile_Y;
    }
    public int getselectedTileX()
    {
        return selected_Tile_X;
    }
    public int getselectedTileY()
    {
        return selected_Tile_Y;
    }
    public void setTilesetImage(BufferedImage tilesetImage)
    {
        this.tilesetImage = tilesetImage;
    }
    public BufferedImage getTilesetImage()
    {
        return tilesetImage;
    }
    public void setTerrainPanel(TerrainPanel terrainPanel)
    {
        this.terrainPanel = terrainPanel;
    }
    
    public TerrainPanel getTerrainPanel()
    {
        return terrainPanel;
    }
    public void setTilesetData(TilesetData tilesetData)
    {
         this.tilesetData = tilesetData;
    }
    public TilesetData getTilesetData()
    {
        return tilesetData;
    }
}
