Brian Tracy
Kuszmaul
AP Computer Science

Make A Flag

	This project was yet another excellent example of the power of the javax.swing framework. By harnessing the blazing fast speed of an Object Oriented approach to graphics and the cutting edge (int ?) precision of swing, our task was to create a scalable American flag, compliant with a rigorous set of specs (http://www.usflag.org/flag.specs.html).

	The application was divided up into two classes, with the majority of code in the FlagFrame class. This class was responsible for drawing the flag and managing its resizability. As a sublcass of JFrame, most standard features and behavior came built in such as 
		- a title bar
		- resizing handles (sides + corner )
		- ability to draw (`paint()` method)

	Building off of the JFrame template, I was able to create the flag though the use of provided methods and my own helper methods to do the heavy lifting. They are as follows:

			- paint ~ called by the system when the graphics need to be redrawn
			- initializeDimensions ~ sets the initial size of the flag to something nice
			- drawUnion ~ draws the union jack in the top left corner. Just a blue square
			- drawStripes ~ runs down the flag drawing alternating color stripes (white, red)
			- windowResized ~ called when the window changes size (through paint()). Sets the flag to an appropriate size
			- drawStars ~ uses loops and some nifty calculations to position stars on the union jack
			- drawStarAtLocation ~ draws a star at the spot specified. Stars are antialiased for better aesthetics at low sizes


	By completing this process, I feel as if I have gotten a nice review of the GUI options provided by Java. I still look forward to lots of learning though as we have yet to use things like JPanels and JComponents. By incorporating both mathematics and graphics into a single application, this project challenged me in many ways and further spiked my interest in graphical applications.

	Let's take a look at the paint method 

		public void paint(Graphics g) {
        	windowResized();
        	drawStripes(g);
        	drawUnion(g);
        	drawStars(g);
    	}

    Instead of taking a the very cluttered and mathematical approach of doing all calculations inside this function, I chose to delegate these processes to helper functions and pass them what they needed to complete the job. For example, instead of performing the calculations for the union, stripes, and stars in the same method, I split them up into their own self contained units. By encapsulating these processes, not only has the paint method been cleared up, but the program has become easier to maintain. For example, if I found an error in the way my stripes were being drawn, instead of hunting through a mile long paint method, I simply jump the the drawStripes method to find all relevant information.

    My biggest lesson learned with this project is that through process of abstracting away the logic of drawing the flag into several methods, I could clean up my code and make it easier to understand and document.
    





