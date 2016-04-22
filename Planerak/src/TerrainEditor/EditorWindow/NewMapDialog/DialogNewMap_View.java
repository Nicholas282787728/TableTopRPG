/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewMapDialog;

import TerrainEditor.JavaFX.SwingActionListener;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static TerrainEditor.TileEditor.DEV_MODE;
import com.jme3.app.SimpleApplication;
/**
 *
 * @author K
 */
public class DialogNewMap_View
{

    
    private JComboBox sizeCombo = null;
    private JComboBox tilesetCombo = null;
    private JDialog DialogNew_Map;
    JButton okButton;
    JButton cancelButton;
    

    public DialogNewMap_View(JFrame jFrame)
    {
        
        DialogNew_Map = new JDialog(jFrame, "New Map");
        DialogNew_Map.setResizable(false);
        DialogNew_Map.setPreferredSize(new Dimension(225, 150));

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

        
        tilesetCombo = new JComboBox(getComboBox());
        
        constraintTopRow.gridx = 1;
        topRow.add(tilesetCombo, constraintTopRow);

        JLabel sizeLabel = new JLabel("Size: ");
        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 1;
        topRow.add(sizeLabel, constraintTopRow);

        sizeCombo = new JComboBox(new String[]
        {
            "8 x 8", "16 x 16", "32 x 32", "64 x 64", "128 x 128"
        });

        constraintTopRow.gridx = 1;
        topRow.add(sizeCombo, constraintTopRow);

        okButton = new JButton("OK");
        

        constraintTopRow.gridx = 0;
        constraintTopRow.gridy = 2;
        topRow.add(okButton, constraintTopRow);

        cancelButton = new JButton("CANCEL");

        constraintTopRow.gridx = 1;
        constraintTopRow.gridy = 2;
        topRow.add(cancelButton, constraintTopRow);


        dialogPanel.add(topRow);
        DialogNew_Map.add(dialogPanel);
        //dialog.setUndecorated(true);
        DialogNew_Map.pack();
        DialogNew_Map.setVisible(true);
    }

    private DefaultComboBoxModel getComboBox()
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

    public JComboBox getSizeComboBox()
    {
        return sizeCombo;
    }
    public int getSizeIndex()
    {
        return sizeCombo.getSelectedIndex();
    }
    public String getComboBoxName()
    {
        return tilesetCombo.getModel().getSelectedItem().toString();
    }
    public JButton getOKButton()
    {
        return okButton;
    }
    public JButton getCancelButton()
    {
        return cancelButton;
    }
    public void setVisible(boolean value)
    {
        DialogNew_Map.setVisible(value);
    }
}
