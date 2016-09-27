*-----------------------------------------------------------
* Program    :
* Written by :
* Date       :
* Description:
*-----------------------------------------------------------


CLOSE_ALL_FILE:

	MOVE	#50,D0
	TRAP	#15
	RTS
	
OPEN_FILE:
	MOVE	#51,D0
	TRAP	#15
	RTS

NEW_FILE:
	MOVE	#52,D0
	TRAP	#15
	RTS
	
READ_FILE:
	MOVE	#53,D0
	TRAP	#15
	RTS
	
WRITE_FILE:
	MOVE	#54,D0
	TRAP	#15
	RTS
	
CLOSE_FILE:
	MOVE	#56,D0
	TRAP	#15
	RTS
	
DELETE_FILE:
	MOVE	#57,D0
	TRAP	#15
	RTS
*~Font name~Courier New~
*~Font size~10~
*~Tab type~1~
*~Tab size~8~
