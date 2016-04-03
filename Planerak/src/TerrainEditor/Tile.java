/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor;

import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.util.BufferUtils;
import java.nio.FloatBuffer;

/**
 *
 * @author K
 */
public class Tile
{

    private int maxRow;
    private int maxFrame;
    private int currentRow;
    private int currentFrame;
    private int[] firstFrame;
    private int[] lastFrame;
    private Texture texture;
    private Material material;
    private Geometry geometry;
    private Node tileNode;
    private VertexBuffer vertexBuffer;
    private float[][] textureCoordinates;
    private float[][] stride;
    private float depth;

    /**
     *
     * @param material
     * @param texture
     * @param name
     * @param maxRow
     * @param maxFrame
     */
    Tile(Material material, Texture texture, String name, int maxRow, int maxFrame)
    {

        this.firstFrame = new int[maxRow];
        this.texture = texture;
        this.maxRow = maxRow;
        this.maxFrame = maxFrame;
        //set to 4 because index 1 3 5 7 need to be changed and have different values
        stride = new float[maxFrame][4];

        int width = texture.getImage().getWidth() / maxFrame;
        int height = texture.getImage().getHeight() / maxRow;

        //Create Quad based on Image Width and Height
        //framesPerRow.length is the total number of rows that exist within the image. framesPerRow[0] is the number of frames in that row
        Quad s_quad = new Quad(width, height);
        //Create the Material that will display the texture.
        this.material = material;
        this.material.setTexture("ColorMap", texture);
        //Create a new Geormetry object that can be displayed in the jmonkey scene graph.
        geometry = new Geometry(name, s_quad);
        //Assign material to Geometry.
        geometry.setMaterial(this.material);
        this.material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);


        tileNode = new Node(name);
        tileNode.attachChild(geometry);
        tileNode.addControl(new TerrainTileControl(this));
    }

    /**
     *
     */
    public void initializeTextureCoordinates()
    {
        //get the texture coordinates from the vertex buffer.
        vertexBuffer = geometry.getMesh().getBuffer(VertexBuffer.Type.TexCoord);
        textureCoordinates = new float[maxRow][];
        float localStride = 0f;

        float cumulativeStride = 0f;
        for (int i = 0; i < maxRow; i++)
        {
            textureCoordinates[i] = BufferUtils.getFloatArray((FloatBuffer) vertexBuffer.getData());
            textureCoordinates[i][0] /= maxFrame;
            textureCoordinates[i][2] /= maxFrame;
            textureCoordinates[i][4] /= maxFrame;
            textureCoordinates[i][6] /= maxFrame;
            textureCoordinates[i][1] /= maxRow;
            textureCoordinates[i][3] /= maxRow;
            textureCoordinates[i][5] /= maxRow;
            textureCoordinates[i][7] /= maxRow;


            if (i == 0)
            {
                localStride = textureCoordinates[i][4];
                depth = textureCoordinates[i][7];
            }

            textureCoordinates[i][1] += depth * i;
            textureCoordinates[i][3] += depth * i;
            textureCoordinates[i][5] += depth * i;
            textureCoordinates[i][7] += depth * i;


        }
        for (int i = 0; i < maxFrame; i++)
        {

            stride[i][0] = cumulativeStride + textureCoordinates[0][0];
            stride[i][1] = cumulativeStride + textureCoordinates[0][2];
            stride[i][2] = cumulativeStride + textureCoordinates[0][4];
            stride[i][3] = cumulativeStride + textureCoordinates[0][6];
            cumulativeStride += localStride;

        }
        vertexBuffer.updateData(BufferUtils.createFloatBuffer(textureCoordinates[currentRow]));
    }

    /**
     *
     * @return
     */
    public Node getNode()
    {
        return tileNode;
    }

    /**
     *
     * @param x
     * @param y
     */
    public void move(int x, int y)
    {
        tileNode.setLocalTranslation(tileNode.getLocalTranslation().x + x, tileNode.getLocalTranslation().y + y, tileNode.getLocalTranslation().z);
    }

    /**
     *
     * @param frame
     * @param row
     */
    public void setFrame(int frame, int row)
    {
        textureCoordinates[row][0] = stride[frame][0];
        textureCoordinates[row][2] = stride[frame][1];
        textureCoordinates[row][4] = stride[frame][2];
        textureCoordinates[row][6] = stride[frame][3];

        currentFrame = frame;
        currentRow = row;
        vertexBuffer.updateData(BufferUtils.createFloatBuffer(textureCoordinates[row]));
    }

    /**
     *
     */
    public void nextFrame()
    {
        currentFrame += 1;
        if (currentFrame >= maxFrame)
        {
            currentFrame = 0;
        }
        textureCoordinates[currentRow][0] = stride[currentFrame][0];
        textureCoordinates[currentRow][2] = stride[currentFrame][1];
        textureCoordinates[currentRow][4] = stride[currentFrame][2];
        textureCoordinates[currentRow][6] = stride[currentFrame][3];
        vertexBuffer.updateData(BufferUtils.createFloatBuffer(textureCoordinates[currentRow]));
    }

    /**
     *
     */
    public void nextRow()
    {
        currentRow += 1;
        if (currentRow >= maxRow)
        {
            currentRow = 0;
        }
        textureCoordinates[currentRow][0] = stride[currentFrame][0];
        textureCoordinates[currentRow][2] = stride[currentFrame][1];
        textureCoordinates[currentRow][4] = stride[currentFrame][2];
        textureCoordinates[currentRow][6] = stride[currentFrame][3];
        vertexBuffer.updateData(BufferUtils.createFloatBuffer(textureCoordinates[currentRow]));
    }

    /**
     *
     * @param texture
     */
    public void changeBaseTexture(Texture texture)
    {
        this.texture = texture;
        material.setTexture("ColorMap", texture);
        geometry.setMaterial(material);
    }

    /**
     *
     * @return
     */
    public Texture getTexture()
    {
        return texture;
    }
}
