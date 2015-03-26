package com.tasma;

public class ZebraJList extends javax.swing.JList{
	
	private java.awt.Color rowColors[] = new java.awt.Color[2];
    private boolean drawStripes = false;
    
    public ZebraJList() {};
    
    public ZebraJList(javax.swing.ListModel dataModel) {
        super(dataModel);
    }
    public ZebraJList(Object[] listData) {
        super(listData);
    }
    public ZebraJList(java.util.Vector<?> listData) {
        super(listData);
    }
    
    /** Add zebra stripes to the background. */
    public void paintComponent(java.awt.Graphics g)
    {
        drawStripes = (getLayoutOrientation()==VERTICAL) && isOpaque();
        if (!drawStripes) {
            super.paintComponent(g);
            return;
        }
        
        
        
    }
}
