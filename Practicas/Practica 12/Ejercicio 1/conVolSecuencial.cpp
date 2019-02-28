#include <iostream>
#include <cstdlib>
#include <ctime>

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

void convolution(int con[][3])
{
    time_t start, end;
    double totalTime;
    time(&start);
    for(int i = 1; i < 9998; ++i)
        for(int j = 1; j < 9998; ++j)
        {
            int neighborhood[][3] = {{matrix[i-1][j-1], matrix[i-1][j], matrix[i-1][j+1]},
            {matrix[i][j-1], matrix[i][j], matrix[i][j+1]}, {matrix[i+1][j-1], matrix[i+1][j], matrix[i+1][j+1]}};
            matrix[i][j] = prod(neighborhood, con);
        }
    time(&end);
    totalTime = difftime(end, start);
    cout << "Needed time was: " <<  totalTime << "s" << endl;
}

void menu()
{
    int option = -1;
    while(option < 0 || option > 5)
        {
            cout << "Menu:\n1.- Focus\n2.- Highlight edges\n3.- Detect edges\n4.- Sobel filter\n";
            cout << "5.- Sharpen filter\n0.- Exit\nSelect option: ";
            cin >> option;
            cout << endl;
        } 
            
        switch(option)
        {
            case 0: cout << "Bye!" << endl; 
                break;
            case 1: 
                convolution(focus);
                break;
            case 2: 
                convolution(highlight);
                break;
            case 3: 
                convolution(detect);
                break;
            case 4: 
                convolution(Sobel);
                break;
            case 5: 
                convolution(sharpen);
                break;
            }
     }

int main(int argc, char const *argv[])
{
    fill();
    menu();
    return 0;
}
