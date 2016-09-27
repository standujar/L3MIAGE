*-----------------------------------------------------------
* Program    :
* Written by :
* Date       :
* Description:
*-----------------------------------------------------------
HARDWARE:
	MOVE 	#32,D0
	TRAP 	#15
	RTS

RESOLUTION:

	MOVE	#33,D0
	TRAP	#15
	RTS

GET_MOUSE:

	MOVE 	#61,D0
	TRAP	#15
	RTS

MOUSE:

	MOVE	#60,D0
	TRAP	#15	
	RTS		

*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~8~
