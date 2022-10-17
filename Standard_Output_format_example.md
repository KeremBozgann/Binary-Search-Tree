#Standard Output format for commands

---   

##Insert 
###Command: `insert <name> <x> <y> <w> <h>`

**If inserted:** 

    `Rectangle accepted: (<name>, <x>, <y>, <w>, <h>)`

**If rejected**

    `Rectangle rejected: (<name>, <x>, <y>, <w>, <h>)`
---    
##Remove by Name
###Command: `remove <name>`

**If removed:**

Do not report.

**If not removed:**

    `Rectangle rejected <name>`
--- 

##Remove by Coordinates
###Command: `remove <x> <y> <w> <h>`

**If removed:**

Nothing to output

**If not removed:**

--- 

##Regionsearch
###Command: `regionsearch <x> <y> <w> <h>`

**If Found:**

    Rectangles intersecting region (<x>, <y>, <w>, <h>): 
    (<name1>, <x1>, <y1>, <w1>, <h1>)
    (<name2>, <x2>, <y2>, <w2>, <h2>)
    .
    .
    (<nameN>, <x2>, <y2>, <w2>, <h2>)

**If No Rectangles found**

Nothing to output

--- 

##Intersections
###Command: `intersections`

**If any exist:**
intersections:
    
    Intersection pairs:
    (<name1>, <x1>, <y1>, <w1>, <h1>) : (<name2>, <x2>, <y2>, <w2>, <h2>) 
    .
    .
    (<nameN>, <xN>, <yN>, <w1>, <hN>) : (<nameN>, <xN>, <yN>, <wN>, <hN>) 

**If none exist:**

    `Intersection pairs:` (note: always output this string even if there are no intersections)

--- 

##Search
###Command: `search <name>`

**If Found:**

    Rectangle found: (<name>, <x>, <y>, <w>, <h>) 

If multiple rects with the same name are found, output:

    Rectangle found: (<name1>, <x1>, <y1>, <w1>, <h1>)
    Rectangle found: (<name2>, <x2>, <y2>, <w2>, <h2>)

**If Not found**

    Rectangle not found: <name>

Nothing to output


--- 

##Dump
###Command: `dump`

    BST dump: <-- Always output even if tree is empty
    Node has depth <d>, Value (<name>, <x>, <y>, <w>, <h>) .
    .
    .
    BST size is: <s>