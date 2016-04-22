/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow;

import TerrainEditor.CustomFiles.TilesetData;
import static TerrainEditor.TileEditor.DEV_MODE;
import TerrainEditor.JavaFX.DialogNewTileset;
import java.awt.event.ActionEvent;
import javax.swing.JDialog;

/**
 *
 * @author Jordan
 */
public class EditorWindowController implements java.awt.event.ActionListener,
                                                   com.jme3.input.controls.ActionListener,
                                                   com.jme3.input.controls.AnalogListener
{
    /*
    private EditorWindowModel editorWindowModel;
    public EditorWindowController(EditorWindowModel editorWindowModel)
    {
        this.editorWindowModel = editorWindowModel;
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if ("Exit".equals(e.getActionCommand()))
        {
            editorWindowModel.closeApplication();
        }
        if ("New_Map".equals(e.getActionCommand()))
        {
                
                JDialog newMap = editorWindowModel.getEditorWindowJDialog_New();
                newMap.setVisible(true);

        }
        if ("New_Tileset".equals(e.getActionCommand()))
        {
            
            DialogNewTileset dial = new DialogNewTileset(editorWindowModel.getEditorWindowJFrame());
            if(DEV_MODE)
            {
                dial.initialize(true,editorWindowModel.getApplication().getAssetManager(),"\\Tilesets\\",System.getProperty("user.dir")+ "\\assets\\Tilesets");
            }
            else
            {
                 dial.initialize(true,editorWindowModel.getApplication().getAssetManager(),"\\Tilesets\\",System.getProperty("user.home") + "\\Planerrak\\Tilesets");

            }
        }
        if("View_Tileset".equals(e.getActionCommand()))
        {
           // DialogViewTileset test = new DialogViewTileset(editorWindow);
            

            System.out.print("TT");
        }
        
        
        if (e.getActionCommand().startsWith("TileButton"))
        {
            String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = test.split("_");
            System.out.println("Frame"+temp[1] + "Row"+temp[0]);
           // selectedFrame = Integer.parseInt(temp[1]);
            //selectedRow = Integer.parseInt(temp[0]);
            //leftPanel.updateCurrentTileIcon(selectedRow,selectedFrame);


        }   
        if ("CreateMap".equals(e.getActionCommand()))
        {
            editorWindowModel.createMap();

        }
        if ("CancelMap".equals(e.getActionCommand()))
        {
            editorWindowModel.getEditorWindowJDialog_New().setVisible(false);
        }
        if("Select_Tileset_Size".equals(e.getActionCommand()))
        {
            editorWindowModel.setTilesetSize();
        }
    }*/

    public void onAction(String name, boolean isPressed, float tpf)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void onAnalog(String name, float value, float tpf)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void actionPerformed(ActionEvent e)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
