/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author K
 */
public class PanelNorth
{

    public PanelNorth()
    {
        
    }
    public static JPanel createTopPanel(SwingActionListener actionListener)
    {
        JPanel topPanel = new JPanel(new GridLayout(1, 0, 0, 10));

        JMenuBar menuBar = new JMenuBar();

        JMenuItem newMap;
        JMenuItem saveMap;
        JMenuItem loadMap;
        JMenuItem finalizeMap;
        JMenuItem exitEditor;
         
        JMenu menu = new JMenu("File");
        JMenu submenu;

        newMap = new JMenuItem("New Map");
        saveMap = new JMenuItem("Save Map");
        loadMap = new JMenuItem("Load Map");
        finalizeMap = new JMenuItem("Finalize Map");
        exitEditor = new JMenuItem("Exit");
        submenu = new JMenu("Recent Maps");

        newMap.setActionCommand("New_Map");
        newMap.addActionListener(actionListener);
        saveMap.setActionCommand("Save_Map");
        saveMap.addActionListener(actionListener);
        loadMap.setActionCommand("Load_Map");
        loadMap.addActionListener(actionListener);
        finalizeMap.setActionCommand("Finalize_Map");
        finalizeMap.addActionListener(actionListener);
        exitEditor.setActionCommand("Exit");
        exitEditor.addActionListener(actionListener);
        menu.add(submenu);
        menu.add(newMap);
        menu.add(saveMap);
        menu.add(loadMap);
        menu.add(finalizeMap);
        menu.addSeparator();
        menu.add(exitEditor);
        menuBar.add(menu);
        
        JMenu tilesetMenu = new JMenu("Tilesets");
        JMenuItem newTileset = new JMenuItem("New Tileset");
        JMenuItem viewTileset = new JMenuItem("View Tilesets");
        
        
        newTileset.setActionCommand("New_Tileset");
        newTileset.addActionListener(actionListener);
        
        viewTileset.setActionCommand("View_Tileset");
        viewTileset.addActionListener(actionListener);
        
        tilesetMenu.add(newTileset);
        tilesetMenu.add(viewTileset);
        menuBar.add(tilesetMenu);



        //JButton button = new JButton();

        topPanel.add(menuBar);
        //topPanel.add(button);
        return topPanel;
    }

}
