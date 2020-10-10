How to run:
first you need to have an single json file called "default.json", then just run
this project as normal gradle project

The json file format:
it should contain following attributes:
 "stickmanSize": "normal" or "large"
 "stickmanPos": "x"
 "cloudVelocity": double
 a list called "platforms", every platform needs "x","y","width"
 "flag": "x","y"
 a list of mushroom: "x","y"
 a list of enemy:  "x":300,
                   "y":305,
                   "range":150,
                   "image1":"/slimeBa.png",
                   "image2":"/slimeBb.png"

 "level": the filename for next level

 To check move transfer to next level just need to give that next level filename
 into the default.json "level" attribute

 key on the keyboard:
 LEFT to move left
 RIGHT to move right
 UP to jump
 Space to shot bullet after eat mushroom
