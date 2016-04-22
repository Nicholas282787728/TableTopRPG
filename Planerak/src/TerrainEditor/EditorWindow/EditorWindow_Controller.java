/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.EditorWindow.EditorWindow_Model;
import TerrainEditor.EditorWindow.EditorWindow_View;
import TerrainEditor.EditorWindow.MenuBar.MenuBar_Controller;
import TerrainEditor.EditorWindow.MenuBar.MenuBar_Model;
import TerrainEditor.TerrainPanel;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.app.SimpleApplication;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class EditorWindow_Controller implements ActionListener,com.jme3.input.controls.ActionListener,AnalogListener
{

    EditorWindow_Model editorWindow_Model;
    EditorWindow_View editorWindow_View;
    SimpleApplication jmeApplication;
    MenuBar_Controller menuBar_Controller;
    private String commandTileButton = "TileButton_";
    private String commandTile_Selection = "Tile_Selection";
    private String commandMove_Panel_UP = "Move_Panel_UP";
    private String commandMove_Panel_DOWN = "Move_Panel_DOWN";
    private String commandMove_Panel_LEFT = "Move_Panel_LEFT";
    private String commandMove_Panel_RIGHT = "Move_Panel_RIGHT";
    public EditorWindow_Controller(EditorWindow_Model editorWindow_Model, EditorWindow_View editorWindow_View,SimpleApplication jmeApplication)
    {
        this.editorWindow_Model = editorWindow_Model;
        this.editorWindow_View = editorWindow_View;
        this.jmeApplication = jmeApplication;
        
        MenuBar_Model menuBar_Model = new MenuBar_Model();
        menuBar_Controller = new MenuBar_Controller(menuBar_Model, editorWindow_View.getMenuBarView(), this);
                  
    }
    
    public void setupInputCanvasCommands()
    {
        jmeApplication.getInputManager().addMapping(commandTile_Selection, new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        jmeApplication.getInputManager().addListener(this, commandTile_Selection);
      
        jmeApplication.getInputManager().addMapping(commandMove_Panel_UP, new KeyTrigger(KeyInput.KEY_UP));
        jmeApplication.getInputManager().addListener(this, commandMove_Panel_UP);
        
        jmeApplication.getInputManager().addMapping(commandMove_Panel_DOWN, new KeyTrigger(KeyInput.KEY_DOWN));
        jmeApplication.getInputManager().addListener(this, commandMove_Panel_DOWN);
        
        jmeApplication.getInputManager().addMapping(commandMove_Panel_LEFT, new KeyTrigger(KeyInput.KEY_LEFT));
        jmeApplication.getInputManager().addListener(this, commandMove_Panel_LEFT);
        
        jmeApplication.getInputManager().addMapping(commandMove_Panel_RIGHT, new KeyTrigger(KeyInput.KEY_RIGHT));
        jmeApplication.getInputManager().addListener(this, commandMove_Panel_RIGHT);
        jmeApplication.getFlyByCamera().setEnabled(false);
        jmeApplication.getInputManager().setCursorVisible(true);
    }
    
    public SimpleApplication getApplication()
    {
        return jmeApplication;
    }
    public EditorWindow_View getView()
    {
        return editorWindow_View;
    }
    public void createMap(String tileset_Name,int nTilesetSizeX, int nTilesetSizeY)
    {
            if (editorWindow_Model.getTerrainPanel() != null)
            {
                //attach terrain panel
                jmeApplication.getGuiNode().detachChild(editorWindow_Model.getTerrainPanel().getNode());
            }
            
            
            if(DEV_MODE)
            {
                editorWindow_Model.setTilesetData((TilesetData) jmeApplication.getAssetManager().loadAsset(tileset_Name));
                 //currentTileset = (TilesetData);
            }
            else
            {
                editorWindow_Model.setTilesetData((TilesetData) jmeApplication.getAssetManager().loadAsset("Tilesets\\Data\\" + tileset_Name));

            }
           

            editorWindow_Model.setTerrainPanel(new TerrainPanel(nTilesetSizeX, nTilesetSizeY, "Panel", jmeApplication.getAssetManager(), editorWindow_Model.getTilesetData()));

            BufferedImage flipped = createFlipped(editorWindow_Model.getTerrainPanel().getImage());
            editorWindow_Model.setTilesetImage(flipped);
            
            jmeApplication.getGuiNode().attachChild(editorWindow_Model.getTerrainPanel().getNode());
            editorWindow_View.createPalletePanel(editorWindow_Model.getTilesetImage(),editorWindow_Model.getTilesetData(),this,commandTileButton);
    }
    
    private BufferedImage createFlipped(BufferedImage image)
    {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }
    
    private BufferedImage createTransformed(
            BufferedImage image, AffineTransform at)
    {
        BufferedImage newImage = new BufferedImage(
                image.getWidth(), image.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().startsWith(commandTileButton))
        {
            String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = test.split("_");
            System.out.println("X"+temp[0] + "Y"+temp[1] );
            editorWindow_Model.setSelectedTiles(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
            int nPixel = editorWindow_Model.getTilesetData().getPixels();
            int nFrames = editorWindow_Model.getTilesetData().getFrames();
            //int nRows = editorWindow_Model.getTilesetData().getRows();
            Image tileImage = editorWindow_Model.getTilesetImage().getSubimage(Integer.parseInt(temp[0]) * nPixel, (nFrames- 1 - Integer.parseInt(temp[1])) * nPixel, nPixel, nPixel);
            editorWindow_View.setCurrentIcon(tileImage);
            



        }
    }

    public void onAction(String name, boolean isPressed, float tpf)
    {
        if (name.equals(commandTile_Selection) && isPressed)
            {
                if (editorWindow_Model.getTerrainPanel() != null)
                {
                    editorWindow_Model.getTerrainPanel().selectTile(jmeApplication.getInputManager(), editorWindow_Model.getselectedTileX(), editorWindow_Model.getselectedTileY());
                }
            }
    }

    public void onAnalog(String name, float value, float tpf)
    {
        if (name.equalsIgnoreCase(commandMove_Panel_UP))
            {
                
                editorWindow_Model.getTerrainPanel().move(0, 10);
                
                
            }
            if (name.equalsIgnoreCase(commandMove_Panel_DOWN))
            {
                
                editorWindow_Model.getTerrainPanel().move(0, -10);
                
                
                
            }
            if (name.equalsIgnoreCase(commandMove_Panel_LEFT))
            {
                
                editorWindow_Model.getTerrainPanel().move(-10, 0);
                
                
                
            }
            if (name.equalsIgnoreCase(commandMove_Panel_RIGHT))
            {
                
                editorWindow_Model.getTerrainPanel().move(10, 0);
                
                
                
            }
    }


    
}
