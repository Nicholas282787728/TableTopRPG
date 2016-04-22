/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TerrainEditor.EditorWindow.NewMapDialog;



import TerrainEditor.EditorWindow.EditorWindow_Controller;
import java.awt.event.ActionEvent;

/**
 *
 * @author Jordan
 */
public class DialogNewMap_Controller implements java.awt.event.ActionListener
                                               
{
    private DialogNewMap_Model model;
    private DialogNewMap_View view;
    private EditorWindow_Controller control;
    private String commandCancel = "CancelMap";
    private String commandOK = "CreateMap";
    private String commandSize = "ChangeSize";
    public DialogNewMap_Controller(DialogNewMap_Model model, DialogNewMap_View view,EditorWindow_Controller control)
    {
        this.model = model;
        this.view = view;
        this.control = control;
        
        setupInputCommands();
        
    }
    private void setupInputCommands()
    {
        view.getOKButton().setActionCommand(commandOK);
         view.getOKButton().addActionListener(this);
         view.getCancelButton().setActionCommand(commandCancel);
         view.getCancelButton().addActionListener(this);
          view.getSizeComboBox().setActionCommand(commandSize);
         view.getSizeComboBox().addActionListener(this);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        String command = e.getActionCommand();
        if (commandOK.equals(command))
        {
            control.createMap(view.getComboBoxName(),model.getTilesetX(),model.getTilesetY());
            view.setVisible(false);

        }
        if (commandCancel.equals(command))
        {
            view.setVisible(false);
        }
        if (commandSize.equals(command))
        {
            setTilesetSize();
        }
    }
    
     public void setTilesetSize()
    {
        switch (view.getSizeIndex())
            {
                case 0:
                    model.setTilesetSize(8, 8);
                    break;
                case 1:
                    model.setTilesetSize(16, 16);                
                    break;
                case 2:
                    model.setTilesetSize(32, 32);                  
                    break;
                case 3:
                    model.setTilesetSize(64, 64);  
                    break;
                case 4:
                    model.setTilesetSize(128, 128);                   
                    break;
            }
    }


}
