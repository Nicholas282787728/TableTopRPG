/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.CustomFiles;

import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.Savable;
import java.io.IOException;
import javax.swing.ImageIcon;

/**
 *
 * @author K
 */
public class SwingIcon extends ImageIcon implements Savable 
{
    ImageIcon icon;
    public void write(JmeExporter ex) throws IOException
    {
      
    }

    public void read(JmeImporter im) throws IOException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
