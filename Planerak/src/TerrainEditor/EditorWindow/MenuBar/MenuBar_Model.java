/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.MenuBar;

import TerrainEditor.JavaFX.DialogNewTileset;
import static TerrainEditor.TileEditor.DEV_MODE;

/**
 *
 * @author Jordan
 */
public class MenuBar_Model
{
    private int nNewMapSize;
    private DialogNewTileset dialogNewTileset;
    private String userTilesetPathRelative;
    private String userTilesetPathFull;
    
    public MenuBar_Model()
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
    }
    public String getTilesetPathRelative()
    {
        return userTilesetPathRelative;
    }
    public String getTilesetPathFull()
    {
        return userTilesetPathFull;
    }
}
