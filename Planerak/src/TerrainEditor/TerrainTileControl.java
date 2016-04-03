/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.control.AbstractControl;

/**
 *
 * @author K
 */
public class TerrainTileControl extends AbstractControl
{

    Tile tile;

    /**
     *
     * @param tile
     */
    public TerrainTileControl(Tile tile)
    {
        this.tile = tile;
        setEnabled(false);
    }

    @Override
    protected void controlUpdate(float tpf)
    {
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp)
    {
    }
}
