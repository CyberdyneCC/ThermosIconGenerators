import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DiscordStyleIcon
{

    //Initialize a random number generator , Generates random number
    public static final Random r = new Random();

    //Image Size
    public static final double width = 64, height = 64;

    public static void main(String[] args) throws Exception
    {
        if(args.length != 1)
        {
            System.out.println("You can only use a one-word server name...!");
        }
        String s = args[0];
        //Create the image
                BufferedImage bi = new BufferedImage((int) width,
                        (int) height,
                        BufferedImage.TYPE_INT_ARGB
                );

        //Initialize Graphics2D for drawing on the image
        Graphics2D g = bi.createGraphics();

        //Some renderer hints that make the output image sharper
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g.setPaint(Color.DARK_GRAY);
        g.fillOval(0, 0, (int) width, (int) height);
        g.setFont(new Font("Consolas", Font.PLAIN, 32));
        Rectangle2D r2d = g.getFontMetrics().getStringBounds(s, g);
        g.setPaint(Color.WHITE);
        float xPos = (float) ((width - r2d.getWidth()) / 2);
        float yPos = (float) (height + r2d.getHeight() / 2) / 2;
        g.drawString(s, xPos, yPos);
        /*for(int i = 0; i < width; i++)
		{
			for(int j = 0; j < height; j++)
			{
				
			}
		}*/
        System.out.println("Writing...");
        ImageIO.write(bi, "png", new File("server-icon.png"));
        System.out.println("Done!");
        if(GraphicsEnvironment.isHeadless())
        {
            return;
        }
        //Display image
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(new JLabel(new ImageIcon(bi)));
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    public static int RGBTransform(int R, int G, int B)
    {
        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;
        return 0xFF000000 | R | G | B;
    }
}
