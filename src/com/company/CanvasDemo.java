package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class CanvasDemo extends JFrame {

    private Filter filter1 = new Filter("/home/fluffle2/Desktop/img_1.jpg");
    private Filter filter2 = new Filter("/home/fluffle2/Desktop/img_1.jpg");
    private Filter filter3 = new Filter("/home/fluffle2/Desktop/img_1.jpg");
    private Filter filter4 = new Filter("/home/fluffle2/Desktop/img_1.jpg");
    private Filter filter5 = new Filter("/home/fluffle2/Desktop/img_1.jpg");
    private Filter filter6 = new Filter("/home/fluffle2/Desktop/img_1.jpg");
    private MyCanvasLeft canvasLeft = new MyCanvasLeft();
    private MyCanvasRight canvasRight= new MyCanvasRight();
    private String imageName;
    //private MyCanvas canvas2;
    JMenuBar menuBar = new JMenuBar();
    JMenu file = new JMenu("File");
    JMenu edit = new JMenu("Edit");
    JMenuItem load = new JMenuItem("Load");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem filterGS = new JMenuItem("Grayscale");
    JMenuItem filterRed = new JMenuItem("Red");
    JMenuItem filterContrast = new JMenuItem("Contrast");
    JMenuItem filterInvert = new JMenuItem("Invert");
    JMenuItem filterTresholding = new JMenuItem("Tresholding");
    JMenuItem filterGamma = new JMenuItem("Gamma Correction");



    public static void main(String[] args)
    {
        CanvasDemo fr = new CanvasDemo();
    }

    public CanvasDemo()
    {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        file.add(load);
        file.add(save);
        edit.add(filterGS);
        edit.add(filterRed);
        edit.add(filterGamma);
        edit.add(filterInvert);
        edit.add(filterTresholding);
        edit.add(filterContrast);
        menuBar.add(file);
        menuBar.add(edit);
        setLayout(new BorderLayout());
        setSize(730,430);
        setTitle("Filter");
        //add("Center",canvas);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        load.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                canvasLeft = new MyCanvasLeft();
                //panel.add(canvasLeft);
                add(canvasLeft);
                setVisible(true);
            }
        } );

//        filter.applyContrast();
//        filter.applyGammaCorrection();
//        filter.applyInvert();
//        filter.applyThresholdingFromGrayscale(120);

        filterGS.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remove(canvasLeft);
                canvasRight.repaint();
                filter1.applyGrayscale();
                imageName = filter1.getImageName();

                canvasRight = new MyCanvasRight();
                //panel.add(canvasRight);
                add(canvasRight);
                setVisible(true);
                //canvasRight.repaint();

            }
        } );

        filterContrast.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remove(canvasLeft);
                canvasRight.repaint();
                filter2.applyContrast();

                imageName = filter2.getImageName();

                canvasRight = new MyCanvasRight();
                //panel.add(canvasRight);
                add(canvasRight);
                setVisible(true);
                //canvasRight.repaint();

            }
        } );

        filterTresholding.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remove(canvasLeft);
                canvasRight.repaint();
                filter3.applyThresholdingFromGrayscale(120);

                imageName = filter3.getImageName();

                canvasRight = new MyCanvasRight();
                //panel.add(canvasRight);
                add(canvasRight);
                setVisible(true);
                //canvasRight.repaint();

            }
        } );

        filterRed.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remove(canvasLeft);
                canvasRight.repaint();

                filter4.applyRed();

                imageName = filter4.getImageName();

                canvasRight = new MyCanvasRight();
                //panel.add(canvasRight);
                add(canvasRight);
                setVisible(true);
                //canvasRight.repaint();
            }
        } );

        filterInvert.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remove(canvasLeft);
                canvasRight.repaint();
                filter5.applyInvert();

                imageName = filter5.getImageName();

                canvasRight = new MyCanvasRight();
                //panel.add(canvasRight);
                add(canvasRight);
                setVisible(true);
                //canvasRight.repaint();

            }
        } );
                //canvasRight.repaint();

        filterGamma.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                remove(canvasLeft);
                canvasRight.repaint();
                filter6.applyGammaCorrection();

                imageName = filter6.getImageName();

                canvasRight = new MyCanvasRight();
                //panel.add(canvasRight);
                add(canvasRight);
                setVisible(true);
                //canvasRight.repaint();

            }
        } );

    }

    private class MyCanvasLeft extends Canvas {

        @Override
        public void paint(Graphics g)
        {
            Image image = new ImageIcon("/home/fluffle2/Desktop/img_1.jpg").getImage();
            g.drawImage(image,20,30,this);
        }
    }

    private class MyCanvasRight extends Canvas {

        @Override
        public void paint(Graphics g)
        {
            //super.paint(g);

            g.clearRect(0, 0, 730, 430);

            Image image = new ImageIcon("/home/fluffle2/Desktop/img_1.jpg").getImage();
            g.drawImage(image,20,30,this);

            //System.out.println(imageName);
            Image dummy = new ImageIcon("/home/fluffle2/Desktop/"+imageName+".jpg").getImage();
            g.drawImage(dummy,370,30,this);

          //  repaint();

        }
    }

}
