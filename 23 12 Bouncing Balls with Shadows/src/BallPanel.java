// James Michael Seehaus
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class BallPanel extends JPanel
{
   private RepaintTimer repaintTimer;
   private ExecutorService threadExecutor;
   private int panelWidth;
   private int panelHeight;
   private int ballCounter;
   private final int MAX_NUMBER_OF_BALLS = 33;
   private Ball[] ballArray = new Ball[ MAX_NUMBER_OF_BALLS ];
   private Random randomGenerator;
  
   public BallPanel( int width, int height )
   {
       setPreferredSize( new Dimension( width, height ) );
      panelWidth = ( int ) getPreferredSize().getWidth();
       panelHeight = ( int ) getPreferredSize().getHeight();
      
       repaintTimer = new RepaintTimer( this, 20 );
      
       threadExecutor = Executors.newCachedThreadPool();
       setBackground( Color.BLACK );
       setOpaque( true );
       addMouseListener( new MouseListener() );
       threadExecutor.execute( repaintTimer );
       ballCounter = 0;
       randomGenerator = new Random();
   }

   public void paintComponent( Graphics g )
   {
       super.paintComponent( g );
       Graphics2D graphics2d = ( Graphics2D ) g;
      
       for( Ball ball : ballArray )
       {      
           if( ball != null )
           {
               graphics2d.setPaint( ball.getBallColour() );
          
               graphics2d.fill( new Ellipse2D.Double( ball.getXCoord(), ball.getYCoord(), ball.getDiameter(), ball.getDiameter()));
              
               graphics2d.setPaint( Color.WHITE );

               double shadowDiameter = ( double ) ball.getDiameter() * ( ( double ) ball.getYCoord() / ( double ) panelHeight );
              
               graphics2d.fill( new Ellipse2D.Double( ball.getXCoord(), panelHeight - ( shadowDiameter / 2 ), shadowDiameter * 1.5, shadowDiameter / 2 ));
           }
       }
   }
  
   public class MouseListener extends MouseAdapter
   {
       public void mousePressed( MouseEvent event )
       {
           if( ballCounter < MAX_NUMBER_OF_BALLS )
           {
               ballArray[ ballCounter ] = new Ball( ( int ) getPreferredSize().getWidth(), ( int ) getPreferredSize().getHeight(), event.getX(), event.getY(), 10 );
              
               ballArray[ ballCounter ].setBallColour( new Color( randomGenerator.nextInt( 256 ), randomGenerator.nextInt( 256 ), randomGenerator.nextInt( 256 )));
                              
               threadExecutor.execute( ballArray[ ballCounter ] );
              
               ballCounter++;
           }
       }
   }
}

