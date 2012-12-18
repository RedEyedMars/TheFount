TheFount
========

Overview:
  The player is a god(implicitly), and as such can control the NPC's that are generated. 
  However at the begining he/she can only control NPC's that he/she has influence over. 
  You gain influence by dealing with that NPC from a character you can control, 
  or you can control automatically offspring of the characters you can already control.

========

TODO:
EVERYTHING!(lol)
Currently there is in existence(not in the repository, yet) a world generator. 
From this you can get the environment that the NPC's will move around in. 
NPC's should obviously be able to in and out of the environment you are currently looking at. 
The environment that is currently set up is not adequent to the task, 
so really what we have is the framework for figuring out what the environment is, but not the actual environment.

Everything to do with NPC's has to be (re) done this includes:
  -How they interact with the world
  -How they plan and go about their life
  -How they exist outside of the environment that is in focus(it should be like, if they are not in your current
   environment they go about their plan, but it should be a time thing, as in, if the plan to cut down wood normally
   takes people in the environment that they travel to an average of 2 minutes(this should be based on the amount of     
   resources within the environment, then that's what should happen when the NPC is there.

Everything to do with Environments has be (re) done this includes:
  -Their resources(how they store them/etc.)
  -How NPC interact in them(whether focused or not)(such as how the NPC are able to move, and collect resources)
  
Everything to do with Flora and Fauna(Animals and Plants :p) (never attempted):
  -These should be randomly generated if possible. It is my hope that we can simulate an entire ecosystem, and have the
   NPC's try to survive in it. For instance they should need to build walls to keep the predators out, but should be 
   able to coral the herbivors.
  -They should be randomly generated at the begining, but have their skills evolve over time to meet the need of their 
   surroundings, all that needs to be done is for variation to be introduced with every offspring, and also a checksum
   of the skills can be preformed and if they vary too much between two individuals then they can't create offspring.
   Their plans will be two things: 
     -find approprate food -> eat food -> gain energy. 
     -When energy >= a mutable amount -> find mate -> mate(lose energy)
