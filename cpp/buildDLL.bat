ECHO "building dll..."
g++ -c base/*.cpp conditions/*.cpp dll/*.cpp java/*.cpp
g++ -o Moona.dll *.o
DEL *.o