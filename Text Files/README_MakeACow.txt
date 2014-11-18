Make A Cow (Again !)

Brian Tracy
AP Computer Science
Mr. Kuszmaul

	This assignment was to draw a cow as a refresher to the Swing API. This assignment required basic knowledge of the Swing API including how to set colors and draw shapes such as
			- rectangles
			- rounded rectangles
			- ellipses
	Other skills used were subclassing JFrame and instantiating and setting up instances of the JFrame class.

	The Main class (Main.java) was used as the entry point to the application and is responsible for setting up the application's single frame. In this class the following is done.
			1 instantiate a CowFrame
			2 allow the frame to be closed
			3 make it visible
			4 disable its resizability 

	The CowFrame class (CowFrame.java) was used as the visual aspect of the application. It was the class that was doing the actual drawing of the cow. This process was broken up into several steps that were all translated into methods of the CowFrame class.

			- drawBackGround() 	| drawing the background which includes the sky and the ground
			- drawBody()		| drawing the body of the cow which took the shape of a rounded rectangle
			- drawLeg()			| this method simply draws a leg at a certain point
			- drawHead()		| along with the head of the cow, this method also draws the eyes
			- drawSun()			| finishes the scene of with a nice sun for the cow to look at!

	These methods all took as an argument the graphics object on which they were to draw their content. This provided an easy way to render the entire scene from the paint() method by simply passing in the given graphics. By encapsulating these tasks, clutter was removed from the paint() routine and the code became easier to follow.

	The overall program execution is as follows:

			1 system calls main(), provides any command line arguments
			2 CowFrame is instantiated and setup
			3 CowFrame's constructor simply calls its init() method, which in turn calls its paint() method through repaint()
			4 The scene is rendered
				a background is drawn
				b body is drawn
				c legs are appended
				d head is drawn
				e sun is drawn
			5 Application waits for JFrame to be quit then terminates



