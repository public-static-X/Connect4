from termcolor import colored 

def drawBoard():
    
 global board
    
 for row in range(6):  
    for col in range(15):
        if col%2 != 0:
           if board[row][col//2] == "X":
             print(colored("O","red",attrs=['bold']),end="")
           elif board[row][col//2] == "O":
               print(colored("O","green",attrs=['bold']),end="")
           else:
               print(" ",end="")
        else:
            print(colored("|","blue"),end="")
    print()
  
def findHeight(col):
    global board 
    for row in range(6):
        if board[5-row][col] == " ":
            return 5-row
    return 9 #force exception
    
def draw():
    global board
    
    for i in range(6):
        for k in range(7):
            if board[i][k] == " ":
                return False
        
    print("It's a draw!")
    return True

def redWins():
    global board
    
    #vertical wins
    
    for column in range(7):
        for rowStart in range(3):
            win = board[5-rowStart][column] == "X" and board[5-rowStart-1][column] == "X"
            win = win and board[5-rowStart-2][column] == "X"
            win = win and board[5-rowStart-3][column] == "X"
            
            if win:
                print("Red Wins!")
                return True
            
    #horizontal wins

    for column in range(4):
        for rowStart in range(6):
            win = board[rowStart][column] == "X" and board[rowStart][column+1] == "X"
            win = win and board[rowStart][column+2] == "X"
            win = win and board[rowStart][column+3] == "X"
            
            if win:
                print("Red Wins!")
                return True
            
    # diagonal left
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][column] == "X" and board[3-rowStart][column+1] == "X"
            win = win and board[4-rowStart][column+2] == "X"
            win = win and board[5-rowStart][column+3] == "X"
            
            if win:
                print("Red Wins!")
                return True
    
    # diagonal right
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][6-column] == "X" and board[3-rowStart][5-column] == "X"
            win = win and board[4-rowStart][4-column] == "X"
            win = win and board[5-rowStart][3-column] == "X"
            
            if win:
                print("Red Wins!")
                return True
            
    return False

def greenWins():
    
    global board
    
    #vertical wins
    
    for column in range(7):
        for rowStart in range(3):
            win = board[5-rowStart][column] == "O" and board[5-rowStart-1][column] == "O"
            win = win and board[5-rowStart-2][column] == "O"
            win = win and board[5-rowStart-3][column] == "O"
            
            if win:
                print("Green Wins!")
                return True
            
    #horizontal wins

    for column in range(4):
        for rowStart in range(6):
            win = board[rowStart][column] == "O" and board[rowStart][column+1] == "O"
            win = win and board[rowStart][column+2] == "O"
            win = win and board[rowStart][column+3] == "O"
            
            if win:
                print("Green Wins!")
                return True
            
    # diagonal left
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][column] == "O" and board[3-rowStart][column+1] == "O"
            win = win and board[4-rowStart][column+2] == "O"
            win = win and board[5-rowStart][column+3] == "O"
            
            if win:
                print("Green Wins!")
                return True
    
    # diagonal right
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][6-column] == "O" and board[3-rowStart][5-column] == "O"
            win = win and board[4-rowStart][4-column] == "O"
            win = win and board[5-rowStart][3-column] == "O"
            
            if win:
                print("Green Wins!")
                return True
            
    return False
    
board = [[" " for c in range(7)] for n in range(6)]

playerOne = True
drawBoard()

while(True):
        
    try: 
        
           if playerOne:
               print("Player 1's turn!")
           else:
                print("player 2's turn!")
            
           column = input("which column would you llike to play in? 0 = first column\n")
           column = int(column)
           row = findHeight(column)
           
           if playerOne:
               board[row][column] = "X"
           else:
               board[row][column] = "O"
        
    except:
          print("column " + str(column) + " is not a valid choice")
          playerOne = not playerOne
          
    playerOne = not playerOne
    
    drawBoard()
    
    if(redWins() or draw() or greenWins()):
        endGame = input("would you like to play again? y/Y = yes, other key = no\n")
        
        if(endGame.lower() != "y"):
            break
        else:
            board = [[" " for c in range(7)] for n in range(6)]
            playerOne = True
            drawBoard()