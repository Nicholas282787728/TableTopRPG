/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.TerrainPanel;
import TerrainEditor.TileEditor;
import com.jme3.app.SimpleApplication;

import com.jme3.system.JmeCanvasContext;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JPanel;
import javax.swing.JSplitPane;


/**
 *
 * @author K
 */
public class FrameEditor
{
    private JFrame editorWindow;
    private JmeCanvasContext ctxSwing;
    private SimpleApplication application;
    private SwingActionListener swingActionListener;
    private DialogNewMap newMap;
    private TerrainPanel panel;
    private int selectedFrame;
    private int selectedRow;
    private int width;
    private int height;
    private PanelWest leftPanel;
    
 

    public JFrame getEditorWindow()
    {
        return editorWindow;
    }

    public FrameEditor(SimpleApplication application,SwingActionListener swingActionListener, int width, int height)
    {
        this.width = width;
        this.height = height;
        this.application = application;
        this.swingActionListener = swingActionListener;
        leftPanel = new PanelWest(swingActionListener);
        

    }

    public void initialize(boolean Visible)
    {

        editorWindow = new JFrame("World Editor");
        editorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorWindow.setUndecorated(true);

        //ACTION LISTENER
        
        swingActionListener.initialize(leftPanel,this);
        //Initailize Inner container
        JPanel innerJPanel = new JPanel();
        JPanel toolbarJPanel = new JPanel();

        JPanel leftJPanel = leftPanel.getParentPanel();
        JPanel topJPanel = PanelNorth.createTopPanel(swingActionListener);
        JPanel bottomJPanel = PanelBottom.createBottomPanel();
        bottomJPanel.setBackground(Color.red);
        innerJPanel.setLayout(new BoxLayout(innerJPanel, BoxLayout.X_AXIS));
        topJPanel.setPreferredSize(new Dimension(width, 25));
        bottomJPanel.setPreferredSize(new Dimension(width, 25));

        toolbarJPanel.setLayout(new BoxLayout(toolbarJPanel, BoxLayout.Y_AXIS));

        toolbarJPanel.add(topJPanel);
        toolbarJPanel.add(innerJPanel);


        toolbarJPanel.add(bottomJPanel);


        ctxSwing = (JmeCanvasContext) application.getContext();
        ctxSwing.setSystemListener(application);


        ctxSwing.getCanvas().setPreferredSize(new Dimension(width, height));
        ctxSwing.getCanvas().setMinimumSize(new Dimension(width / 10, height / 10));
        leftJPanel.setPreferredSize(new Dimension(width / 4, height));
        leftJPanel.setMinimumSize(new Dimension(width / 10, height / 10));

        final JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftJPanel, ctxSwing.getCanvas());
        split.setContinuousLayout(true);
        innerJPanel.add(split);


        editorWindow.setPreferredSize(new Dimension(width, height));
        editorWindow.setMaximumSize(new Dimension(width, height));
        editorWindow.add(toolbarJPanel);
        editorWindow.pack();
        editorWindow.setVisible(Visible);
        
        
        
        
        /*
          @Override
  public void simpleInitApp() {
     String userHome = System.getProperty("user.home");
     assetManager.registerLocator(userHome, FileLocator.class);
     Node loadedNode = (Node)assetManager.loadModel("Models/MyModel.j3o");
     loadedNode.setName("loaded node");
     rootNode.attachChild(loadedNode);
  }*/

        

    }
    public void setVisible(boolean Visible)
    {
        editorWindow.setVisible(Visible);
    }
    public void actionPerformed(ActionEvent e)
    {
        if ("Exit".equals(e.getActionCommand()))
        {
            closeApplication();
        }
        if ("New_Map".equals(e.getActionCommand()))
        {

                newMap = new DialogNewMap(this.getEditorWindow(),swingActionListener);
                newMap.setVisible(true);

        }
        if ("New_Tileset".equals(e.getActionCommand()))
        {
            
            DialogNewTileset dial = new DialogNewTileset(editorWindow);
            dial.initialize(true,application.getAssetManager(),"\\Tilesets\\",System.getProperty("user.home") + "\\Planerrak\\Tilesets");
        }
        if("View_Tileset".equals(e.getActionCommand()))
        {
            DialogViewTileset test = new DialogViewTileset(editorWindow);
            

            System.out.print("TT");
        }
        if ("CreateMap".equals(e.getActionCommand()))
        {
            newMap.setVisible(false);
            int x_Tiles = 0;
            int y_Tiles = 0;
            if (panel != null)
            {
                application.getGuiNode().detachChild(panel.getNode());
            }
            switch (newMap.getSizeIndex())
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
           
            TilesetData data = (TilesetData) application.getAssetManager().loadAsset("Tilesets\\Data\\"+ newMap.getTilesetName());
            
            //panel = new TerrainPanel(x_Tiles, y_Tiles, 32, 32, "Panel", application.getAssetManager(), "Textures/LPC Terrain/terrain_atlas.png");
            if(panel != null)
            {
                application.getGuiNode().detachChild(panel.getNode());
            }
            panel = new TerrainPanel(x_Tiles, y_Tiles, "Panel", application.getAssetManager(), data);
            leftPanel.initializePallete(panel.getImage(), data);
            application.getGuiNode().attachChild(panel.getNode());

        }
        if ("CancelMap".equals(e.getActionCommand()))
        {
            newMap.setVisible(false);
        }
        if (e.getActionCommand().startsWith("TileButton"))
        {
            String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = test.split("_");
            System.out.println("Frame"+temp[1] + "Row"+temp[0]);
            selectedFrame = Integer.parseInt(temp[1]);
            selectedRow = Integer.parseInt(temp[0]);
            leftPanel.updateCurrentTileIcon(selectedRow,selectedFrame);


        }
    }

    public int getRow()
    {
        return selectedRow;
    }

    public int getFrame()
    {
        return selectedFrame;
    }

    public void closeApplication()
    {
        ctxSwing.destroy(true);
        editorWindow.setVisible(false);
        editorWindow.dispose();
    }

    public TerrainPanel getPanel()
    {
        return panel;
    }
    
    public void loadTilesets()
    {
        
    }
   
}
