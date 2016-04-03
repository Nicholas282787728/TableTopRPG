/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import TerrainEditor.CustomFiles.TilesetData;
import com.jme3.asset.AssetManager;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.FilenameFilter;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

/**
 *
 * @author K
 */
public class DialogViewTileset
{
    private JDialog dialog;
    private JButton jButtonCancel;
    private JButton jButtonEdit;
    private JButton jButtonPreview;
    private JButton jButtonSelect;
    private JList jListTilesets;
    private JPanel jPanelButtons;
    private JPanel jPanelParent;
    private JScrollPane jScrollPaneTilesets;
    
    public DialogViewTileset(JFrame frame)
    {
        GridBagConstraints gridBagConstraints;
        dialog = new JDialog(frame,"New Tileset");
        
        jPanelParent = new JPanel();
        jScrollPaneTilesets = new JScrollPane();
        jListTilesets = new JList();
        jPanelButtons = new JPanel();
        jButtonSelect = new JButton();
        jButtonCancel = new JButton();
        jButtonPreview = new JButton();
        jButtonEdit = new JButton();

        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setTitle("View Tilesets");
        dialog.setResizable(false);
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.LINE_AXIS));

        jPanelParent.setMinimumSize(new Dimension(400, 300));
        jPanelParent.setName(""); // NOI18N
        jPanelParent.setPreferredSize(new Dimension(400, 300));
        jPanelParent.setLayout(new GridBagLayout());

        // your directory

            File f = new File(System.getProperty("user.home") + "\\Planerrak\\Tilesets\\Data");
            
            
            File[] matchingFiles = f.listFiles();
            DefaultListModel model = new DefaultListModel();
            
            for(int j=0; j < matchingFiles.length; j++)
            {
                model.add(j, matchingFiles[j].getName());
            }
           //TilesetData data = (TilesetData) assetManager.loadAsset("Tilesets\\Data\\jTextField4.TileSet");
            
            
        jListTilesets.setModel(model);
        

        
        jListTilesets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListTilesets.setToolTipText("");
        jScrollPaneTilesets.setViewportView(jListTilesets);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 0);
        jPanelParent.add(jScrollPaneTilesets, gridBagConstraints);
        jScrollPaneTilesets.getAccessibleContext().setAccessibleName("");

        jPanelButtons.setLayout(new GridBagLayout());

        jButtonSelect.setText("Select");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        jPanelButtons.add(jButtonSelect, gridBagConstraints);

        jButtonCancel.setText("Cancel");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 0, 10, 0);
        jPanelButtons.add(jButtonCancel, gridBagConstraints);

        jButtonPreview.setText("Preview");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 0, 10, 0);
        jPanelButtons.add(jButtonPreview, gridBagConstraints);

        jButtonEdit.setText("Edit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        jPanelButtons.add(jButtonEdit, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.25;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        jPanelParent.add(jPanelButtons, gridBagConstraints);
        jPanelButtons.getAccessibleContext().setAccessibleName("");

        dialog.getContentPane().add(jPanelParent);
        jPanelParent.getAccessibleContext().setAccessibleDescription("");

        dialog.pack();
        dialog.setVisible(true);
    }
    
}
