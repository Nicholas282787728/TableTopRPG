/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_Model;
import TerrainEditor.EditorWindow.NewMapDialog.DialogNewMap_View;
import TerrainEditor.JavaFX.DialogNewTileset;
import TerrainEditor.JavaFX.DialogViewTileset;
import TerrainEditor.JavaFX.PanelBottom;
import TerrainEditor.JavaFX.PanelNorth;
import TerrainEditor.JavaFX.PanelWest;
import TerrainEditor.JavaFX.SwingActionListener;
import TerrainEditor.TerrainPanel;
import TerrainEditor.TileEditor;
import com.jme3.app.SimpleApplication;

import com.jme3.system.JmeCanvasContext;
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;


/**
 *
 * @author K
 */
public class EditorWindowView
{
/*
    private JmeCanvasContext ctxSwing;
            
    private EditorWindowController editorWindow_Controller;
    //private EditorWindowModel editorWindowModel;

    private JComboBox NewTerrainMap_Size;
    
    private int width;
    private int height;

    
    public EditorWindowView(SimpleApplication application, int width, int height)
    {
        this.width = width;
        this.height = height;
        editorWindowModel = new EditorWindowModel(application,this);
        editorWindow_Controller = new EditorWindowController(editorWindowModel);
        
        
        

    }

    public void startSwingEditor(boolean Visible)
    {

        JFrame editorWindowView = new JFrame("World Editor");
        
        editorWindowView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        editorWindowView.setUndecorated(true);

        //ACTION LISTENER
        
        
        //Initailize Inner container
        JPanel innerJPanel = new JPanel();
        JPanel jframeParentPanel = new JPanel();

        JPanel toolboxJPanel = createToolbox();
        JPanel toolbarJPanel = createToolbarHeader();
        JPanel bottomJPanel = PanelBottom.createBottomPanel();
        
        bottomJPanel.setBackground(Color.red);
        innerJPanel.setLayout(new BoxLayout(innerJPanel, BoxLayout.X_AXIS));
        toolbarJPanel.setPreferredSize(new Dimension(width, 25));
        bottomJPanel.setPreferredSize(new Dimension(width, 25));

        jframeParentPanel.setLayout(new BoxLayout(jframeParentPanel, BoxLayout.Y_AXIS));

        jframeParentPanel.add(toolbarJPanel);
        jframeParentPanel.add(innerJPanel);


        jframeParentPanel.add(bottomJPanel);


        ctxSwing = (JmeCanvasContext) jmeApplication.getContext();
        ctxSwing.setSystemListener(editorWindowModel.getApplication());


        ctxSwing.getCanvas().setPreferredSize(new Dimension(width, height));
        ctxSwing.getCanvas().setMinimumSize(new Dimension(width / 10, height / 10));
        toolboxJPanel.setPreferredSize(new Dimension(width / 4, height));
        toolboxJPanel.setMinimumSize(new Dimension(width / 10, height / 10));

        final JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, toolboxJPanel, ctxSwing.getCanvas());
        split.setContinuousLayout(true);
        innerJPanel.add(split);


        editorWindowView.setPreferredSize(new Dimension(width, height));
        editorWindowView.setMaximumSize(new Dimension(width, height));
        editorWindowView.add(jframeParentPanel);
        editorWindowView.pack();
        editorWindowView.setVisible(Visible);
        editorWindowModel.addEditorWindowJFrame(editorWindowView);
        

    }
    public JmeCanvasContext getJMECanvas()
    {
        return ctxSwing;
    }
    
   public JPanel createToolbarHeader()
    {
        JPanel ToolbarHeader = new JPanel(new GridLayout(1, 0, 0, 10));

        JMenuBar Toolbar = new JMenuBar();

        JMenuItem fileNewMap = new JMenuItem("New Map");
        JMenuItem fileSaveMap = new JMenuItem("Save Map");
        JMenuItem fileLoadMap = new JMenuItem("Load Map");
        JMenuItem fileFinalizeMap = new JMenuItem("Finalize Map");
        JMenuItem fileExitEditor = new JMenuItem("Exit");
         
        JMenu fileMenu = new JMenu("File");
        JMenu fileSubMenu = new JMenu("Recent Maps");

        
        fileMenu.add(fileSubMenu);
        fileMenu.add(fileNewMap);
        fileMenu.add(fileSaveMap);
        fileMenu.add(fileLoadMap);
        fileMenu.add(fileFinalizeMap);
        fileMenu.addSeparator();
        fileMenu.add(fileExitEditor);
        Toolbar.add(fileMenu);
        
        JMenu tilesetMenu = new JMenu("Tilesets");
        JMenuItem tilesetNewTileset = new JMenuItem("New Tileset");
        JMenuItem tilesetViewTilesets = new JMenuItem("View Tilesets");
        
        tilesetMenu.add(tilesetNewTileset);
        tilesetMenu.add(tilesetViewTilesets);
        Toolbar.add(tilesetMenu);
        
        ToolbarHeader.add(Toolbar);
        
        fileNewMap.setActionCommand("New_Map");
        fileNewMap.addActionListener(editorWindow_Controller);
        
        fileSaveMap.setActionCommand("Save_Map");
        fileSaveMap.addActionListener(editorWindow_Controller);
        
        fileLoadMap.setActionCommand("Load_Map");
        fileLoadMap.addActionListener(editorWindow_Controller);
        
        fileFinalizeMap.setActionCommand("Finalize_Map");
        fileFinalizeMap.addActionListener(editorWindow_Controller);
        
        fileExitEditor.setActionCommand("Exit");
        fileExitEditor.addActionListener(editorWindow_Controller);
        
        tilesetNewTileset.setActionCommand("New_Tileset");
        tilesetNewTileset.addActionListener(editorWindow_Controller);
        
        tilesetViewTilesets.setActionCommand("View_Tileset");
        tilesetViewTilesets.addActionListener(editorWindow_Controller);
        
        return ToolbarHeader;
    }
    
   public JPanel createToolbox()
   {
        
        JPanel parentPanel = new JPanel(new GridLayout(0, 1, 10, 0));
        JPanel splitPaneTop = new JPanel();
        splitPaneTop.setLayout(new GridBagLayout());
        JPanel scrollPanel = new JPanel();
        JScrollPane splitPaneBottom = new JScrollPane(scrollPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneTop,splitPaneBottom);
        splitPane.setContinuousLayout(true);
        JLabel currentIcon = new JLabel();

        parentPanel.add(splitPane, BorderLayout.CENTER);
        GridBagConstraints constraint = new GridBagConstraints();
        
        
        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        splitPaneTop.add(new JLabel("Current Tile: "),constraint);
        constraint.gridx = 1;
        constraint.gridy = 0;
        splitPaneTop.add(currentIcon,constraint);
        parentPanel.revalidate();
        
        return parentPanel;
    }
    public JDialog DialogNewMapView(JFrame jFrame)
    {
        
        JDialog dialogNewMap = new JDialog(jFrame, "New Map");
        dialogNewMap.setResizable(false);
        dialogNewMap.setPreferredSize(new Dimension(225, 150));

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));

        JPanel topRow = new JPanel();
        topRow.setLayout(new GridBagLayout());
        GridBagConstraints constraintTopRow = new GridBagConstraints();

        JLabel tilesetLabel = new JLabel("Select Tileset: ");
        constraintTopRow.fill = GridBagConstraints.HORIZONTAL;
        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 0;
        topRow.add(tilesetLabel, constraintTopRow);

        
        JComboBox tilesetCombo = new JComboBox(editorWindowModel.getComboBox());
        
        constraintTopRow.gridx = 1;
        topRow.add(tilesetCombo, constraintTopRow);

        JLabel sizeLabel = new JLabel("Size: ");
        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 1;
        topRow.add(sizeLabel, constraintTopRow);

        JComboBox sizeCombo = new JComboBox(new String[]
        {
            "8 x 8", "16 x 16", "32 x 32", "64 x 64", "128 x 128"
        });
        sizeCombo.setActionCommand("Select_Tileset_Size");
        sizeCombo.addActionListener(editorWindow_Controller);
        constraintTopRow.gridx = 1;
        topRow.add(sizeCombo, constraintTopRow);

        JButton okButton = new JButton("OK");
        okButton.setActionCommand("CreateMap");
        okButton.addActionListener(editorWindow_Controller);

        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 2;
        topRow.add(okButton, constraintTopRow);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setActionCommand("CancelMap");
        cancelButton.addActionListener(editorWindow_Controller);
        constraintTopRow.gridx = 1;
        constraintTopRow.gridy = 2;
        topRow.add(cancelButton, constraintTopRow);

        editorWindowModel.addNewDialogJDialog(dialogNewMap,sizeCombo,tilesetCombo);
        dialogPanel.add(topRow);
        dialogNewMap.add(dialogPanel);
        //dialog.setUndecorated(true);
        dialogNewMap.pack();
        dialogNewMap.setVisible(false);
        
        return dialogNewMap;
    }*/
    
