import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Mandelbrot
{
    public static Boolean found(Vector< ImgNum > data, ImgNum test)
    {
        Boolean ret = false;
        for (int i = 0; i < data.size(); i++)
        {
            if (data.elementAt(i).iEq(test))
                ret = true;
        }

        return ret;
    }

    //c is of the form c = x + iy
    //i is the square root of -1
    public static Integer Mandelbrot(Integer threshold, Double cx, Double cy)
    {
        Integer ret = 0;

        Vector<ImgNum> prevZ = new Vector<ImgNum>();
        ImgNum zCurrent = new ImgNum(0.0, 0.0);
        ImgNum c = new ImgNum(cx, cy);

        Integer steps = 0;

        while (!found(prevZ, zCurrent) && steps < threshold)
        {
            prevZ.addElement(zCurrent);
            zCurrent = zCurrent.iMult(zCurrent);
            zCurrent = zCurrent.iAdd(c);

            steps++;
        }
        if (found(prevZ, zCurrent))
            ret = steps;

        return ret;
    }

    public static void main(String[] args) throws IOException
    {
        int width = 800;
        int height = 800;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Color setColor = Color.LIGHT_GRAY; // Set the color for the Mandelbrot set
        Color bgColor = Color.BLACK; // Set the color for the background
        
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double cx = (j - width / 2.0) * 4.0 / width;
                double cy = (i - height / 2.0) * 4.0 / width;
                Integer steps = Mandelbrot(30, cx, cy);
                if (steps.equals(0))
                    image.setRGB(j, i, bgColor.getRGB());
                else
                    image.setRGB(j, i, setColor.getRGB());
            }
        }

        File outputFile = new File("mandelbrot.png");
        ImageIO.write(image, "png", outputFile);
    }
}

class ImgNum
{
    public ImgNum()
    {
        x = 0.0;
        y = 0.0;
    }

    public ImgNum(Double inputx, Double inputy)
    {
        x = inputx;
        y = inputy;
    }

    public ImgNum iMult(ImgNum A)
    {
        // (x + y i)(A.× + A.y i)
        // x A.x + (X A.y + y A.x)i - y A.y
        // x A.x - y A.y + (x A.y + y A.x)i

        ImgNum ret = new ImgNum();

        ret.x = x * A.x - y * A.y;
        ret.y = x * A.y + y * A.x;

        return ret;
    }

    public ImgNum iAdd(ImgNum A)
    {
        ImgNum ret = new ImgNum();

        ret.x = x + A.x;
        ret.y = y + A.y;

        return ret;
    }

    public Boolean iEq(ImgNum A)
    {
        if (x.equals(A.x) && y.equals(A.y))
            return true;
        return false;
    }

    public void iPrint()
    {
        System.out.println(x + " + " + y + "i");
    }

    // c = x + yi
    Double x;
    Double y;
}
