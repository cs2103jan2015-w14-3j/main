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
        
        // Paint zebra background stripes
        final java.awt.Insets insets = getInsets();
        final int w   = getWidth()  - insets.left - insets.right;
        final int h   = getHeight() - insets.top  - insets.bottom;
        final int x   = insets.left;
        int y         = insets.top;
        int nRows     = 0;
        int startRow  = 0;
        int rowHeight = getFixedCellHeight(); 
        if (rowHeight > 0)
            nRows = h / rowHeight;
        else {
            // Paint non-uniform height rows first
            final int nItems = getModel().getSize( );
            rowHeight = 17; // A default for empty lists
            for ( int i = 0; i < nItems; i++, y+=rowHeight ) {
                rowHeight = getCellBounds( i, i ).height;
                g.setColor( rowColors[i&1] );
                g.fillRect( x, y, w, rowHeight );
            }
            // Use last row height for remainder of list area
            nRows    = nItems + (insets.top + h - y) / rowHeight;
            startRow = nItems;
        }
        
        for (int i = startRow; i < nRows; i++, y+=rowHeight) {
            g.setColor(rowColors[i&1]);
            g.fillRect(x, y, w, rowHeight);
        }
        final int remainder = insets.top + h - y;
        if (remainder > 0) {
            g.setColor(rowColors[nRows&1]);
            g.fillRect(x, y, w, remainder);
        }
 
        // Paint component
        setOpaque(false);
        super.paintComponent(g);
        setOpaque(true);
    }
    
    /** Wrap a cell renderer to add zebra stripes behind list cells. */
    private class RendererWrapper implements javax.swing.ListCellRenderer
    {
        public javax.swing.ListCellRenderer ren = null;
 
        public java.awt.Component getListCellRendererComponent(
            javax.swing.JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus )
        {
            final java.awt.Component c = ren.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus );
            if ( !isSelected && drawStripes )
                c.setBackground( rowColors[index&1] );
            return c;
        }
    }
}
