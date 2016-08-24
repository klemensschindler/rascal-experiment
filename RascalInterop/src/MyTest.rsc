module MyTest

import IO;

void hello() {
   println("Hello world, this is my first Rascal program");
   writeFile(|cwd:///hello.txt|, "Helooo!");
}
