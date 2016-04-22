/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.CustomFiles;

import com.jme3.export.InputCapsule;
import com.jme3.export.JmeExporter;
import com.jme3.export.JmeImporter;
import com.jme3.export.OutputCapsule;
import com.jme3.export.Savable;
import com.jme3.texture.Texture;
import java.io.IOException;
import javax.swing.ImageIcon;


/**
 *
 * @author K
 */
public class TilesetData implements Savable
{
    String imageLocation = "TempImage";
    String tilesetName = "TempTile";
    String imageName = "TempName";
    ImageIcon imageIcons[][] = null;
    ImageIcon defaultTile = null;
    Texture tilesetTexture = null;
    int rows = 0;
    int frames = 0;
    int pixels = 0;
    int defaultRow = 0;
    int defaultFrame = 0;

    public TilesetData()
    {
        
    }

    public TilesetData(String imageLocation,String imageName, String tilesetName, String rows, String frames, String pixels,int defaultFrame, int defaultRow)
    {
        
        this.imageLocation = imageLocation;
        this.imageName = imageName;
        this.tilesetName = tilesetName;
        this.rows = Integer.parseInt(rows);
        this.frames = Integer.parseInt(frames);
        this.pixels = Integer.parseInt(pixels);
        this.defaultFrame = defaultFrame;
        this.defaultRow = defaultRow;

    }

    
    public String getImageLocation()
    {
        return imageLocation;
    }
    public String getImageName()
    {
        return imageName;
    }
    public int getRows()
    {
        return rows;
    }
    public int getDefaultRow()
    {
        return defaultRow;
    }
    public int getFrames()
    {
        return frames;
    }
    public int getDefaultFrame()
    {
        return defaultFrame;
    }
    public int getPixels()
    {
        return pixels;
    }
    public String getName()
    {
        return tilesetName;
    }
    public void write(JmeExporter ex) throws IOException
    {
        OutputCapsule capsule = ex.getCapsule(this);
        capsule.write(imageLocation, "ImageLocation", "TempImage");
        capsule.write(imageName, "ImageName", "TempName");
        capsule.write(tilesetName, "TilesetName", "TempTile");
        capsule.write(rows, "Rows", 32);
        capsule.write(frames, "Frames", 32);
        capsule.write(pixels, "Pixels", 32);
        capsule.write(defaultFrame, "DefaultFrame", 0);
        capsule.write(defaultRow, "DefaultRow", 0);
    }

    public void read(JmeImporter im) throws IOException
    {
        InputCapsule capsule = im.getCapsule(this);
        imageLocation = capsule.readString("ImageLocation", "TempImage");
        imageName = capsule.readString("ImageName", "TempName");
        tilesetName = capsule.readString("TilesetName", "TempTile");
        rows = capsule.readInt("Rows", 32);
        frames = capsule.readInt("Frames", 32);
        pixels = capsule.readInt("Pixels", 32);
        defaultFrame = capsule.readInt("DefaultFrame", 0);
        defaultRow = capsule.readInt("DefaultRow", 0);
        
    }


    
          
}
