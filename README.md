# Java-Compiler
omer811
yechielmer



=============================
=      File description     =
=============================
**add later might change file name*

README - this file



=============================
=          Design           =
=============================
when designing the program, we started first scathing on paper the different modules of the program
and how will they interact with each other.
we noted that the must be a "first pass" on the code line in order to generate
the global variable map and the method map.
and the we can go over the lines in the method and verify them.

So the question was, what is the best way to store the code lines so we can more easily go over them later,
we required some sort of hierarchy between the code blocks, because and if while block is nested inside a method,
and we need this hierarchy in order to mange our variables maps in the correct way.
the data structure we chose was a tree, it's was quite a natural choice because nested method and if while blocks can
easily be presented as a tree.
in order to create the tree we reallied on the parentheses required by the s java specs, and also while creating the
tree we verified the correct parentheses structure.
the tree creation was done using a recursive algorithm that identify each sub block of a given block.
there was no need to keep the lines themself inside the blocks, this would've only waste memory,
so the indexes were saved instead

now that we have the structure of the program we can easily go over each block, and we know the hierarchy so we can
provide the correct variable map.

// the lines analyzer//
we created an "line analyzer" class. this class is sort of "main" class for the lines, which identify the basic
line problem, and send to the "identifiers". there is interface of "identifier" which represent the sorts of identify
in the lines. there are two class which implements it  - variable identifier and function-call-identifier.
the variable identifier is abstract class, because we never create instance of that class.
its contains only the general details of identify variable, which the all the identify of the vars has in common.
there are two classes which extends its class - variable-lines-identifier, which responsible to all the identify of
variables in line, and arguments-identifier, which responsible to identify of the arguments in function.
the function identifier check the call of an function.

// global factory
first we generated the global map, this was done separately because the global map has some different specification,
and then the map was integrated into a scope object, which handles all the mapping of variables while
verifying the program.
we used factory class for created the map of globals with only one static method.
in this way, we can change only the factory class, for adding new features to the global factory.

// the method analyzer
we used class which call "scope", which contains the map of variables in every entering to new block.
after we got the map of globals, we can go over the methods.
we created class of "method analyzer" - which is sort of "main class", which operate all the analyze of the methods.
first of all, its create all the methods, by calling the "method factory" - which names "method - extractor".
its go all over the lines in the tree and if its method, it send the relevant line to the "method identifier", which
has check all the details of the method.
after we have the method,then we can go over the lines, checking if we are entering a new code block,
 and the entering this block recursively.

// variables and variables type
we create the class which represent the variable - in this way its easy to add features to the the variable,
like final or assigned etc.
we use enum for variable type - way which help adding new types to the variables
- its use te first choice principle.

variable:
we used an object to store variables in the code, we needed to have information about the variables so we could verify
assignments and other expressions later on. to store the type of the variable we use an enum, the enum also
provided other type related services to the program, like verifying if a value is suitable to a given type,
or for checking illegal casting.
this meant centralizing the handling of types in one place, which means adding or changing types is super easy.

=============================
=  Implementation details   =
=============================
parsing the commands from the file in order to create the tree:
the file parser uses a special list of parentheses, the special functionality of this list is that it is validating
the parentheses structure while adding parentheses to is.
and after the addition is done, you can generate a tree from the inserted parentheses

creating the tree:
when creating the tree we opted to create a custom data structure for our need, the CodeBlockNode,
which has multiple children, and the indexes of the block start and end lines.
the tree creation is done inside the parentheses list object, because this is the sole purpose of this list,
and its done using a recursive algorithm, that identifies multiple children inside the list using a stack,
and then creates a tree from those child recursively.

going over the tree:
going over the tree is also done recursively, each node has a method that provides a list of indexes that are in the
"level" of the node, meaning they are inside the scope of the node, and also a method that allows you to get a child
by line index. so the program is going over the lines of a block until it's reached a child, and then it goes into the
child and start again recursively.
there is som unique behaviors for the first blocks, anf for the "inside blocks", the first block must be
method declaration, so they are verified at the method map generation inside the method extractor.
and all other blocks also get their first and last line verifies separately on the ifwhile verifier.
that leaves only code lines to be verified be a single method.
in this way. if we will want to add another type of "mater" black (etc method) we can easily change the method
identifier
and same goes for a "inner" block

