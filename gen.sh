#!/bin/bash


function cm2in {
	echo $1*0.393700787*300 | bc -l | cut -d. -f1
}


cols=(red green blue cyan magenta yellow random)
function rnd_col {
	i=$((RANDOM % 7))
	echo random #${cols[$i]}
}


EXEC="java -cp bin sierpyr.Decor"
#EXEC="java -cp sierpyr-decor/bin sierpyr.Decor"

function gen {
# $1 ... filename, ... followed by other parameters
# $2 ... base length in cm
# $3 ... count
	filename=$1
	len=$(cm2in $2)
	count=$3
	d=$(( $len / 150 ))
	depth=${4:-$d}
	$EXEC $filename $len $depth $count
	convert -density 300 -units pixelsperinch $filename $filename
}


gen a1.png 10 3 <<EOF
depth yellow none
depth none red
depth cyan blue
EOF

gen a2.png 10 3 <<EOF
random yellow none
random none red
random cyan none
EOF

gen a3.png 10 3 <<EOF
evenodd yellow cyan none
evenodd green magenta none
evenodd cyan blue none
EOF
