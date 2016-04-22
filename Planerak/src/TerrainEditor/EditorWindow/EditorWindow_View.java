/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.EditorWindow.MenuBar.MenuBar_Controller;
import TerrainEditor.EditorWindow.MenuBar.MenuBar_Model;
import TerrainEditor.EditorWindow.MenuBar.MenuBar_View;
import TerrainEditor.JavaFX.PanelBottom;
import com.jme3.app.SimpleApplication;
import com.jme3.system.JmeCanvasContext;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author Jordan
 */
public class EditorWindow_View
{

    //Variables
    private int nEditorWindow_Width;
    private int nEditorWindow_Height;
    MenuBar_View menuBar_View;
    //Swing Variables
    private JFrame editorWindow;
    private JPanel newPallete;
    private JPanel scrollPanel;
    private JmeCanvasContext jmeCanvas;
    private JLabel currentIcon;
    
    public EditorWindow_View(SimpleApplication jmeApplication,int nEditorWindow_Width, int nEditorWindow_Height, boolean resizableWindow)
    {
        
        
        
        this.nEditorWindow_Width = nEditorWindow_Width;
        this.nEditorWindow_Height = nEditorWindow_Height;
        
        editorWindow = new JFrame("World Editor");
        
        editorWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Use the opposite of the value given undecorated == false is resizable
        //undecorated == true removes minimize, maximize, and exit
        editorWindow.setUndecorated(!resizableWindow);

        //ACTION LISTENER
        
        menuBar_View = new MenuBar_View();
        
        
        
        //Initailize Inner container
        JPanel innerJPanel = new JPanel();
        JPanel jframeParentPanel = new JPanel();

        JPanel toolboxJPanel = createToolbox();
        //JPanel toolbarJPanel = createToolbarHeader();
        JPanel bottomJPanel = PanelBottom.createBottomPanel();
        
        bottomJPanel.setBackground(Color.red);
        innerJPanel.setLayout(new BoxLayout(innerJPanel, BoxLayout.X_AXIS));
        menuBar_View.getMenuJPanel().setPreferredSize(new Dimension(nEditorWindow_Width, 25));
        bottomJPanel.setPreferredSize(new Dimension(nEditorWindow_Width, 25));

        jframeParentPanel.setLayout(new BoxLayout(jframeParentPanel, BoxLayout.Y_AXIS));

        jframeParentPanel.add(menuBar_View.getMenuJPanel());
        jframeParentPanel.add(innerJPanel);


        jframeParentPanel.add(bottomJPanel);


        jmeCanvas = (JmeCanvasContext) jmeApplication.getContext();
        jmeCanvas.setSystemListener(jmeApplication);


        jmeCanvas.getCanvas().setPreferredSize(new Dimension(nEditorWindow_Width, nEditorWindow_Height));
        jmeCanvas.getCanvas().setMinimumSize(new Dimension(nEditorWindow_Width / 10, nEditorWindow_Height / 10));
        toolboxJPanel.setPreferredSize(new Dimension(nEditorWindow_Width / 4, nEditorWindow_Height));
        toolboxJPanel.setMinimumSize(new Dimension(nEditorWindow_Width / 10, nEditorWindow_Height / 10));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, toolboxJPanel, jmeCanvas.getCanvas());
        split.setContinuousLayout(true);
        innerJPanel.add(split);


        editorWindow.setPreferredSize(new Dimension(nEditorWindow_Width, nEditorWindow_Height));
        editorWindow.setMaximumSize(new Dimension(nEditorWindow_Width, nEditorWindow_Height));
        editorWindow.add(jframeParentPanel);
        editorWindow.pack();
        editorWindow.setVisible(true);
        
        
        
    }
    
    public JPanel createToolbox()
   {
        
        JPanel parentPanel = new JPanel(new GridLayout(0, 1, 10, 0));
        JPanel splitPaneTop = new JPanel();
        splitPaneTop.setLayout(new GridBagLayout());
        scrollPanel = new JPanel();
        JScrollPane splitPaneBottom = new JScrollPane(scrollPanel);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, splitPaneTop,splitPaneBottom);
        splitPane.setContinuousLayout(true);
        currentIcon = new JLabel();

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
    public void createPalletePanel(BufferedImage image, TilesetData currentTileset, ActionListener actionListener, String commandTileButton)
    {
        if (newPallete != null)
        {
            newPallete.removeAll();
        }
        newPallete = new JPanel();
        
        //pixelSize = tileImage.getWidth() / tileSize;
       
        newPallete.removeAll();
        newPallete.setLayout(new GridLayout(0, currentTileset.getFrames()));
        
        int nPixels = currentTileset.getPixels();
        
        for (int i = 0; i < currentTileset.getFrames(); i++)
        {
            for (int j = 0; j < currentTileset.getRows(); j++)
            {
                ImageIcon icon = new ImageIcon(image.getSubimage(j * nPixels, i * nPixels, nPixels, nPixels));
                JButton button = new JButton(icon);
                button.addActionListener(actionListener);
                button.setActionCommand(commandTileButton + j + "_" + (currentTileset.getFrames() - 1 - i));
                button.setPreferredSize(new Dimension(nPixels, nPixels));
                newPallete.add(button);
            }

        }  
        newPallete.revalidate();
        newPallete.repaint();
        scrollPanel.add(newPallete);
        
        
        
        

    }
    public void setCurrentIcon(Image image)
    {
        currentIcon.setIcon(new ImageIcon(image));
    }
    public MenuBar_View getMenuBarView()
    {
        return menuBar_View;
    }
    public JFrame getEditorWindow()
    {
        return editorWindow;
    }
    public JmeCanvasContext getCanvas()
    {
        return jmeCanvas;
    }
    
    
    
}
