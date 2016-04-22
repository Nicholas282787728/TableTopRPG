/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.Panels;

import com.jme3.texture.Texture;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import jme3tools.converters.ImageToAwt;

/**
 *
 * @author Jordan
 */
public class PanelTextureButton
{

    private ImageIcon[][] imageIcons;
    private int maxFrame;
    JPanel textureButtonPanel;

    public JPanel createDefaultSelectionPanel(Texture texture, ActionListener actionListener,String actionCommand, int pixels, int frame, int rows)
    {
        maxFrame = frame;
        textureButtonPanel = new JPanel();
        
        textureButtonPanel.setLayout(new GridLayout(0, frame));
        BufferedImage texImage = ImageToAwt.convert(texture.getImage(), false, true, 0);
        BufferedImage flippedImage = createFlipped(texImage);

        //pixelSize = flippedImage.getWidth() / tileSize;

        //textureButtonPanel.setPreferredSize(new Dimension(texture.getImage().getWidth(), texture.getImage().getHeight()));
        imageIcons = new ImageIcon[rows][frame];

        for (int i = 0; i < frame; i++)
        {
            for (int j = 0; j < rows; j++)
            {
                imageIcons[j][i] = new ImageIcon(flippedImage.getSubimage(j * pixels, i * pixels, pixels, pixels));
                JButton button = new JButton(imageIcons[j][i]);
                button.addActionListener(actionListener);
                button.setActionCommand(actionCommand + "_" + j + "_" + (frame - 1 - i));
                button.setPreferredSize(new Dimension(pixels, pixels));
                textureButtonPanel.add(button);
            }

        }

        textureButtonPanel.revalidate();
        
        return textureButtonPanel;
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
    
    public ImageIcon getImage(int row, int frame)
    {
        return imageIcons[row][maxFrame - 1 - frame];
    }
}
