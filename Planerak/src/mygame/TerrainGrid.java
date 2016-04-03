/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import java.util.Random;

/**
 *
 * @author K
 */
public class TerrainGrid
{

    TerrainTile[][] level0;
    private Node centerNode;

    TerrainGrid(int height, int width, String name, AssetManager assetManager)
    {
        level0 = new TerrainTile[height][width];
        centerNode = new Node(name);
        int array[] = new int[32];
        for (int i = 0; i < 32; i++)
        {
            array[i] = 32;
        }

        for (int i = 0; i < height; i++)
        {
            for (int k = 0; k < width; k++)
            {
                //level0[i][k]= new TerrainTile(array, name+"_"+i+"_"+k, "Textures/LPC Terrain/terrain_atlas.png", assetManager);
                level0[i][k] = new TerrainTile(array, name + "_" + i + "_" + k, "Textures/LPC Terrain/terrain_atlas.png", assetManager);
                level0[i][k].move(32 * i, 32 * k);
                level0[i][k].nextFrame();
                //level0[i][k].setRow(28);
                Random rn = new Random();

                level0[i][k].setFrame(28, 20);
                centerNode.attachChild(level0[i][k].getNode());
            }
        }
    }

    public Node getNode()
    {
        return centerNode;
    }

    public void move(int x, int y)
    {
        centerNode.setLocalTranslation(centerNode.getLocalTranslation().x + x, centerNode.getLocalTranslation().y + y, centerNode.getLocalTranslation().z);
    }

    public void moveAbsolute(int x, int y)
    {
        centerNode.setLocalTranslation(x, y, centerNode.getLocalTranslation().z);
    }
}
