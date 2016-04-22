/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_View;
import TerrainEditor.EditorWindow.EditorWindowView;
import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.TerrainPanel;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author K
 */
public class SwingActionListener implements ActionListener,com.jme3.input.controls.ActionListener,AnalogListener
{

    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onAction(String name, boolean isPressed, float tpf)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onAnalog(String name, float value, float tpf)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /*
    JFrame jFrame;
    EditorWindowView frameEditor;
    DialogNewMap_View dialogNewMap;
    SimpleApplication simpleApplication;
    private TerrainPanel terrainPanel;
    private PanelWest panelWest;
    private int selectedRow;
    private int selectedFrame;
    
    public SwingActionListener()
    {
        
        
 
    }
    public void setApplication(SimpleApplication simpleApplication)
    {
        this.simpleApplication = simpleApplication;
    }

    public void initialize(PanelWest panelWest,EditorWindowView frameEditor)
    {
        this.panelWest = panelWest;
        this.frameEditor = frameEditor;
        this.jFrame = frameEditor.getEditorWindow();
        
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if ("Exit".equals(e.getActionCommand()))
        {
            frameEditor.closeApplication();
        }
        if ("New_Map".equals(e.getActionCommand()))
        {
            if(dialogNewMap == null)
            {
                dialogNewMap = new DialogNewMap_View(frameEditor.getEditorWindow(),this);
            }
            dialogNewMap.setVisible(true);

        }
        if ("New_Tileset".equals(e.getActionCommand()))
        {
            
            DialogNewTileset dialogNewTileset = new DialogNewTileset(frameEditor.getEditorWindow());
            dialogNewTileset.initialize(true,simpleApplication.getAssetManager(),"\\Tilesets\\",System.getProperty("user.home") + "\\Planerrak\\Tilesets");
            
        }
        if("View_Tileset".equals(e.getActionCommand()))
        {
            DialogViewTileset test = new DialogViewTileset(frameEditor.getEditorWindow());
            

            
        }
        if ("CreateMap".equals(e.getActionCommand()))
        {
            
            dialogNewMap.setVisible(false);
            int x_Tiles = 0;
            int y_Tiles = 0;
            if (terrainPanel != null)
            {
                
                simpleApplication.getGuiNode().detachChild(terrainPanel.getNode());
            }
            switch (dialogNewMap.getSizeIndex())
            {
                case 0:
                    x_Tiles = 8;
                    y_Tiles = 8;
                    break;
                case 1:
                    x_Tiles = 16;
                    y_Tiles = 16;
                    break;
                case 2:
                    x_Tiles = 32;
                    y_Tiles = 32;
                    break;
                case 3:
                    x_Tiles = 64;
                    y_Tiles = 64;
                    break;
                case 4:
                    x_Tiles = 128;
                    y_Tiles = 128;
                    break;
            }
           
            TilesetData data = (TilesetData) simpleApplication.getAssetManager().loadAsset("Tilesets\\Data\\"+ dialogNewMap.getTilesetName());
            
            //panel = new TerrainPanel(x_Tiles, y_Tiles, 32, 32, "Panel", application.getAssetManager(), "Textures/LPC Terrain/terrain_atlas.png");
            if(terrainPanel != null)
            {
                simpleApplication.getGuiNode().detachChild(terrainPanel.getNode());
            }
            terrainPanel = new TerrainPanel(x_Tiles, y_Tiles, "Panel", simpleApplication.getAssetManager(), data);
            panelWest.initializePallete(terrainPanel.getImage(), data);
            simpleApplication.getGuiNode().attachChild(terrainPanel.getNode());

        }
        if ("CancelMap".equals(e.getActionCommand()))
        {
            dialogNewMap.setVisible(false);
        }
        if (e.getActionCommand().startsWith("TileButton"))
        {
            String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = test.split("_");
            System.out.println("Frame"+temp[1] + "Row"+temp[0]);
            selectedRow = Integer.parseInt(temp[0]);
            selectedFrame = Integer.parseInt(temp[1]);
            panelWest.updateCurrentTileIcon(selectedRow,selectedFrame);


        }
    }
    
    //JMONKEY SCENE ACTIONLISTENER
    public void onAction(String name, boolean isPressed, float tpf)
    {
        if (name.equals("Select_Tile") && isPressed)
            {
                if (terrainPanel != null)
                {
                    terrainPanel.selectTile(simpleApplication.getInputManager(), selectedRow, selectedFrame);
                }
            }
    }
    //JMONKEY SCENE ANALOGLISTENER
    public void onAnalog(String name, float value, float tpf)
    {
         if (name.equalsIgnoreCase("Move_Up"))
            {
                
                terrainPanel.move(0, 10);
                
                
            }
            if (name.equalsIgnoreCase("Move_Down"))
            {
                
                terrainPanel.move(0, -10);
                
                
                
            }
            if (name.equalsIgnoreCase("Move_Left"))
            {
                
                terrainPanel.move(-10, 0);
                
                
                
            }
            if (name.equalsIgnoreCase("Move_Right"))
            {
                
                terrainPanel.move(10, 0);
                
                
                
            }
    }*/
    
}
