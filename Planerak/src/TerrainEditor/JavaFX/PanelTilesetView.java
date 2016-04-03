/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import TerrainEditor.CustomFiles.TilesetData;
import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import jme3tools.converters.ImageToAwt;

/**
 *
 * @author K
 */
public class PanelTilesetView
{
    TilesetData data;
    private int frame;
    private int row;
    private Texture texture;
    private JPanel topPane;
    private JPanel parentPanel;
    
    private JLabel currentIcon;
    private BufferedImage tileImage;
    
    //private int pixelSize;
    private GridBagConstraints constraint;

    private ActionListener actionListener;
    
    private ImageIcon[][] imageIcons;
    
    public PanelTilesetView(ActionListener actionListener, JLabel currentIcon)
    {
        this.actionListener = actionListener;
        parentPanel = new JPanel(new GridLayout(0, 1, 10, 0));
        topPane = new JPanel();
        
        this.currentIcon = currentIcon;
        parentPanel.add(topPane, BorderLayout.CENTER);
        constraint = new GridBagConstraints();
        
    }


    public JPanel getParentPanel()
    {
        return parentPanel;
    }

    public void initializePallete(AssetManager assetManager, TilesetData data)
    {
        
        this.data = data;
        
        texture = assetManager.loadTexture(data.getImageLocation());
        
        BufferedImage texImage = ImageToAwt.convert(texture.getImage(), false, true, 0);
        tileImage = createFlipped(texImage);
        
        //pixelSize = tileImage.getWidth() / tileSize;
       
        topPane.removeAll();
        topPane.setLayout(new GridLayout(0, data.getFrames()));
        imageIcons = new ImageIcon[data.getRows()][data.getFrames()];
        
        for (int i = 0; i < data.getFrames(); i++)
        {
            for (int j = 0; j < data.getRows(); j++)
            {
                imageIcons[j][i] = new ImageIcon(tileImage.getSubimage(j * data.getPixels(), i * data.getPixels(), data.getPixels(), data.getPixels()));
                JButton button = new JButton(imageIcons[j][i]);
                button.addActionListener(actionListener);
                button.setActionCommand("TileButton_" + j + "_" + (data.getFrames() - 1 - i));
                button.setPreferredSize(new Dimension(data.getPixels(), data.getPixels()));
                topPane.add(button);
            }

        }

        parentPanel.revalidate();
    }

    public ImageIcon getImage()
    {
        return imageIcons[row][data.getFrames() - 1 - frame];
    }
    public int getRow()
    {
        return row;
    }
    public int getFrame()
    {
        return frame;
    }

    public void updateCurrentTileIcon(int row, int frame)
    {
        this.row = row;
        this.frame = frame;
        currentIcon.setIcon(imageIcons[row][data.getFrames() - 1 - frame]);
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
}
