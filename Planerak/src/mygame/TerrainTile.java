/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;
import java.nio.FloatBuffer;

/**
 *
 * @author K
 */
public class TerrainTile extends AbstractControl
{

    @Override
    protected void controlUpdate(float tpf)
    {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
    }

    private enum ResetLocation
    {

        Start, End
    };
    private Texture baseTexture;
    private AssetManager assetManager;
    private Material baseMaterial;
    private Geometry baseGeometry;
    private float[][] texCoordPerRow;
    private float[][] baseTexCoordPerRow;
    private VertexBuffer s_uvTexture;
    private float depth;
    private float stride;
    private int currentFrame;
    private int currentRow;
    private int[] framesPerRow;
    private int[] minimumFramesPerRow;
    private Node baseNode;
    private int maxFrame;

    TerrainTile(int[] framesPerRow, String name, String imageLocation, AssetManager assetManager)
    {
        this.minimumFramesPerRow = new int[framesPerRow.length];
        this.assetManager = assetManager;
        baseTexture = assetManager.loadTexture(imageLocation);
        this.framesPerRow = framesPerRow;
        findMaxFrames(framesPerRow);
        int width = baseTexture.getImage().getWidth() / maxFrame;
        int height = baseTexture.getImage().getHeight() / framesPerRow.length;

        //Create Quad based on Image Width and Height
        //framesPerRow.length is the total number of rows that exist within the image. framesPerRow[0] is the number of frames in that row
        Quad s_quad = new Quad(width, height);
        //Create the Material that will display the texture.
        baseMaterial = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        baseMaterial.setTexture("ColorMap", baseTexture);
        //Create a new Geormetry object that can be displayed in the jmonkey scene graph.
        baseGeometry = new Geometry(name, s_quad);
        //Assign material to Geometry.
        baseGeometry.setMaterial(baseMaterial);
        baseMaterial.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);

        initializeTextureCoordinates();

