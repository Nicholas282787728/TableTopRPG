/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.JavaFX;

import TerrainEditor.CustomFiles.TilesetData;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

/**
 *
 * @author K
 */
public class PanelWest
{

    private JScrollPane scrollPane;
    private JPanel topPane;
    private JPanel bottomPane;
    private JPanel parentPanel;
    private SwingActionListener swingActionListener;
    private JSplitPane splitPane;
    private JLabel currentIcon;
    private BufferedImage tileImage;
    private TilesetData data;
    //private int pixelSize;
    private GridBagConstraints constraint;

    public PanelWest(SwingActionListener swingActionListener)
    {
        this.swingActionListener = swingActionListener;
        parentPanel = new JPanel(new GridLayout(0, 1, 10, 0));
        bottomPane = new JPanel();
        bottomPane.setLayout(new GridBagLayout());
        topPane = new JPanel();
        scrollPane = new JScrollPane(topPane);

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollPane, bottomPane);
        splitPane.setContinuousLayout(true);
        currentIcon = new JLabel();

        parentPanel.add(splitPane, BorderLayout.CENTER);
        constraint = new GridBagConstraints();
        
    }


    public JPanel getParentPanel()
    {
        return parentPanel;
    }

    public void initializePallete(BufferedImage image, TilesetData data)
    {
        tileImage = createFlipped(image);
        this.data = data;
        //pixelSize = tileImage.getWidth() / tileSize;
       
        topPane.removeAll();
        topPane.setLayout(new GridLayout(0, data.getFrames()));
        for (int i = 0; i < data.getFrames(); i++)
        {
            for (int j = 0; j < data.getRows(); j++)
            {
                ImageIcon icon = new ImageIcon(tileImage.getSubimage(j * data.getPixels(), i * data.getPixels(), data.getPixels(), data.getPixels()));
                JButton button = new JButton(icon);
                button.addActionListener(swingActionListener);
                button.setActionCommand("TileButton_" + j + "_" + (data.getFrames() - 1 - i));
                button.setPreferredSize(new Dimension(data.getPixels(), data.getPixels()));
                topPane.add(button);
            }

        }

        constraint.fill = GridBagConstraints.HORIZONTAL;
        constraint.gridx = 0;
        constraint.gridy = 0;
        bottomPane.add(new JLabel("Current Tile: "),constraint);
        constraint.gridx = 1;
        constraint.gridy = 0;
        bottomPane.add(currentIcon,constraint);
        parentPanel.revalidate();
    }

    public void updateCurrentTileIcon(int x, int y)
    {
        currentIcon.setIcon(new ImageIcon(tileImage.getSubimage(x * data.getPixels(), (data.getFrames()- 1 - y) * data.getPixels(), data.getRows(), data.getFrames())));
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
