# Hangman

Overview
----
Hangman game with console interaction.
First try in functional paradigm.
Dictionary consists of 51301 russinal nouns.


Rules:
----


Player sees the encoded word and need to pick a letter to try.
Player can make total of 4 mistakes. 5th mistake will to lead to a loss.
It is also possible for player to try to guess a whole word if they have a suggestion.
As game goes on and player keeps doing mistakes, the 'gallows' picture is 
showing up in more complete way.
Final picture when player has lost:
![image](https://github.com/Rnd-mi/hangman-game/assets/124258830/d288edc6-eb18-40fe-bee4-f9a0c6197966)


Logic after game is over
----
After the game set of used character is cleared, word
is deleted from the dictionary list, and
game status is returning to ONGOING.