        baseNode = new Node(name);
        baseNode.attachChild(baseGeometry);
        baseNode.addControl(this);
    }

    private void findMaxFrames(int[] framesPerRow)
    {
        int tempMaxFrame = 0;
        for (int i = 0; i < framesPerRow.length; i++)
        {
            if (framesPerRow[i] > tempMaxFrame)
            {
                tempMaxFrame = framesPerRow[i];
            }
        }
        maxFrame = tempMaxFrame;

    }

    private void initializeTextureCoordinates()
    {
        //get the texture coordinates from the vertex buffer.
        s_uvTexture = baseGeometry.getMesh().getBuffer(VertexBuffer.Type.TexCoord);
        texCoordPerRow = new float[framesPerRow.length][];
        for (int i = 0; i < framesPerRow.length; i++)
        {
            texCoordPerRow[i] = BufferUtils.getFloatArray((FloatBuffer) s_uvTexture.getData());
            texCoordPerRow[i][0] /= maxFrame;
            texCoordPerRow[i][2] /= maxFrame;
            texCoordPerRow[i][4] /= maxFrame;
            texCoordPerRow[i][6] /= maxFrame;
            texCoordPerRow[i][1] /= framesPerRow.length;
            texCoordPerRow[i][3] /= framesPerRow.length;
            texCoordPerRow[i][5] /= framesPerRow.length;
            texCoordPerRow[i][7] /= framesPerRow.length;
            /*
             for (int j = 0; j < texCoordPerRow[i].length; j++) {
             switch (j) {
             case 0:
             case 2:
             case 4:
             case 6:
             texCoordPerRow[i][j] /= maxFrame;
             break;
             case 1:
             case 3:
             case 5:
             case 7:
             texCoordPerRow[i][j] /= framesPerRow.length;
             break;
             }
             }*/
            if (i == 0)
            {
                stride = texCoordPerRow[i][4];
                depth = texCoordPerRow[i][7];
            }

            texCoordPerRow[i][1] += depth * i;
            texCoordPerRow[i][3] += depth * i;
            texCoordPerRow[i][5] += depth * i;
            texCoordPerRow[i][7] += depth * i;

        }
        baseTexCoordPerRow = new float[texCoordPerRow.length][texCoordPerRow[0].length];

        for (int k = 0; k < texCoordPerRow.length; k++)
        {
            System.arraycopy(texCoordPerRow[k], 0, baseTexCoordPerRow[k], 0, texCoordPerRow[0].length);
        }
        s_uvTexture.updateData(BufferUtils.createFloatBuffer(texCoordPerRow[currentRow]));
    }

    public void nextFrame()
    {
        if (currentFrame < (framesPerRow[currentRow]))
        {


            texCoordPerRow[currentRow][0] += stride;
            texCoordPerRow[currentRow][2] += stride;
            texCoordPerRow[currentRow][4] += stride;
            texCoordPerRow[currentRow][6] += stride;
            s_uvTexture.updateData(BufferUtils.createFloatBuffer(texCoordPerRow[currentRow]));
            currentFrame += 1;

        }
        if (currentFrame == (framesPerRow[currentRow]))
        {

            resetCoordinates(ResetLocation.Start);
        }
    }

    public void previousFrame()
    {
        if (currentFrame >= minimumFramesPerRow[currentRow])
        {

            texCoordPerRow[currentRow][0] -= stride;
            texCoordPerRow[currentRow][2] -= stride;
            texCoordPerRow[currentRow][4] -= stride;
            texCoordPerRow[currentRow][6] -= stride;
            s_uvTexture.updateData(BufferUtils.createFloatBuffer(texCoordPerRow[currentRow]));
            currentFrame -= 1;
        }
        if (currentFrame < minimumFramesPerRow[currentRow])
        {
            resetCoordinates(ResetLocation.End);
        }
    }

    private void resetCoordinates(ResetLocation reset)
    {
        if (reset == ResetLocation.Start)
        {
            texCoordPerRow[currentRow][0] = baseTexCoordPerRow[currentRow][0];
            texCoordPerRow[currentRow][2] = baseTexCoordPerRow[currentRow][2];
            texCoordPerRow[currentRow][4] = baseTexCoordPerRow[currentRow][4];
            texCoordPerRow[currentRow][6] = baseTexCoordPerRow[currentRow][6];

            currentFrame = minimumFramesPerRow[currentRow];
        } else
        {

            texCoordPerRow[currentRow][0] = baseTexCoordPerRow[currentRow][0];
            texCoordPerRow[currentRow][2] = baseTexCoordPerRow[currentRow][2];
            texCoordPerRow[currentRow][4] = baseTexCoordPerRow[currentRow][4];
            texCoordPerRow[currentRow][6] = baseTexCoordPerRow[currentRow][6];
            //For some reason a rounding error occurs if the values are not added together. Maybe im missing something
            for (int i = minimumFramesPerRow[currentRow]; i < framesPerRow[currentRow] - 1; i++)
            {
                texCoordPerRow[currentRow][0] += stride;
                texCoordPerRow[currentRow][2] += stride;
                texCoordPerRow[currentRow][4] += stride;
                texCoordPerRow[currentRow][6] += stride;
            }
            currentFrame = framesPerRow[currentRow] - 1;
        }
        s_uvTexture.updateData(BufferUtils.createFloatBuffer(texCoordPerRow[currentRow]));
    }

    public void setFrame(int row, int frame)
    {
        texCoordPerRow[row][0] = baseTexCoordPerRow[row][0];
        texCoordPerRow[row][2] = baseTexCoordPerRow[row][2];
        texCoordPerRow[row][4] = baseTexCoordPerRow[row][4];
        texCoordPerRow[row][6] = baseTexCoordPerRow[row][6];
        //For some reason a rounding error occurs if the values are not added together. Maybe im missing something
        for (int i = 0; i < frame - 1; i++)
        {
            texCoordPerRow[row][0] += stride;
            texCoordPerRow[row][2] += stride;
            texCoordPerRow[row][4] += stride;
            texCoordPerRow[row][6] += stride;
        }
        currentFrame = frame - 1;
        s_uvTexture.updateData(BufferUtils.createFloatBuffer(texCoordPerRow[row]));
    }

    public Node getNode()
    {
        return baseNode;
    }

    public void setRow(int row)
    {
        this.currentRow = row;
        //resetCoordinates(ResetLocation.Start);
    }

    public void changeRow()
    {
        if (this.currentRow + 1 < framesPerRow.length)
        {
            this.currentRow++;
        } else
        {
            this.currentRow = 0;
        }
        System.out.print(currentRow);

    }

    public void changeBaseTexture(String imageLocation)
    {
        baseTexture = assetManager.loadTexture(imageLocation);
        baseMaterial.setTexture("ColorMap", baseTexture);
        baseGeometry.setMaterial(baseMaterial);
    }

    public void move(int x, int y)
    {
        baseNode.setLocalTranslation(baseNode.getLocalTranslation().x + x, baseNode.getLocalTranslation().y + y, baseNode.getLocalTranslation().z);
    }

    public void moveSmooth(int x, int y, float tpf)
    {


        baseNode.setLocalTranslation(baseNode.getLocalTranslation().x + x, baseNode.getLocalTranslation().y + y, baseNode.getLocalTranslation().z);
    }

    public void moveAbsolute(int x, int y)
    {
        baseNode.setLocalTranslation(x, y, baseNode.getLocalTranslation().z);
    }

    public void setMinFrame(int row, int min)
    {
        minimumFramesPerRow[row] = min;
        for (int i = 0; i < min; i++)
        {
            baseTexCoordPerRow[row][0] += stride;
            baseTexCoordPerRow[row][2] += stride;
            baseTexCoordPerRow[row][4] += stride;
            baseTexCoordPerRow[row][6] += stride;
        }
    }

    public void setFrame(int frame)
    {
        currentFrame = frame;
    }
}
