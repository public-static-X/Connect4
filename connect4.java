import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.awt.Font;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class connect4 {
    //Board size
	
    static int X = 6;
    static int Y = 7;
    //This variable can be used to turn your debugging output on and off. Preferably use 
    static boolean DEBUG = true;
    public static void main (String[] args) {
        //TODO: Read in a command line argument that states whether the program will be in either terminal
        //      mode (T) or in GUI mode (G) [Hint: use args and the variable below]

        //Sets whether the game is in terminal mode or GUI mode
        boolean gui = true;
        boolean loop = true;//only used in GUI

        if(args[0].equals("T")) {
        	gui = false;
        }else if(args[0].equals("G")) {
        	gui = true;
        }
        
        boolean check = false;
        boolean again = true;
        
        int moveCount = 0;
        int endGame;
        int input = 0;
        int pos = -1;
        int audio = 0;
        
        //The 6-by-7 grid that represents the gameboard, it is initially empty
       
        int [][] grid = new int [X][Y];               
        //Shows which player's turn it is, true for player 1 and false for player 2
        boolean player1 = true;
        //Shows the number of special tokens a player has left
        int [] p1powers = {2, 2, 2};
        int [] p2powers = {2, 2, 2};      
        
        if (!gui) {
            //Terminal mode

            //TODO: Display 10 line title
        	
    		StdOut.println("****************************************************************************");
    		StdOut.println("*  _______  _______  __    _  __    _  _______  _______  _______  _   ___  *");
    		StdOut.println("* |       ||       ||  |  | ||  |  | ||       ||       ||       || | |   | *");
    		StdOut.println("* |       ||   _   ||   |_| ||   |_| ||    ___||       ||_     _|| |_|   | *");
    		StdOut.println("* |       ||  | |  ||       ||       ||   |___ |       |  |   |  |       | *");
    		StdOut.println("* |      _||  |_|  ||  _    ||  _    ||    ___||      _|  |   |  |___    | *");
    		StdOut.println("* |     |_ |       || | |   || | |   ||   |___ |     |_   |   |      |   | *");
    		StdOut.println("* |_______||_______||_|  |__||_|  |__||_______||_______|  |___|      |___| *");
    		StdOut.println("*                                                                          *");
    		StdOut.println("****************************************************************************");

        	
            //Array for current player's number of power storage
            int [] curppowers = new int[3];
     
            while (true) {
            	
                if (player1) {
                    StdOut.println("Player 1's turn (You are Red):");
                    curppowers = p1powers;
                } else {
                    StdOut.println("Player 2's turn (You are Yellow):");
                    curppowers = p2powers;
                }
                StdOut.println("Choose your move: \n 1. Play Normal \n 2. Play Bomb ("+curppowers[0]+" left) \n 3. Play Teleportation ("+curppowers[1]+" left) \n 4. Play Colour Changer ("+curppowers[2]+" left)\n 5. Display Gameboard \n 6. Load Test File \n 0. Exit");
                //TODO: Read in chosen move, check that the data is numeric
                
                try {
                	input = StdIn.readInt();	
                }catch(Exception ex) {
			input = 9;
                }
                
                switch(input) {
                    case 0: Exit();
                            break;

                    case 1: StdOut.println("Choose a column to play in:");
                            //TODO: Read in chosen column
                            //TODO: Check that value is within the given bounds, check that the data is numeric
                    		
                    		check = false;
                    		
                    		while(!check) {
                    			
                        		try {
                        			pos = StdIn.readInt();
                        			if(pos >= 0 && pos < 7) {
                        				check = true;
                        			}else {
                        				StdOut.println("Illegal move, please input legal move:");
                        			}
                        		}catch(Exception ex) {
                        			StdOut.println("Illegal move, please input legal move:");
                        		}
                        		
                        		}
                    		
                    		
                            //TODO: Play normal disc in chosen column
                    		
                    	
                    		if(grid[0][pos] == 0) {
                    			
                    			for(int i = 0; i < 5; i++) {
                    				if(grid[i+1][pos] != 0) {
                    					if(player1) {
                    						grid[i][pos] = 1;
                    						moveCount++;
                
                    					}else {
                    						grid[i][pos] = 2;
                    						moveCount++;
                    						
                    					}
                    					break;
                    				}
                    			}
                    			if(grid[5][pos] == 0) {
                    				if(player1) {
                    					grid[5][pos] = 1;
                    					moveCount++;
                    				}else {
                    					grid[5][pos] = 2;
                    					moveCount++;
                    				}
                    			}
                    			
                    		}else {
                    			StdOut.println("Column is full.");
                    		}
                            break;
 
                    case 2://StdOut.println("Choose a column to play in:");
							//TODO: Read in chosen column
							//TODO: Check that value is within the given bounds, check that the data is numeric
                    		check = false;
                    		
                    		if(player1) {
            					if(p1powers[0] > 0) {
            						p1powers[0] = p1powers[0] - 1;
            						StdOut.println("Choose a column to play in:");
  
            					}else {
							StdOut.println("Choose a column to play in:");
            						StdOut.println("You have no bomb power discs left");
            						player1 = !player1;
            						check = true;
            						break;
            					}
            				}else {
            					if(p2powers[0] > 0) {
            						p2powers[0] = p2powers[0] - 1;
            						StdOut.println("Choose a column to play in:");
            					
            					}else {
							StdOut.println("Choose a column to play in:");
            						StdOut.println("You have no bomb power discs left");
            						player1 = !player1;
            						check = true;
            						break;
            					}
            				}
                    		
                    		while(!check) {
                    			
                    		try {
                    			pos = StdIn.readInt();
                    			if(pos >= 0 && pos <= 6) {
                    				check = true;
                    			}else {
                    				StdOut.println("Illegal move, please input legal move:");
                    			}
                    		}catch(Exception ex) {
                    			StdOut.println("Illegal move, please input legal move:");
                    		}
                    		
                    		}
                    		
                    			if(grid[0][pos] == 0) {
                    		
                    				
            		
                    			for(int i = 0; i < 5; i++) {
                    				if(grid[i+1][pos] != 0) {
                    					Bomb(i,grid,player1,pos);
                    					
                    					moveCount++;
                    					break;
                    				}
                    			}
                    			if(grid[5][pos] == 0) {
                    				moveCount++;
                    				Bomb(5,grid,player1,pos);
                    			}
                    			
                    		}else {
                    			StdOut.println("Column is full.");
					
                    			check = true;
                        		
                        		
                    		}
                    		
							//TODO: Play bomb disc in chosen column and reduce the number of bombs left
							//TODO: Check that player has bomb discs left to play, otherwise print out an error message
                    	
                    
                            break;

                    case 3: //StdOut.println("Choose a column to play in:");
                    check = false;
                    if(player1) {
    					if(p1powers[1] > 0) {
    						p1powers[1] = p1powers[1] - 1;
    						StdOut.println("Choose a column to play in:");

    					}else {
						StdOut.println("Choose a column to play in:");
    						StdOut.println("You have no teleporter power discs left");
    						player1 = !player1;
    						check = true;
    						break;
    					}
    				}else {
    					if(p2powers[1] > 0) {
    						p2powers[1] = p2powers[1] - 1;
    						StdOut.println("Choose a column to play in:");
    					
    					}else {
						StdOut.println("Choose a column to play in:");
    						StdOut.println("You have no teleporter power discs left");
    						player1 = !player1;
    						check = true;
    						break;
    					}
    				}
                    
							//TODO: Read in chosen column
							//TODO: Check that value is within the given bounds, check that the data is numeric
                    
                    while(!check) {
            			
                		try {
                			pos = StdIn.readInt();
                			if(pos >= 0 && pos <= 6) {
                				check = true;
                			}else {
                				StdOut.println("Illegal move, please input legal move:");
                			}
                		}catch(Exception ex) {
                			StdOut.println("Illegal move, please input legal move:");
                		}
                		
                		}
                		
                    	Teleport(pos, grid, player1);
                    
							//TODO: Play teleport disc in chosen column and reduce the number of teleporters left
							//TODO: Check that player has teleport discs left to play, otherwise print out an error message
                            break;

                    case 4: //StdOut.println("Choose a column to play in:");
							//TODO: Read in chosen column
							//TODO: Check that value is within the given bounds, check that the data is numeric
                    
                    check = false;
                    if(player1) {
    					if(p1powers[2] > 0) {
    						p1powers[2] = p1powers[2] - 1;
    						StdOut.println("Choose a column to play in:");

    					}else {
						StdOut.println("Choose a column to play in:");
    						StdOut.println("You have no colour changer power discs left");
    						player1 = !player1;
    						check = true;
    						break;
    					}
    				}else {
    					if(p2powers[2] > 0) {
    						p2powers[2] = p2powers[2] - 1;
    						StdOut.println("Choose a column to play in:");
    					
    					}else {
						StdOut.println("Choose a column to play in:");
    						StdOut.println("You have no colour changer power discs left");
    						player1 = !player1;
    						check = true;
    						break;
    					}
    				}
                    
							//TODO: Play Colour Change disc in chosen column and reduce the number of colour changers left
							//TODO: Check that player has Colour Change discs left to play, otherwise print out an error message
                    
                    while(!check) {
            			
                		try {
                			pos = StdIn.readInt();
                			if(pos >= 0 && pos <= 6) {
                				check = true;
                			}else {
                				StdOut.println("Illegal move, please input legal move:");
                			}
                		}catch(Exception ex) {
                			StdOut.println("Illegal move, please input legal move:");
                		}
                		
                		}
                    
                    	Colour_Changer(pos,grid,player1);
                    
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 5: Display(grid);
                    		//Displays the current gameboard again, doesn't count as a move, so the player stays the same
                            player1 = !player1;
                            check = false;
                            break;

					//This part will be used during testing, please DO NOT change it. This will result in loss of marks
                    case 6: grid = Test(StdIn.readString());
                    		//Reads in a test file and loads the gameboard from it.
                            player1 = !player1;
                            break;
                            
                    case 7: Save(StdIn.readString(), grid);
                    		player1 = !player1;
                    		break;

					default: //TODO: Invalid choice was made, print out an error message but do not switch player turns
                           StdOut.println("Please choose a valid option");
			   //StdOut.println("");

			   Display(grid);

                           player1 = !player1;
                           check = false;
						break;
                }
				//Displays the grid after a new move was played
               if(check) {
                Display(grid);
               }
				//Checks whether a winning condition was met
                int win = Check_Win(grid);
                
              //TODO: Switch the players turns

              player1 = !player1;
                again = true; // again makes a loop until the player chooses 1 or 2
               endGame = 0;
                while(again) {
                
                if(win == 1) {
                	//StdOut.println();
                	StdOut.println("Player 1 wins!");
                	StdOut.println("Do you want to play again? \n 1. Yes \n 2. No");
                    //endGame = Integer.parseInt(StdIn.readLine());
                	try {
                		endGame = StdIn.readInt(); 
                	}catch(Exception ex) {
                		StdOut.println("Choose either 1 for Yes or 2 for No");
                	}
                    
                	if(endGame == 1) {
                		
                		if(moveCount % 2 == 0) {
                			player1 = true;
                		}else {
                			player1 = false;
                		}
                		
                		again = false;
                		
                		for(int i = 0; i < 6; i++) {
                    		for(int j = 0; j < 7; j++) {
                    			grid[i][j] = 0;
                    		}
                    	}
                		moveCount = 0;
                		
                		p1powers[0] = 2; p1powers[1] = 2; p1powers[2] = 2;
                		p2powers[0] = 2; p2powers[1] = 2; p2powers[2] = 2;
                		
                	}else if(endGame == 2) {
                		again = false;
                		System.exit(0);
                	}else {
                		StdOut.println("Choose either 1 for Yes or 2 for No");
                	}
                	
                }else if(win == 2) {
                	//StdOut.println();
                	StdOut.println("Player 2 wins!");
                	StdOut.println("Do you want to play again? \n 1. Yes \n 2. No");
                	
                	//endGame = Integer.parseInt(StdIn.readLine());
                	try {
                		endGame = StdIn.readInt(); 
                	}catch(Exception ex) {
                		StdOut.println("Choose either 1 for Yes or 2 for No");
                	} 
                	
                	if(endGame == 1) {
                		again = false;
                		//player1 = !player1;
                		
                		if(moveCount % 2 == 0) {
                			player1 = false;
                		}else {
                			player1 = true;
                		}
                		
                		for(int i = 0; i < 6; i++) {
                    		for(int j = 0; j < 7; j++) {
                    			grid[i][j] = 0;
                    		}
                    	}
                		moveCount = 0;
                		
                		p1powers[0] = 2; p1powers[1] = 2; p1powers[2] = 2;
                		p2powers[0] = 2; p2powers[1] = 2; p2powers[2] = 2;
                		
                	}else if(endGame == 2) {
                		again = false;
                		System.exit(0);
                	}else {
                		StdOut.println("Choose either 1 for Yes or 2 for No");
                	}
                	
                }else if(win == 3) {
                	
                	StdOut.println("Draw!");
                	StdOut.println("Do you want to play again? \n 1. Yes \n 2. No");
                	
                	//endGame = Integer.parseInt(StdIn.readLine());
                	try {
                		endGame = StdIn.readInt(); 
                	}catch(Exception ex) {
                		StdOut.println("Choose either 1 for Yes or 2 for No");
                	}
                    
                	if(endGame == 1) {
                		again = false;
                		player1 = !player1;
                		
                		if(player1) {
                			
                			if(moveCount % 2 == 0) {
                    			player1 = true;
                    		}else {
                    			player1 = false;
                    		}

                			
                		}else {
                			
                			if(moveCount % 2 == 0) {
                    			player1 = false;
                    		}else {
                    			player1 = true;
                    		}

                			
                		}
                		
                		//StdOut.println("Choose either 1 for Yes or 2 for No");
                		
                		for(int i = 0; i < 6; i++) {
                    		for(int j = 0; j < 7; j++) {
                    			grid[i][j] = 0;
                    		}
                    	}
                		moveCount = 0;
                		
                		p1powers[0] = 2; p1powers[1] = 2; p1powers[2] = 2;
                		p2powers[0] = 2; p2powers[1] = 2; p2powers[2] = 2;
                		
                	}else if(endGame == 2) {
                		again = false;
                		System.exit(0);
                	}else {
                		StdOut.println("Choose either 1 for Yes or 2 for No");
                	}
                	
                }else {
                	again = false;
                }
             
                }
                
            }
        } else {/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //Graphics mode
        	
		
        	Thread thread = new Thread(new Runnable() { // thread is for the possibility of wanting to exit the game by using escape
        												// in case it counts for marks
        		public void run() {
        			
        			while(true) {
        			StdDraw.pause(1);
        			if(StdDraw.isKeyPressed(27)) {
        				System.exit(0);
        			}
        			}
        		}
        		
        	});
        	thread.start();
        	
        	StdDraw.setCanvasSize(900,900);
//StdDraw.enableDoubleBuffering();
            int win = 0;
        	DispGUI(p1powers, p2powers, player1, grid);
           
            while(true) {
            	
            	loop = true;
            	
           while(loop) {
        	StdDraw.pause(10);   
           if(StdDraw.isMousePressed() && StdDraw.isKeyPressed(84)) {// teleporter disc 
        	  
        	   audio = 3;
        	   loop = false;
        	   while(StdDraw.isMousePressed() || StdDraw.isKeyPressed(84)) {// ensures the machine gun effect does not happen
        		   StdDraw.pause(10);
        	   }
        	   
        	   if(player1) {
					if(p1powers[1] > 0) {
						p1powers[1] = p1powers[1] - 1;
					}else {
						player1 = !player1;
						JOptionPane.showMessageDialog(null, "You have no teleporter discs left");
						audio = 0;
						break;
					}
				}else {
					if(p2powers[1] > 0) {
						p2powers[1] = p2powers[1] - 1;
					}else {
						player1 = !player1;
						JOptionPane.showMessageDialog(null, "You have no teleporter discs left");
						audio = 0;
						break;
					}
				}
              
						//TODO: Read in chosen column
						//TODO: Check that value is within the given bounds, check that the data is numeric
              
       	   double x = StdDraw.mouseX(); 
        	  
        	  x = x/0.143333;
       	   pos = (int) x; // rounds x down 
          		
              	Teleport(pos, grid, player1);
        	  
           }else if(StdDraw.isKeyPressed(66) && StdDraw.isMousePressed()) {// bomb disc
        	  
        	   audio = 2;
        	   loop = false;
        	   while(StdDraw.isMousePressed() || StdDraw.isKeyPressed(66)) {// ensures the machine gun effect does not happen
           		   StdDraw.pause(10);
        	   }
        	   
        	   if(player1) {
					if(p1powers[0] > 0) {
						p1powers[0] = p1powers[0] - 1;
					}else {
						JOptionPane.showMessageDialog(null, "You have no bomb discs left");
						player1 = !player1;
						audio = 0;
						break;
					}
				}else {
					if(p2powers[0] > 0) {
						p2powers[0] = p2powers[0] - 1;
					}else {
						JOptionPane.showMessageDialog(null, "You have no bomb discs left");
						player1 = !player1;
						audio = 0;
						break;
					}
				}
       		
        	   
       		 double x = StdDraw.mouseX(); 
           	  
           	  x = x/0.143333;
          	   pos = (int) x; // rounds x down 

       		
       			if(grid[0][pos] == 0) {
       		
       				
		
       			for(int i = 0; i < 5; i++) {
       				if(grid[i+1][pos] != 0) {
       					Bomb(i,grid,player1,pos);
       					break;
       				}
       			}
       			if(grid[5][pos] == 0) {
       				
       				Bomb(5,grid,player1,pos);
       			}
       			
       		}else {
       		JOptionPane.showMessageDialog(null, "Column is full");	
       		player1 = !player1;
       		audio = 0;
       		
       		}
       			
        	   
           }else if(StdDraw.isKeyPressed(67) && StdDraw.isMousePressed()) {// colour changer disc
        
        	   audio = 4;
        	   loop = false;
        	   while(StdDraw.isMousePressed() || StdDraw.isKeyPressed(67)) {// ensures the machine gun effect does not happen
           		   StdDraw.pause(10);
           	   }
        	   
        	   if(player1) {
					if(p1powers[2] > 0) {
						p1powers[2] = p1powers[2] - 1;
					}else {
						JOptionPane.showMessageDialog(null, "You have no colour changer discs left");
						player1 = !player1;
						audio = 0;
						break;
					}
				}else {
					if(p2powers[2] > 0) {
						p2powers[2] = p2powers[2] - 1;
					}else {
						JOptionPane.showMessageDialog(null, "You have no colour changer discs left");
						player1 = !player1;
						audio = 0;
						break;
					}
				}
               
        	   double x = StdDraw.mouseX(); 
          	  
          	  x = x/0.143333;
         	   pos = (int) x; // rounds x down 
               
               	Colour_Changer(pos,grid,player1);
               
        	   
           }else if(StdDraw.isMousePressed()) {// normal disc
        	  loop = false;
        	
        	  while(StdDraw.isMousePressed()) {// ensures the machine gun effect does not happen
       		   StdDraw.pause(10);
       	   }
        	  
        	  double x = StdDraw.mouseX(); 
         	  
         	  x = x/0.143333;
        	   pos = (int) x; // rounds x down 
         	   
         	   if(grid[0][pos] == 0) {
        			
         		   audio = 1;
        			for(int i = 0; i < 5; i++) {
        				if(grid[i+1][pos] != 0) {
        					if(player1) {
        						grid[i][pos] = 1;
        						
    
        					}else {
        						grid[i][pos] = 2;
        						
        						
        					}
        					break;
        				}
        			}
        			if(grid[5][pos] == 0) {
        				if(player1) {
        					grid[5][pos] = 1;
        					
        				}else {
        					grid[5][pos] = 2;
        					
        				}
        			}
        			
        		}else {
        			JOptionPane.showMessageDialog(null,"Column is full!");
        			player1 = !player1;
        			audio = 0;
        		}
        	  
           }
           
           }//end of while(loop)
            // end of turn
           player1 = !player1;
           DispGUI(p1powers, p2powers, player1, grid);
           
                //audio begin
           
           if(audio == 1) {
        	   StdAudio.play("normal.wav");
           }else if(audio == 2) {
        	   StdAudio.play("bomb.wav");
           }else if(audio == 3) {
        	   StdAudio.play("teleport.wav");
           }else if(audio == 4) {
        	   StdAudio.play("changer.wav");
           }
           
           //audio end
          
          win = Check_Win(grid);
            
          if(win == 1) {
        	endGame = JOptionPane.showConfirmDialog(null, "Would you like to play again?","Player 1 wins!", JOptionPane.YES_NO_OPTION);
        	if(endGame == JOptionPane.YES_OPTION) {
        		player1 = true;
        		for(int i = 0; i < 6; i++) {
            		for(int j = 0; j < 7; j++) {
            			grid[i][j] = 0;
            		}
            	}
        		
        		for(int k = 0; k < 3; k++) {
            		p1powers[k] = 2; p2powers[k] = 2;
            	}
        		
        		DispGUI(p1powers, p2powers, player1, grid);
        	}else{
        		System.exit(0);
        	}
          }else if(win == 2) { 
        	  endGame = JOptionPane.showConfirmDialog(null, "Would you like to play again?","Player 2 wins!", JOptionPane.YES_NO_OPTION);
          	if(endGame == JOptionPane.YES_OPTION) {
          		player1 = true;
        		for(int i = 0; i < 6; i++) {
            		for(int j = 0; j < 7; j++) {
            			grid[i][j] = 0;
            		}
            	}
        		
        		for(int k = 0; k < 3; k++) {
            		p1powers[k] = 2; p2powers[k] = 2;
            	}
        		
        		DispGUI(p1powers, p2powers, player1, grid);// shows a starting grid
          	}else{
          		System.exit(0);
          	}
          }else if(win == 3) {
        	  endGame = JOptionPane.showConfirmDialog(null, "Would you like to play again?","It's a draw!", JOptionPane.YES_NO_OPTION);
          	if(endGame == JOptionPane.YES_OPTION) {
          		player1 = true;
        		for(int i = 0; i < 6; i++) {
            		for(int j = 0; j < 7; j++) {
            			grid[i][j] = 0;
            		}
            	}
        		
        		for(int k = 0; k < 3; k++) {
            		p1powers[k] = 2; p2powers[k] = 2;
            	}
        		
        		DispGUI(p1powers, p2powers, player1, grid);
        		
          	}else{
          		System.exit(0);
          	}
          }
        	
        }// end of while(true)
        
        }
        
    }

    /**
     * Displays the grid, empty spots as *, player 1 as R and player 2 as Y. Shows column and row numbers at the top.
     * @param grid  The current board state
     */
    
    
    
    public static void Display (int [][] grid) {
        //TODO: Display the given board state with empty spots as *, player 1 as R and player 2 as Y. Shows column and row numbers at the top.
    	
    	String grid2[][] = new String[7][8];
    	grid2[0][0] = " ";
    	
    	for(int i = 1; i < 7;i++) {
    		grid2[i][0] = "" + (i-1);
    	}
    	
    	for(int j = 1; j < 8; j++) {
    		grid2[0][j] = "" + (j - 1);
    	}
    	
    	for(int i = 0; i < 6; i++) {
			for(int j = 0; j < 7; j++) {
				
				if(grid[i][j] == 0) {
					grid2[i+1][j+1] = "*";
				}else if(grid[i][j] == 1) {
					grid2[i+1][j+1] = "R";
				}else if(grid[i][j] == 2) {
					grid2[i+1][j+1] = "Y";
				}
				
			}
    	}
			StdOut.println("");
			for(int k = 0; k < 7; k++) {
				for(int g = 0; g < 8; g++) {
					StdOut.print(grid2[k][g] + " ");
				}
				StdOut.println(" ");
			}
			StdOut.println("");
			}
    	
    	
		public static void DispGUI(int p1powers[], int p2powers[], boolean player1, int grid[][]) {
			
			//StdDraw.clear();
			StdDraw.enableDoubleBuffering();
			
			 StdDraw.setPenColor(StdDraw.BLUE.darker());
	            StdDraw.filledRectangle(0.5, 0.43, 0.5, 0.43); 
 
	           
	            StdDraw.setPenColor(StdDraw.WHITE);
	            
	            
	            for(int j = 0;j < 6;j++){
	            for(int i = 0;i < 7;i++){
	                StdDraw.filledCircle(0.143333333333/2 + (i * 0.143333333333), 0.1428571429/2 + (j * 0.1428571429), 0.05);
	            }
	            }
			
			 StdDraw.setPenColor(StdDraw.BLACK);
	            StdDraw.line(0, 0.88, 0.77, 0.88);
	            StdDraw.line(0, 1.15, 0.77, 1.15);
	            StdDraw.line(0, 1.08, 0.77, 1.08);
	            StdDraw.textLeft(0.02, 0.94, "PLAYER1");
	            StdDraw.textLeft(0.02, 0.9, "PLAYER2");
	            StdDraw.textLeft(0.208, 0.98, "BOMB");
	            StdDraw.textLeft(0.37, 0.98, "TELEPORTER");
	            StdDraw.textLeft(0.55, 0.98, "COLOUR CHANGER");
	            StdDraw.line(0.77, 1, 0.77, 0.88);
	            StdDraw.line(0, 0.97, 0.77, 0.97);
	            StdDraw.line(0.15, 1, 0.15, 0.88);
	          
	            if(player1) {
			
			StdDraw.setPenColor(StdDraw.WHITE);
	            	StdDraw.filledCircle(0.8, 0.905, 0.021);

	            	StdDraw.setPenColor(StdDraw.RED);
	            	StdDraw.filledCircle(0.8, 0.94, 0.02);

	            }else {
	
			StdDraw.setPenColor(StdDraw.WHITE);
	            	StdDraw.filledCircle(0.8, 0.94, 0.021);

	            	StdDraw.setPenColor(StdDraw.YELLOW);
	            	StdDraw.filledCircle(0.8, 0.905, 0.02);

	            }
	            
	            StdDraw.setPenColor(StdDraw.BLACK);
	            
	            for(int i = 0; i < 3; i++) {
	            	StdDraw.textLeft(0.225 + i * 0.2, 0.94,"" + p1powers[i]);
	            }
	            
	            for(int i = 0; i < 3; i++) {
	            	StdDraw.textLeft(0.225 + i * 0.2, 0.9,"" + p2powers[i]);
	            }
			
	            for(int j = 0;j < 6;j++){
		            for(int i = 0;i < 7;i++){
		            	
		            	if(grid[j][i] == 0) {
		            		StdDraw.setPenColor(StdDraw.WHITE);
		            		StdDraw.filledCircle(0.143333333333/2 + (i * 0.143333333333), 0.1428571429/2 + ( j * 0.1428571429), 0.05);
		            	}else if(grid[j][i] == 1) {
		            		StdDraw.setPenColor(StdDraw.RED);
		            		StdDraw.filledCircle(0.143333333333/2 + (i * 0.143333333333), 0.1428571429/2 + ((5-j) * 0.1428571429), 0.051);
		            	}else if(grid[j][i] == 2) {
		            		StdDraw.setPenColor(StdDraw.YELLOW);
		            		StdDraw.filledCircle(0.143333333333/2 + (i * 0.143333333333), 0.1428571429/2 + ( (5-j) * 0.1428571429), 0.051);
		            	}
		            	
		                
		            }
		            }
	           StdDraw.show();
		}
	
    

    /**
     * Exits the current game state
     */
    public static void Exit() {
        //TODO: Exit the game
    	System.exit(0);
    }
    
  
    /**
     *Checks whether a player has 4 tokens in a row or if the game is a draw.
     * @param grid The current board state in a 2D array of integers
     */
    public static int Check_Win (int [][] grid) {
        int winner = 0;
        int drawCheck = 1;
        ArrayList<Integer> list = new ArrayList<>();// helps with drawCheck
      
        //TODO: Check for all the possible win conditions as well as for a possible draw.
        
        
        //case 1: horizontal, begin
        for(int i = 0; i < 6; i++) {
        for(int j = 0; j < 4;j++) {
        	
        	if(grid[i][j] * grid[i][j+1] * grid[i][j+2] * grid[i][j+3] == 1) {
        		winner = 1;
        		list.add(1);
        		
        	}else if(grid[i][j] * grid[i][j+1] * grid[i][j+2] * grid[i][j+3] == 16) {
        		winner = 2;
            	list.add(2);
        	}
        	
        }
        }
        //case 1: horizontal, end
        
        //case 2:vertical, begin
        
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j <= 6;j++) {
            	
            	if(grid[i][j] * grid[i+1][j] * grid[i+2][j] * grid[i+3][j] == 1) {
            		winner = 1;
            		list.add(1);
            		
            	}else if(grid[i][j] * grid[i+1][j] * grid[i+2][j] * grid[i+3][j] == 16) {
            		winner = 2;
                	list.add(2);
            	}
            	
            
            }
            }
        //case 2: vertical, end
        
        //case 3: diagonal North-East/South-West, begin
        
        for(int i = 3; i < 6; i++) {
            for(int j = 0; j < 4;j++) {
            	
            	if(grid[i][j] * grid[i-1][j+1] * grid[i-2][j+2] * grid[i-3][j+3] == 1) {
            		winner = 1;
            		list.add(1);
            		
            	}else if(grid[i][j] * grid[i-1][j+1] * grid[i-2][j+2] * grid[i-3][j+3] == 16) {
            		winner = 2;
                	list.add(2);
            	}
            	
            
            }
            }
        //case 3: finished
        
        //case 4 / final case, begin
        
        int i = 2;
        
        while(i >= 0) {
        	
        	for(int j = 0; j < 4;j++) {
            	
            	if(grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] * grid[i+3][j+3] == 1) {
            		winner = 1;
            		list.add(1);
            		
            	}else if(grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] * grid[i+3][j+3] == 16) {
            		winner = 2;
                	list.add(2);
                	
            	}
            
            }
        	i = i - 1;
        }
        
        for(int k = 0; k < 6; k++) {
        	drawCheck *= grid[0][k] * grid[0][k+1];
        }
        
        if(drawCheck > 0 && winner == 0) {
        	winner = 3; // draw
        
        }
        
        if(list.contains(1) && list.contains(2)) {
        winner = 3;
        // list checks if player 1 and player 2 are seen as the winner in the same check_win
       }
        
        return winner;
    }

    /**
     * Plays a bomb disc that blows up the surrounding tokens and drops down tokens above it. Should not affect the
     * board state if there's no space in the column. In that case, print the error message: "Column is full."
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
   
    public static int [][] Save(String name, int [][] grid) {
    	try{
    		FileWriter fileWriter = new FileWriter(name+".txt");
	    	for (int i = 0; i < X; i++) {
	    		for (int j = 0; j < Y; j++) {
		    		fileWriter.write(Integer.toString(grid[i][j]) + " ");
		    	}
		    	fileWriter.write("\n");
		    }
		    fileWriter.close();
	    } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return grid;
    }
    
    
    
    public static int [][] Bomb (int i, int [][] grid, boolean player1, int pos) {
        //TODO: Play a bomb in the given column and make an explosion take place. Discs should drop down afterwards. Should not affect the
        //      board state if there's no space in the column. In that case, print the error message: "Column is full."
        //      Leaves behind a normal disc of the player's colour
    	
    	if(player1) {//player1
    		
    		if(pos > 0 && pos < 6) {
    		
    		if(i < 5 && i > 0) {
    			for(int j = -1; j < 2;j++) {
    				for(int k = -1; k < 2; k++) {
    					grid[i + j][pos + k] = 0;
    				}
    			}
    			grid[i + 1][pos] = 1;
    			
    		}else if(i == 5) {
    			for(int j = -1; j < 1;j++) {
    				for(int k = -1; k < 2; k++) {
    					grid[i + j][pos + k] = 0;
    				}
    			}
    			grid[i][pos] = 1;
    		}else if(i == 0) {
    			for(int j = 0; j < 2;j++) {
    				for(int k = -1; k < 2; k++) {
    					grid[i + j][pos + k] = 0;
    				}
    			}
    			grid[i + 1][pos] = 1;
    		}
    		
    		}else if(pos == 0) {
    			
    			if(i < 5 && i > 0) {
        			for(int j = -1; j < 2;j++) {
        				for(int k = 0; k < 2; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i + 1][pos] = 1;
        			
        			
        			
        		}else if(i == 5) {
        			for(int j = -1; j < 1;j++) {
        				for(int k = 0; k < 2; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i][pos] = 1;
        		}else if(i == 0) {
        			for(int j = 0; j < 2;j++) {
        				for(int k = 0; k < 2; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i + 1][pos] = 1;
        		}
    			
    		}else if(pos == 6) {
    			
    			if(i < 5 && i > 0) {
        			for(int j = -1; j < 2;j++) {
        				for(int k = -1; k < 1; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i + 1][pos] = 1;
        		}else if(i == 5) {
        			for(int j = -1; j < 1;j++) {
        				for(int k = -1; k < 1; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i][pos] = 1;
        		}else if(i == 0) {
        			for(int j = 0; j < 2;j++) {
        				for(int k = -1; k < 1; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i + 1][pos] = 1;
        		}
    			
    		}
    		
    	}else {// player2
    		
    		if(pos > 0 && pos < 6) {
        		
        		if(i < 5 && i > 0) {
        			for(int j = -1; j < 2;j++) {
        				for(int k = -1; k < 2; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i + 1][pos] = 2;
        		}else if(i == 5) {
        			for(int j = -1; j < 1;j++) {
        				for(int k = -1; k < 2; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i][pos] = 2;
        		}else if(i == 0) {
        			for(int j = 0; j < 2;j++) {
        				for(int k = -1; k < 2; k++) {
        					grid[i + j][pos + k] = 0;
        				}
        			}
        			grid[i + 1][pos] = 2;
        		}
        		
        		}else if(pos == 0) {
        			
        			if(i < 5 && i > 0) {
            			for(int j = -1; j < 2;j++) {
            				for(int k = 0; k < 2; k++) {
            					grid[i + j][pos + k] = 0;
            				}
            			}
            			grid[i + 1][pos] = 2;
            		}else if(i == 5) {
            			for(int j = -1; j < 1;j++) {
            				for(int k = 0; k < 2; k++) {
            					grid[i + j][pos + k] = 0;
            				}
            			}
            			grid[i][pos] = 2;
            		}else if(i == 0) {
            			for(int j = 0; j < 2;j++) {
            				for(int k = 0; k < 2; k++) {
            					grid[i + j][pos + k] = 0;
            				}
            			}
            			grid[i + 1][pos] = 2;
            		}
        			
        		}else if(pos == 6) {
        			
        			if(i < 5 && i > 0) {
            			for(int j = -1; j < 2;j++) {
            				for(int k = -1; k < 1; k++) {
            					grid[i + j][pos + k] = 0;
            				}
            			}
            			grid[i + 1][pos] = 2;
            		}else if(i == 5) {
            			for(int j = -1; j < 1;j++) {
            				for(int k = -1; k < 1; k++) {
            					grid[i + j][pos + k] = 0;
            				}
            			}
            			grid[i][pos] = 2;
            		}else if(i == 0) {
            			for(int j = 0; j < 2;j++) {
            				for(int k = -1; k < 1; k++) {
            					grid[i + j][pos + k] = 0;
            				}
            			}
            			grid[i + 1][pos] = 2;
            		}
        			
        		}
        		
    		
    	}
    	
    	if(pos > 0 && pos < 6) {				//case safe, i.e. no out of bounds to worry about
    	if(i < 5 && i > 1) {
			for(int k = i; k -1 > 0; k--) {
				grid[k+1][pos-1] = grid[k - 2][pos - 1];
				grid[k - 2][pos - 1] = 0;
				grid[k+1][pos+1] = grid[k - 2][pos + 1];
				grid[k - 2][pos + 1] = 0;
			}
		}else if(i == 5) {
			for(int k = i; k -1 > 0; k--) {
				grid[k][pos-1] = grid[k - 2][pos - 1];
				grid[k - 2][pos - 1] = 0;
				grid[k][pos+1] = grid[k - 2][pos + 1];
				grid[k - 2][pos + 1] = 0;
			}
		}
    	}else if(pos == 0) {
    		
    		if(i > 1 && i < 5) {
    			for(int k = i; k -1 > 0; k--) {
    				grid[k+1][pos+1] = grid[k - 2][pos + 1];
    				grid[k - 2][pos + 1] = 0;
    			}
    		}else if(i == 5) {
    			for(int k = i; k -1 > 0; k--) {
    				grid[k][pos+1] = grid[k - 2][pos + 1];
    				grid[k - 2][pos + 1] = 0;
    			}
    		}
    		
    	}else if(pos == 6) {
    		
    		if(i > 1 && i < 5) {
    			for(int k = i; k -1 > 0; k--) {
    				grid[k+1][pos-1] = grid[k - 2][pos - 1];
    				grid[k - 2][pos - 1] = 0;
    			}
    		}else if(i == 5) {
    			for(int k = i; k -1 > 0; k--) {
    				grid[k][pos-1] = grid[k - 2][pos - 1];
    				grid[k - 2][pos - 1] = 0;
    			}
    		}
    		
    	}
    	//bomb completed
        return grid;
    }

    /**
     * Plays a teleporter disc that moves the targeted disc 3 columns to the right. If this is outside of the board
     * boundaries, it should wrap back around to the left side. If the column where the targeted disc lands is full,
     * destroy that disc. If the column where the teleporter disc falls is full, play as normal, with the teleporter
     * disc replacing the top disc.
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Teleport (int pos, int [][] grid, boolean player1) {
        //TODO: Play a teleporter disc that moves the targeted disc 3 columns to the right. If this is outside of the board
        //      boundaries, it should wrap back around to the left side. If the column where the targeted disc lands is full,
        //      destroy that disc. If the column where the teleporter disc falls is full, play as normal, with the teleporter
        //      disc replacing the top disc.
        //      No error message is required.
        int count = 1;
        int pos1 = 0;
        int countR = 0;
    	
    	for(int k = 0; k < 6; k++) {
    		if(grid[k][pos] != 0) {
    			count = k;
    			break;
    		}
    		count++; // if count = 6 then the whole column is empty and nothing happens.
    	}
    	
    	// use similar approach for position + 3, just use countR this time
    	 
    	if(count < 6) {
    		//do everything here
    		
    		if(pos < 4) {	//safe columns
    			pos1 = pos+3;
    			if(grid[0][pos1] == 0) {
    				
    				for(int k = 0; k < 6; k++) {
    		    		if(grid[k][pos1] != 0) {
    		    			countR = k;
    		    			break;
    		    		}
    		    		countR++;
    		    	}
    				
    				grid[countR-1][pos1] = grid[count][pos];
    				
    			}else {
    				StdOut.println("Column is full.");
    			}
    			
    			if(player1) {
    				grid[count][pos] = 1;
    			}else {
    				grid[count][pos] = 2;
    			}
    			
    			
    		}else {			//wrap around columns
    			pos1 = pos - 4;
    			if(grid[0][pos1] == 0) {
    				
    				for(int k = 0; k < 6; k++) {
    		    		if(grid[k][pos1] != 0) {
    		    			countR = k;
    		    			break;
    		    		}
    		    		countR++;
    		    	}
    				
    				grid[countR-1][pos1] = grid[count][pos];
    				
    			}else {
    				StdOut.println("Column is full");
    			}
    			
    			if(player1) {
    				grid[count][pos] = 1;
    			}else {
    				grid[count][pos] = 2;
    			}
    			
    		}
    		//end everything here
    	}
        
        return grid;
    }

    /**
     * Plays the colour changer disc that changes the affected disc's colour to the opposite colour
     * @param i         Column that the disc is going to be played in
     * @param grid      The current board state
     * @param player1   The current player
     * @return grid     The modified board state
     */
    public static int [][] Colour_Changer (int pos, int [][] grid, boolean player1) {
        //TODO: Colour Change: If the colour change disc lands on top of another disc, it changes the colour of that
        //      disc to the opposite colour. The power disc does not remain.
        //      If the colour change disc lands on the bottom row, it must leave a disc of the current players colour.
    	int count = 0;
    	
    	for(int k = 0; k < 6; k++) {
    		if(grid[k][pos] != 0) {
    			count = k;
    			break;
    		}
    		count++; //same logic as in teleporter
    	}
    	
    	if(count == 6) {
    		if(player1) {
    			grid[count-1][pos] = 1;
    		}else {
    			grid[count-1][pos] = 2;
    		}
    	}else {
    		
    		if(grid[count][pos] == 1) {
    			grid[count][pos] = 2;
    		}else if(grid[count][pos] == 2) {
    			grid[count][pos] = 1;
    		}
    		
    	}
    	
        return grid;
    }
    
    


    /**
     * Reads in a board from a text file.
     * @param name  The name of the given file
     * @return
     */
    //Reads in a game state from a text file, assumes the file is a txt file
    public static int [][] Test(String name) {
        int [][] grid = new int[6][7];
        try{
            File file = new File(name+".txt");
            Scanner sc = new Scanner(file);

            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    grid[i][j] = sc.nextInt();
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return grid;
    }

    /**
     * Debugging tool, preferably use this since we can then turn off your debugging output if you forgot to remove it.
     * Only prints out if the DEBUG variable is set to true.
     * @param line  The String you would like to print out.
     */
    public static void Debug(String line) {
        if (DEBUG)
            System.out.println(line);
    }
}