   public JPanel createTilesetPanel()
   {
       JPanel scrollPanel = new JPanel();
       scrollPanel.removeAll();
       /*scrollPanel.setLayout(new GridLayout(0, currentTileset.getFrames()));
       for (int i = 0; i < currentTileset.getFrames(); i++)
        {
            for (int j = 0; j < currentTileset.getRows(); j++)
            {
                ImageIcon icon = new ImageIcon(tileImage.getSubimage(j * currentTileset.getPixels(), i * currentTileset.getPixels(), currentTileset.getPixels(), currentTileset.getPixels()));
                JButton button = new JButton(icon);
                button.addActionListener(editorWindow_Controller);
                button.setActionCommand("TileButton_" + j + "_" + (currentTileset.getFrames() - 1 - i));
                button.setPreferredSize(new Dimension(currentTileset.getPixels(), currentTileset.getPixels()));
                scrollPanel.add(button);
            }

        }*/
       return scrollPanel;
   }
   /*
   public void createImageButton(int nFrames, int nRows, int nPixels, BufferedImage tileImage, JPanel palletePanel)
   {
       for (int i = 0; i < nFrames; i++)
        {
            for (int j = 0; j < nRows; j++)
            {
                ImageIcon icon = new ImageIcon(tileImage.getSubimage(j * nPixels, i * nPixels, nPixels, nPixels));
                JButton button = new JButton(icon);
                button.addActionListener(editorWindow_Controller);
                button.setActionCommand("TileButton_" + j + "_" + (nFrames - 1 - i));
                button.setPreferredSize(new Dimension(nPixels, nPixels));
                palletePanel.add(button);
            }

        }  
       
   }*/
   
}