line analyzer:
the main goal of the line analyzer is to be a manger of all local or global line analysing tasks
in that way we can customize the features according to the required syntax support
for example if wanted to add support for new syntax for calling class methods,we can change it in the line analyzer.
for each program line there are some global syntax requirements, like semi colon in end of line.
they are checked before sending the line to the more specific identifiers.

AnaylezerUtils:
there are some function there are needed all across the analyzing and identifing task,
we opted to create a util class which encorprate those tasks and can later be used be all classe
those tasks are such as cleaning line white spaces and blank lines.

ifwhile analyzer and method analyzer:
we implemented those tasks in defferent classes becasue they don't share much i comment, while the method anaylzer
needs to produce an argument list and validate thos arguments,
the ifwhile analyzer needs to validate a boolean expression using the boolean verifier.
the boolean verifier is using the varType enum to know which boolean expressions are supported,
thise implemt exetendabilty.


the method analyzer uses the argument identifier, which is samilier to variable identifier task, so we used a abstract
variable identifier to share most of the code with the variable line identifier

the main class (runner):
first we create the code tree, later we extract all global and method to creat maps of them.
then we go over each method and analyze it, in this action we trigger a recursive algorithem that analyze all the
method coneten and all of it's children cotent ass well.
we are using a catch block and if during the running of this code we encouner one of the 4 types of exception,
or IO exception the we act accordingly.

method and type classes:
we are using class for type and method which concludes all its important parameters, for later use,
like verifing method calls or verifing variable assignments.


exception hierarchy:
when implementing the exception for teh program, we decided on 5 main type of exception: method exception,
variable exceptions, tree exception, line exception, ifwhile exceptions.
we created 5 main exception classes which are super classes for the more specific exception. so each class an implement
it's unique exceptions.



=============================
=    Intuitiveness and understandability    =
=============================
the program is comprised of 4 main packages beside main, 3 analyzer modules, for the 3 different types of line the
program will encounter, anf the block tree extractor which is responsible for building to code tree, the
separation of modules makes to code clearer.



=============================
=    Questions   =
=============================
*****report in your README file how you handled s-Java code errors in this exercise, and why you chose to do so.*****

we decided to split our errors on the different modules of the program, so each module will only throw it's
related errors, we tries to catch the errors on the most specific way we can, for example, when giving illegal type in
the code, the part that will catch it is the VariableType enum, which is the most specific class of the program
that handles types, this makes changes a lot easier, because you don't need to change any complex checks along the way,
according to the single choice principle.



****How would you modify your code to add new types of variables (e.g., float)?****

adding new types of variables to the code is easy, all we need to do is change the VariableType enum
and add a new type, specify it's compatible types, and it's accepted values, and we are done.

ג€¢ Below are three features your program currently does not support.
Please select two of them, and describe which modifications/
extensions you would have to make in your code in order to support them.
Please briefly describe which classes you would add to your code,
which methods you would add to existing classes, and which classes you would modify.
You are not required to implement these features.
****Classes****
implementing classes would require creating a new class object, which is smilier to method, only that it hold a
modified scope which support class data members, then following the tree structure of the program, each class would be
a child of the global scope, and when entering a class there is not much difference to the recursive verification then
if inside a global scope, first we will go over and create a method map and global(data member) map' like in the
current implementation, and then dive recursively into each method, changes will also be made to the line analyzer to
support creation of object of given classes, according to the class map.

****Different methodsג€™ types (i.e int foo())****

if we would like to support other return types other the void, we will need to add an expected return type field
to the method object, and also to the line analyzer, the line analyzer would have "setExpectedReturn" method
and when moving from method to method ion the method analyzer we will change this field according to the current method.
then validating each return statement inside the line analyzer for the correct return type.



*****In your README file, please describe two of the main regular expressions you used in your code*****
1. extrating arguments from method : ^[\s]*void[\s]+([^\d_][\w]*)[\s]*\(([\w\s]*[,[\w\s]*]*)\)[\s]*\{[\s]*$
by using the regular expression we were able to easily get the argument for the method all that while checking that the
method declartion is legal, this enables us to extand our program in a timely manner.
if we wanted to change the method call signeture all we will need to do is change the regex.

2. exctractin boolean arguments from the boolean expression : [\s]*(\|\|)[\s]*|[\s]*(\&\&)[\s]*
by using the regex we can easly support more boolean operators just by adding the to the regex.
also we able to exctract the boolan fields to verify.

in genral we used many regular expression in the writing od the program, and the usage of packeges like \s allows
for support over more cases.



