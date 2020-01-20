#include <iostream>
#include <cstdlib>
#include <thread>
#include <chrono>
#include <ratio>

using namespace std;

static int matrix[10000][10000];
int focus[][3] = {{0, -1, 0}, {-1, 5, -1}, {0, -1, 0}};
int highlight[][3] = {{0,0,0}, {-1,1,0}, {0,0,0}};
int detect[][3] = {{0,1,0}, {1,-4,1}, {0,1,0}};
int Sobel[][3] = {{-1, 0, 1}, {-2,0,2}, {-1,0,1}};
int sharpen[][3] = {{1,-2,1}, {-2,5,-2}, {1,-2,1}};

void fill()
{
    for(int i = 0; i < 9999; ++i)
        for(int j = 0; j < 9999; ++j)
            matrix[i][j] = (rand() % 40) - 20;
}

int prod(int n[][3], int con[][3])
{
    int res[3] = {0,0,0}; 
    for(int i = 0; i < 3; ++i)
        for(int j = 0; j < 3; ++j)
            res[i] += n[i][j]*con[j][i];

    int finalRes = 0;        
    for(int i = 0; i < 3; ++i)
        finalRes += res[i];
        
    return finalRes;
}

void convolution(int con[][3], unsigned start, unsigned end)
{
    for(int i = start; i < end; ++i)
        for(int j = 1; j < 9998; ++j)
        {
            int neighborhood[][3] = {{matrix[i-1][j-1], matrix[i-1][j], matrix[i-1][j+1]},
            {matrix[i][j-1], matrix[i][j], matrix[i][j+1]}, {matrix[i+1][j-1], matrix[i+1][j], matrix[i+1][j+1]}};
            matrix[i][j] = prod(neighborhood, con);
        }
}

void call(int kernel[][3], unsigned start, unsigned end, unsigned n_threads,
unsigned frame)
{
    thread v[n_threads];
    for(unsigned i = 0; i < n_threads; ++i)
    {
        v[i] = thread(convolution, kernel, start, end);
        start = end + 1;
        end += frame;
    }
    for(unsigned i{(n_threads-1)}; i != -1; --i)
        v[i].join();
}

void menu(unsigned n_threads)
{
    int option = -1;
    while(option < 0 || option > 5)
        {
            cout << "Menu:\n1.- Focus\n2.- Highlight edges\n3.- Detect edges\n4.- Sobel filter\n";
            cout << "5.- Sharpen filter\n0.- Exit\nSelect option: ";
            cin >> option;
            cout << endl;
        } 

        unsigned frame{9998/n_threads},
        start_point{1}, endPoint{frame};

        using namespace chrono;

        chrono::high_resolution_clock::time_point start, end;
        start = chrono::high_resolution_clock::now();

        switch(option)
        {
            case 0: cout << "Bye!" << endl; 
                break;
            case 1: 
                call(focus, start_point, endPoint, n_threads, frame);
                break;
            case 2:
                call(highlight, start_point, endPoint, n_threads, frame); 
                break;
            case 3: 
                call(detect, start_point, endPoint, n_threads, frame); 
                break;
            case 4: 
                call(Sobel, start_point, endPoint, n_threads, frame); 
                break;
            case 5: 
                call(sharpen, start_point, endPoint, n_threads, frame); 
                break;
        }

        end = chrono::high_resolution_clock::now();
        chrono::duration<double> totalTime = duration_cast<duration<double>>(end - start);

        using namespace std;
        cout << "Needed time was: " <<  totalTime.count() << "s" << endl;
     }

int main(int argc, char const *argv[])
{
    if(argc > 1)
    {
        int n_threads{atoi(argv[1])};
        if(n_threads != 0)
        {
            fill();
            menu(n_threads);
        }
        else
            cout << "Not acceptable input" << endl;
    }
    else
        cout << "Not enough input arguments" << endl;
    
    return 0;
}
