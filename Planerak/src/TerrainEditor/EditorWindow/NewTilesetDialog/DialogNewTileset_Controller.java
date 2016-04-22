/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewTilesetDialog;

import TerrainEditor.CustomFiles.TilesetData;
import TerrainEditor.JavaFX.DialogNewTileset;
import static TerrainEditor.JavaFX.DialogNewTileset.isNumeric;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.export.binary.BinaryExporter;
import com.jme3.texture.Texture;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Jordan
 */
public class DialogNewTileset_Controller implements ActionListener
{

    DialogNewTileset_Model tileset_Model;
    DialogNewTileset_View tileset_View;
    PanelNewTilesetSelection_View selection_View;
    String commandBrowse = "Browse";
    String commandSaveTileset = "SaveTileset";
    String commandCloseSelection= "CloseSelection";
    String commandCancelTileset = "CancelTileset";
    String commandSelectTile = "SelectTile";
    String commandCalculateImage = "CalculateImage";
    String commandTileSelectButtons = "Tile";
    

    public DialogNewTileset_Controller(DialogNewTileset_Model tileset_Model, DialogNewTileset_View tileset_View)
    {
        this.tileset_Model = tileset_Model;
        this.tileset_View = tileset_View;
        setupInputCommands();
    }

    private void setupInputCommands()
    {
        tileset_View.getjButtonBrowse().setActionCommand(commandBrowse);
        tileset_View.getjButtonBrowse().addActionListener(this);

        tileset_View.getjButtonOK().setActionCommand(commandSaveTileset);
        tileset_View.getjButtonOK().addActionListener(this);
        tileset_View.getjButtonCancel().setActionCommand(commandCancelTileset);
        tileset_View.getjButtonCancel().addActionListener(this);



        tileset_View.getjButtonDefault().setActionCommand(commandSelectTile);
        tileset_View.getjButtonDefault().addActionListener(this);

        tileset_View.getjButtonCalculate().setActionCommand(commandCalculateImage);
        tileset_View.getjButtonCalculate().addActionListener(this);
        tileset_View.getjTextFieldTileFrames().setEditable(false);
        tileset_View.getjTextFieldTileRows().setEditable(false);

        File path = new File(tileset_Model.getTilesetPathFull());
        tileset_View.getjFileChooserTextureLocation().setCurrentDirectory(path);

    }

