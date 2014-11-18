/*
    This class manages the graphical (and only) aspect of the application. It produces a scalable american flag
    with the help of the wonderful swing framework.
 */

package com.company;    /* I messed up some setting in IntelliJ, so now we have to use a package!? */

import javax.swing.*;   /* For JFrame, the superclass   */
import java.awt.*;      /* For things like Point, Color */

public class FlagFrame extends JFrame {

                                                                /* see http://www.usflag.org/flag.specs.html         */
                                                                /* Note: All Measurements are relative to the height */
    private class FlagDimensions {
        static final float FLAG_WIDTH               = 1.9f;     /* The width of the flag */
        static final float UNION_HEIGHT             = 0.5385f;  /* Height of the union   */
        static final float UNION_WIDTH              = 0.76f;    /* Width of the union    */
        static final float STRIPE_HEIGHT            = 0.0769f;  /* Height of stripe, 1/13 of height of flag  */
        static final float HORIZONTAL_STAR_DISTANCE = 0.063f;   /* Distance between lateral edge of flag and stars   */
        static final float VERTICAL_STAR_DISTANCE   = 0.054f;   /* Distance between vertical edge of flag and stars  */
        static final float STAR_DIAMETER            = 0.0616f;  /* Diameter of the stars, relative to height */
        static final float STAR_INNER_RADIUS        = 0.39f;    /* Radius of inner pentagon in star */
        static final int   HEADER_OFFSET            = 22;       /* Height of stupid title bar that is INCLUDED in coords */
        static final int   STARS_IN_LARGE_ROW       = 6;        /* Number of stars in larger row of stars  */
        static final int   STARS_IN_SMALL_ROW       = 5;        /* Number of stars in smaller row of stars */
        static final int   NUMBER_OF_LARGE_ROWS     = 5;        /* How many large rows of stars there are */
        static final int   NUMBER_OF_SMALL_ROWS     = 4;        /* How many small rows of stars there are */
    }

    private float flagHeight;                                   /* Sole instance variable of class, used as base for all others */
    private float getRoot() { return flagHeight;  }
    private void  setFlagHeight(float f) { this.flagHeight = f; }
                                                                                    /* See FlagDimensions for these measurements */
    private float getFlagHeight()               { return flagHeight; }
    private float getFlagWidth()                { return FlagDimensions.FLAG_WIDTH                  * getRoot(); }
    private float getUnionHeight()              { return FlagDimensions.UNION_HEIGHT                * getRoot(); }
    private float getUnionWidth()               { return FlagDimensions.UNION_WIDTH                 * getRoot(); }
    private float getStripeHeight()             { return FlagDimensions.STRIPE_HEIGHT               * getRoot(); }
    private float getHorizontalStarDistance()   { return FlagDimensions.HORIZONTAL_STAR_DISTANCE    * getRoot(); }
    private float getVerticalStarDistance()     { return FlagDimensions.VERTICAL_STAR_DISTANCE      * getRoot(); }
    private float getStarDiameter()             { return FlagDimensions.STAR_DIAMETER               * getRoot(); }

    private int getHeaderOffset() { return FlagDimensions.HEADER_OFFSET; } /* equal to JFrame.getInsets().top */

    /**
     * <summary>Default constructor. Not much done except transfer control to `initializeDimensions()`. </summary>
     */
    public FlagFrame() {
        initializeDimensions();
    }

    private void initializeDimensions() {
        setFlagHeight(707.0f);                                                  /* Good looking initial size */
        setSize((int)getFlagWidth(), (int)getFlagHeight() + getHeaderOffset()); /* Set the physical frame (JFrame method) */
    }

    /**
     * <summary>
     *          Called each time window is resized, and when JFrame is first created, paints the flag through the
     *          series of methods below, passing each the system created Graphics object
     * </summary>
     * @param g The graphics on which to be drawn
     */
    @Override
    public void paint(Graphics g) {
        windowResized();
        drawStripes(g);
        drawUnion(g);
        drawStars(g);


    }

    /**
     * <summary> This method draws the union jack. Note: does not draw the stars.</summary>
     * @param g The graphics on which to be drawn
     */
    private void drawUnion(Graphics g) {
        g.setColor(new Color(0x015BB6));    /* A crisp navy blue */
        g.fillRect(0, getHeaderOffset(), (int)getUnionWidth(), (int)getUnionHeight());      /* Stroke and fill union */
    }

    /**
     * <summary></summary>
     * @param g The graphics on which to be drawn
     */
    private void drawStripes(Graphics g) {

        for (int i = 0; i < 13; i++) {                  /* iterate through all 13 stripes-to-be */
            g.setColor(Color.white);                    /* A casual blanc */

            if (i % 2 == 0) {                           /* is this a red or blue stripe? */
                g.setColor(new Color(0xB60704));        /* A nice rustic red! */
            }
                                                        /* stroke the stripe */
            g.fillRect(0, getHeaderOffset() + (int)(getStripeHeight() * i), (int)getFlagWidth(), (int)getStripeHeight());
        }
    }

