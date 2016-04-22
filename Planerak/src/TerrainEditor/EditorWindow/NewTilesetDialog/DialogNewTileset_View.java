/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewTilesetDialog;

import TerrainEditor.CustomFiles.EmptyIcon;
import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.JavaFX.PanelTilesetView;
import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Jordan
 */
public class DialogNewTileset_View
{
    private JDialog dialogNewTileset;

    
    private JLabel jLabelImage;
    private JLabel jLabelTileSize;
    private JLabel jLabelTilePixelSize;
    private JLabel jLabelTilesetName;
    
    private JLabel jLabelTileImage;
    private JLabel jLabelTileImageIcon;
    
    private JFileChooser jFileChooserTextureLocation;
    private JScrollPane jScrollPaneView;
    
    private JPanel jPanelNewTileset;
    private JPanel jPanelSelectDefault;
    
   private  JTextField jTextFieldImagePath;
    private JTextField jTextFieldTileRows;
    private JTextField jTextFieldTileFrames;
    private JTextField jTextFieldTilePixelSize;
    private JTextField jTextFieldTilesetName;
    
    private JButton jButtonBrowse;
    private JButton jButtonOK;
    private JButton jButtonCancel;
    private JButton jButtonDefault;
    private JButton jButtonCalculate;
   
    
    public DialogNewTileset_View(JFrame jFrame)
    {

        this.jFileChooserTextureLocation = new JFileChooser();
        this.jButtonCalculate = new JButton();
        this.jButtonDefault = new JButton();
        this.jButtonCancel = new JButton();
        this.jButtonOK = new JButton();
        this.jButtonBrowse = new JButton();
        this.jTextFieldTilesetName = new JTextField();
        this.jTextFieldTilePixelSize = new JTextField();
        this.jTextFieldTileFrames = new JTextField();
        this.jTextFieldTileRows = new JTextField();
        this.jTextFieldImagePath = new JTextField();
        this.jPanelSelectDefault = new JPanel( new GridBagLayout());
        this.jPanelNewTileset = new JPanel( new GridBagLayout());
        this.jLabelTileImageIcon = new JLabel();
        this.jLabelTileImage = new JLabel();
        this.jLabelTilesetName = new JLabel();
        this.jLabelTilePixelSize = new JLabel();
        this.jLabelTileSize = new JLabel();
        this.jLabelImage = new JLabel();
        
        GridBagConstraints gridBagConstraints;
        dialogNewTileset = new JDialog(jFrame,"New Tileset");
        dialogNewTileset.setResizable(false);
        dialogNewTileset.setPreferredSize(new Dimension(350, 250));
        
        jLabelImage.setText("Image Path:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        jPanelNewTileset.add(jLabelImage, gridBagConstraints);

        jTextFieldImagePath.setText("Image Path");
        jTextFieldImagePath.setEditable(false);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.weightx = 0.75;
        jPanelNewTileset.add(jTextFieldImagePath, gridBagConstraints);

        jButtonBrowse.setText("Browse");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 5, 0, 10);
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jButtonBrowse, gridBagConstraints);

        jLabelTileSize.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelTileSize.setText("Rows x Frames: ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jLabelTileSize, gridBagConstraints);

        jTextFieldTileRows.setText("0");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jTextFieldTileRows, gridBagConstraints);
        
        jTextFieldTileFrames.setText("0");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jTextFieldTileFrames, gridBagConstraints);

        jButtonCalculate.setText("Calculate");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 5, 0, 10);
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jButtonCalculate, gridBagConstraints);
                
        jLabelTilePixelSize.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabelTilePixelSize.setText("Tile Size (px): ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jLabelTilePixelSize, gridBagConstraints);
        
        jTextFieldTilePixelSize.setText("32");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        gridBagConstraints.weightx = 0.25;
        jPanelNewTileset.add(jTextFieldTilePixelSize, gridBagConstraints);

        jLabelTilesetName.setText("Tileset Name:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        jPanelNewTileset.add(jLabelTilesetName, gridBagConstraints);

        jTextFieldTilesetName.setText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 10, 0, 10);
        jPanelNewTileset.add(jTextFieldTilesetName, gridBagConstraints);

        jLabelTileImage.setText("Default Tile:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        jPanelNewTileset.add(jLabelTileImage, gridBagConstraints);
        
        jLabelTileImageIcon.setIcon(new EmptyIcon(32,32));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        
        
        jPanelNewTileset.add(jLabelTileImageIcon, gridBagConstraints);
        
        jButtonDefault.setText("Select");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        jPanelNewTileset.add(jButtonDefault, gridBagConstraints);
           
        
        jButtonOK.setText("OK");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        jPanelNewTileset.add(jButtonOK, gridBagConstraints);
        jButtonOK.setEnabled(false);
        
        
        jButtonCancel.setText("CANCEL");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 10, 0, 10);
        jPanelNewTileset.add(jButtonCancel, gridBagConstraints);
        
        dialogNewTileset.add(jPanelNewTileset);

        dialogNewTileset.pack();
        dialogNewTileset.setVisible(true);
        
        
        /**
         * Tileset Selection Window
         */
        
        
        
    }

    public JFileChooser getjFileChooserTextureLocation()
    {
        return jFileChooserTextureLocation;
    }
    
    
    
    
    public JTextField getjTextFieldImagePath()
    {
        return jTextFieldImagePath;
    }


    public JTextField getjTextFieldTileRows()
    {
        return jTextFieldTileRows;
    }



    public JTextField getjTextFieldTileFrames()
    {
        return jTextFieldTileFrames;
    }



    public JTextField getjTextFieldTilePixelSize()
    {
        return jTextFieldTilePixelSize;
    }



    public JTextField getjTextFieldTilesetName()
    {
        return jTextFieldTilesetName;
    }



    public JButton getjButtonBrowse()
    {
        return jButtonBrowse;
    }



    public JButton getjButtonOK()
    {
        return jButtonOK;
    }



    public JButton getjButtonCancel()
    {
        return jButtonCancel;
    }


    public JButton getjButtonDefault()
    {
        return jButtonDefault;
    }



    public JButton getjButtonCalculate()
    {
        return jButtonCalculate;
    }
    public JDialog getJDialogNewTileset()
    {
        return dialogNewTileset;
    }

    public JLabel getjLabelTileImageIcon()
    {
        return jLabelTileImageIcon;
    }


}
