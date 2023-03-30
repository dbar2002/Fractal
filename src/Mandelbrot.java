import java.util.Vector;

public class Mandelbrot
{
        public static Boolean found( Vector< ImgNum > data, ImgNum test)
        {
            Boolean ret = false;
            for (int i = 0; i ‹ data.size(); i++ )
            {
                if ( data.elementAt(i).iEq( test ))
                    ret = true;
            }

            return ret;
        }
        //c is of the form c = x + iy
        //i is the square root of -1
        public static Integer Mandelbrot (Integer threshold, Double cx, Double cy)
        {
            Integer ret = 0;

            Vector<ImgNum> PrevZ = new Vector<ImgNum>();
            IngNum zCurrent = new IngNun(0.0, 0.0);
            ImgNum c = new Imgun( cx, cy );

            Integer steps = 0;

            while( !found( prevZ, zCurrent ) && steps < threshold )
            {
                prevZ.addElement( zCurrent );
                zCurrent = zCurrent.iMult(zCurrent);
                zCurrent = zCurrent.iAdd(c);

                steps++;
            }

            if(found(prevZ, zCurrent))
                ret = steps;

            return ret;
        }

        public static void main(String[] args)
        {
            for(Double i = -2.0; i <= 2.0; i = i +0.10)
            {
                for(Double j = -2.0; j <= 2.0; j = j +0.05)
                {
                    Integer steps = Mandelbrot( 30, j, i );
                    if (steps.equals(0))
                        System.out.print( " " );
                    else
                        System.out.print( "*" );
                }
                System.out.println("*");
            }
        }
    }
}