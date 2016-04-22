/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.MenuBar;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class MenuBar_View
{

    public MenuBar_View()
    {
        
        ToolbarHeader = new JPanel(new GridLayout(1, 0, 0, 10));

        Toolbar = new JMenuBar();

        fileNewMap = new JMenuItem("New Map");
        fileSaveMap = new JMenuItem("Save Map");
        fileLoadMap = new JMenuItem("Load Map");
        fileFinalizeMap = new JMenuItem("Finalize Map");
        fileExitEditor = new JMenuItem("Exit");

        fileMenu = new JMenu("File");
        fileSubMenu = new JMenu("Recent Maps");


        fileMenu.add(fileSubMenu);
        fileMenu.add(fileNewMap);
        fileMenu.add(fileSaveMap);
        fileMenu.add(fileLoadMap);
        fileMenu.add(fileFinalizeMap);
        fileMenu.addSeparator();
        fileMenu.add(fileExitEditor);
        Toolbar.add(fileMenu);


        tilesetMenu = new JMenu("Tilesets");
        tilesetNewTileset = new JMenuItem("New Tileset");
        tilesetViewTileset = new JMenuItem("View Tilesets");

        tilesetMenu.add(tilesetNewTileset);
        tilesetMenu.add(tilesetViewTileset);
        Toolbar.add(tilesetMenu);

        ToolbarHeader.add(Toolbar);






    }

    public JPanel getMenuJPanel()
    {
        return ToolbarHeader;
    }
    public JMenuItem getFileNewMap()
    {
        return fileNewMap;
    }

    public JMenuItem getFileSaveMap()
    {
        return fileSaveMap;
    }

    public JMenuItem getFileLoadMap()
    {
        return fileLoadMap;
    }

    public JMenuItem getFileFinalizeMap()
    {
        return fileFinalizeMap;
    }

    public JMenuItem getExitEditor()
    {
        return fileExitEditor;
    }

    public JMenuItem getNewTileset()
    {
        return tilesetNewTileset;
    }

    public JMenuItem getViewTileset()
    {
        return tilesetViewTileset;
    }

    JMenuBar Toolbar;
    JMenuItem fileNewMap;
    JMenuItem fileSaveMap;
    JMenuItem fileLoadMap;
    JMenuItem fileFinalizeMap;
    JMenuItem fileExitEditor;
    JMenu fileMenu;
    JMenu fileSubMenu;
    JPanel ToolbarHeader;
    JMenu tilesetMenu;
    JMenuItem tilesetNewTileset;
    JMenuItem tilesetViewTileset;
}