    /**
     * <summary>This method is called each time the window is resized (through `paint()`).</summary>
     */
    private void windowResized() {
        int width = getWidth();                         /* width of JFrame */
        int height = getHeight() - getHeaderOffset();   /* height of JFrame - top header = actual drawing height */

        int protoHeight = 0;                            /* what we could possibly be setting the height to */
        while (true) {
            if (protoHeight < height && (int)(protoHeight * FlagDimensions.FLAG_WIDTH) < width) {
                protoHeight++;
                /*
                    The rectangle occupied by a flag of height `protoHeight` fits in the window. Try to make it bigger
                 */
            }
            else {
                break;
                /*
                    The rectangle occupied by a flag of height `protoHeight` did not fit. That means the previous height
                    was a big as possible. Let's roll with that one and break out.
                 */
            }

        }
        setFlagHeight(protoHeight);     /* Now actually make the height that optimal height we just determined */
    }

    /**
     * <summary> This method is responsible for positioning the stars in the union. </summary>
     * @param g The graphics on which to be drawn
     */
    private void drawStars(Graphics g) {
        g.setColor(Color.white);        /* A stark, snowy white */

        /*
            Iterate over each of the six starred rows and fill them with stars
            `offset` represents how far over we are in the row
         */
        for (int largeRows = 0; largeRows < FlagDimensions.NUMBER_OF_LARGE_ROWS; largeRows++) {
            for (int staridx = 0, offset = 0; staridx < FlagDimensions.STARS_IN_LARGE_ROW; staridx++, offset += (2 * (int)getHorizontalStarDistance())) {
                int y = getHeaderOffset() + (int) getVerticalStarDistance() + ((int)getVerticalStarDistance() * largeRows * 2);
                int x = (int)getHorizontalStarDistance() + offset;
                drawStarAtLocation(g, new Point(x, y));
            }
        }

        /*
            Iterate over each of the five star rows and fill them with stars
            `offset` represents how far over we are in the row
         */
        for (int smallRows = 0; smallRows < FlagDimensions.NUMBER_OF_SMALL_ROWS; smallRows++) {
            for (int staridx = 0, offset = 0; staridx < FlagDimensions.STARS_IN_SMALL_ROW; staridx++, offset += (2 * (int)getHorizontalStarDistance())) {
                int y = getHeaderOffset() + ((int) getVerticalStarDistance() * 2) + ((int)getVerticalStarDistance() * smallRows * 2);
                int x = (2 * (int)getHorizontalStarDistance()) + (2 * (int) getHorizontalStarDistance() * staridx);
                drawStarAtLocation(g, new Point(x, y));
            }
        }
    }

    /**
     * <summary>This method draws a single star on the given graphics at the given point.</summary>
     * @param g The graphics on which to be drawn
     * @param position The center of the star
     */
    private void drawStarAtLocation(Graphics g, Point position) {
        final float  outerPentagonRadius    = getStarDiameter() / 2;                                    /* set up radii of pentagons */
        final float  innerPentagonRadius    = outerPentagonRadius * FlagDimensions.STAR_INNER_RADIUS;

        final double outerPentagonRotation  = Math.toRadians(72);                                       /* set up ratations to form pentagons */
        final double innerPentagonOffset    = outerPentagonRotation / 2.0;

        final int numberOfPoints = 10;                  /* Two pentagons = 10 points. We need arrays to hold these */
        int[] xPositions = new int[numberOfPoints];
        int[] yPositions = new int[numberOfPoints];

        /*
            Iterate through the five points of both pentagons simultaneously, assigning them there values
            Use your newfound ability of Trigonometry (I'm actually just taking trig this year!)
         */
        for (int idx = 0; idx < 5; idx++) {                                                                                     /* switches off between inner and outer */
            xPositions[idx * 2]         = (-(int)(outerPentagonRadius * Math.sin(outerPentagonRotation * idx))) + position.x;   /* inner pent */
            yPositions[idx * 2]         = (-(int)(outerPentagonRadius * Math.cos(outerPentagonRotation * idx))) + position.y;

            xPositions[(idx * 2) + 1]   = (-(int)(innerPentagonRadius * Math.sin((outerPentagonRotation * idx) + innerPentagonOffset))) + position.x; /* outer pent */
            yPositions[(idx * 2) + 1]   = (-(int)(innerPentagonRadius * Math.cos((outerPentagonRotation * idx) + innerPentagonOffset))) + position.y;
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); /* thankyou to Andrew Tam for the antialiasing tip! */
        g2.fillPolygon(xPositions, yPositions, numberOfPoints);                                  /* Fill and Stroke the beautiful, antialiased star  */
    }
}