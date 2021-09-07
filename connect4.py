from termcolor import colored 
import numpy as np
import random
import copy

playerAI = 2
playerHUMAN = 1
HUMAN = "X"
AI = "O"

def endGame():
    ans = input("would you like to play again? y/Y = yes, n/N = no\n")
    if ans.lower() == "y":
        return "y"
    elif ans.lower() == "n":
        return "n"
    else:
        endGame()
    

def drawBoard(board):
    
# global board
    
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

 for k in range(7):
     print("", k,end = "")

 print()
  
def findHeight(col,board):
  #  global board 
    for row in range(6):
        if board[5-row][col] == " ":
            return 5-row
    return 9 #force exception
    
def draw(board):
    #global board
    
    for i in range(6):
        for k in range(7):
            if board[i][k] == " ":
                return False
        
   # print("It's a draw!")
    return True

def redWins(board):
   # global board
    
    #vertical wins
    
    for column in range(7):
        for rowStart in range(3):
            win = board[5-rowStart][column] == "X" and board[5-rowStart-1][column] == "X"
            win = win and board[5-rowStart-2][column] == "X"
            win = win and board[5-rowStart-3][column] == "X"
            
            if win:
              #  print("Red Wins!")
                return True
            
    #horizontal wins

    for column in range(4):
        for rowStart in range(6):
            win = board[rowStart][column] == "X" and board[rowStart][column+1] == "X"
            win = win and board[rowStart][column+2] == "X"
            win = win and board[rowStart][column+3] == "X"
            
            if win:
               # print("Red Wins!")
                return True
            
    # diagonal left
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][column] == "X" and board[3-rowStart][column+1] == "X"
            win = win and board[4-rowStart][column+2] == "X"
            win = win and board[5-rowStart][column+3] == "X"
            
            if win:
              #  print("Red Wins!")
                return True
    
    # diagonal right
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][6-column] == "X" and board[3-rowStart][5-column] == "X"
            win = win and board[4-rowStart][4-column] == "X"
            win = win and board[5-rowStart][3-column] == "X"
            
            if win:
               # print("Red Wins!")
                return True
            
    return False

def greenWins(board):
    
   # global board
    
    #vertical wins
    
    for column in range(7):
        for rowStart in range(3):
            win = board[5-rowStart][column] == "O" and board[5-rowStart-1][column] == "O"
            win = win and board[5-rowStart-2][column] == "O"
            win = win and board[5-rowStart-3][column] == "O"
            
            if win:
              #  print("Green Wins!")
                return True
            
    #horizontal wins

    for column in range(4):
        for rowStart in range(6):
            win = board[rowStart][column] == "O" and board[rowStart][column+1] == "O"
            win = win and board[rowStart][column+2] == "O"
            win = win and board[rowStart][column+3] == "O"
            
            if win:
              #  print("Green Wins!")
                return True
            
    # diagonal left
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][column] == "O" and board[3-rowStart][column+1] == "O"
            win = win and board[4-rowStart][column+2] == "O"
            win = win and board[5-rowStart][column+3] == "O"
            
            if win:
              #  print("Green Wins!")
                return True
    
    # diagonal right
    
    for rowStart in range(3):
        for column in range(4):
            win = board[2-rowStart][6-column] == "O" and board[3-rowStart][5-column] == "O"
            win = win and board[4-rowStart][4-column] == "O"
            win = win and board[5-rowStart][3-column] == "O"
            
            if win:
              #  print("Green Wins!")
                return True
            
    return False
    
def scoreCheck(arr,player):
    
    if player == "X":
        opp_player = "O"
    else:
        opp_player = "X"
    
    score = 0
    if arr.count(player) == 2 and arr.count(" ") == 2:
        score += 2
    elif arr.count(player) == 3 and arr.count(" ") == 1:
        score += 5
    elif arr.count(player) == 4:
        score += 999999999999999999999
        
    if arr.count(opp_player) == 2 and arr.count(" ") == 2:
        score -= 2
    elif arr.count(opp_player) == 3 and arr.count(" ") == 1:
        score -= 6
    elif arr.count(opp_player) == 4:
        score -= 20000
    
    return score
    
