/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import TerrainEditor.CustomFiles.EmptyIcon;
import TerrainEditor.CustomFiles.TilesetData;
import com.jme3.asset.AssetManager;
import com.jme3.export.binary.BinaryExporter;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
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
import jme3tools.converters.ImageToAwt;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.texture.Texture;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author K
 */
public class DialogNewTileset implements ActionListener
{
    
    
    private JDialog dialogNewTileset;
    private JDialog dialogSelectDefault;
    
    JLabel jLabelImage = new JLabel();
    JLabel jLabelTileSize = new JLabel();
    JLabel jLabelTilePixelSize = new JLabel();
    JLabel jLabelTilesetName = new JLabel();
    
    JLabel jLabelTileImage = new JLabel();
    JLabel jLabelTileImageIcon = new JLabel();
    
    JFileChooser fc;
    JScrollPane jScrollPaneView;
    
    JPanel jPanelNewTileset = new JPanel( new GridBagLayout());
    JPanel jPanelSelectDefault = new JPanel( new GridBagLayout());
    
    JTextField jTextFieldImagePath = new JTextField();
    JTextField jTextFieldTileRows = new JTextField();
    JTextField jTextFieldTileFrames = new JTextField();
    JTextField jTextFieldTilePixelSize = new JTextField();
    JTextField jTextFieldTilesetName = new JTextField();
    
    JButton jButtonBrowse = new JButton();
    JButton jButtonOK = new JButton();
    JButton jButtonCancel = new JButton();
    JButton jButtonDefault = new JButton();
    JButton jButtonCalculate = new JButton();
    
    JButton jButtonOKView = new JButton();
    JButton jButtonCancelView = new JButton();
    
    PanelTilesetView panelTilesetView;
    

    private JLabel jLabelCurrent;
    private JLabel jLabelIcon;
    private JPanel jPanelButtons;
    private JScrollPane jScrollPaneSelection;
    
    private AssetManager assetmanager;

    String tilesetPathFull;
    private String tilesetPathRelative;
    File imageFile;
    
    public DialogNewTileset(JFrame frame)
    {
        GridBagConstraints gridBagConstraints;
        dialogNewTileset = new JDialog(frame,"New Tileset");
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
        dialogNewTileset.setVisible(false);
        tilesetView();

    }
    
    public void tilesetView()
    {
        
        
        dialogSelectDefault = new JDialog(dialogNewTileset,"Select Default Tile");
        dialogSelectDefault.setResizable(true);
        dialogSelectDefault.setPreferredSize(new Dimension(350, 350));
        
            
        

        GridBagConstraints constraintsDefault;

        jScrollPaneSelection = new JScrollPane();
        jPanelButtons = new JPanel();
        jButtonOKView = new JButton();
        jButtonCancelView = new JButton();
        jLabelCurrent = new JLabel();
        jLabelIcon = new JLabel();

        dialogSelectDefault.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        //dialogSelectDefault.setPreferredSize(new Dimension(500, 500));
        dialogSelectDefault.getContentPane().setLayout(new GridBagLayout());
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 0;
        constraintsDefault.gridy = 0;
        constraintsDefault.gridwidth = 3;
        constraintsDefault.gridheight = 3;
        constraintsDefault.fill = GridBagConstraints.BOTH;
        constraintsDefault.weightx = 0.75;
        constraintsDefault.weighty = 0.75;
        constraintsDefault.insets = new Insets(10, 10, 10, 10);
        dialogSelectDefault.getContentPane().add(jScrollPaneSelection, constraintsDefault);

        jPanelButtons.setPreferredSize(new Dimension(100, 50));
        jPanelButtons.setLayout(new GridBagLayout());

        jButtonOKView.setText("OK");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 0;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 10, 0, 10);
        jPanelButtons.add(jButtonOKView, constraintsDefault);
        

