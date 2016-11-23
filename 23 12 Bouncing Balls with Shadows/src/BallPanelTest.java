// James Michael Seehaus
import javax.swing.JFrame;

public class BallPanelTest extends JFrame
{
   /****/
   private static final long serialVersionUID = 1L;
   BallPanel ballPanel;
  
   public BallPanelTest()
   {
       super( "Bouncing Ball!" );
       ballPanel = new BallPanel( 600, 600 );
       add( ballPanel );
   }

   public static void main( String args[] )
   {
       BallPanelTest bpt = new BallPanelTest();
       bpt.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
       bpt.pack();
       bpt.setVisible( true );
   }
}