    private boolean isNumeric(String string)
    {
        return string.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public void actionPerformed(ActionEvent e)
    {
        if (commandCalculateImage.equals(e.getActionCommand()))
        {
            calculatePixels();
        }
        if (commandSelectTile.equals(e.getActionCommand()))
        {

            selection_View = new PanelNewTilesetSelection_View(tileset_View.getJDialogNewTileset(),
                                                                                   tileset_Model.getTilesetTexture().getImage().getWidth(),
                                                                                   tileset_Model.getTilesetTexture().getImage().getHeight(),
                                                                                   tileset_Model.getTilesetTexture(),
                                                                                   this,
                                                                                   commandTileSelectButtons,
                                                                                   tileset_Model.getPixels(),
                                                                                   tileset_Model.getFrames(),
                                                                                   tileset_Model.getRows());
            selection_View.getjButtonOKView().setActionCommand(commandCloseSelection);
            selection_View.getjButtonOKView().addActionListener(this);
            

        }
        if (commandCloseSelection.equals(e.getActionCommand()))
        {
            selection_View.getDialog().dispose();
            //tileset_View.getjLabelTileImageIcon().setIcon(panelTilesetView.getImage());
            //dialogSelectDefault.dispose();
        }
        if (e.getActionCommand().startsWith(commandTileSelectButtons))
        {
            //String test = e.getActionCommand().substring(11, e.getActionCommand().length());
            String[] temp = e.getActionCommand().split("_");
            System.out.println("Frame" + temp[2] + "Row" + temp[1]);
            int selectedFrame = Integer.parseInt(temp[2]);
            int selectedRow = Integer.parseInt(temp[1]);
            
            tileset_Model.setDefaultFrame(selectedFrame);
            tileset_Model.setDefaultRow(selectedRow);
            
            tileset_View.getjLabelTileImageIcon().setIcon(selection_View.getTextureButtonPanel(selectedRow, selectedFrame));
            selection_View.getCurrentIcon().setIcon(selection_View.getTextureButtonPanel(selectedRow, selectedFrame));
            // panelTilesetView.updateCurrentTileIcon(selectedRow,selectedFrame);


        }
        if (commandBrowse.equals(e.getActionCommand()))
        {
            int returnVal = tileset_View.getjFileChooserTextureLocation().showOpenDialog(tileset_View.getJDialogNewTileset());

            if (returnVal == JFileChooser.APPROVE_OPTION)
            {
                tileset_Model.setImageFile(tileset_View.getjFileChooserTextureLocation().getSelectedFile());

                System.out.println("Opening: " + tileset_Model.getImageFile().getName());
                tileset_View.getjTextFieldImagePath().setText(tileset_View.getjFileChooserTextureLocation().getCurrentDirectory() + "\\" + tileset_Model.getImageFile().getName());
                calculatePixels();
                tileset_View.getjButtonOK().setEnabled(true);
            }
            else
            {
                System.out.println("Open command cancelled by user.");
            }
        }

        if (commandSaveTileset.equals(e.getActionCommand()))
        {
            if (tileset_Model.getImageFile() != null)
            {
                if(tileset_View.getjTextFieldTilesetName().getText().isEmpty() == false)
                {
                    //String path = tilesetPath+"\\"+ imageFile.getName();
                
                //String path = tileset_View.getjFileChooserTextureLocation().getCurrentDirectory() + "\\" + tileset_Model.getImageFile().getName();
                //path = tilesetPathRelative + imageFile.getName();

                // TilesetData data  = new TilesetData(path,tileset_Model.getImageFile().getName(),tileset_View.getjTextFieldTilesetName().getText(),tileset_View.getjTextFieldTileRows().getText(),tileset_View.getjTextFieldTileFrames().getText(),tileset_View.getjTextFieldTilePixelSize().getText(),panelTilesetView.getFrame(),panelTilesetView.getRow());
                File TilesetFile;
                if (DEV_MODE)
                {
                    TilesetFile = new File(tileset_Model.getTilesetPathFull() + "\\" + tileset_View.getjTextFieldTilesetName().getText() + ".TileSet");

                }
                else
                {
                    TilesetFile = new File(tileset_Model.getTilesetPathFull() + "\\Data\\" + tileset_View.getjTextFieldTilesetName().getText() + ".TileSet");

                }


                BinaryExporter exporter = BinaryExporter.getInstance();

                try
                {
                 String path;
                        if (DEV_MODE)
                        {
                            path = tileset_Model.getImageFile().getName();
                        }
                        else
                        {
                            path = tileset_Model.getTilesetPathRelative() + tileset_Model.getImageFile().getName();
                        }
                    //data.write(exporter);
                    TilesetData data = new TilesetData(path, tileset_Model.getImageFile().getName(),
                            tileset_View.getjTextFieldTilesetName().getText(),
                            tileset_View.getjTextFieldTileRows().getText(),
                            tileset_View.getjTextFieldTileFrames().getText(), 
                            tileset_View.getjTextFieldTilePixelSize().getText(), 
                            tileset_Model.getDefaultFrame(), 
                            tileset_Model.getDefaultRow());
                    exporter.save(data, TilesetFile);
                }
                catch (IOException ex)
                {
                    Logger.getLogger(DialogNewTileset.class.getName()).log(Level.SEVERE, "Error: Failed to save game!", ex);
                }

                 tileset_View.getJDialogNewTileset().dispose();
                }
                else
                {
                    System.err.println("TilesetName Empty");
                }
                
            }
        }
        if (commandCancelTileset.equals(e.getActionCommand()))
        {
            tileset_View.getJDialogNewTileset().dispose();
        }
    }

    private void calculatePixels()
    {
        String parse = tileset_View.getjTextFieldTilePixelSize().getText();
        int nPixels;
        String path;
        if (DEV_MODE)
        {
            path = tileset_Model.getImageFile().getName();
        }
        else
        {
            path = tileset_Model.getTilesetPathRelative() + tileset_Model.getImageFile().getName();
        }
        if (isNumeric(parse))
        {
            nPixels = Integer.parseInt(parse);

            Texture texture = tileset_Model.loadTilesetTexture(path);

            int i = texture.getImage().getWidth() / nPixels;
            int j = texture.getImage().getHeight() / nPixels;
            tileset_View.getjTextFieldTileRows().setText(String.valueOf(i));
            tileset_View.getjTextFieldTileFrames().setText(String.valueOf(j));
            tileset_View.getjTextFieldTileFrames().setEditable(true);
            tileset_View.getjTextFieldTileRows().setEditable(true);
            tileset_Model.setPixels(nPixels);
            tileset_Model.setFrames(j);
            tileset_Model.setRows(i);

        }
    }
}