        jButtonCancelView.setText("Cancel");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 1;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 10, 0, 10);
        jPanelButtons.add(jButtonCancelView, constraintsDefault);

        jLabelCurrent.setText("Current:");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 2;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 10, 0, 0);
        jPanelButtons.add(jLabelCurrent, constraintsDefault);

        jLabelIcon.setText("");
        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 3;
        constraintsDefault.gridy = 0;
        constraintsDefault.insets = new Insets(0, 5, 0, 0);
        jPanelButtons.add(jLabelIcon, constraintsDefault);

        constraintsDefault = new GridBagConstraints();
        constraintsDefault.gridx = 0;
        constraintsDefault.gridy = 4;
        constraintsDefault.gridwidth = 3;
        constraintsDefault.fill = GridBagConstraints.HORIZONTAL;
        constraintsDefault.weightx = 0.25;
        constraintsDefault.insets = new Insets(0, 10, 10, 10);
        dialogSelectDefault.getContentPane().add(jPanelButtons, constraintsDefault);

         panelTilesetView = new PanelTilesetView(this, jLabelIcon);   
        
        dialogSelectDefault.pack();
    }
    
    
    public void initialize(boolean Visible,final AssetManager assetmanager,String userTilesetPathRelative, String userTilesetPathFull)
    {
        this.tilesetPathRelative = userTilesetPathRelative;
        this.assetmanager = assetmanager;
        dialogNewTileset.setVisible(true);
        jButtonBrowse.setActionCommand("BROWSE");
        jButtonBrowse.addActionListener(this);
        jButtonOK.setActionCommand("OK");
        jButtonOK.addActionListener(this);
        jButtonCancel.setActionCommand("CANCEL");
        jButtonCancel.addActionListener(this);
        
        jButtonOKView.setActionCommand("OK_View");
        jButtonOKView.addActionListener(this);
        jButtonCancelView.setActionCommand("CANCEL_View");
        jButtonCancelView.addActionListener(this);
        
        jButtonDefault.setActionCommand("SELECT");
        jButtonDefault.addActionListener(this);
        
        jButtonCalculate.setActionCommand("CALCULATE");
        jButtonCalculate.addActionListener(this);
        
        jTextFieldTileFrames.setEditable(false);
        jTextFieldTileRows.setEditable(false);
             
        this.tilesetPathFull = userTilesetPathFull;
        fc = new JFileChooser(tilesetPathFull);
    }
    public void run()
            {
                
                
                
            }
    public static boolean isNumeric(String string)
    {
      return string.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void actionPerformed(ActionEvent e)
    {
        if("CALCULATE".equals(e.getActionCommand()))
        {
            String parse = jTextFieldTilePixelSize.getText();
                int nPixels;
                String path;
                if(DEV_MODE)
                {
                     path = imageFile.getName();
                }
                else
                {
                    path = tilesetPathRelative + imageFile.getName();
                }
                if(isNumeric(parse))
                {
                    nPixels = Integer.parseInt(parse);
                    Texture texture = panelTilesetView.getTilesetTexture(assetmanager, path);
                    int i = texture.getImage().getWidth()/nPixels;
                    int j = texture.getImage().getHeight()/nPixels;
                    jTextFieldTileRows.setText(String.valueOf(i));
                    jTextFieldTileFrames.setText(String.valueOf(j));
                    jTextFieldTileFrames.setEditable(true);
                    jTextFieldTileRows.setEditable(true);
                }
        }
        if("SELECT".equals(e.getActionCommand()))
        {
            String path;
            if(DEV_MODE)
            {
                 path = imageFile.getName();
            }
            else
            {
                path = tilesetPathRelative + imageFile.getName();
            }
            
            
            TilesetData data  = new TilesetData(path,imageFile.getName(),jTextFieldTilesetName.getText(),jTextFieldTileRows.getText(),jTextFieldTileFrames.getText(),jTextFieldTilePixelSize.getText(),panelTilesetView.getFrame(),panelTilesetView.getRow());
            panelTilesetView.initializePallete(assetmanager, data);
            
            dialogSelectDefault.setMaximumSize(new Dimension(panelTilesetView.getWidth(), panelTilesetView.getHeight()));
            dialogSelectDefault.setPreferredSize(new Dimension(panelTilesetView.getWidth(), panelTilesetView.getHeight()));
            
            dialogSelectDefault.setVisible(true);
            jScrollPaneSelection.setViewportView(panelTilesetView.getParentPanel());
        }
        if("OK_View".equals(e.getActionCommand()))
        {
            jLabelTileImageIcon.setIcon(panelTilesetView.getImage());
            
            dialogSelectDefault.dispose();
        }
        if (e.getActionCommand().startsWith("TileButton"))
        {
            String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = test.split("_");
            System.out.println("Frame"+temp[1] + "Row"+temp[0]);
            int selectedFrame = Integer.parseInt(temp[1]);
            int selectedRow = Integer.parseInt(temp[0]);
            panelTilesetView.updateCurrentTileIcon(selectedRow,selectedFrame);


        }
        if("BROWSE".equals(e.getActionCommand()))
        {
            int returnVal = fc.showOpenDialog(dialogNewTileset);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                imageFile = fc.getSelectedFile();
                //This is where a real application would open the file.
                System.out.println("Opening: " + imageFile.getName());
                jTextFieldImagePath.setText(fc.getCurrentDirectory() +"\\"+ imageFile.getName());
                jTextFieldTileFrames.setEditable(false);
                jTextFieldTileRows.setEditable(false);
                    jTextFieldTileFrames.setText("0");
                    jTextFieldTileRows.setText("0");
            } else 
            {
                System.out.println("Open command cancelled by user.");
            }
        }

        if("OK".equals(e.getActionCommand()))
        {
            if(imageFile != null)
            {
                //String path = tilesetPath+"\\"+ imageFile.getName();
                String path;
                path = fc.getCurrentDirectory() +"\\" +imageFile.getName();
                //path = tilesetPathRelative + imageFile.getName();
                
                TilesetData data  = new TilesetData(path,imageFile.getName(),jTextFieldTilesetName.getText(),jTextFieldTileRows.getText(),jTextFieldTileFrames.getText(),jTextFieldTilePixelSize.getText(),panelTilesetView.getFrame(),panelTilesetView.getRow());
                File TilesetFile;
                if(DEV_MODE)
                {
                   TilesetFile = new File(tilesetPathFull+"\\"+jTextFieldTilesetName.getText()+".TileSet");

                }
                else
                {
                   TilesetFile = new File(tilesetPathFull+"\\Data\\"+jTextFieldTilesetName.getText()+".TileSet");

                }
                

                BinaryExporter exporter = BinaryExporter.getInstance();
                
                try {
                    //data.write(exporter);
                    exporter.save(data, TilesetFile);
                  } catch (IOException ex) {
                    Logger.getLogger(DialogNewTileset.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
                  }
                
                dialogNewTileset.dispose();
            }
        }
        if("CANCEL".equals(e.getActionCommand()))
        {
            dialogNewTileset.dispose();
        }
    }
}