def scoreBoard(board,player):
    
    L = []
    score = 0
    
    if player == "X":
        opp_player = "O"
    else:
        opp_player = "X"
    
    newBoard = np.array(board)
    centre = newBoard[:,3]
    centre = list(centre)
    
    score += 3 * centre.count(player)
    score -= 2 * centre.count(opp_player)
    
    #vertical scores
    
    for column in range(7):
        for rowStart in range(3):
            L = [board[5-rowStart][column],board[5-rowStart-1][column],board[5-rowStart-2][column], board[5-rowStart-3][column]]
            score += scoreCheck(L,player)
    
    #horizontal scores
    
    for column in range(4):
        for rowStart in range(6):
            L = [board[rowStart][column],board[rowStart][column+1],board[rowStart][column+2],board[rowStart][column+3]]
            score += scoreCheck(L,player)
            
    #diagonal left
    
    for rowStart in range(3):
        for column in range(4):
            L = [board[2-rowStart][column],board[3-rowStart][column+1],board[4-rowStart][column+2],board[5-rowStart][column+3]]
            score += scoreCheck(L,player)
            
    #diagonal right
    
    for rowStart in range(3):
        for column in range(4):
            L = [board[2-rowStart][6-column],board[3-rowStart][5-column],board[4-rowStart][4-column],board[5-rowStart][3-column]]
            score += scoreCheck(L,player)
            
    return score

def validColumns(board):
    cols = []
    
    for i in range(7):
        if findHeight(i, board) < 9:
            cols.append(i)
    
    return cols

def terminate_node(board):
    return redWins(board) or greenWins(board) or draw(board)

def minMax(board,depth,alpha,beta,maximiser):
    columns = validColumns(board)
    finalCol = random.choice(columns)
    terminal_node = terminate_node(board)
    
    if depth == 0 or terminal_node:
        if depth == 0:
            return None, scoreBoard(board, AI)
        else:
            if redWins(board):
                if playerAI == 1:
                    return (None, 9999999999)
                else:
                    return (None, -9999999999)
            elif greenWins(board):
                if playerAI == 2:
                    return (None, 9999999999)
                else:
                    return (None, -9999999999)
            else:
                return (None, -1)
            
    if maximiser:
        value = -9999999999
        
        for col in columns:
            row = findHeight(col, board)
            board2 = copy.deepcopy(board)
            board2[row][col] = AI
            newVal = minMax(board2,depth-1,alpha,beta,False)[1]
            
            if newVal > value:
                value = newVal
                finalCol = col
            alpha = max(alpha,value)
            if alpha >= beta:
                break
        return finalCol, value
    
    else:
        value = 9999999999
        
        for col in columns:
            row = findHeight(col,board)
            board2 = copy.deepcopy(board)
            board2[row][col] = HUMAN
            newVal = minMax(board2,depth-1,alpha,beta,True)[1]
            
            if newVal < value:
                value = newVal
                finalCol = col
            beta = min(beta,value)
            if alpha >= beta:
                break
        return finalCol, value
                

board = [[" " for c in range(7)] for n in range(6)]

playerOne = True
drawBoard(board)

while(True):
        
    try: 
        
           if playerOne:
               print("Player 1's turn!")
               
               if playerAI == 1:
                   column, score = minMax(board,6,-9999999999,9999999999,True)
                   column = int(column)
                   print(column)
                   row = findHeight(column,board)
                   board[row][column] = AI
               else:
                   column = input("which column would you like to play in? 0 = first column\n")
                   column = int(column)
                   row = findHeight(column,board)
                   board[row][column] = HUMAN
               
           else:
                print("player 2's turn!")
            
                if playerAI == 2:
                   #print(column)
                   column, score = minMax(board,6,-9999999999,9999999999,True)
                   #print(column)
                   column = int(column)
                   #print(column)
                   row = findHeight(column,board)
                   board[row][column] = AI
                else:
                   column = input("which column would you like to play in? 0 = first column\n")
                   column = int(column)
                   row = findHeight(column,board)
                   board[row][column] = HUMAN
           
        
    except:
           print("column " + str(column) + " is not a valid choice")
           playerOne = not playerOne
              
    playerOne = not playerOne
 
    drawBoard(board)
    
    if redWins(board):
        print("Red Wins")
    elif greenWins(board):
        print("Green Wins")
    elif draw(board):
        print("It's a draw")
 
    if(redWins(board) or draw(board) or greenWins(board)):
     ans = endGame()
     
     if(ans != "y"):
         break
     else:
         board = [[" " for c in range(7)] for n in range(6)]
         playerOne = True
         drawBoard(board)
