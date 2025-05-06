import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Matchpairs
{
    
    public static int mousex = 0;
    public static int mousey = 0;
    public static int x1 = 0;public static int x2 = 0;
    public static int y1 = 0;public static int y2 = 0;

    public static int paintcount = 0;
    public static int ovalcount = 0;

    public static boolean donepaired[][] = {{false,false,false,false},{false,false,false,false},{false,false,false,false},{false,false,false,false}};

    public static class Window extends JFrame
    {
        Window()
        {
            super("Window");
            setSize(425, 425);
            setVisible(true);
            setLocationRelativeTo(null);
        }


    }   

    public static class Canvas extends JPanel implements MouseListener
    {
        public int colors[][] = new int[4][4];

        Canvas(int numbers[][])
        {
            for(int i = 0;i < 4;i++)
            {
                for(int j = 0;j < 4; j++)
                {
                    colors[i][j] = numbers[i][j];
                }
            }
        }
        
        public void paint(Graphics g)
        {
            super.paint(g);

            int x = 0;int y = 0;

            for(int i = 0;i < 4;i++)
            {
                for(int j = 0;j < 4;j++)
                {
                    if(donepaired[i][j])
                    {
                        continue;
                    }
                    g.setColor(Color.BLACK);
                    g.fillRect(x, y, 100,100);
                    
                    g.setColor(Color.WHITE);
                    g.drawLine(x, 0, x, 400);
                    g.drawLine(x, y, 400, y);
                    x += 100;
                }
                y +=100; 
                x = 0;
            }
            y = 0;

            if(paintcount > 0)
            {
                switch(colors[y1][x1])
                {
                    case 0:
                    g.setColor(Color.GRAY);
                    break;
                    case 1:
                    g.setColor(Color.BLUE);
                    break;
                    case 2:
                    g.setColor(Color.YELLOW);
                    break;
                    case 3:
                    g.setColor(Color.ORANGE);
                    break;
                    case 4:
                    g.setColor(Color.RED);
                    break;
                    case 5:
                    g.setColor(Color.GREEN);
                    break;
                    case 6:
                    g.setColor(Color.CYAN);
                    break;
                    case 7:
                    g.setColor(Color.PINK);
                    break;
                }
                if(!donepaired[y1][x1])
                {
                    g.fillOval((x1)*100 + 25,(y1)*100 + 25, 50, 50);
                    ovalcount++;

                if(ovalcount == 2)
                {
                    switch(colors[y2][x2])
                    {
                        case 0:
                        g.setColor(Color.GRAY);
                        break;
                        case 1:
                        g.setColor(Color.BLUE);
                        break;
                        case 2:
                        g.setColor(Color.YELLOW);
                        break;
                        case 3:
                        g.setColor(Color.ORANGE);
                        break;
                        case 4:
                        g.setColor(Color.RED);
                        break;
                        case 5:
                        g.setColor(Color.GREEN);
                        break;
                        case 6:
                        g.setColor(Color.CYAN);
                        break;
                        case 7:
                        g.setColor(Color.PINK);
                        break;
                    }
                    g.fillOval((x2)*100 + 25,(y2)*100 + 25, 50, 50);
                    ovalcount++;

                    if(colors[y1][x1] == colors[y2][x2])
                    {
                        g.clearRect(x1, y1, 100, 100);
                        g.clearRect(x2, y2, 100, 100);
                        donepaired[y1][x1] = true; 
                        donepaired[y2][x2] = true;
                        repaint();
                    }
                }

                if(ovalcount > 2)
                {   
                    ovalcount = 0;
                }
                }
            }

            paintcount++;
            if(paintcount > 2)
            {
                paintcount = 0;
            }
        }

        public void mouseClicked(MouseEvent e) 
        {
            mousex = e.getX();
            mousey = e.getY();

        }
        public void mouseEntered(MouseEvent e) 
        {
        }
        public void mouseExited(MouseEvent e) 
        {
        }

        public void mousePressed(MouseEvent e) 
        {
        mousex = e.getX();
        mousey = e.getY();
        
        if(paintcount == 1)
        {
            x1 = mousex/100;
            y1 = mousey/100;
        }
        else if(paintcount == 2)
        {
            x2 = mousex/100;
            y2 = mousey/100;

        }
        System.out.println("Here (y,x) (" + mousey + "," + mousex + ")");
        repaint();
        }

        public void mouseReleased(MouseEvent e) 
        {
        mousex = e.getX();
        mousey = e.getY();  
        }
    }
    
    public static void shuffle(int numbers[])
    {
        Random random = new Random();

        for(int i = numbers.length-1,count = 0;i > 0;i--,count++)
        {
            int position = random.nextInt(count+1);

            int temp = numbers[position];
            numbers[position] = numbers[i];
            numbers[i] = temp;
            
        }
    }
    public static void main(String[] args)
    {
        new Matchpairs();
        
        int pairs[][] = new int[4][4];
        int numbers[] = new int[16];
        int matrixlength = 4;
        int filling = 0;

        for(int i = 0; i < numbers.length ;i++)
        {
            numbers[i] = filling;
            filling++;

            if (filling == 8) 
                filling = 0;
        }
        filling = 0;
        shuffle(numbers);

        for(int i = 0;i < matrixlength;i++)
        {
            for(int j = 0;j < matrixlength;j++,filling++)
            {
                pairs[i][j] = numbers[filling];
                System.out.println(pairs[i][j]);
            }
        }
        
        Window window = new Window();
        Canvas canvas = new Canvas(pairs);
        window.setContentPane(canvas);
        canvas.addMouseListener(canvas);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
