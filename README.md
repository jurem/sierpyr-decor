sierpyr-decor
=============

Sierpinski-Pyramids Decorations

Application generates (Christmas tree) decoration in the shape of pyramid. Sierpinski triangle is rendered on each face of the pyramid. It is written in Java and used in command line.

To see some images of application results, go to (text is in Slovene)
  * http://lalg.fri.uni-lj.si/jurij/blog/okraski-sierpinskega/
  * http://lalg.fri.uni-lj.si/jurij/blog/generiranje-okraskov-sierpinskega/

Installation
------------

Download or clone source code and run make.

    git clone git://github.com/jurem/sierpyr-decor.git
    cd sierpyr-decor
    make

Usage
-----

Application accepts four arguments:
  * java -cp bin sierpyr.Decor filename length depth count

where
  * filename is the name of the output PNG file,
  * length is the side length in pixels of the full triangle,
  * depth is the number of iterations for Sierpinski triangle generation,
  * count is the number of full triangles.

The configuration of style of each full triangle is read from the standard input. The following styles are supported:
  * random inside last
  * depth inside last
  * evenodd even odd last
where inside, last even and odd are the color names (red,green,blue,cyan,magenta,yellow,none,random). See gen.sh script for examples.

    
