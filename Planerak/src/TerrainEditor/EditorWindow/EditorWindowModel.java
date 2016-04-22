/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_View;
import TerrainEditor.JavaFX.PanelWest;
import TerrainEditor.JavaFX.SwingActionListener;
import TerrainEditor.TerrainPanel;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.app.SimpleApplication;
import com.jme3.system.JmeCanvasContext;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Jordan
 */
public class EditorWindowModel
{/*
    private JFrame editorWindowJFrame;
    private JmeCanvasContext ctxSwing;
    private SimpleApplication jmeApplication;

    private JDialog editorWindowJDialog_New;
    
    //private TerrainPanel panel;
    private int selectedFrame;
    private int selectedRow;

    //private PanelWest leftPanel;
    
    private BufferedImage tileImage;
    private TilesetData currentTileset;
    private JComboBox Select_Tileset_Size;
    private JComboBox Select_Tileset_Name;
    JPanel newPallete;
    TerrainPanel terrainPanel;
    private int nTilesetSizeX = 8;
    private int nTilesetSizeY = 8;
    private String sTilesetName = "";
    
    EditorWindowView editorWindowView;
    
    
    
    
    public EditorWindowModel(SimpleApplication jmeApplication,EditorWindowView editorWindowView)
    {
        this.jmeApplication = jmeApplication;
        this.editorWindowView = editorWindowView;
    }
    
    public void addJMEContext(JmeCanvasContext ctxSwing)
    {
       this.ctxSwing = ctxSwing;
    }
    
    public void addEditorWindowJFrame(JFrame editorWindow)
    {
        this.editorWindowJFrame = editorWindow;
    }
    
    public void addNewDialogJDialog(JDialog dialogNewMapView,JComboBox Select_Tileset_Size, JComboBox Select_Tileset_Name)
    {
        this.editorWindowJDialog_New = dialogNewMapView;
        this.Select_Tileset_Size = Select_Tileset_Size;
        this.Select_Tileset_Name = Select_Tileset_Name;
    }
    public JFrame getEditorWindowJFrame()
    {
        return editorWindowJFrame;
    }
    public JDialog getEditorWindowJDialog_New()
    {
        return editorWindowView.DialogNewMapView(editorWindowJFrame);
    }

    public SimpleApplication getApplication()
    {
        return jmeApplication;
    }
    public JmeCanvasContext getCanvasContext()
    {
        return (JmeCanvasContext) jmeApplication.getContext();
    }
    public void closeApplication()
    {
        ctxSwing.destroy(true);
        editorWindowJFrame.setVisible(false);
        editorWindowJFrame.dispose();
    }
    public void setVisible(boolean Visible)
    {
        editorWindowJFrame.setVisible(Visible);
    }


    public int getRow()
    {
        return selectedRow;
    }

    public int getFrame()
    {
        return selectedFrame;
    }



    public TerrainPanel getPanel()
    {
        return terrainPanel;
    }
    
    public void loadTilesets()
    {
        
    }
    public void getTerrainPallete(BufferedImage image, TilesetData currentTileset)
    {
        tileImage = createFlipped(image);
        this.currentTileset = currentTileset;
    }

    public JLabel getCurrentTileIcon(JLabel currentTileIcon, int x, int y)
    {
        currentTileIcon.setIcon(new ImageIcon(tileImage.getSubimage(x * currentTileset.getPixels(), (currentTileset.getFrames()- 1 - y) * currentTileset.getPixels(), currentTileset.getRows(), currentTileset.getFrames())));
        return currentTileIcon;
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
    
    public void setTilesetSize()
    {
        switch (Select_Tileset_Size.getSelectedIndex())
            {
                case 0:
                    nTilesetSizeX = 8;
                    nTilesetSizeY = 8;
                    break;
                case 1:
                    nTilesetSizeX = 16;
                    nTilesetSizeY = 16;
                    break;
                case 2:
                    nTilesetSizeX = 32;
                    nTilesetSizeY = 32;
                    break;
                case 3:
                    nTilesetSizeX = 64;
                    nTilesetSizeY = 64;
                    break;
                case 4:
                    nTilesetSizeX = 128;
                    nTilesetSizeY = 128;
                    break;
            }
    }
    
    public DefaultComboBoxModel getComboBox()
    {
        File tilesetFile;
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        if(DEV_MODE == true)
        {
            tilesetFile = new File(System.getProperty("user.dir")+ "\\assets\\Tilesets");
        }
        else
        {
            tilesetFile = new File(System.getProperty("user.home") + "\\Planerrak\\Tilesets\\Data");
        }
        

            File[] matchingFiles = tilesetFile.listFiles();
            for(int j=0; j < matchingFiles.length; j++)
            {
                model.addElement(matchingFiles[j].getName());
            }
        return model;
    }
    
    public void createMap()
    {
        editorWindowJDialog_New.setVisible(false);
            if (terrainPanel != null)
            {
                //attach terrain panel
                jmeApplication.getGuiNode().detachChild(terrainPanel.getNode());
            }
            
            
            if(DEV_MODE)
            {
                currentTileset = (TilesetData) jmeApplication.getAssetManager().loadAsset(Select_Tileset_Name.getModel().getSelectedItem().toString());
                 //currentTileset = (TilesetData);
            }
            else
            {
                currentTileset = (TilesetData) jmeApplication.getAssetManager().loadAsset("Tilesets\\Data\\" + sTilesetName);

            }

            //panel = new TerrainPanel(x_Tiles, y_Tiles, 32, 32, "Panel", application.getAssetManager(), "Textures/LPC Terrain/terrain_atlas.png");
            if (terrainPanel != null)
            {
                jmeApplication.getGuiNode().detachChild(terrainPanel.getNode());
            }
            terrainPanel = new TerrainPanel(nTilesetSizeX, nTilesetSizeY, "Panel", jmeApplication.getAssetManager(), currentTileset);
            createPalletePanel(tileImage);
            
            jmeApplication.getGuiNode().attachChild(terrainPanel.getNode());
    }
    
    public void createPalletePanel(BufferedImage image)
    {
        newPallete = new JPanel();
        tileImage = createFlipped(image);
        
        //pixelSize = tileImage.getWidth() / tileSize;
       
        newPallete.removeAll();
        newPallete.setLayout(new GridLayout(0, currentTileset.getFrames()));
        
        int nPixels = currentTileset.getPixels();
        editorWindowView.createImageButton(currentTileset.getFrames(), currentTileset.getRows(), nPixels, tileImage, newPallete);
        

    }*/
    
}
