# NIM-Game-Advanced
NimGame to be played between two player either Human or an AI Player who can make a move to win the game
Nim: A Game of Strategy
Nim is a two-player game, and the rules to be as follows: 
• The game begins with a number of objects (e.g., stones placed on a table).
• Each player takes turns removing stones from the table.
• On each turn, a player must remove at least one stone. In addition, there is an upper bound on the
number of stones that can be removed in a single turn. For example, if this upper bound is 3, a
player has the choice of removing 1, 2 or 3 stones on each turn.
• The game ends when there are no more stones remaining. The player who removes the last stone,
loses. The remaining player wins.
• Both the initial number of stones, and the upper bound on the number that can be removed, can be
varied from game to game, and must be chosen before a game commences.

A player, as described by the NimPlayer will have below details to be saved: 
• A username
• A given name
• A family name
• Number of games played
• Number of games won
The system should allow players to be added. It should also allow for players to be deleted, or for their
details to be edited. Players should not be able to directly edit their game statistics, although they
should be able to reset them.
The system is a text based interactive program that reads and executes commands from standard input
(the keyboard) until an ‘exit’ command is issued, which will terminate the program. If a command
produces output, it should be printed to standard output (the terminal).
Syntax: addplayer username,family_name,given_name
removeplayer [username]
Syntax: editplayer username,new_family_name,new_given_name
Syntax: resetstats [username]
Syntax: displayplayer [username]
Syntax: rankings [asc|desc]
Syntax: startgame initialstones,upperbound,username1,username2
Syntax: exit
All player information  stored, i.e., usernames, given / family names, and number of games played / won. If the program exit with proper exit command it store all
player information and start from there next time program initiated.
AI player: The only difference between a human player and an AI player is in the way that they make a move. Instead of prompting for a move to be entered via standard 
input, the AI player should choose their own move based on state of the game. As it has intelligence it will try to win everytime. 
