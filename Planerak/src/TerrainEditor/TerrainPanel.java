/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor;

import TerrainEditor.CustomFiles.TilesetData;
import com.jme3.asset.AssetManager;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.material.Material;
import com.jme3.math.Ray;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.texture.Texture;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import jme3tools.converters.ImageToAwt;

/**
 *
 * @author K
 */
public class TerrainPanel
{

    Node terrainNode;
    Texture texture;

    /**
     *
     * @param width
     * @param height
     * @param textureRows
     * @param textureFrames
     * @param name
     * @param assetManager
     * @param fileLocation
     */
    public TerrainPanel(int width, int height, int textureRows, int textureFrames, String name, AssetManager assetManager, String fileLocation)
    {
        texture = assetManager.loadTexture(fileLocation);
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        terrainNode = new Node(name);
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                Tile tile = new Tile(material, texture, "Tile_" + i + "_" + j, textureRows, textureFrames);
                tile.initializeTextureCoordinates();
                tile.move(textureFrames * i, textureRows * j);
                terrainNode.attachChild(tile.getNode());


            }
        }
    }

    public TerrainPanel(int x_Tiles, int y_Tiles, String panel, AssetManager assetManager, TilesetData data)
    {
        texture = assetManager.loadTexture(data.getImageLocation());
        Material material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        terrainNode = new Node(data.getName());
        for (int i = 0; i < x_Tiles; i++)
        {
            for (int j = 0; j < y_Tiles; j++)
            {
                Tile tile = new Tile(material, texture, "Tile_" + i + "_" + j, data.getRows(), data.getFrames());
                tile.initializeTextureCoordinates();
                tile.move(data.getPixels() * i, data.getPixels() * j);
                System.out.println(data.getDefaultFrame()+" " + data.getDefaultRow());
                tile.setFrame(data.getDefaultFrame(), data.getDefaultRow());
                terrainNode.attachChild(tile.getNode());


            }
        }
    }

    /**
     *
     * @return
     */
    public Node getNode()
    {
        return terrainNode;
    }

    /**
     *
     * @param inputManager
     */
    public void selectTile(InputManager inputManager, int row, int frame)
    {
        // 1. Reset results list.
        CollisionResults results = new CollisionResults();
        // 2. Aim the ray from cam loc to cam direction.

        Ray mouseRay = new Ray(new Vector3f(inputManager.getCursorPosition().x, inputManager.getCursorPosition().y, 1), new Vector3f(0, 0, -1));

        // 3. Collect intersections between Ray and Shootables in results list.

        terrainNode.collideWith(mouseRay, results);

        // 4. Print the results

        for (int i = 0; i < results.size(); i++)
        {
            // For each hit, we know distance, impact point, name of geometry.

            String hit = results.getCollision(i).getGeometry().getName();

            if (terrainNode.getChild(hit).getControl(TerrainTileControl.class) != null)
            {
                TerrainTileControl te = terrainNode.getChild(hit).getControl(TerrainTileControl.class);
                te.tile.setFrame(row, frame);
            }

        }
    }

    /**
     *
     * @param x
     * @param y
     */
    public void move(int x, int y)
    {
        terrainNode.setLocalTranslation(terrainNode.getLocalTranslation().x + x, terrainNode.getLocalTranslation().y + y, terrainNode.getLocalTranslation().z);
    }

    public BufferedImage getImage()
    {

        BufferedImage texImage = ImageToAwt.convert(texture.getImage(), false, true, 0);

        return texImage;
    }
}
