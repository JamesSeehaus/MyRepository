// James Michael Seehaus
import javax.swing.JPanel;

public class RepaintTimer implements Runnable
{
   private JPanel repaintPanel;
   private int sleepTime;

   public RepaintTimer( JPanel panel, int time )
   {
       repaintPanel = panel;
       sleepTime = time;
   }
  
   public void run()
   {
       while( true )
       {
           try
           {
               Thread.sleep( sleepTime );
               repaintPanel.repaint();
           }
           catch( InterruptedException exception )
           {
               System.out.println( "Interrupted exception in RepaintTimer" );
           }
       }
   }
}


