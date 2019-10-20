# TicTacToe - Java console bases application.

This game mainly consists of 4 players.
1. Human - us.
2. Random Player - uses random generator function and picks up vacant spot on the board.
3. Blocking Player - looks at the board for a move that would block it's opponent from winning on next move.
4. Smart Player - first looks at board, if it can find move to win immediately, it makes that move. Otherwise, it looks for a way to
   block opponent's from winning on the next move. Otherwise, it picks a vacant sqaure at random.
   
   
<<<<<<< Game output >>>>>>>>


Please enter the name of the 'X' player: vaibhav

What type of player is vaibhav?
  1: Human
  2: Random Player
  3: Blocking Player
  4: Smart Player
Please enter a number in the range 1-4: 1

Please enter the name of the 'O' player: Blocking player

What type of player is Blocking player?
  1: Human
  2: Random Player
  3: Blocking Player
  4: Smart Player
Please enter a number in the range 1-4: 3
          |col 0|col 1|col 2
          +-----+-----+-----+
          |     |     |     |
    row 0 |     |     |     |
          |     |     |     |
          +-----+-----+-----+
          |     |     |     |
    row 1 |     |     |     |
          |     |     |     |
          +-----+-----+-----+
          |     |     |     |
    row 2 |     |     |     |
          |     |     |     |
          +-----+-----+-----+
vaibhav, What row should your next X be placed in?
1
vaibhav, What column should your next X be placed in?
1
          |col 0|col 1|col 2
          +-----+-----+-----+
          |     |     |     |
    row 0 |     |     |     |
          |     |     |     |
          +-----+-----+-----+
          |     |     |     |
    row 1 |     |  X  |     |
          |     |     |     |
          +-----+-----+-----+
          |     |     |     |
    row 2 |     |     |     |
          |     |     |     |
          +-----+-----+-----+
          
